import org.junit.jupiter.api.Test

class StudyTest {

    private val str = "abcde"

    @Test
    fun String을_Map으로_바꿔보기() {
        val ex1: Map<Int, IndexedValue<Char>> = str.withIndex().associateBy({ it.index })
        val ex1_1: Map<Int, IndexedValue<Char>> = str.withIndex().associateBy { it.index }
        println(ex1)
        println(ex1_1)

        val ex2: Map<Int, Char> = str.withIndex().associateBy({ it -> it.index }, { it -> it.value })
        println(ex2)

        val ex3: List<Pair<Int, Char>> = str.mapIndexed { index: Int, s: Char -> index + 1 to s }
        println(ex3)

        val ex4: Map<Int, Char> = str.mapIndexed { index: Int, s: Char -> index + 1 to s }.toMap()
        println(ex4)
    }

    @Test
    fun 코틀린_pair_써보기() {

        val pair = Pair(1, 'a')
        println(pair)
        val pairs = listOf<Pair<Int, Char>>(Pair(1, 'a'), Pair(2, 'b'))
        println(pairs)
        val mutablePairs = mutableListOf(Pair(1, 'a'), Pair(2, 'b'))
        println(mutablePairs)
        mutablePairs.removeAt(0)
        println(mutablePairs)

        val stringToPair = str.map { c -> c to false }.toMutableList()
        println(stringToPair)
        println(stringToPair[0])
        println(stringToPair[0].first)
    }
}
