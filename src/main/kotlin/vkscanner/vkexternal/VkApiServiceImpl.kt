package vkscanner.vkexternal

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.wall.responses.SearchResponse
import org.springframework.stereotype.Service
import vkscanner.VkApiController
import vkscanner.filter.FilterService
import java.util.*
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
@Service
internal class VkApiServiceImpl @Inject constructor(val filterService: FilterService) : VkApiService {

    val MAX_VK_RESPONSE_COUNT: Int = 100

    override fun getAllResponsesByAllFilters(): Set<OutputDto> {
//        val filters = filterService.findAll()
//        val responses = java.util.ArrayList<com.vk.api.sdk.objects.wall.responses.SearchResponse>()
//        filters.flatMap { it.queries }.forEach {
//            var currentOffset = 0
//            val paginationSize = MAX_VK_RESPONSE_COUNT
//            var totalCount = paginationSize // For first while prok
//            while (currentOffset < totalCount) {
//                val singleResponse = getSingleResponse(VkApiController.EntryBitches(paginationSize, currentOffset, it, true))
//                responses.add(singleResponse)
//                totalCount = singleResponse.count
//                currentOffset += paginationSize
//            }
//        }
//        val okValues = responses.flatMap { it.items }.map(::OutputDto)
//        val finalEntry = LinkedHashSet<OutputDto>()
//        filters.flatMap { it.queries }.forEach {
//            query ->
//            okValues.filter { it.text.toLowerCase().contains(query.toLowerCase()) }.forEach {
//                finalEntry.add(it)
//            }
//        }
        return HashSet()// finalEntry
    }

    private fun getSingleResponse(params: VkApiController.EntryBitches): SearchResponse {
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

}