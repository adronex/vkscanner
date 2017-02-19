package vkscanner.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Default class description.
 * Created on 19.02.2017.
 * @author Pavel
 */
interface PostRepository : MongoRepository<Post, String> {
    fun findAllByOrderByDateDesc(pageable: Pageable): Page<Post>
}