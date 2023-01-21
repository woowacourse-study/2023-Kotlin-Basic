package domain

data class Word(
    private val value: String
) {

    init {
        require(value.length == WORD_LENGTH) { "단어는 5자여야 합니다." }
    }

    override fun toString(): String = value
}

private const val WORD_LENGTH = 5
