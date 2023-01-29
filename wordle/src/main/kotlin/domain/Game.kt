package domain

import java.time.LocalDateTime

class Game(
    private val wordList: WordList, // TODO: 이름 개선
    now: LocalDateTime
) {
    private val todayWord = TodayWordPicker(wordList).pick(now)
    val letterCompareResultsList = mutableListOf<LetterCompareResults>()

    // TODO: 리팩토링
    fun submitWord(text: String): LetterCompareResults {
        val submittedWord = Word(text)
        validateWord(submittedWord)

        val letterCompareResults = submittedWord.compareWithCorrectAnswer(todayWord)
        letterCompareResultsList.add(letterCompareResults)

        return letterCompareResults
    }

    private fun validateWord(submittedWord: Word) {
        if (!wordList.contains(submittedWord)) {
            throw IllegalArgumentException("단어 리스트에 존재하는 단어를 제출해야합니다.")
        }
    }
}
