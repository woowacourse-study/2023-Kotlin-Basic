package domain

class BlackJackGame(
    playersName: List<String>,
    playersBet: List<Int>
) {
    private val playingCards = PlayingCards()
    private val players = playersName.indices.map {
        Player(playersName[it], playersBet[it], playingCards.hand())
    }
    val dealer = Dealer(playingCards.hand())
    var turn = 0

    fun start() {
        players.forEach { dealer.deposit(it.bet) }
        while (players[turn].isFinished()) turn += 1
    }

    fun allPlayersDone() = players.all { it.isFinished() }

    fun draw(select: Boolean) {
        if (select) players[turn].draw(playingCards.pop()) else players[turn].finish()
        while (players[turn].isFinished() && turn < players.size - 1) turn += 1
    }

    fun end() {
        if (!dealer.isFinished()) dealer.draw(playingCards.pop())
        dealer.end()

        players.forEach {
            val winning = it.winning(dealer)
            dealer.withdraw(winning)
        }
    }

    fun playerOf(turn: Int) = players[turn]

    fun participants() = players + dealer
}
