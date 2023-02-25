import domain.card.CardDeck
import domain.person.Dealer
import domain.person.GamerName
import domain.person.Gamers
import domain.person.Player
import view.inputPlayerNames
import view.printAfterReceive
import view.printInitDistribution
import view.printResult

fun main() {
    val playerNames = inputPlayerNames()
    val gamers = initGamers(playerNames)

    val initDistributeResult = gamers.distribute()
    printInitDistribution(playerNames, initDistributeResult)

    do {
        val roundResult = gamers.playAfterInit()
        printAfterReceive(roundResult)
    } while (gamers.anyPlayerCanGetMoreCard() && roundResult.isNotEmpty())

    printResult(gamers.calculateResult())
}

fun initGamers(playerNames: List<String>): Gamers =
    Gamers.of(
        Dealer(GamerName("딜러"), CardDeck()),
        playerNames.map { Player(GamerName(it)) }
    )
