package pvt.hrk.personal.tracker.adapters.db

import pvt.hrk.personal.tracker.adapters.StorageAdapter
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import pvt.hrk.personal.tracker.domain.model.RecordSuspenseModel
import pvt.hrk.personal.tracker.domain.model.SupportedUnit

@Service
@Profile("jpa")
class JPAStorageAdapter(val recordDetailsJPARepository: RecordDetailsJPARepository, val recordSuspenseJPARepository: RecordSuspenseJPARepository) :
    StorageAdapter {

    private fun toEntity(model: RecordDetailsModel): RecordDetailsEntity {
        val recordDetailsEntity = RecordDetailsEntity()
        recordDetailsEntity.name = model.name
        recordDetailsEntity.recordCategory = model.recordCategory
        recordDetailsEntity.eventDate = model.eventDate
        recordDetailsEntity.duration = model.duration
        recordDetailsEntity.durationUnit = model.durationUnit!!.name
        recordDetailsEntity.amount = model.amount
        recordDetailsEntity.amountUnit = model.amountUnit!!.name
        recordDetailsEntity.remarks = model.remarks
        return recordDetailsEntity
    }

    private fun toModel(recordDetailsEntity: RecordDetailsEntity): RecordDetailsModel {
        return RecordDetailsModel(
            recordDetailsEntity.id,
            recordDetailsEntity.name!!, recordDetailsEntity.recordCategory!!,
            recordDetailsEntity.eventDate,
            recordDetailsEntity.duration, SupportedUnit.lookup(recordDetailsEntity.durationUnit),
            recordDetailsEntity.amount, SupportedUnit.lookup(recordDetailsEntity.amountUnit),
            recordDetailsEntity.remarks
        )
    }

    override fun saveOrUpdate(model: RecordDetailsModel) {
        val recordDetailsEntityAfterSave = recordDetailsJPARepository.save(toEntity(model))
        model.id = recordDetailsEntityAfterSave.id
    }

    override fun addSuspense(suspenseModel: RecordSuspenseModel) {
        var recordSuspenseEntity = RecordSuspenseEntity()
        recordSuspenseEntity.eventDate = suspenseModel.eventDate
        recordSuspenseEntity.rawRequest = suspenseModel.rawRequest
        recordSuspenseJPARepository.save(recordSuspenseEntity)
    }

    override fun findAllRecords(): List<RecordDetailsModel> {
        return recordDetailsJPARepository.findAll().map {toModel(it) }
    }

    override fun findAllSuspense(): List<RecordSuspenseModel> {
        return recordSuspenseJPARepository.findAll().map { RecordSuspenseModel(it.id,it.eventDate,it.rawRequest) }
    }
}