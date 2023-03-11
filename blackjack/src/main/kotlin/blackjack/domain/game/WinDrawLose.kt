package blackjack.domain.game

enum class WinDrawLose(
    val koreanName: String
) {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;
}
