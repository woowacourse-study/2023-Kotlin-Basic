package domain

class WinningLotto(
    private val winningNumbers: Lotto,
    private val bonusBall: LottoNumber
) {
    init {
        require(!winningNumbers.contains(bonusBall)) { "당첨 번호와 보너스 볼은 중복될 수 없습니다." }
    }

    constructor(winningNumbers: List<Int>, bonusBall: Int) : this(Lotto.from(winningNumbers), LottoNumber(bonusBall))

    fun judgeRank(purchasedLotto: Lotto): Rank {
        fun hasBonusBall(): Boolean = purchasedLotto.contains(bonusBall)

        val count = winningNumbers.countMatches(purchasedLotto)

        return when {
            count == 6 -> Rank.FIRST
            count == 5 && hasBonusBall() -> Rank.SECOND
            count == 5 -> Rank.THIRD
            count == 4 -> Rank.FOURTH
            count == 3 -> Rank.FIFTH
            else -> Rank.FAILED
        }
    }
}
