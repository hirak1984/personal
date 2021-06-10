package pvt.hrk.personal.tracker.domain.model

import java.time.LocalDateTime

class RecordSuspenseModel (var id:Long?=null,
                           val eventDate: LocalDateTime? = LocalDateTime.now(),
                           val rawRequest: String) {
}