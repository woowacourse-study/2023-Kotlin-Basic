package wordle.domain

class Judgement(
    private val answer: Word,
    private val guess: Word,
) {

    private val countOfChars: Map<Char, Int> = answer.toCountOfChars()

    private fun Word.toCountOfChars(): Map<Char, Int> {
        return value.toCharArray()
            .toList()
            .groupingBy { it }
            .eachCount()
    }

    fun judge(): List<Tile> {
        val mutableCountOfChars = countOfChars.toMutableMap()
        val mutableTiles = DEFAULT_TILES.toMutableList()

        fillGreenTiles(mutableTiles, mutableCountOfChars)
        fillYellowTiles(mutableTiles, mutableCountOfChars)

        return mutableTiles
    }

    private fun fillGreenTiles(tiles: MutableList<Tile>, countOfChars: MutableMap<Char, Int>) {
        for (index in 0 until answer.value.length) {
            fillGreenTile(index, tiles, countOfChars)
        }
    }

    private fun fillGreenTile(index: Int, tiles: MutableList<Tile>, countOfChars: MutableMap<Char, Int>) {
        if (answer.isSameIndex(guess, index)) {
            tiles[index] = Tile.GREEN
            countOfChars.decreaseIfPresent(guess.value[index])
        }
    }

    private fun fillYellowTiles(tiles: MutableList<Tile>, countOfChars: MutableMap<Char, Int>) {
        for (index in 0 until answer.value.length) {
            fillYellowTile(tiles, index, countOfChars)
        }
    }

    private fun fillYellowTile(tiles: MutableList<Tile>, index: Int, countOfChars: MutableMap<Char, Int>) {
        if (isFillYellowTile(tiles, index, countOfChars)) {
            tiles[index] = Tile.YELLOW
            countOfChars.decreaseIfPresent(guess.value[index])
        }
    }

    private fun isFillYellowTile(tiles: MutableList<Tile>, index: Int, countOfChars: MutableMap<Char, Int>) =
        tiles[index].isGray()
                && countOfChars.containsKey(guess.value[index])
                && countOfChars[guess.value[index]] != 0

    private fun MutableMap<Char, Int>.decreaseIfPresent(key: Char) {
        computeIfPresent(key) { _, value -> value - 1 }
    }

    companion object {
        val DEFAULT_TILES = listOf(Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.GRAY)
    }
}
