package domain

class WordleGame(var round: Int = 0,
                 var isEnd: Boolean = false,
                 val gameResult: GameResult = GameResult()) {

    private val answer = "river"
    private val answerChecker = AnswerChecker(answer)

    fun proceed(word: Word) {
        val roundResult = answerChecker.check(word)
        round++
        gameResult.appendResult(roundResult)

        if (round == 6 || answerChecker.isAnswer) {
            isEnd = true
        }
    }

    /*
    입력한 답) roier
    답 ) river

    GREEN GREY YELLOW GREEN GREEN
    -> 타일은 입력한 값 기준임!

     */


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

