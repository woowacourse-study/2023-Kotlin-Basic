package domain

import domain.Compared.EQUAL

class Wordle(
    private val words: List<Word>,
    policy: AnswerPolicy
) {
    val answer = policy.pick(words)
    var hit = false
        private set
    var trial = MAX_TRIAL
        private set

    fun guess(word: Word): List<Compared> {
        requireNotEnded()
        require(words.contains(word)) { "게임 단어 목록에 포함된 답만 제시할 수 있습니다" }

        trial -= 1
        val result = answer.compare(word)
        if (result.all { it == EQUAL }) hit = true
        return result
    }

    fun isEnded(): Boolean {
        return trial <= 0 || hit
    }

    private fun requireNotEnded() {
        check(trial > 0) { "비교 가능 횟수가 남아있지 않습니다" }
        check(!hit) { "이미 정답을 맞췄습니다" }
    }

    companion object {
        const val MAX_TRIAL = 6
        const val WORD_LENGTH = 5
    }
}


