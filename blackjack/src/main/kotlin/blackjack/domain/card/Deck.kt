package blackjack.domain.card

import java.util.*

class Deck private constructor(
    private val value: Queue<Card>,
) {

    constructor(generate: () -> List<Card>) : this(LinkedList(generate()))

    fun pick(): Card {
        require(value.isNotEmpty()) { "카드가 모두 소진되었습니다." }
        return value.poll()
    }
}
