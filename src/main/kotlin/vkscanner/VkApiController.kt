package vkscanner

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vkscanner.vkexternal.VkApiService
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Default class description.
 * Created on 19.02.2017.
 * @author Pavel
 */
@RestController
@RequestMapping("/vk")
private class VkApiController @Inject constructor(val service: VkApiService) {

    @GetMapping(path = arrayOf("/groups/{query}"))
    fun getCommunities(@PathVariable query: String) = service.findCommunitiesByQuery(query)
}