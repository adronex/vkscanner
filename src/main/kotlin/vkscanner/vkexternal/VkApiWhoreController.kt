package vkscanner.vkexternal

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject


/**
 * Created on 17.01.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */

@RestController
class VkApiWhoreController @Inject constructor(val service: VkApiService) {

    data class EntryBitches(
            var count: Int = 10,
            var offset: Int = 0,
            var query: String = "",
            var ownersOnly: Boolean = true
    )

//    @GetMapping(path = arrayOf("/vk"))
//    fun nameRequest(params: EntryBitches): SearchResponse {
//    }

//    @GetMapping(path = arrayOf("/db"))
//    fun db(query: String): List<Filter> {
//        return repository.findByQueries(query)
//    }

    @GetMapping(path = arrayOf("/filter"))
    fun filtered(): Set<OutputDto> {
        return service.getAllResponsesByAllFilters()
    }

}