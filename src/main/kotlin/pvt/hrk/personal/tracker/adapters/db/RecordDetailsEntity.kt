package pvt.hrk.personal.tracker.adapters.db

import javax.persistence.GeneratedValue
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RecordDetailsEntity {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(nullable = false)
    var recordCategory: String? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var eventDate: LocalDateTime? = null

    @Column(nullable = true)
    var duration: Long? = null

    @Column(nullable = true)
    var durationUnit: String? = null

    @Column(nullable = true)
    var amount: Double? = null

    @Column(nullable = true)
    var amountUnit: String? = null

    @Column(nullable = true)
    var remarks: String? = null
}