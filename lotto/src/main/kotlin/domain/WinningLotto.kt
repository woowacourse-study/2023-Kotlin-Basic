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
        fun judgeSecondPrize(): Rank {
            if (purchasedLotto.contains(bonusBall)) {
                return Rank.SECOND
            }
            return Rank.THIRD
        }

        return when (winningNumbers.countMatches(purchasedLotto)) {
            6 -> Rank.FIRST
            5 -> judgeSecondPrize()
            4 -> Rank.FOURTH
            3 -> Rank.FIFTH
            else -> Rank.FAILED
        }
    }
}
