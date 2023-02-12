package blackjack.domain.card

data class Card(
    private val denomination: Denomination,
    private val suit: Suit,
) {
}
