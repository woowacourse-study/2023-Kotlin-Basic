package blackjack.domain.participant

data class Name(
    val value: String
) {

    init {
        require(value.isNotBlank()) { "이름은 공백이 될 수 없습니다. (${value})" }
    }
}
