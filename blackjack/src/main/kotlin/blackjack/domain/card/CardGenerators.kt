package blackjack.domain.card

fun randomGenerate(): List<Card> {
    return Denomination.values()
        .map(::createIntactCards)
        .flatten()
        .shuffled()
        .toList()
}

private fun createIntactCards(denomination: Denomination): Set<Card> {
    return Suit.values()
        .map { Card(denomination, it) }
        .toSet()
}
