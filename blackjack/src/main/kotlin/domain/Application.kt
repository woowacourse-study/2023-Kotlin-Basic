package domain

fun main() {
    val playersName = inputNames()
    val playerBets = playersName.map { inputBetOf(it) }
    val game = BlackJackGame(playersName, playerBets)
    outputParticipantsState(game.participants())

    game.start()
    while (!game.allPlayersDone()) {
        val turn = game.turn
        val select = inputPlayerSelect(game.playerOf(turn).name)
        game.draw(select)
        if (select) outputParticipantState(game.playerOf(turn)) else println()
    }
    game.end()

    outputDealerState(game.dealer)
    outputParticipantsResult(game.participants())
}
