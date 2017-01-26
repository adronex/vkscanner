package vkscanner

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.wall.WallpostFull
import com.vk.api.sdk.objects.wall.responses.SearchResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vkscanner.filter.Filter
import vkscanner.filter.FilterRepository
import java.util.*
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject


/**
 * Created on 17.01.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */

@RestController
class VkApiWhoreController @Inject constructor(val repository: FilterRepository) {

    val MAX_VK_RESPONSE_COUNT: Int = 10

    data class EntryBitches(
            var count: Int = 10,
            var offset: Int = 0,
            var query: String = "",
            var ownersOnly: Boolean = true
    )

    class OutputBitches(entry: WallpostFull) {
        val postId = entry.id
        val text = entry.text
        val date = entry.date
        val likes = entry.likes.count
        val reposts = entry.reposts.count
        val comments = entry.comments.count
    }

    @GetMapping(path = arrayOf("/vk"))
    fun nameRequest(params: EntryBitches): SearchResponse {
        val transportClient = HttpTransportClient()
        val vk = VkApiClient(transportClient)
        val response = vk.wall().search()
                .count(params.count)
                .offset(params.offset)
                .ownerId(-24983798)
                .ownersOnly(params.ownersOnly)
                .query(params.query)
                .execute()
        return response
    }

    @GetMapping(path = arrayOf("/db"))
    fun db(query: String): List<Filter> {
        return repository.findByQueries(query)
    }

    @GetMapping(path = arrayOf("/filter"))
    fun filtered(): Set<OutputBitches> {
        val filters = repository.findAll()
        val responses = ArrayList<SearchResponse>()
        filters.flatMap { it.queries }.forEach {
            var currentOffset = 0
            val paginationSize = MAX_VK_RESPONSE_COUNT
            var totalCount = paginationSize // For first while prok
            while (currentOffset < totalCount) {
                val singleResponse = nameRequest(EntryBitches(paginationSize, currentOffset, it, true))
                responses.add(singleResponse)
                totalCount = singleResponse.count
                currentOffset += paginationSize
            }
        }
        val okValues = responses.flatMap { it.items }.map(::OutputBitches)
        val finalEntry = LinkedHashSet<OutputBitches>()
        filters.flatMap { it.queries }.forEach {
            query ->
            okValues.filter { it.text.toLowerCase().contains(query.toLowerCase()) }.forEach {
                finalEntry.add(it)
            }
        }
        return finalEntry
    }

}