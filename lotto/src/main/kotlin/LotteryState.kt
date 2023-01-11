import domain.Lotto

data class LotteryState(
    val manualLottos: List<Lotto>,
    val autoLottos: List<Lotto>
)
