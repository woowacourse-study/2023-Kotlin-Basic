package wordle.domain

fun interface WordsReader {

    fun read(): List<Word>
}
