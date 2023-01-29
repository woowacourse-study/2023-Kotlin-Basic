package wordle.domain

private const val LIMIT_SIZE = 6

class Judgements(
    private val value: MutableList<Judgement> = mutableListOf(),
) {

    val isPlaying
        get() = value.size < LIMIT_SIZE

    val isEnd
        get() = value.isAllGreenTiles()

    private fun List<Judgement>.isAllGreenTiles() = map { it.judge() }
        .any { it.all(Tile::isGreen) }

    fun add(judgement: Judgement) {
        value.add(judgement)
    }

    fun judgeAll() = value.map(Judgement::judge)
}
