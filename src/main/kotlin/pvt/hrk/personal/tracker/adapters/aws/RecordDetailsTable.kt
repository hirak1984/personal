package pvt.hrk.personal.tracker.adapters.aws

class RecordDetailsTable {
    var hashKey:Int = hashCode()
    var name: String? = null
    var recordCategory: String? = null
    var eventDate: String? = null
    var duration: Long? = null
    var durationUnit: String? = null
    var amount: Double? = null
    var amountUnit: String? = null
    var remarks: String? = null

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (recordCategory?.hashCode() ?: 0)
        result = 31 * result + (eventDate?.hashCode() ?: 0)
        result = 31 * result + (duration?.hashCode() ?: 0)
        result = 31 * result + (durationUnit?.hashCode() ?: 0)
        result = 31 * result + (amount?.hashCode() ?: 0)
        result = 31 * result + (amountUnit?.hashCode() ?: 0)
        result = 31 * result + (remarks?.hashCode() ?: 0)
        return result
    }


}
