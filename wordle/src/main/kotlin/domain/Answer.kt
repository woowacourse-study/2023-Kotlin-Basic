package domain

class Answer(
    val word: Word
) {

    init {
        require(Words.contains(word)) { "정답은 유효한 단어여야 합니다." }
    }
}
