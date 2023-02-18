package domain

sealed interface Deck {

    fun draw(card: Card): Deck

    fun score(): Int

    fun stay(): Deck

    fun isFinished(): Boolean
}

class Hit(
    private val cards: Cards
) : Deck {

    override fun draw(card: Card): Deck {
        cards.add(card)
        return if (cards.sum() > 21) Bust(cards) else this
    }

    override fun score() = cards.sum()

    override fun stay(): Deck = Stand(cards)

    override fun isFinished() = false
}

class Stand(
    private val cards: Cards
) : Deck {

    override fun draw(card: Card): Deck = throw IllegalStateException("카드를 더 추가할 수 없습니다")

    override fun score() = cards.sum()

    override fun stay(): Deck = this

    override fun isFinished() = true
}

class Bust(
    private val cards: Cards
) : Deck {

    override fun draw(card: Card): Deck = throw IllegalStateException("카드를 더 추가할 수 없습니다")

    override fun score() = 0

    override fun stay() = this

    override fun isFinished() = true
}

class BlackJack(
    private val cards: Cards
) : Deck {

    override fun draw(card: Card): Deck = throw IllegalStateException("카드를 더 추가할 수 없습니다")

    override fun score() = 21

    override fun stay() = this

    override fun isFinished() = true
}
