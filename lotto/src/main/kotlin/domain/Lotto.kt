package domain

class WinningLotto(private val lotto: Lotto, private val bonus: Ball) {
    init {
        require(!lotto.contains(bonus)) { "보너스 숫자는 로또 번호와 중복될 수 없습니다" }
    }

    fun score(other: Lotto): Prize {
        val hits = lotto.match(other)
        val bonusHit = other.contains(bonus)
        return Prize.match(hits, bonusHit)
    }
}

class Lotto(strategy: LottoStrategy) {
    private val balls: Set<Ball> = strategy.generateNumbers().map { Ball(it) }.toSet()

    init {
        require(balls.distinct().size == 6) { "로또는 중복되지 않는 6개 숫자로 구성되어야 합니다" }
    }

    fun contains(ball: Ball): Boolean {
        return balls.contains(ball)
    }

    fun match(other: Lotto): Int {
        return balls.count { other.balls.contains(it) }
    }

    fun state(): String {
        return balls.sortedBy { it.number }
            .joinToString(separator = ", ", transform = { it.number.toString() })
    }

    companion object {
        const val PRICE = 1_000
    }
}

data class Ball(val number: Int) {
    init {
        require(number in 1..45) { "로또 숫자는 1 이상 45 이하입니다" }
    }
}
