import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class FileReaderTest: StringSpec({

    "경로와 주어진 날짜들의 간격으로 파일에서 단어를 읽어온다" {
        val path = "src/test/resources/words.txt"
        val from = LocalDateTime.of(2022, 1, 22, 0, 0)
        val to = LocalDateTime.of(2022, 1, 25, 0, 0)

        getWordFromFile(path, from, to) shouldBe "humph"
    }
})
