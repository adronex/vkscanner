package vkscanner.vkexternal

import com.vk.api.sdk.objects.groups.responses.SearchResponse

/**
 * Default class description.
 * Created on 19.02.2017.
 * @author Pavel
 */
interface VkApiService {
    fun findCommunitiesByQuery(query: String): SearchResponse
}