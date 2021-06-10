package pvt.hrk.personal.tracker.adapters.aws

import pvt.hrk.personal.extensions.asDate
import pvt.hrk.personal.extensions.asString
import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import pvt.hrk.personal.tracker.domain.model.RecordSuspenseModel
import pvt.hrk.personal.tracker.domain.model.SupportedUnit
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.lang.IllegalArgumentException

enum class DynamoDBHandler(private val converter: ModelConverter) {
    TRACKING_RECORDS(object : ModelConverter {
        private fun generateId(model: RecordDetailsModel): Int {
            var result = model.name?.hashCode() ?: 0
            result = 31 * result + (model.recordCategory?.hashCode() ?: 0)
            result = 31 * result + (model.eventDate.asString().hashCode() ?: 0)
            result = 31 * result + (model.duration?.hashCode() ?: 0)
            result = 31 * result + (model.durationUnit?.name.hashCode() ?: 0)
            result = 31 * result + (model.amount?.hashCode() ?: 0)
            result = 31 * result + (model.amountUnit?.name.hashCode() ?: 0)
            result = 31 * result + (model.remarks?.hashCode() ?: 0)
            return result
        }

        override fun toItemMap(model: Any): Map<String, AttributeValue> {
            if (model is RecordDetailsModel) {
                return mapOf<String, AttributeValue>(
                    "hashKey" to AttributeValue.builder().n(generateId(model as RecordDetailsModel).toString()).build(),
                    "name" to AttributeValue.builder().s(model.name).build(),
                    "recordCategory" to AttributeValue.builder().s(model.recordCategory).build(),
                    "eventDate" to AttributeValue.builder().s(model.eventDate.asString()).build(),
                    "amount" to AttributeValue.builder().n(model.amount.toString()).build(),
                    "amountUnit" to AttributeValue.builder().s(model.amountUnit?.name).build(),
                    "duration" to AttributeValue.builder().n(model.duration.toString()).build(),
                    "durationUnit" to AttributeValue.builder().s(model.durationUnit?.name).build(),
                    "remarks" to AttributeValue.builder().s(model.remarks).build(),
                )
            } else {
                throw IllegalArgumentException("Expecting RecordDetailsModel here")
            }
        }

        override fun fromItemMap(item: Map<String, AttributeValue>): Any {
            return RecordDetailsModel(
                id = item["hashKey"].toString().toLong(),
                eventDate = item["eventDate"].toString().asDate(),
                name = item["name"].toString(),
                recordCategory = item["recordCategory"].toString(),
                amount = item["amount"].toString().toDouble(),
                amountUnit = SupportedUnit.lookup(item["amount"].toString()),
                duration = item["duration"].toString().toLong(),
                durationUnit = SupportedUnit.lookup(item["durationUnit"].toString()),
                remarks = item["remarks"].toString()
            )
        }
    }),
    TRACKING_SUSPENSE(object : ModelConverter {
        private fun generateId(model: RecordSuspenseModel): Int {
            var result = model.rawRequest?.hashCode() ?: 0
            result = 31 * result + (model.eventDate.asString().hashCode() ?: 0)
            return result
        }

        override fun toItemMap(model: Any): Map<String, AttributeValue> {
            if (model is RecordSuspenseModel) {
                return mapOf<String, AttributeValue>(
                    "hashKey" to AttributeValue.builder().n(generateId(model).toString()).build(),
                    "eventDate" to AttributeValue.builder().s(model.eventDate.asString()).build(),
                    "rawRequest" to AttributeValue.builder().s(model.rawRequest).build(),
                )
            } else {
                throw IllegalArgumentException("Expecting RecordSuspenseModel here")
            }
        }

        override fun fromItemMap(item: Map<String, AttributeValue>): Any {
            return RecordSuspenseModel(
                item["hashKey"].toString().toLong(),
                item["eventDate"].toString().asDate(),
                item["rawRequest"].toString()
            )
        }
    });

    fun convertToItem(model: Any): Map<String, AttributeValue> {
        return converter.toItemMap(model)
    }
    fun convertFromItem(item: Map<String, AttributeValue>): Any {
        return converter.fromItemMap(item)
    }
}
