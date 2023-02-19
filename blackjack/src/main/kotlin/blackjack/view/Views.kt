package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Name
import blackjack.domain.participant.Player

private const val SPLIT_REGEX = ","
private val NEW_LINE = System.lineSeparator()

/**
 * input
 */
fun getNames(): Map<String, String> {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    return generateNames(readln().split(SPLIT_REGEX.toRegex()))
}

private fun generateNames(splitNames: List<String>): Map<String, String> {
    val names: MutableMap<String, String> = HashMap()
    for (name in splitNames) {
        println("${NEW_LINE}${name} 의 배팅 금액은?")
        val money = readln()
        validateNumeric(money)
        names[name] = money
    }
    return names
}

private fun validateNumeric(input: String) {
    require(isAllDigit(input)) { "정수만 가능합니다." }
}

private fun isAllDigit(input: String): Boolean {
    return input.all { it.isDigit() }
}

fun getPlayCommand(player: Player): PlayCommand {
    println("${player.name.value}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    return PlayCommand.of(readln())
}

/**
 * output
 */
fun printStart(dealer: Dealer, players: List<Player>) {
    println("${NEW_LINE}딜러와 ${generateNames(players)}에게 2장씩 나누었습니다.")
    printDealerCard(dealer)
    players.forEach { printPlayerCard(it) }
    println()
}

private fun generateNames(players: List<Player>): String {
    return players.map(Player::name)
        .map(Name::value)
        .joinToString(", ")
}

private fun printDealerCard(dealer: Dealer) {
    println("${dealer.name.value} 카드: ${generateCardMessage(dealer.cards[0])}")
}

fun printPlayerCard(player: Player) {
    println("${player.name.value} 카드: ${generateCardsMessage(player.cards)}")
}

private fun generateCardsMessage(cards: List<Card>): String {
    return cards.joinToString(", ") { generateCardMessage(it) }
}

private fun generateCardMessage(card: Card): String {
    return card.denomination.value + card.suit.value
}

fun printDealerDrawable() {
    println("${NEW_LINE}딜러는 16이하라 한장의 카드를 더 받았습니다.")
}

fun printResult(dealer: Dealer, players: List<Player>) {
    println()
    printDealerResult(dealer)
    players.forEach { printPlayerResult(it) }
}

private fun printDealerResult(dealer: Dealer) {
    val cardsInfo = generateCardsMessage(dealer.cards)
    println("딜러: ${cardsInfo} - 결과: ${dealer.totalScore}")
}

private fun printPlayerResult(player: Player) {
    val cardsInfo = generateCardsMessage(player.cards)
    println("${player.name.value}: ${cardsInfo} - 결과: ${player.totalScore}")
}

fun printProfit(dealerProfit: String, players: List<PlayerResult>) {
    println("$NEW_LINE## 최종 수익")
    println("딜러: $dealerProfit")
    players.forEach { println("${it.name}: ${it.profit}") }
}
