package vkscanner

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.wall.responses.SearchResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vkscanner.filter.Filter
import vkscanner.filter.FilterRepository
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject


/**
 * Created on 17.01.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */

@RestController
class VkApiWhoreController @Inject constructor(val repository: FilterRepository) {

    data class EntryBitches(
            var count: Int = 10,
            var offset: Int = 0,
            var query: String = "",
            var ownersOnly: Boolean = true
    )

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

}