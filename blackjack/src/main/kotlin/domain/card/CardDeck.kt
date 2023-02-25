package domain.card

class CardDeck {

    var cards = mutableListOf<Card>()

    init {
        CardNumber.values().forEach { number ->
            CardSuit.values().forEach { suit ->
                cards.add(Card(number = number, suit = suit))
            }
        }

        cards.shuffle()
    }

    fun distribute(count: Int): List<Card> {
        val distributeCards = cards.slice(0..count - 1)
        cards = cards.slice(count..cards.lastIndex).toMutableList()
        return distributeCards
    }
}
