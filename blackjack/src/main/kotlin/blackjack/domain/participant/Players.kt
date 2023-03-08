package blackjack.domain.participant

private const val MINIMUM_PLAYER_SIZE = 1

class Players(
    names: Map<String, String>,
) {

    val value: List<Player> = names
        .map { Player(it.key, it.value) }
        .toList()

    init {
        require(value.size >= MINIMUM_PLAYER_SIZE) { "플레이어는 최소 1명입니다." }
    }

    fun acceptAll(accept: (Player) -> Unit) {
        for (player in value) {
            accept(player)
        }
    }
}
