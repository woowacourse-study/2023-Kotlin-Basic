package wordle.domain

class Words(
    val value: List<Word>,
) {

    fun validateNonExist(word: Word) {
        require(value.contains(word)) { "[예외] 사전에 존재하지 않는 단어입니다." }
    }
}
