import java.io.File

fun readWordsFromFile(path: String): List<String> {
    return File(path).inputStream().bufferedReader().use { it.readLines() }
}
