package blackjack.domain.card

private const val BLACKJACK_SCORE = 21
private const val BONUS_ACE_SCORE = 11

enum class Denomination(
    val score: Int,
    val value: String,
) {

    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K")
    ;

    operator fun plus(score: Int): Int {
        if (this == ACE) {
            return calculateAceScore(score)
        }

        return this.score + score
    }

    private fun calculateAceScore(score: Int): Int {
        if ((score + BONUS_ACE_SCORE) > BLACKJACK_SCORE) {
            return score + ACE.score
        }

        return score + BONUS_ACE_SCORE
    }
}
