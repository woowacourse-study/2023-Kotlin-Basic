import java.io.File
import java.time.Duration
import java.time.LocalDateTime

fun getWordFromFile(path: String, from: LocalDateTime, to: LocalDateTime): String {
    val words = File(path).inputStream().bufferedReader().use { it.readLines() }
    val duration = Duration.between(from, to)
    return words[duration.toDays().toInt() % words.size]
}
