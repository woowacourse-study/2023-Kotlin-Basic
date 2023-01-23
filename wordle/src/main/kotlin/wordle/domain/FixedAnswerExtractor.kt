package wordle.domain

import java.time.Duration
import java.time.LocalDateTime
import kotlin.math.abs

class FixedAnswerExtractor : AnswerExtractor {

    override fun extract(words: List<Word>): Word {
        val index = abs(toDays() % words.size)
        return words[index.toInt()]
    }

    private fun toDays() = Duration.between(LocalDateTime.now(), STANDARD_LOCAL_DATE_TIME).toDays()

    companion object {
        val STANDARD_LOCAL_DATE_TIME: LocalDateTime = LocalDateTime.of(2021, 6, 19, 0, 0)
    }
}
