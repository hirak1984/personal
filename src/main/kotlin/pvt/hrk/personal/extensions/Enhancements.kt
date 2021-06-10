package pvt.hrk.personal.extensions

import java.lang.Exception
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


    val dateTimeFormat = "DD/MM/YY HH:mm"
    val timeFormat = "HH:mm"

     fun String?.asDate(): LocalDateTime {
        return try {
            LocalDateTime.parse(this, DateTimeFormatter.ofPattern(dateTimeFormat))
        } catch (de: DateTimeParseException) {
            //another try
            try {
                val time = LocalTime.parse(this, DateTimeFormatter.ofPattern(timeFormat))
                LocalDate.now().atTime(time)
            } catch (e: Exception) {
                throw e
            }
        } catch (e: Exception) {
            throw RuntimeException("Cannot parse dateInString :$this")
        }
    }

    fun LocalDateTime?.asString(): String = if(this==null)  "" else this.format(DateTimeFormatter.ofPattern(dateTimeFormat))

