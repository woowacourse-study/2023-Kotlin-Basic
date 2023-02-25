package domain.person

import view.inputPlayerMoreCards

class Gamers(
    val dealer: Dealer,
    val players: List<Player>
) {

    fun distribute(): String =
        dealer.distributeInitCards(players)

    fun playAfterInit(): MutableMap<String, String> {
        val result = mutableMapOf<String, String>()
        players
            .filter { it.canPlay() }
            .forEach {
                if (inputPlayerMoreCards(it)) {
                    it.receiveCards(dealer.distributeAfterInitCard())
                    result[it.name.value] = it.showCardAll()
                }
            }

        dealer.receiveCard()
        return result
    }

    fun anyPlayerCanGetMoreCard(): Boolean =
        players.any { it.canPlay()}

    fun calculateResult(): MutableMap<String, String> {
        val result = linkedMapOf<String, String>()
        result[dealer.name.value] = dealer.showFinalResult()
        players
            .forEach {
                result[it.name.value] = it.showFinalResult()
            }
        return result
    }

    companion object {

        fun of(dealer: Dealer, players: List<Player>): Gamers =
            Gamers(
                dealer = dealer,
                players = players,
            )
    }
}
