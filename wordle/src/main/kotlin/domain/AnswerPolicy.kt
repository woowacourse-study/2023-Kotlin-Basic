package domain

import java.time.LocalDate
import java.time.Period

fun interface AnswerPolicy {
    fun pick(words: List<Word>): Word
}

class ByDateTimePolicy : AnswerPolicy {

    override fun pick(words: List<Word>): Word {
        val period = Period.between(LocalDate.of(2021, 6, 19), LocalDate.now())
        return words[period.days % words.size]
    }
}
