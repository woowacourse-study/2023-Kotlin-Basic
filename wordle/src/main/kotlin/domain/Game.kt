package domain

import java.time.LocalDateTime

private const val MAX_SUBMIT_COUNT = 6

class Game(
    private val wordList: WordList
) {
    private val submittedWords = mutableMapOf<Word, LetterCompareResults>()
    val isEnd
        get() = submittedWords.size >= MAX_SUBMIT_COUNT

    // TODO: 시간 관련 리팩토링
    fun submitWord(text: String, now: LocalDateTime): LetterCompareResults {
        val submittedWord = Word(text)

        val todayWordPicker = TodayWordPicker(wordList)
        val todayWord = todayWordPicker.pick(now)

        val letterCompareResult = submittedWord.compareWithCorrectAnswer(todayWord)
        submittedWords[submittedWord] = letterCompareResult

        return letterCompareResult
    }
}
