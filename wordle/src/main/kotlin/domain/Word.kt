package domain

class Word(private val value: String) {

    init {
        require(value in ANSWER_CANDIDATES) { "단어 리스트에 존재하지 않는 단어입니다." }
    }

    val length = value.length

    operator fun get(index: Int): Char {
        return value[index]
    }

    fun toCharAndIsMarked(): MutableList<CharAndIsMarked> {
        return value.map { CharAndIsMarked(it) }.toMutableList()
    }

    fun withIndex(): Iterable<IndexedValue<Char>> {
        return value.withIndex()
    }
}
