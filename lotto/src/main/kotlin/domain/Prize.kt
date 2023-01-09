package domain

enum class Prize(
    private val hits: Int,
    val prize: Int
) {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FORTH(4, 50_000),
    FIFTH(3, 5_000),
    FAIL(0, 0),
    ;

    companion object {
        fun match(hits: Int, bonusHit: Boolean): Prize {
            val prize = values().firstOrNull() { it.hits == hits } ?: FAIL
            return if (prize == SECOND && !bonusHit) THIRD else prize
        }
    }
}
