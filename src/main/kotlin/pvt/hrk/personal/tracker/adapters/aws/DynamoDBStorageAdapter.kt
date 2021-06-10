package pvt.hrk.personal.tracker.adapters.aws

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import pvt.hrk.personal.extensions.asString
import pvt.hrk.personal.tracker.adapters.StorageAdapter
import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import pvt.hrk.personal.tracker.domain.model.RecordSuspenseModel

@Service
@Profile("aws")
class DynamoDBStorageAdapter @Autowired constructor(val awsClients: AWSClients): StorageAdapter {

    val recordDetailsTable = "tracking-records"
    val recordSuspenseTable = "tracking-suspense"


    override fun saveOrUpdate(model: RecordDetailsModel) {
        var recordDetailsTable = RecordDetailsTable()
        recordDetailsTable.name = model.name
        recordDetailsTable.recordCategory = model.recordCategory
        recordDetailsTable.eventDate = model.eventDate.asString()
        recordDetailsTable.amount = model.amount
        recordDetailsTable.amountUnit = model.amountUnit?.name
        recordDetailsTable.duration = model.duration
        recordDetailsTable.durationUnit = model.durationUnit?.name
        recordDetailsTable.remarks = model.remarks

    }

    override fun addSuspense(recordSuspenseModel: RecordSuspenseModel) {
        RecordSuspenseTable(eventDate=recordSuspenseModel.eventDate, rawRequest = recordSuspenseModel.rawRequest)
    }

    override fun findAllRecords(): List<RecordDetailsModel> {
        return awsClients.DynamoDbClient().listTables().tableNames().map { RecordDetailsModel(recordCategory = it) }
    }

    override fun findAllSuspense(): List<RecordSuspenseModel> {
        TODO("Not yet implemented")
    }
}

