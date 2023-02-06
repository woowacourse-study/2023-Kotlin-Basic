package domain

import java.time.LocalDate

class WordleGame private constructor(var round: Int = 0,
                                     var isEnd: Boolean = false,
                                     val gameResult: GameResult = GameResult()) {

    val answer = Answer.answerOfDate(LocalDate.now())
    private val answerChecker = AnswerChecker(answer)

    val isInProgress: Boolean
        get() = !isEnd

    fun proceed(word: Word) {
        val roundResult = answerChecker.check(word)
        round++
        gameResult.appendResult(roundResult)

        if (roundResult.isAnswer || round.isFinish()) {
            isEnd = true
        }
    }

    companion object {
        fun start(): WordleGame {
            return WordleGame()
        }
    }
}

private const val TOTAL_ROUND = 6

private fun Int.isFinish(): Boolean {
    return this == TOTAL_ROUND
}
