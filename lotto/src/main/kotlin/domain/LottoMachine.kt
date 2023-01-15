package domain

object LottoMachine {
    private val lottoNumberPool = LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE

    fun issueAutomatically(count: PurchaseCount): List<Lotto> = (1..count.value).map { issueRandomLotto() }

    private fun issueRandomLotto(): Lotto = Lotto.from(lottoNumberPool.shuffled().subList(0, Lotto.NUMBERS_SIZE))
}
