package wordle.domain

import java.time.LocalDate
import java.time.Period

class FixedAnswerExtractor : AnswerExtractor {

    override fun extract(words: List<Word>): Word {
        return words[toDays() % words.size]
    }

    private fun toDays() = Period.between(LocalDate.now(), STANDARD_LOCAL_DATE).days

    companion object {
        val STANDARD_LOCAL_DATE: LocalDate = LocalDate.of(2021, 6, 19)
    }
}
