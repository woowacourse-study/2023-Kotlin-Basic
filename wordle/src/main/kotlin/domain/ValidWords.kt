package domain

import java.io.FileNotFoundException
import kotlin.io.path.Path
import kotlin.io.path.readLines

object ValidWords {

    private val cache: List<Word> = Path(filePath ?: throw FileNotFoundException())
        .readLines()
        .map(::Word)

    val size = cache.size

    fun contains(word: Word): Boolean = cache.contains(word)

    operator fun get(index: Int) = cache[index]
}

private val filePath = ValidWords.javaClass.classLoader.getResource("words.txt")?.path
