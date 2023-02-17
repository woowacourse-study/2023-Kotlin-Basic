package blackjack.domain.card

data class Card(
    val denomination: Denomination,
    val suit: Suit,
) {

    fun calculateScore(score: Int): Int {
        return denomination + score
    }

    fun isAce(): Boolean {
        return denomination.isAce()
    }
}
