package blackjack.domain.card

class Card private constructor(
    val rank: Rank,
    val shape: Shape,
) {
    val score: Int
        get() = rank.score

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

    override fun toString(): String {
        return "Card(rank=$rank, shape=$shape)"
    }
}

fun Collection<Card>.hasAce(): Boolean = this.any { it.rank == Rank.ACE }

fun Collection<Card>.aceCount(): Int = this.count { it.rank == Rank.ACE }
