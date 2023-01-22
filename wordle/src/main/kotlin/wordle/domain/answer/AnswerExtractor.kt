package wordle.domain.answer

import wordle.domain.Word

interface AnswerExtractor {

    fun extract(words: List<Word>): Word
}
