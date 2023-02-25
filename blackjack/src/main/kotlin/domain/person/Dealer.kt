package domain.person

import domain.card.Card
import domain.card.CardDeck

class Dealer(
    val name: GamerName,
    val gameCardDeck: CardDeck,
) : Gamer {

    val playCards: MutableList<Card> = mutableListOf()

    fun distributeInitCards(players: List<Player>) = with(StringBuilder()) {
        receiveCards(gameCardDeck.distribute(2))
        append("$name: ${showCard(1)}\n")
        players.forEach {
            it.receiveCards(gameCardDeck.distribute(2))
            append("${it.name}: ${it.showCard(2)}\n")
        }
        toString()
    }

    fun receiveCards(distribute: List<Card>) {
        playCards += distribute
    }

    fun showCard(count: Int): String =
        playCards.subList(0, count).joinToString { playCards[0].toString() }

    fun getMoreCard(): Boolean {
        receiveCards(gameCardDeck.distribute(1))
        if (playCards.sumOf { it.number.value } > 21) {
            return true
        }
        return false
    }

    fun distributeAfterInitCard(): List<Card> {
        return gameCardDeck.distribute(1)
    }

    fun receiveCard() {
        if (shouldGetMore()) {
            receiveCards(gameCardDeck.distribute(1))
        }
    }

    fun shouldGetMore(): Boolean {
        return playCards.sumOf { it.number.value } <= 16
    }

    fun showFinalResult(): String =
        playCards.joinToString(", ") + " - " + "결과: " + playCards.sumOf { it.number.value }
}
