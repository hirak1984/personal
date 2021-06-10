package pvt.hrk.personal.tracker.adapters.web.rest

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class Record {
    var recordCategory: String? = null
    var eventDate: String? = null
    var duration: String? = null
    var amount: String? = null
    var remarks: String? = ""
    override fun toString(): String {
        return "Record{" +
                "recordCategory='" + recordCategory + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", duration='" + duration + '\'' +
                ", amount='" + amount + '\'' +
                ", remarks='" + remarks + '\'' +
                '}'
    }
}