package pvt.hrk.personal.tracker.domain

import pvt.hrk.personal.tracker.adapters.web.rest.Record
import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import java.util.*
import java.util.stream.Collectors

class AbcTest {
    fun test() {
        val model: List<RecordDetailsModel> = ArrayList()
        model.stream().map { recordDetailsModel: RecordDetailsModel ->
            val r = Record()
            r.amount = recordDetailsModel.name
            r
        }.collect(Collectors.toList())
    }
}