package study

import org.junit.jupiter.api.Test

class MapTest {

    @Test
    fun `불변 Map을 가변으로 만들면 깊은 복사된다`() {
        val immutableMap = mapOf(1 to "1", 2 to "2", 3 to "3")
        val mutableMap = immutableMap.toMutableMap()

        mutableMap[1] = "10"

        println("immutableMap => $immutableMap")
        println("mutableMap => $mutableMap")
    }

    @Test
    fun `문자열을 grouping 한다`() {
        val value = "hello"
        val charArray = value.toCharArray()
            .toList()
            .groupingBy { it }
            .eachCount()

        println(charArray)
    }
}
