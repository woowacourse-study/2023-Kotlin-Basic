package wordle.domain.answer

import wordle.domain.Word
import java.time.Duration
import java.time.LocalDate

class FixedAnswerExtractor : AnswerExtractor {

    override fun extract(words: List<Word>): Word {
        val index = toDays() % words.size
        return words[index.toInt()]
    }

    private fun toDays() = Duration.between(LocalDate.now(), STANDARD_LOCAL_DATE).toDays()

    companion object {
        val STANDARD_LOCAL_DATE: LocalDate = LocalDate.of(2021, 6, 19)
    }
}
