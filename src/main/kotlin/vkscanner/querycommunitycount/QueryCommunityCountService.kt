package vkscanner.querycommunitycount

/**
 * Created on 10.03.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */
interface QueryCommunityCountService {
    fun findByCommunityId(communityId: Int) : List<QueryCommunityCount>
    fun save(qcc: QueryCommunityCount)
}