package vkscanner.filter

import org.springframework.data.annotation.Id

/**
 * Default class description.
 * Created on 23.01.2017.
 * @author Pavel
 * @since ${VERSION}
 */

data class Filter(@Id val userId: String, val queries: Set<String>, val communities: Set<String>)