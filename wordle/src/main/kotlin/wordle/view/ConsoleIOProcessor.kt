package wordle.view

import wordle.domain.IOProcessor
import wordle.domain.Tile
import java.lang.Exception
import java.util.Scanner

class ConsoleIOProcessor(
    private val scanner: Scanner,
) : IOProcessor {

    override fun start() {
        println("WORDLE을 6번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    override fun inputGuess() = retryNextLine(scanner)

    private fun retryNextLine(scanner: Scanner): String = try {
        println("정답을 입력해 주세요.")
        scanner.nextLine()
    } catch (e: Exception) {
        println(e.message)
        retryNextLine(scanner)
    }

    override fun end(tiles: List<List<Tile>>) {
        println("\n${tiles.size}/6")
        outputTiles(tiles)
    }

    override fun outputTiles(tiles: List<List<Tile>>) {
        println()
        println(tiles.joinToString("\n", transform = ::joinTile))
        println()
    }

    private fun joinTile(it: List<Tile>) = it.map(Tile::symbol)
        .joinToString("")

    override fun fail() {
        println("게임에 실패하였습니다.")
    }
}
