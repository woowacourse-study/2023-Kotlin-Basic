package domain

class Prediction(
    val word: Word
) {

    init {
        require(ValidWords.contains(word)) { "유효한 단어 목록에 있는 단어로 정답을 예측해야 합니다." }
    }

    constructor(word: String) : this(Word(word))
}
