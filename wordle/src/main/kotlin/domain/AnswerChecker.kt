package domain

private fun String.toAnswerMarkingMap(): AnswerMarkings {
    return AnswerMarkings(this.map { CharAndIsMarked(it) }.toMutableList())
}

class AnswerChecker(val answer: String) {

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
            if (answerAndIsMarked[index].isMarked) continue
            if (answerAndIsMarked.doesNotContain(alphabet)) continue
            val index: Int = answerAndIsMarked.getMarkingIndex(alphabet) ?: continue
            resultTile.markYellowAt(index)
            answerAndIsMarked[index].isMarked = true
        }
    }
}
