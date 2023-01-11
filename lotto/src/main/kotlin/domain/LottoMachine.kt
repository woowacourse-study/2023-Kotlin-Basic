package domain

object LottoMachine {
    private val lottoNumberPool = 1..45

    fun issueAutomatically(count: PurchaseCount): List<Lotto> = (1..count.value).map { issueRandomLotto() }

    private fun issueRandomLotto(): Lotto = Lotto.from(lottoNumberPool.shuffled().subList(0, 6))
}
