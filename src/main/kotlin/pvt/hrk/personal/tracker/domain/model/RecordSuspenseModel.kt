package pvt.hrk.personal.tracker.domain.model

import java.time.LocalDateTime

class RecordSuspenseModel (val eventDate: LocalDateTime? = LocalDateTime.now(),
                           val rawRequest: String) {
}