package domain

object LottoMachine {
    private val lottoNumberPool = (1..45)

    fun issueAutomatically(count: Int): List<Lotto> {
        val lotteries = mutableListOf<Lotto>()
        for (i in 1..count) {
            lotteries.add(issueRandomLotto())
        }
        return lotteries
    }

    private fun issueRandomLotto(): Lotto = Lotto.from(lottoNumberPool.shuffled().subList(0, 6))
}
