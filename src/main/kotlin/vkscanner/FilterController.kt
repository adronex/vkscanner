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
@RestController
@RequestMapping("/filters")
class FilterController @Inject constructor(val service: FilterService) {

    @GetMapping
    fun findAll() = service.findAll()

    @GetMapping(path = arrayOf("/{id}"))
    fun findOne(@PathVariable id: String) = service.findOne(id)

    @PostMapping
    fun save(@RequestBody filter: Filter) {
        service.save(filter)
    }

    @DeleteMapping
    fun delete(@RequestParam id: String) {
        service.delete(id)
    }
}