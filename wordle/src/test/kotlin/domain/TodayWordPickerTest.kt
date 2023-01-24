package domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import java.time.LocalDateTime

class TodayWordPickerTest : FunSpec({

    // TODO: clock.now 모킹을 통해 개선
    test("pick 메소드는 오늘 날짜의 단어를 반환한다.") {
        val testNow = LocalDateTime.of(2021, 6, 21, 0, 0)

        val wordList = WordList("HELLO\nSPILL\nMYSQL\nREACT")
        val todayWordPicker = TodayWordPicker(wordList)
        val actual = todayWordPicker.pick(testNow)

        actual.letters.map { it.letter } shouldContainExactly listOf("M", "Y", "S", "Q", "L")
    }
})
