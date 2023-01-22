package wordle.domain

enum class Tile(
    val symbol: String,
) {

    GREEN("🟩"),
    YELLOW("🟨"),
    GRAY("⬜");

    fun isGreen() = this == GREEN

    fun isGray() = this == GRAY
}
