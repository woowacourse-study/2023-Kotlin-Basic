package blackjack.domain

class Card private constructor(
    val rank: Rank,
    val shape: Shape,
) {
    companion object {
        private val cache = mutableSetOf<Card>()

        fun from(rank: Rank, shape: Shape): Card {
            return cache.find { it.rank == rank && it.shape == shape }
                ?: Card(rank, shape).also { cache.add(it) }
        }
    }
}
