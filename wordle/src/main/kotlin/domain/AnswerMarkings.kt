package domain

class AnswerMarkings(private val answerAndIsMarked: MutableList<CharAndIsMarked>) : Iterable<CharAndIsMarked> {

    override fun iterator(): Iterator<CharAndIsMarked> {
        return answerAndIsMarked.iterator()
    }

    operator fun get(index: Int): CharAndIsMarked {
        return answerAndIsMarked[index]
    }

    fun doesNotContain(alphabet: Char): Boolean {
        return !answerAndIsMarked
                .filter { !it.isMarked }
                .map { it.char }
                .contains(alphabet)
    }

    fun getMarkingIndex(alphabet: Char): Int? {
        return answerAndIsMarked.withIndex()
                .filter { !it.value.isMarked && it.value.char == alphabet }
                .map { it.index }
                .getOrNull(0)
    }
}
