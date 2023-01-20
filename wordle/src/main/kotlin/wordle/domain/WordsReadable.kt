package wordle.domain

fun interface WordsReadable {

    fun read(): List<Word>
}
