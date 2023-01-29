package domain

import domain.Tile.GREEN

data class Tiles(
    val values: List<Tile>
) {
    val isCorrect
        get() = values.all { it == GREEN }
}
