package domain

import java.time.Duration
import java.time.LocalDateTime

fun interface AnswerPolicy {
    fun pick(words: List<Word>): Word
}

class ByDateTimePolicy : AnswerPolicy {

    override fun pick(words: List<Word>): Word {
        val duration = Duration.between(LocalDateTime.of(2021, 6, 19, 0, 0), LocalDateTime.now())
        return words[duration.toDays().toInt() % words.size]
    }
}
