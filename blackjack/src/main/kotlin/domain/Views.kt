package domain

import domain.Number.*
import domain.Suit.*

fun inputNames(): List<String> {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    return readln().split(",").map { it.trim() }
}

fun inputBetOf(name: String): Int {
    println("${name}의 베팅 금액은?")
    return try {
        readln().toInt()
    } catch (e: NumberFormatException) {
        println("숫자만 입력 가능합니다")
        inputBetOf(name)
    }
}

fun inputPlayerSelect(name: String): Boolean {
    println("$name 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    val select = readln()
    return try {
        when (select) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException()
        }
    } catch (e: IllegalArgumentException) {
        println("y 또는 n으로 응답하세요")
        inputPlayerSelect(name)
    }
}

fun outputParticipantsState(participants: List<Participant>) {
    println()
    participants.forEach { outputParticipantState(it) }
}

fun outputParticipantState(participant: Participant) =
    println(participant.name + ": " + participant.showCards().joinToString(separator = ", ") { cardToString(it) })

fun outputDealerState(dealer: Dealer) {
    if (dealer.deck.cards().size == 3) println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다\n")
}

fun outputParticipantsResult(participants: List<Participant>) {
    println()
    participants.forEach { outputParticipantResult(it.name, it.showCards(), it.deck.score()) }
    println("\n최종 수익")
    participants.forEach { println(it.name + ": " + it.earning()) }
}

fun outputParticipantResult(name: String, cards: List<Card>, score: Int) =
    println(name + ": " + cards.joinToString(separator = ", ") { cardToString(it) } + " - 결과: " + score)

private fun cardToString(card: Card): String {

    val number = when (card.number) {
        KING -> "K"
        QUEEN -> "Q"
        JACK -> "J"
        else -> card.number.value.toString()
    }

    val suit = when (card.suit) {
        SPADE -> "스페이드"
        HEART -> "하트"
        DIAMOND -> "다이아몬드"
        CLUB -> "클로버"
    }

    return number + suit
}
