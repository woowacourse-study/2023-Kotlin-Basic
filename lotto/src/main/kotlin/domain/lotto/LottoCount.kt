package domain.lotto

data class LottoCount(
    val value: Int
) {
    init {
        require(value >= 0) { "로또 개수가 음수일 수 없습니다." }
    }
}
