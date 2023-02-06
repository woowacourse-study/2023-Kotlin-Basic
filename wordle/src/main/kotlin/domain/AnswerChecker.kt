package domain


class AnswerChecker(private val answer: Answer) {

    fun check(word: Word): RoundResult {
        val answerAndIsMarked = answer.toAnswerMarkingMap()
        val resultTiles: RoundResult = RoundResult.initTile()

        markGreenTiles(answerAndIsMarked, resultTiles, word)
        markYellowTiles(answerAndIsMarked, resultTiles, word)

        return resultTiles
    }

    private fun markGreenTiles(answerAndIsMarked: AnswerMarkings, resultTile: RoundResult, word: Word) {
        answerAndIsMarked.zip(word.withIndex()) { wordAndIsMarked, answerWord ->
            if (wordAndIsMarked.char == answerWord.value) {
                resultTile.markGreenAt(answerWord.index)
                wordAndIsMarked.isMarked = true
            }
        }
    }

    private fun markYellowTiles(answerAndIsMarked: AnswerMarkings, resultTile: RoundResult, word: Word) {
        for ((index: Int, alphabet: Char) in word.withIndex()) {
            if (isNotMarkingCase(answerAndIsMarked, index, alphabet)) continue
            val markingIndex: Int = answerAndIsMarked.getMarkingIndex(alphabet) ?: continue
            resultTile.markYellowAt(markingIndex)
            answerAndIsMarked[markingIndex].isMarked = true
        }
    }

    private fun isNotMarkingCase(answerAndIsMarked: AnswerMarkings, index: Int, alphabet: Char): Boolean {
        return answerAndIsMarked[index].isMarked || answerAndIsMarked.doesNotContain(alphabet)
    }
}
