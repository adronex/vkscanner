package vkscanner.filter

import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Default class description.
 * Created on 27.01.2017.
 * @author Pavel
 */
@Service
private class FilterServiceImpl @Inject constructor(val repository: FilterRepository) : FilterService {

    override fun findAllDtos() = repository.findAll().map { IdNameDto(it.id!!, it.name) }

    override fun findAll(): List<Filter> = repository.findAll()

    override fun findOne(id: String) = repository.findOne(id)

    override fun save(filter: Filter) {
        repository.save(filter)
    }

    override fun delete(id: String) {
        repository.delete(id)
    }
}