import domain.Lotto

data class LottosDto(
    val manualLottos: List<Lotto>,
    val autoLottos: List<Lotto>
)
