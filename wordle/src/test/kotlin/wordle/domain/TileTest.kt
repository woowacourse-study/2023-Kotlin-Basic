package wordle.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TileTest {

    @Test
    fun `⬜인지 판별한다`() {
        val tile = Tile.GRAY

        val actual = tile.isGray()

        assertTrue(actual)
    }
}
