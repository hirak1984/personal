package pvt.hrk.personal.tracker.domain.model

import java.time.LocalDateTime

class RecordDetailsModel(var id:Long?=null,
                         val name:String = "T",
                         val recordCategory: String = "unknown",
                         val eventDate: LocalDateTime? = LocalDateTime.now(),
                         val duration: Long? = null,
                         val durationUnit: SupportedUnit?=null,
                         val amount: Double? = null,
                         val amountUnit: SupportedUnit? = null,
                         val remarks: String? = null) {
}