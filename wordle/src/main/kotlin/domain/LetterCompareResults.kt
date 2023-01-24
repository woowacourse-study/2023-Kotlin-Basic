package domain

import domain.LetterCompareResult.GREEN

data class LetterCompareResults(
    val values: List<LetterCompareResult>
) {
    val isCorrect
        get() = values.all { it == GREEN }
}
