package vkscanner.filter

import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Default class description.
 * Created on 27.01.2017.
 * @author Pavel
 */
@Service
internal class FilterServiceImpl @Inject constructor(val repository: FilterRepository) : FilterService {

    override fun findAll() = repository.findAll().map { IdNameDto(it.id!!, it.name) }

    override fun findOne(id: String) = repository.findOne(id)

    override fun save(filter: Filter) {
        repository.save(filter)
    }

    override fun delete(id: String) {
        repository.delete(id)
    }
}