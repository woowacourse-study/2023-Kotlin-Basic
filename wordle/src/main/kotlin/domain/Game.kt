package domain

import java.time.LocalDateTime

class Game(
    private val wordList: WordList // TODO: 이름 개선
) {
    private val submittedWords = mutableMapOf<Word, LetterCompareResults>()
    val letterCompareResultsList // TODO: 이름 개선
        get() = submittedWords.values

    // TODO: 리팩토링
    fun submitWord(text: String, now: LocalDateTime): LetterCompareResults {
        val submittedWord = Word(text)
        if (!wordList.contains(submittedWord)) {
            throw IllegalArgumentException("단어 리스트에 존재하는 단어를 제출해야합니다.")
        }

        val todayWordPicker = TodayWordPicker(wordList)
        val todayWord = todayWordPicker.pick(now)

        val letterCompareResult = submittedWord.compareWithCorrectAnswer(todayWord)
        submittedWords[submittedWord] = letterCompareResult

        return letterCompareResult
    }
}
