package wordle.domain

import wordle.domain.Word

fun interface AnswerExtractor {

    fun extract(words: List<Word>): Word
}
