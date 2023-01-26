package wordle.domain

data class Word(
    val value: String,
) {

    init {
        require(value.length == LIMIT_LENGTH) { "[예외] 단어는 ${LIMIT_LENGTH}글자만 가능합니다." }
        require(value.matches(REGEX)) { "[예외] 단어는 알파벳만 가능합니다." }
    }

    fun isSameIndex(other: Word, index: Int) = value[index] == other.value[index]

    companion object {
        const val LIMIT_LENGTH = 5
        val REGEX = Regex("^[a-zA-Z]*$")
    }
}
