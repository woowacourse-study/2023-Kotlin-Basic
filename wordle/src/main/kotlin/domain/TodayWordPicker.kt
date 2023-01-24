package domain

import java.time.Duration
import java.time.LocalDateTime

private val STANDARD_DATE_TIME = LocalDateTime.of(2021, 6, 19, 0, 0)

class TodayWordPicker(
    private val wordList: WordList
) {
    // TODO: 리팩토링
    fun pick(now: LocalDateTime): Word {
        val duration = Duration.between(STANDARD_DATE_TIME, now).toDays()
        val index = (duration % wordList.values.size).toInt()
        return wordList.values[index]
    }
}
