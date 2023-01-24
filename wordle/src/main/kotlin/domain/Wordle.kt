package domain

import domain.Compared.EQUAL

class Wordle(policy: AnswerPolicy) {

    private val answer = Word(policy.getWord())
    var trial = MAX_TRIAL
        private set
    private var hit = false

    fun guess(word: Word): List<Compared> {
        requireNotEnded()
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
