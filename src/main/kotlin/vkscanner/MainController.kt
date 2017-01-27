package vkscanner

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import vkscanner.vkexternal.OutputDto
import vkscanner.vkexternal.VkApiService
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject


/**
 * Created on 17.01.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */

@RestController
class MainController @Inject constructor(val service: VkApiService) {

    data class EntryBitches(
            var count: Int = 10,
            var offset: Int = 0,
            var query: String = "",
            var ownersOnly: Boolean = true
    )

    @GetMapping(path = arrayOf("/filter"))
    fun filtered(): Set<OutputDto> {
        return service.getAllResponsesByAllFilters()
    }

}