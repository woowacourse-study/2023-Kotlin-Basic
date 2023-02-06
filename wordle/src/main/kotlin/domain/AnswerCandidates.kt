package domain

import java.io.File
import java.io.FileNotFoundException

val PATH = object {}.javaClass.classLoader.getResource("words.txt").toURI()
        ?: throw FileNotFoundException("words.txt 파일이 존재하지 않습니다.")
val ANSWER_CANDIDATES = readAnswerCandidatesFile()

private fun readAnswerCandidatesFile(): List<String> {
    val file = File(PATH)
    return file.useLines { lines -> lines.toList() }
}
