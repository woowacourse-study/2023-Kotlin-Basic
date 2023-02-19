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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (rank != other.rank) return false
        if (shape != other.shape) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rank.hashCode()
        result = 31 * result + shape.hashCode()
        return result
    }
}
