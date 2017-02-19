package vkscanner.post

import com.vk.api.sdk.objects.wall.WallpostFull
import org.springframework.data.annotation.Id

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
class Post(entry: WallpostFull = WallpostFull(),
           triggeredOn: String = "") {
    @Id val postId = entry.id
    val ownerId = entry.ownerId
    val text = entry.text
    val date = entry.date
    val likes = entry.likes?.count
    val reposts = entry.reposts?.count
    val comments = entry.comments?.count
    val triggeredOn = triggeredOn
}