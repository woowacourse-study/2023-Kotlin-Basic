package util

import java.io.File

fun readWords(): List<String> =
    File("src/main/resources/words.txt")
        .bufferedReader()
        .use { it.readLines() }
