package pvt.hrk.personal.tracker.adapters.aws

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import pvt.hrk.personal.tracker.adapters.StorageAdapter
import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import pvt.hrk.personal.tracker.domain.model.RecordSuspenseModel
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import software.amazon.awssdk.services.dynamodb.model.ScanRequest

@Service
@Profile("aws")
class DynamoDBStorageAdapter @Autowired constructor(val awsClients: AWSClients) : StorageAdapter {

    override fun saveOrUpdate(model: RecordDetailsModel) {
        awsClients.DynamoDbClient().putItem(
            PutItemRequest.builder()
                .tableName(DynamoDBHandler.TRACKING_RECORDS.name)
                .item(DynamoDBHandler.TRACKING_RECORDS.convertToItem(model))
                .build()
        )
    }

    override fun addSuspense(recordSuspenseModel: RecordSuspenseModel) {
        awsClients.DynamoDbClient().putItem(
            PutItemRequest.builder()
                .tableName(DynamoDBHandler.TRACKING_SUSPENSE.name)
                .item(DynamoDBHandler.TRACKING_SUSPENSE.convertToItem(recordSuspenseModel))
                .build()
        )
    }

    override fun findAllRecords(): List<RecordDetailsModel>? {
        return awsClients.DynamoDbClient().scan(
            ScanRequest.builder()
                .tableName(DynamoDBHandler.TRACKING_RECORDS.name)
                .build()
        )?.items()?.map { DynamoDBHandler.TRACKING_RECORDS.convertFromItem(it) as RecordDetailsModel }
    }

    override fun findAllSuspense(): List<RecordSuspenseModel>? {
        return awsClients.DynamoDbClient().scan(
            ScanRequest.builder()
                .tableName(DynamoDBHandler.TRACKING_SUSPENSE.name)
                .build()
        )?.items()?.map { DynamoDBHandler.TRACKING_SUSPENSE.convertFromItem(it) as RecordSuspenseModel }
    }
}

