package vkscanner.filter

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Default class description.
 * Created on 23.01.2017.
 * @author Pavel
 */
interface FilterRepository : MongoRepository <Filter, String> {

    fun findByQueries(query: String): List<Filter>
}