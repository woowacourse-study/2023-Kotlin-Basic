package domain

import java.io.File
import java.time.Duration
import java.time.LocalDateTime


fun interface AnswerPolicy {
    fun getWord(): String
}

class FromFileByDatePolicy: AnswerPolicy {

    override fun getWord(): String {
        val path = "src/main/resources/words.txt"
        val words = File(path).inputStream().bufferedReader().use { it.readLines() }
        val duration = Duration.between(LocalDateTime.of(2021, 6, 19, 0, 0), LocalDateTime.now())
        return words[duration.toDays().toInt() % words.size]
    }
}
