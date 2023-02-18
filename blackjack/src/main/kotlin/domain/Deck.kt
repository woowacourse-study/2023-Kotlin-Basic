package domain

sealed interface Deck {

    fun draw(card: Card): Deck

    fun score(): Int

    fun stay(): Deck
}

class Hit(
    private val cards: Cards
) : Deck {

    override fun draw(card: Card): Deck {
        cards.add(card)
        return if (cards.sum() > 21) Bust(cards) else this
    }

    override fun score(): Int {
        return cards.sum()
    }

    override fun stay(): Deck {
        return Stand(cards)
    }
}

class Stand(
    private val cards: Cards
) : Deck {

    override fun draw(card: Card): Deck {
        throw IllegalStateException("카드를 더 추가할 수 없습니다")
    }

    override fun score(): Int {
        return cards.sum()
    }

    override fun stay(): Deck {
        return this
    }
}

class Bust(
    private val cards: Cards
) : Deck {

    override fun draw(card: Card): Deck {
        throw IllegalStateException("카드를 더 추가할 수 없습니다")
    }

    override fun score(): Int {
        return 0
    }

    override fun stay(): Deck {
        return this
    }
}

class BlackJack(
    private val cards: Cards
) : Deck {

    override fun draw(card: Card): Deck {
        throw IllegalStateException("카드를 더 추가할 수 없습니다")
    }

    override fun score(): Int {
        return 21
    }

    override fun stay(): Deck {
        return this
    }
}
