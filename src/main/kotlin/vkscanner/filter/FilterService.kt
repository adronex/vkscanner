package vkscanner.filter

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
interface FilterService {
    fun findAllDtos(): List<IdNameDto>
    fun findAll(): List<Filter>
    fun findOne(id: String): Filter
    fun save(filter: Filter)
    fun delete(id: String)
}