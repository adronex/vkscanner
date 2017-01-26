package vkscanner.filter

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Default class description.
 * Created on 23.01.2017.
 * @author Pavel
 */
internal interface FilterRepository : MongoRepository <Filter, String> {

    override fun findAll(): List<Filter>

    fun findByQueries(query: String): List<Filter>
}