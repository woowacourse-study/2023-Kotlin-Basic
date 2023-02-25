package domain

import domain.Odds.*

sealed interface Deck {

    fun draw(card: Card): Deck

    fun stay(): Finish

    fun score(): Int

    fun isFinished(): Boolean

    fun getOdd(other: Finish): Odds
}

sealed class Running(val cards: Cards) : Deck {

    override fun isFinished() = false

    override fun getOdd(other: Finish) = throw IllegalStateException("아직 승률을 구할 수 없습니다")
}

sealed class Finish(val cards: Cards) : Deck {

    override fun stay() = this

    override fun draw(card: Card) = throw IllegalStateException("더이상 카드를 추가할 수 없습니다")

    override fun isFinished() = true
}

class Started(private val cards: Cards) {

    init {
        require(cards.size() == 2) { "두 장의 카드로 시작해야 합니다" }
    }

    fun toDeck() = if (cards.size() == 2 && cards.sum() == 11 && cards.hasAce()) BlackJack(cards) else Hit(cards)
}

class Hit(cards: Cards) : Running(cards) {

    override fun draw(card: Card): Deck {
        cards.add(card)
        return if (cards.sum() > 21) Bust(cards) else this
    }

    override fun stay() = Stand(cards)

    override fun score() = cards.sum()
}

class Stand(cards: Cards) : Finish(cards) {

    override fun score(): Int {
        return if (cards.hasAce() && cards.sum() + 10 <= 21) cards.sum() + 10 else cards.sum()
    }

    override fun getOdd(other: Finish): Odds {
        if (other is BlackJack || score() < other.score()) {
            return LOSE
        }
        if (score() > other.score()) {
            return WIN
        }
        return DRAW
    }
}

class Bust(cards: Cards) : Finish(cards) {

    override fun score() = 0

    override fun getOdd(other: Finish) = if (other is Bust) WIN else LOSE
}

class BlackJack(cards: Cards) : Finish(cards) {

    override fun score() = 21

    override fun getOdd(other: Finish) = if (other is BlackJack) WIN else BLACKJACK_WIN
}
