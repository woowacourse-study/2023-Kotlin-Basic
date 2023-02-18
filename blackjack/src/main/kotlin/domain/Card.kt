package domain

class Cards(
    private val cards: MutableList<Card>
) {
    fun sum(): Int {
        return cards.sumOf { it.number.value }
    }

    fun add(card: Card) {
        cards.add(card)
    }

    fun size(): Int {
        return cards.size
    }

    fun hasAce(): Boolean {
        return cards.any { it.number == Number.ACE }
    }

    fun toDeck(): Deck {
        if (cards.size == 2 && sum() == 11 && hasAce()) return BlackJack(this)
        if (sum() > 21) return Bust(this)
        return Hit(this)
    }
}

data class Card(
    val suit: Suit,
    val number: Number
) {
}

enum class Suit{
    SPADE,
    HEART,
    DIAMOND,
    CLUB
}

enum class Number(
    val value: Int
) {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    QUEEN(10),
    JACK(10)
}
