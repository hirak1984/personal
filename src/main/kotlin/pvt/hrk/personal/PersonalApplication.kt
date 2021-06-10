package pvt.hrk.personal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonalApplication

fun main(args: Array<String>) {
	runApplication<PersonalApplication>(*args)
}
