package domain

class WordleGame(var round: Int = 0,
                 var isEnd: Boolean = false,
                 val gameResult: GameResult = GameResult()) {

    val answer = Answer.todayAnswer()
    private val answerChecker = AnswerChecker(answer)

    fun proceed(word: Word) {
        val roundResult = answerChecker.check(word)
        round++
        gameResult.appendResult(roundResult)

        if (roundResult.isAnswer || round.isFinish()) {
            isEnd = true
        }
    }

    val isInProgress: Boolean
        get() {
            return !isEnd
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
