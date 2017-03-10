package vkscanner.querycommunitycount

import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Created on 10.03.2017;
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */
@Service
private class QueryCommunityCountServiceImpl @Inject constructor(val repository: QueryCommunityCountRepository)
    : QueryCommunityCountService {

    override fun findByCommunityId(communityId: Int): List<QueryCommunityCount> = repository.findAll()


    override fun save(qcc: QueryCommunityCount){
        repository.save(qcc)
    }
}