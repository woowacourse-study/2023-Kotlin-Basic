package domain

enum class Odds(
    val ratio: Double
) {
    BLACKJACK_WIN(2.5),
    WIN(2.0),
    DRAW(1.0),
    LOSE(0.0)
}
