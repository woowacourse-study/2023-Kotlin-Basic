package domain

enum class LetterCompareResult(
    val character: String
) {
    GREEN("\uD83D\uDFE9"),
    YELLOW("\uD83D\uDFE8"),
    GRAY("⬜"),
}
