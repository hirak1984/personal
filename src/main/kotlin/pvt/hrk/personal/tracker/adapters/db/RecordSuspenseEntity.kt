package pvt.hrk.personal.tracker.adapters.db

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class RecordSuspenseEntity {
    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(nullable = false)
    var eventDate: LocalDateTime? = null

    @Column(nullable = false)
    lateinit var rawRequest: String
}