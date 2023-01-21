package domain

data class JudgementTile(
    val colors: List<Color>
) {

    init {
        require(colors.size == Word.LENGTH) { "타일은 ${Word.LENGTH}칸이어야 합니다." }
    }

    fun isAllGreen(): Boolean = colors.all { it == Color.GREEN }
}
