package domain

import java.lang.IllegalArgumentException

class Answer private constructor(
    private val values: String
) {

    companion object {

        private const val ANSWER_SIZE = 5

        private val alphabets = ('a'..'z')

        fun from(value: String): Answer {
            return Answer(value)
        }
    }

    init {
        validateAnswer(values)
    }

    private fun validateAnswer(values: String) {
        validateLength(values)
        validateAlphabet(values)
    }

    private fun validateAlphabet(values: String) {
        if (containsNotAlphabet(values)) {
            throw IllegalArgumentException("알파벳이 아닌 문자를 포함하고 있습니다.")
        }
    }

    private fun containsNotAlphabet(values: String): Boolean =
        values.lowercase().any { it !in alphabets }

    private fun validateLength(values: String) {
        if (values.length != ANSWER_SIZE) {
            throw IllegalArgumentException("정답은 다섯 글자여야 합니다. ")
        }
    }
}
