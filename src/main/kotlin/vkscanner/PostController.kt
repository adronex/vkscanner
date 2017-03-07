package vkscanner

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
    fun findAll(@RequestParam(required = false, defaultValue = "0") page: Int,
                @RequestParam(required = false, defaultValue = "15") limit: Int) =
            service.findAll(page, limit)

    @GetMapping(path = arrayOf("/hopHop"))
    fun buttonFeeder() {
        service.buttonFeeder()
    }
}