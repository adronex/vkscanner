package vkscanner.post

import org.springframework.data.domain.Page

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
interface PostService {
    fun findAll(interestingOnly: Boolean, page: Int, limit: Int): Page<Post>

    fun setInteresting(postId: Int, interesting: Boolean)

    fun buttonFeeder()
}