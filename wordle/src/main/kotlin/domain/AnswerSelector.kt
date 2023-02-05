package domain

import java.time.LocalDate

private val baseDate = LocalDate.of(2022, 6, 19)

object AnswerSelector {

    fun findTodayAnswer(today: LocalDate, words: List<String>): String {
        val daysDifference = today.minusDays(baseDate.toEpochDay()).toEpochDay().toInt()
        return words[daysDifference % words.size]
    }
}
