package wordle.domain

enum class Tile(
    val symbol: String,
) {

    GREEN("🟩"),
    YELLOW("🟨"),
    GRAY("⬜");

    fun isGray() = this == GRAY
}
