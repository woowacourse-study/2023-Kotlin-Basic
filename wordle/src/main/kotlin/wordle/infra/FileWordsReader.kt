package wordle.infra

import wordle.domain.WordsReader
import wordle.domain.Word
import kotlin.io.path.Path
import kotlin.io.path.readLines

class FileWordsReader : WordsReader {

    override fun read(): List<Word> {
        return Path(PATH).readLines()
            .map(::Word)
    }

    companion object {
        const val PATH = "src/main/resources/words.txt"
    }
}
