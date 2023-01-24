package domain

class WordleGame(var round: Int = 1,
                 var isEnd: Boolean = false) {

    private val answer = "river"
    private val answerChecker = AnswerChecker(answer)

    fun proceed(word: Word) {
        answerChecker.check(word)
        round++

        if (round == 7 || answerChecker.isAnswer) {
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

