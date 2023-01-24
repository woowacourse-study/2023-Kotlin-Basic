package domain

class AnswerChecker(val answer: String) {

    val isAnswer: Boolean
        get() {
            return false
        }

    fun check(word: Word) {
        val answerAndIsMarked = answer.map { c -> CharAndIsMarked(c) }.toMutableList()
        val resultTiles = mutableListOf<Tile>(Tile.GREY, Tile.GREY, Tile.GREY, Tile.GREY, Tile.GREY)

        println(resultTiles)
        markGreenTiles(answerAndIsMarked, resultTiles, word)
        println(resultTiles)
    }

    private fun markGreenTiles(answerAndIsMarked: MutableList<CharAndIsMarked>, resultTile: MutableList<Tile>, word: Word) {
        answerAndIsMarked.zip(word.withIndex()) { wordAndIsMarked, answerWord ->
            if (wordAndIsMarked.char == answerWord.value) {
                resultTile[answerWord.index] = Tile.GREEN
                wordAndIsMarked.isMarked = true
            }
        }
    }


}
