package vkscanner

import org.springframework.web.bind.annotation.*
import vkscanner.filter.Filter
import vkscanner.filter.FilterService
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Default class description.
 * Created on 27.01.2017.
 * @author Pavel
 */
@RestController(value = "/filters")
class FilterController @Inject constructor(val service: FilterService) {

    @GetMapping
    fun findAllFilters() = service.findAll()

    @PostMapping
    fun saveFilter(@RequestBody filter: Filter) {
        service.save(filter)
    }

    @DeleteMapping
    fun deleteFilter(@RequestParam id: String) {
        service.delete(id)
    }
}