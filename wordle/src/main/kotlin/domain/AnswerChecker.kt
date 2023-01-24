package domain

class AnswerChecker(val answer: String) {

    fun check(word: Word): RoundResult {
        val answerAndIsMarked = answer.map { CharAndIsMarked(it) }.toMutableList()
        val resultTiles: RoundResult = RoundResult.initTile()

        println(resultTiles)
        markGreenTiles(answerAndIsMarked, resultTiles, word)
        println(resultTiles)

        return resultTiles
    }

    private fun markGreenTiles(answerAndIsMarked: MutableList<CharAndIsMarked>, resultTile: RoundResult, word: Word) {
        answerAndIsMarked.zip(word.withIndex()) { wordAndIsMarked, answerWord ->
            if (wordAndIsMarked.char == answerWord.value) {
                resultTile.markGreenAt(answerWord.index)
                wordAndIsMarked.isMarked = true
            }
        }
    }
}
