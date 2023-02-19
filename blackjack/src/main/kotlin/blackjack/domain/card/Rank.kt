package blackjack.domain.card

enum class Rank(
    val symbol: String,
    val score: Int
) {
    ACE("A", 10), // 기본적으로 10점 취급, 버스트 시 1점 취급
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ;
}
