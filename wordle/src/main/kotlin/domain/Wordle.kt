package domain

class Wordle(
    pickAnswer: PickAnswer
) {
    val answer: Answer = pickAnswer()
    var round: Int = 0
        private set
    var isEnd: Boolean = false
        private set
    private val judgementTiles: MutableList<JudgementTile> = mutableListOf()

    fun proceedRound(predictionString: String): List<JudgementTile> {
        require(!isEnd) { "이미 게임이 종료되었습니다." }
        val judgementTile = answer.judge(Prediction(predictionString))
        round++
        judgementTiles.add(judgementTile)
        if (judgementTile.isAllGreen() || round == MAX_ROUND) {
            isEnd = true
        }
        return judgementTiles
    }

    companion object {
        const val MAX_ROUND: Int = 6
    }
}
