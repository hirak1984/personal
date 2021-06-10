package pvt.hrk.personal.tracker.adapters.aws

import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import software.amazon.awssdk.services.dynamodb.model.AttributeValue

interface ModelConverter {
    fun toItemMap(model: Any): Map<String, AttributeValue>
    fun fromItemMap(item : Map<String, AttributeValue>): Any
}