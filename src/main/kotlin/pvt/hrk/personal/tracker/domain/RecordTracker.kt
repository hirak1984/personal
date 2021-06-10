package pvt.hrk.personal.tracker.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import pvt.hrk.personal.tracker.adapters.StorageAdapter
import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import pvt.hrk.personal.tracker.domain.model.RecordSuspenseModel
import java.time.LocalDateTime

@Component
class RecordTracker @Autowired constructor(val storageAdapter: StorageAdapter){

    fun addOrUpdate(model: RecordDetailsModel): Long {
        storageAdapter!!.saveOrUpdate(model)
        return model.id!!
    }

    fun addSuspense(request: String,eventDate:LocalDateTime= LocalDateTime.now()) {
        storageAdapter!!.addSuspense(RecordSuspenseModel(eventDate,request))
    }

    fun allTrackingRecords(): List<RecordDetailsModel> {
        return storageAdapter!!.findAllRecords()
    }
    fun allTrackingSuspense(): List<RecordSuspenseModel> {
        return storageAdapter!!.findAllSuspense()
    }
}