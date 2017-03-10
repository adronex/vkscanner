package vkscanner.querycommunitycount

import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created on 10.03.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */
internal interface QueryCommunityCountRepository : MongoRepository<QueryCommunityCount, String> {
    fun findByCommunityId(communityId: Int): List<QueryCommunityCount>
}