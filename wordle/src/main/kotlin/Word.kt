class Word(private val alphabets: List<Alphabet>) {
}

class Alphabet(private val value: Char) {

    init {
        require(value in alphabets) { "값은 영어 알파벳이어야 합니다" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Alphabet

        return value.lowercase() == other.value.lowercase()
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        val alphabets = ('a'..'z').plus('A'..'Z')
    }
}
