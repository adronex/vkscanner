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
private class FilterController @Inject constructor(val service: FilterService) {

    @GetMapping
    fun findAll() = service.findAllDtos()

    @GetMapping(path = arrayOf("/{id}"))
    fun findOne(@PathVariable id: String) = service.findOne(id)

    @PostMapping
    fun save(@RequestBody filter: Filter) {
        service.save(filter)
    }

    @DeleteMapping(path = arrayOf("/{id}"))
    fun delete(@PathVariable id: String) {
        service.delete(id)
    }
}