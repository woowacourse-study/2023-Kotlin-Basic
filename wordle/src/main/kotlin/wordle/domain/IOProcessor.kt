package wordle.domain

interface IOProcessor {

    fun start()

    fun inputGuess(): String

    fun end(tiles: List<List<Tile>>)

    fun outputTiles(tiles: List<List<Tile>>)

    fun fail(message: String?)
}
