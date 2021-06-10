package pvt.hrk.personal.tracker.adapters.aws

import java.time.LocalDateTime

class RecordSuspenseTable(val eventDate: LocalDateTime?, val rawRequest: String) {
    var hashKey: Int = hashCode()

    override fun hashCode(): Int {
        var result = eventDate?.hashCode() ?: 0
        result = 31 * result + rawRequest.hashCode()
        return result
    }
}