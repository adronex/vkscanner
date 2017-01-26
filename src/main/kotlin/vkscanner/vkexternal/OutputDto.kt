package vkscanner.vkexternal

import com.vk.api.sdk.objects.wall.WallpostFull

/**
 * Default class description.
 * Created on 26.01.2017.
 * @author Pavel
 */
class OutputDto(entry: WallpostFull) {
    val postId = entry.id
    val text = entry.text
    val date = entry.date
    val likes = entry.likes.count
    val reposts = entry.reposts.count
    val comments = entry.comments.count
}