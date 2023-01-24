package domain

data class RoundResult(val tiles: MutableList<Tile>) {
    companion object {
        fun initTile(): RoundResult {
            return RoundResult(mutableListOf<Tile>(Tile.GREY, Tile.GREY, Tile.GREY, Tile.GREY, Tile.GREY))
        }
    }

    val isAnswer: Boolean
        get() {
            return tiles.all { tile -> tile == Tile.GREEN }
        }

    operator fun get(index: Int): Tile {
        return tiles[index]
    }

    fun markGreenAt(index: Int) {
        tiles[index] = Tile.GREEN
    }
}
