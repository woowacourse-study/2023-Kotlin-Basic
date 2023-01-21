package domain

import java.io.File

object ValidWords {

    private val cache: List<Word> = File("src/main/resources/words.txt")
        .readLines()
        .map { Word(it) }

    fun contains(word: Word): Boolean = cache.contains(word)

    operator fun get(index: Int) = cache[index]
}
