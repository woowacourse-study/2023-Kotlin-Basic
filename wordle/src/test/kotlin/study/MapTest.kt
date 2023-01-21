package study

import org.junit.jupiter.api.Test

class MapTest {

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
