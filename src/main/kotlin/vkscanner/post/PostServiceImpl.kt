package vkscanner.post

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.wall.responses.SearchResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import vkscanner.filter.FilterService
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
                                                  val filterService: FilterService) : PostService {

    override fun findAll(page: Int, limit: Int): Page<Post> =
            repository.findAllByOrderByDateDesc(PageRequest(page, limit))

    data class QueriedSearchResponse(val searchResponse: SearchResponse,
                                     val query: String)

    @Scheduled(cron = "0 0 0/1 1/1 * ?")
    private fun getAllResponsesByAllFilters() {

        val MAX_VK_RESPONSE_COUNT: Int = 100
        val QUERIES_PER_SECOND: Short = 3
        val filters = filterService.findAll()
        val responses = ArrayList<QueriedSearchResponse>()

        filters.forEach {
            val ownersOnly = it.ownersOnly
            it.queries.forEach {
                var currentOffset = 0
                val paginationSize = MAX_VK_RESPONSE_COUNT
                var totalCount = paginationSize // For first while prok
                while (currentOffset < totalCount) {
                    val singleResponse = getSingleResponse(paginationSize,
                            currentOffset, it, ownersOnly)
                    TimeUnit.MILLISECONDS.sleep(1000L / QUERIES_PER_SECOND)
                    responses.add(QueriedSearchResponse(singleResponse, it))
                    totalCount = singleResponse.count
                    currentOffset += paginationSize
                }
            }
        }
        // Transforming into this system format
        val okValues = responses.flatMap {
            val triggeredOn = it.query
            it.searchResponse.items.map { Post(it, triggeredOn) }
        }
        // Filtering by text containing
        val finalEntry = LinkedHashSet<Post>()
        filters.flatMap { it.queries }.forEach {
            query ->
            okValues.filter { it.text.toLowerCase().contains(query.toLowerCase()) }.forEach {
                finalEntry.add(it)
            }
        }
        // Saving to database
        finalEntry.forEach {
            repository.save(it)
        }
    }

    private fun getSingleResponse(count: Int,
                                  offset: Int,
                                  query: String,
                                  ownersOnly: Boolean): SearchResponse {
        val transportClient = HttpTransportClient()
        val vk = VkApiClient(transportClient)
        val response = vk.wall().search()
                .count(count)
                .offset(offset)
                .ownerId(-24983798)
                .ownersOnly(ownersOnly)
                .query(query)
                .execute()
        return response
    }
}