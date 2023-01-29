package wordle.domain

enum class Tile(
    val symbol: String,
) {

    GREEN("🟩"),
    YELLOW("🟨"),
    GRAY("⬜");

    val isGreen
        get() = this == GREEN

    val isGray
        get() = this == GRAY
}
