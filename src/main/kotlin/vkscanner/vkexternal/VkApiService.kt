package vkscanner.vkexternal

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
interface VkApiService {
    fun getAllResponsesByAllFilters(): Set<OutputDto>
}