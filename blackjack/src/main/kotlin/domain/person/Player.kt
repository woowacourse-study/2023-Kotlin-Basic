package domain.person

import domain.card.Card

class Player(
    val name: GamerName,
) : Gamer {

    val playCards: MutableList<Card> = mutableListOf()

    fun receiveCards(distribute: List<Card>): Boolean {
        playCards += distribute
        return canPlay()
    }

    fun showCard(count: Int): String =
        playCards.subList(0, count).joinToString(", ")

    fun showCardAll(): String =
        playCards.joinToString(", ")

    fun getMoreCard() {
        TODO("Not yet implemented")
    }

    fun canPlay(): Boolean {
        return playCards.sumOf { it.number.value } < 21
    }

    fun showFinalResult(): String =
        playCards.joinToString(", ") + " - " + "결과: " + playCards.sumOf { it.number.value }
}
