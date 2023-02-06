package domain

class Word(word: String) {

    private val alphabets: List<Alphabet> = word.map { Alphabet(it) }

    init {
        require(alphabets.size == Wordle.WORD_LENGTH) { "단어는 ${Wordle.WORD_LENGTH} 글자로 구성됩니다" }
    }

    fun compare(other: Word): List<Compared> {
        val result = MutableList(Wordle.WORD_LENGTH) { Compared.NONE }
        val pool = AlphabetsPool(alphabets)

        for ((i, alphabet) in other.alphabets.withIndex()) {
            if (alphabets[i] == alphabet) {
                result[i] = Compared.EQUAL
                pool.spend(alphabet)
                continue
            }

            if (alphabets.contains(alphabet) && pool.has(alphabet)) {
                result[i] = Compared.EXIST
                pool.spend(alphabet)
            }
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Word

        return (alphabets == other.alphabets)
    }

    override fun hashCode(): Int {
        return alphabets.hashCode()
    }

    override fun toString(): String {
        return alphabets.map { it.value }.joinToString(separator = "")
    }
}

class AlphabetsPool(alphabets: List<Alphabet>) {

    private val pool: MutableMap<Alphabet, Int> = alphabets.groupingBy { it }.eachCount().toMutableMap()

    fun has(alphabet: Alphabet): Boolean {
        val count = pool[alphabet] ?: return false
        return count > 0
    }

    fun spend(alphabet: Alphabet) {
        if (pool.containsKey(alphabet)) {
            pool[alphabet] = pool[alphabet]!!.minus(1)
        }
    }
}

class Alphabet(val value: Char) {

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
