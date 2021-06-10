package pvt.hrk.personal.tracker.adapters

import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import pvt.hrk.personal.tracker.domain.model.RecordSuspenseModel
import java.time.LocalDateTime

interface StorageAdapter {
    fun saveOrUpdate(model: RecordDetailsModel)
    fun addSuspense(recordSuspenseModel: RecordSuspenseModel)
    fun findAllRecords(): List<RecordDetailsModel>
    fun findAllSuspense(): List<RecordSuspenseModel>
}