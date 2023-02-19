package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.game.GameResult
import blackjack.domain.game.WinDrawLose
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

private const val INPUT_DELIMITER = ","
private const val OUTPUT_DELIMITER = ", "

fun startGameView(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    return readln().split(INPUT_DELIMITER)
}

fun initialDealCardsView(
    dealer: Dealer,
    players: List<Player>,
) {
    println()

    println("딜러와 ${players.toNames()}에게 2장을 나누었습니다.")
    println("딜러: ${dealer.hand.cards.toNames()}")

    for (player in players) {
        println("${player.name}: ${player.hand.cards.toNames()}")
    }
}

fun playerHitOrStandView(
    player: Player
): Boolean {
    println()

    println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

    return when(readln()) {
        "y" -> true
        "n" -> false
        else -> throw IllegalArgumentException("y 또는 n 만 입력할 수 있습니다.")
    }
}

fun dealerHitView() {
    println()
    println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
}

fun participantsCardsAndScoreView(
    dealer: Dealer,
    players: List<Player>,
) {
    println()
    println("딜러 카드: ${dealer.hand.cards.toNames()} - 결과: ${dealer.score}")

    for (player in players) {
        println("${player.name} 카드: ${player.hand.cards.toNames()} - 결과: ${player.score}")
    }
}

fun finalResultView(
    gameResult: GameResult
) {
    println()
    println("## 최종 승패")

    print("딜러: ")
    with(gameResult.dealerResult.result) {
        if (this[WinDrawLose.WIN] != null) print("${this[WinDrawLose.WIN]}승 ")
        if (this[WinDrawLose.DRAW] != null) print("${this[WinDrawLose.DRAW]}무 ")
        if (this[WinDrawLose.LOSE] != null) print("${this[WinDrawLose.LOSE]}패")
    }

    println()

    for (entry in gameResult.playersResult.result) {
        println("${entry.key.name}: ${entry.value.koreanName}")
    }
}

private fun List<Player>.toNames() = this.joinToString(OUTPUT_DELIMITER) { it.name }

private fun Set<Card>.toNames() = this.joinToString(OUTPUT_DELIMITER) { it.toDisplayName() }

private fun Card.toDisplayName() = "${this.rank.symbol}${this.shape.koreanName}"
