package vkscanner

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.wall.responses.SearchResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Created on 17.01.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */

@RestController
class VkApiWhoreController {

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
                .ownerId(-24983798)
                .ownersOnly(params.ownersOnly)
                .query(params.query)
                .execute()
        return response
    }

}