package domain

class Cards(
    val cards: MutableList<Card>
) {
    init {
        require(cards == cards.distinct()) { "중복되는 카드로 구성할 수 없습니다" }
    }

    fun sum() = cards.sumOf { it.number.value }

    fun add(card: Card) { cards.add(card) }

    fun size() = cards.size

    fun hasAce() = cards.any { it.number == Number.ACE }
}

class PlayingCards {

    private val playingCards = Suit.values().flatMap { suit ->
        Number.values().map { number -> Card(suit, number) }
    }.shuffled().toMutableList()

    fun hand(): Cards {
        val hand = playingCards.take(2)
        playingCards.removeAll(hand)
        return Cards(hand.toMutableList())
    }

    fun pop() = playingCards.removeFirst()
}

data class Card(
    val suit: Suit,
    val number: Number
) {}

enum class Suit {
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
