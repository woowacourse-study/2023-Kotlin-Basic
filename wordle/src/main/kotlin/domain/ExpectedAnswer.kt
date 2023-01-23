package domain

class ExpectedAnswer(private val value: String?) {

    init {
        if (value !in candidates) {
            throw IllegalArgumentException("단어 리스트에 존재하지 않는 단어입니다.")
        }
    }
}
