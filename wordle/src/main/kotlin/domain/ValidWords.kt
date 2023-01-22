package domain

import java.io.File

object ValidWords {

    private val cache: List<Word> = File(FILE_PATH!!.toURI())
        .readLines()
        .map(::Word)

    val size = cache.size

    fun contains(word: Word): Boolean = cache.contains(word)

    operator fun get(index: Int) = cache[index]
}

private val FILE_PATH = ValidWords.javaClass.classLoader.getResource("words.txt")
