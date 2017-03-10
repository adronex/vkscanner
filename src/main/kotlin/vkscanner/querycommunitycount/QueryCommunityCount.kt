package vkscanner.querycommunitycount

import org.springframework.data.annotation.Id

/**
 * Created on 10.03.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */
data class QueryCommunityCount
(
        @Id val query: String = "",
        val communityId: Int = 0,
        val count: Int = 0
)