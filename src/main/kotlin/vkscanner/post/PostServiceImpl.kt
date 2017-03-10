package vkscanner.post

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.wall.responses.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import vkscanner.filter.FilterService
import vkscanner.querycommunitycount.QueryCommunityCount
import vkscanner.querycommunitycount.QueryCommunityCountService
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
@Service
private class PostServiceImpl @Inject constructor(val repository: PostRepository,
                                                  val filterService: FilterService,
                                                  val queryCommunityCountService: QueryCommunityCountService) : PostService {

    override fun findAll(interestingOnly: Boolean, page: Int, limit: Int): Page<Post> {
        if (!interestingOnly) {
            return repository.findByOrderByPostedDesc(PageRequest(page, limit))
        }
        return repository.findByInterestingOrderByPostedDesc(interestingOnly, PageRequest(page, limit))
    }

    override fun setInteresting(postId: Int, interesting: Boolean) {
        val post = repository.findByPostId(postId)
        post.interesting = interesting
        repository.save(post)
    }

    data class QueriedSearchResponse(val searchResponse: SearchResponse,
                                     val query: String)

    // @Scheduled(cron = "0 0 0/1 1/1 * ?")
    // private fun getAllResponsesByAllFilters() {
    override fun buttonFeeder() {

        val filters = filterService.findAll()
        val responses = ArrayList<QueriedSearchResponse>()

        filters.forEach { f ->
            f.communities.forEach { communityId ->
                // Collecting new posts since last check
                val totalPosts = getTotalPosts(communityId)
                val minQueryScannedCount = queryCommunityCountService.findByCommunityId(communityId)
                        .groupBy { it.count }
                        .keys
                        .min()

                val sinceLastCheck: Int =
                        if (minQueryScannedCount != null) totalPosts - minQueryScannedCount else totalPosts

                f.queries.forEach { query ->
                    responses.addAll(shitOnPagination(query, communityId, f.ownersOnly))
                    queryCommunityCountService.save(QueryCommunityCount(query, communityId, totalPosts))
                }
            }
        }
        // Transforming into this system format
        val okValues = ArrayList<Post>()
        responses.forEach { (searchResponse, query) ->
            searchResponse.items.forEach {
                val postId = it.id
                val post = okValues.find { it.postId == postId }
                if (post == null) {
                    okValues.add(Post(it, query))
                } else {
                    okValues.remove(post)
                    post.triggeredOn.add(query)
                    okValues.add(post)
                }
            }
        }
        // Filtering by text containing
        val finalEntries = LinkedHashSet<Post>()
        filters.flatMap { it.queries }.forEach {
            query ->
            okValues.filter { it.text.toLowerCase().contains(query.toLowerCase()) }.forEach {
                finalEntries.add(it)
            }
        }
        // Saving to database
        finalEntries.forEach {
            repository.save(it)
        }
    }

    private fun shitOnPagination(query: String,
                                 communityId: Int,
                                 ownersOnly: Boolean): List<QueriedSearchResponse> {
        // Defining constants
        val MAX_VK_RESPONSE_COUNT: Int = 100
        val QUERIES_PER_SECOND: Short = 3
        // Defining init values
        val responses = ArrayList<QueriedSearchResponse>()
        var currentOffset = 0
        val paginationSize = MAX_VK_RESPONSE_COUNT
        var totalCount = paginationSize // For first while prok
        // Doing all here
        while (currentOffset < totalCount) {
            val singleResponse = getSingleResponse(paginationSize,
                    currentOffset, query, communityId, ownersOnly)
            TimeUnit.MILLISECONDS.sleep(1000L / QUERIES_PER_SECOND)
            if (singleResponse.count > 0) {
                responses.add(QueriedSearchResponse(singleResponse, query))
            }
            totalCount = singleResponse.count
            currentOffset += paginationSize
        }
        return responses
    }

    private fun getSingleResponse(count: Int,
                                  offset: Int,
                                  query: String,
                                  communityId: Int,
                                  ownersOnly: Boolean): SearchResponse {
        val transportClient = HttpTransportClient()
        val vk = VkApiClient(transportClient)
        val response = vk.wall().search()
                .count(count)
                .offset(offset)
                .ownerId(communityId)
                .ownersOnly(ownersOnly)
                .query(query)
                .execute()
        return response
    }

    private fun getTotalPosts(communityId: Int): Int {
        val transportClient = HttpTransportClient()
        val vk = VkApiClient(transportClient)
        val response = vk.wall().get().count(1).ownerId(communityId).execute()
        return response.count
    }
}