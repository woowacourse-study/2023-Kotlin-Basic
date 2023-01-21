package domain

class Prediction(
    val word: Word
) {

    init {
        require(Words.contains(word)) { "유효한 단어로 정답을 예측해야 합니다." }
    }

    constructor(word: String) : this(Word(word))
}
