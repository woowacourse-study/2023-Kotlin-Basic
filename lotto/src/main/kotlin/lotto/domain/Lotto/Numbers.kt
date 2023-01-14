package lotto.domain.Lotto

object Numbers {

        private val values = (1..45)

        fun getShuffledNumbers(count: Int): List<Int> =
            values.shuffled().subList(0, count).toList()
}
