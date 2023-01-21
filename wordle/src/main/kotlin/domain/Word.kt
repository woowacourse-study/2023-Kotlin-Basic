package domain

data class Word(
    private val value: String
) {

    init {
        require(value.length == LENGTH) { "단어는 ${LENGTH}자여야 합니다." }
    }

    override fun toString(): String = value

    companion object {
        const val LENGTH = 5
    }
}
