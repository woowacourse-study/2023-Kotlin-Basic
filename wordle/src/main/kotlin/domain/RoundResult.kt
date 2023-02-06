package domain

data class RoundResult(val tiles: MutableList<Tile>) : Iterable<Tile> {

    val isAnswer: Boolean
        get() {
            return tiles.all { it == Tile.GREEN }
        }

    fun markGreenAt(index: Int) {
        tiles[index] = Tile.GREEN
    }

    fun markYellowAt(index: Int) {
        tiles[index] = Tile.YELLOW
    }

    operator fun get(index: Int): Tile {
        return tiles[index]
    }

    override fun iterator(): Iterator<Tile> {
        return tiles.iterator()
    }

    companion object {
        fun initTile(): RoundResult {
            return RoundResult(mutableListOf<Tile>(Tile.GREY, Tile.GREY, Tile.GREY, Tile.GREY, Tile.GREY))
        }
    }
}
