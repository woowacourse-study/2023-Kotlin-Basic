package blackjack.view

enum class PlayCommand(
    val value: String,
) {

    YES("y"),
    NO("n")
    ;

    fun isYes(): Boolean {
        return this == YES
    }

    fun isNo(): Boolean {
        return this == NO
    }

    companion object {
        fun of(value: String): PlayCommand {
            return PlayCommand.values()
                .find { it.value == value }
                ?: throw IllegalArgumentException("존재하지 않는 명령어 입니다.")
        }
    }
}
