package domain.card

class Card(
    val number: CardNumber,
    val suit: CardSuit,
) {

    override fun toString(): String {
        return "${number.value}${suit.shape}"
    }
}
