package lotto.domain.Lotto

class Numbers {

    companion object {
        private val values = mutableListOf<Number>().also {
            for (i in 1..45)
                it.add(Number(i))
        }

        fun getShuffledNumbers(count: Int): MutableList<Number> =
            values.shuffled().subList(0, count).toMutableList()
    }
}
