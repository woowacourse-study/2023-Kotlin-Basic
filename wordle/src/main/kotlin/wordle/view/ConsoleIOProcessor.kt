package wordle.view

import wordle.domain.IOProcessor
import wordle.domain.Tile

class ConsoleIOProcessor : IOProcessor {

    override fun start() {
        println(
            """
            WORDLE을 6번 만에 맞춰 보세요.
            시도의 결과는 타일의 색 변화로 나타납니다.
            """.trimIndent()
        )
    }

    override fun inputGuess(): String {
        println("정답을 입력해 주세요.")
        return readln()
    }

    override fun end(tiles: List<List<Tile>>) {
        println("\n${tiles.size}/6")
        outputTiles(tiles)
    }

    override fun outputTiles(tiles: List<List<Tile>>) {
        val tiles = tiles.joinToString("\n", transform = ::joinTile)
        println("\n${tiles}\n")
    }

    private fun joinTile(it: List<Tile>) = it.map(Tile::symbol).joinToString("")

    override fun fail(message: String?) {
        println(message ?: "[예외] 의도하지 않은 예외가 발생하였습니다.")
    }
}
