package vkscanner.filter

import org.springframework.data.annotation.Id
import java.util.*

/**
 * Default class description.
 * Created on 23.01.2017.
 * @author Pavel
 * @since ${VERSION}
 */

data class Filter(@Id var id: String? = null,
                  var queries: Set<String> = HashSet<String>(),
                  var communities: Set<String> = HashSet<String>())