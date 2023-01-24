package domain

import java.time.Duration
import java.time.LocalDateTime

private val STANDARD_DATE_TIME = LocalDateTime.of(2021, 6, 19, 0, 0)

class TodayWordPicker(
    wordsText: String
) {
    private val wordList: List<Word>

    init {
        this.wordList = wordsText.split("\n").map { Word(it) }
    }

    fun pick(now: LocalDateTime): Word {
        val duration = Duration.between(STANDARD_DATE_TIME, now).toDays()
        val index = (duration % wordList.size).toInt()
        return wordList[index]
    }
}
