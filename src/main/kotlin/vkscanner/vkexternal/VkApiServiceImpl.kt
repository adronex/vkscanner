package vkscanner.vkexternal

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.httpclient.HttpTransportClient
import com.vk.api.sdk.objects.groups.responses.SearchResponse
import org.springframework.stereotype.Service

/**
 * Default class description.
 * Created on 19.02.2017.
 * @author Pavel
 */
@Service
private class VkApiServiceImpl : VkApiService {

    //todo: make it nice
    override fun findCommunitiesByQuery(query: String): SearchResponse {
        val transportClient = HttpTransportClient()
        val vk = VkApiClient(transportClient)
        val response = vk.groups().search(null, query).count(5).execute()
        return response
    }
}