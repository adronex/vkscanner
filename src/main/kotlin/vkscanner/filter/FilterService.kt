package vkscanner.filter

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
interface FilterService {
    fun findAll(): List<IdNameDto>
    fun findOne(id: String): Filter
    fun save(filter: Filter)
    fun delete(id: String)
}