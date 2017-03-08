package vkscanner.post

import com.vk.api.sdk.objects.wall.WallpostFull
import org.springframework.data.annotation.Id
import java.util.*

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
class Post(entry: WallpostFull = WallpostFull(),
           triggeredOnString: String) {
    @Id val postId = entry.id
    val ownerId = entry.ownerId
    val text = entry.text
    val posted = entry.date
    val scanned = Date()
    val likes = entry.likes?.count
    val reposts = entry.reposts?.count
    val comments = entry.comments?.count
    val triggeredOn = LinkedHashSet<String>()
    val interesting = true

    init {
        this.triggeredOn.add(triggeredOnString)
    }
}