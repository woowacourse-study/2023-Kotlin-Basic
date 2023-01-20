package wordle.domain

class Word(
    val value: String
) {

    init {
        require(value.length == 5) { "[예외] 단어는 5글자만 가능합니다." }
        require(value.matches(REGEX)) { "[예외] 단어는 알파벳만 가능합니다." }
    }

    companion object {
        val REGEX = Regex("^[a-zA-Z]*$")
    }
}
