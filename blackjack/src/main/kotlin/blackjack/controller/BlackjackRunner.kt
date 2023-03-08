package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.card.randomGenerate
import blackjack.domain.participant.*
import blackjack.view.*

private const val REVERSE_MONEY = -1.0

class BlackjackRunner {

    fun run() {
        val deck = Deck { randomGenerate() }
        val dealer = Dealer()
        val players = Players(getNames())
        ready(deck, dealer, players.value)
        printStart(dealer, players.value)
        players.acceptAll { playing(deck, it, PlayCommand.YES) }
        drawDealer(deck, dealer)
        val playersValue: List<Player> = players.value

        printResult(dealer, playersValue)
        printProfit(getDealerProfit(dealer, playersValue), createPlayerResults(dealer, playersValue))
    }

    private fun ready(deck: Deck, dealer: Dealer, players: List<Player>) {
        readyParticipant(deck, dealer)
        players.forEach { readyParticipant(deck, it) }
    }

    private fun readyParticipant(deck: Deck, participant: Participant) {
        if (!participant.isReady()) {
            participant.hit(deck.pick())
            readyParticipant(deck, participant)
        }
    }

    private fun playing(deck: Deck, player: Player, playCommand: PlayCommand) {
        var playCommand = playCommand
        if (isPlaying(player, playCommand)) {
            playCommand = getPlayCommand(player)
            pickCard(deck, player, playCommand)
            playing(deck, player, playCommand)
        }
    }

    private fun isPlaying(player: Player, playCommand: PlayCommand): Boolean {
        return player.isDrawable() && playCommand.isYes()
    }

    private fun pickCard(deck: Deck, player: Player, playCommand: PlayCommand) {
        when {
            playCommand.isYes() -> {
                player.hit(deck.pick())
                printPlayerCard(player)
            }
            playCommand.isNo() -> {
                player.stay()
            }
        }
    }

    private fun drawDealer(deck: Deck, dealer: Dealer) {
        if (dealer.isDrawable()) {
            dealer.hit(deck.pick())
            printDealerDrawable()
            drawDealer(deck, dealer)
        }
    }

    private fun getDealerProfit(dealer: Dealer, players: List<Player>): String {
        return players.map { it.calculateProfit(dealer) * REVERSE_MONEY }
            .sumOf(BettingMoney::value)
            .toString()
    }

    private fun createPlayerResults(dealer: Dealer, players: List<Player>): List<PlayerResult> {
        return players.map { PlayerResult(it.name.value, it.calculateProfit(dealer).value.toString()) }
            .toList()
    }
}
