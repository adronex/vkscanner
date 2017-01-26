package vkscanner.filter

import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

/**
 * Default class description.
 * Created on 27.01.2017.
 * @author Pavel
 */
@Service
internal class FilterServiceImpl @Inject constructor(val repository: FilterRepository) : FilterService {

    override fun findAll(): List<Filter> {
        return repository.findAll()
    }
}