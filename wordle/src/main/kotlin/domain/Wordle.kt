package domain

class Wordle private constructor(
    private val values: String,
) {

    companion object {

        private const val ANSWER_SIZE = 5

        private val alphabets = ('a'..'z')

        fun from(value: String): Wordle {
            return Wordle(value)
        }
    }

    init {
        validateAnswer(values)
    }

    fun markAnswer(tryAnswer: Wordle): String =
        with(StringBuilder()) {
            for (i in (0..(tryAnswer.length() - 1)) step 1) {
                if (values[i] == tryAnswer[i]) {
                    this.append(Color.G)
                } else if (tryAnswer[i] in values) {
                    this.append(Color.Y)
                } else {
                    this.append(Color.W)
                }
            }
            toString()
        }

    fun length(): Int =
        values.length

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

    operator fun get(index: Int): Char {
        return values[index]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Wordle

        if (values != other.values) return false

        return true
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }

    override fun toString(): String {
        return values
    }
}
