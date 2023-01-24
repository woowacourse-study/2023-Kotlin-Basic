package domain

import java.time.LocalDate
import java.time.Period

class Answer(val value: String) {

    fun toAnswerMarkingMap(): AnswerMarkings {
        return AnswerMarkings(value.map { CharAndIsMarked(it) }.toMutableList())
    }

    companion object {
        val STANDARD_DATE = LocalDate.of(2021, 6, 19)

        fun todayAnswer(): Answer {
            val dayDiffer: Int = Period.between(STANDARD_DATE, LocalDate.now()).days
            return Answer(ANSWER_CANDIDATES[dayDiffer % ANSWER_CANDIDATES.size])
        }
    }
}
