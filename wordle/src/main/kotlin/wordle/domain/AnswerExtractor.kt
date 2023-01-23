package wordle.domain

fun interface AnswerExtractor {

    fun extract(words: List<Word>): Word
}
