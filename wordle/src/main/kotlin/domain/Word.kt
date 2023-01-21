package domain

data class Word(
    private val value: String
) : Iterable<Char> {
    val length = value.length

    init {
        require(value.length == LENGTH) { "단어는 ${LENGTH}자여야 합니다." }
    }

    operator fun get(index: Int): Char = value[index]

    override fun iterator(): Iterator<Char> = value.iterator()

    override fun toString(): String = value

    companion object {
        const val LENGTH = 5
    }
}
