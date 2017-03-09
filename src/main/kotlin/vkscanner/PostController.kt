package vkscanner

import org.springframework.web.bind.annotation.*
import vkscanner.post.PostService
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject


/**
 * Created on 17.01.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */

@RestController
@RequestMapping("/posts")
private class PostController @Inject constructor(val service: PostService) {

    @GetMapping
    fun findAll(@RequestParam(required = false, defaultValue = "true") interestingOnly: Boolean,
                @RequestParam(required = false, defaultValue = "0") page: Int,
                @RequestParam(required = false, defaultValue = "15") limit: Int) =
            service.findAll(interestingOnly, page, limit)

    @PostMapping(path = arrayOf("/{postId}/setInteresting"))
    fun findAll(@PathVariable postId: Int,
                @RequestParam(required = false, defaultValue = "false") interesting: Boolean) {
        service.setInteresting(postId, interesting)
    }

    @GetMapping(path = arrayOf("/hopHop"))
    fun buttonFeeder() {
        service.buttonFeeder()
    }
}