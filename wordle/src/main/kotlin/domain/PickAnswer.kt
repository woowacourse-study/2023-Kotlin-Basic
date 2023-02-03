package domain

import java.time.LocalDate
import java.time.Period

fun interface PickAnswer {

    operator fun invoke(): Answer

    class Default : PickAnswer {

        override fun invoke(): Answer {
            val days = Period.between(START_DATE, LocalDate.now()).days
            return Answer(ValidWords[days % ValidWords.size])
        }

        companion object {
            private val START_DATE = LocalDate.of(2023, 1, 20)
        }
    }
}
