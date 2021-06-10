package pvt.hrk.personal.tracker.adapters.web.rest

import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import pvt.hrk.personal.tracker.domain.RecordTracker
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import pvt.hrk.personal.extensions.asDate
import pvt.hrk.personal.tracker.domain.model.RecordDetailsModel
import pvt.hrk.personal.tracker.domain.model.RecordSuspenseModel
import pvt.hrk.personal.tracker.domain.model.SupportedUnit
import java.lang.Exception
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

@RestController
class RecordTrackingRestController @Autowired constructor(val recordTracker: RecordTracker) {

    @PostMapping("/tracker/create")
    fun create(@RequestBody record: Record): Long {
        try {
            val model = toModel(record)
            return recordTracker.addOrUpdate(model)
        } catch (e: Exception) {
            e.printStackTrace()
            recordTracker.addSuspense(record.toString())
        }
        return -1L
    }

    @GetMapping("/tracker/records")
    fun findAll(): List<Record> {
        return recordTracker.allTrackingRecords().stream().map { recordDetailsModel: RecordDetailsModel ->
            val record = Record()
            if(recordDetailsModel.amountUnit==SupportedUnit.NULL){
                record.amount = "${recordDetailsModel.amount} ${recordDetailsModel.amountUnit}"
            }
            if(recordDetailsModel.durationUnit==SupportedUnit.NULL){
                record.duration = "${recordDetailsModel.duration} ${recordDetailsModel.durationUnit}"
            }
            record.recordCategory = recordDetailsModel.recordCategory
            record.eventDate = recordDetailsModel.eventDate!!.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm"))
            record.remarks = recordDetailsModel.remarks
            record
        }.collect(Collectors.toList())

    }
    @GetMapping("/tracker/suspense")
    fun findAllSuspense(): List<String> {
        return recordTracker.allTrackingSuspense().stream().map { model: RecordSuspenseModel ->
           "${model.eventDate} --> ${model.rawRequest}"
        }.collect(Collectors.toList())

    }

    companion object {
        private fun toModel(record: Record, name: String = "T"): RecordDetailsModel {
            val eventDate = record.eventDate.asDate()
            val (duration, durationUnit) = convertToDuration(record.duration)
            val (amount, amountUnit) = convertToAmount(record.amount)
            return RecordDetailsModel(
                null,
                name = name,
                record.recordCategory!!,
                eventDate,
                duration,
                durationUnit,
                amount,
                amountUnit,
                record.remarks
            )
        }

        private fun convertToAmount(amountInString: String?): Pair<Double?, SupportedUnit?> {
            return try {
                when(amountInString.isNullOrBlank()){
                    true-> Pair(0.0,SupportedUnit.NULL)
                    false-> {
                        var tokens = splitIntoAmountAndUnit(amountInString)
                        Pair(tokens!!.first.toDouble(), SupportedUnit.lookup(tokens!!.second))
                    }
                }
            } catch (e: Exception) {
                throw RuntimeException(e.message)
            }
        }

        private fun convertToDuration(durationInString: String?): Pair<Long?, SupportedUnit?> {
            return try {
                when(durationInString.isNullOrBlank()){
                    true-> Pair(0L,SupportedUnit.NULL)
                    false-> {
                        var tokens = splitIntoAmountAndUnit(durationInString)
                        Pair(tokens!!.first.toLong(), SupportedUnit.lookup(tokens.second))
                    }
                }
            } catch (e: Exception) {
                throw RuntimeException(e.message)
            }
        }

        private fun splitIntoAmountAndUnit(durationInString: String): Pair<String, String> {
            var numberBuilder = StringBuilder()
            var unitIndex = -1;
            val arr = durationInString.toCharArray()
            for ((index, ch) in arr.withIndex()) {
                if (Character.isLetter(ch)) {
                    unitIndex = index
                    break;
                } else {
                    numberBuilder.append(ch)
                }
            }
            return Pair(numberBuilder.toString().trim(), durationInString.substring(unitIndex).trim())
        }
    }
}