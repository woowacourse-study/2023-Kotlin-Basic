package domain

fun interface LottoStrategy {
    fun generateNumbers(): List<Int>
}

class AutoLottoStrategy : LottoStrategy {
    override fun generateNumbers(): List<Int> {
        return (1..45).toList().shuffled().subList(0, 6)
    }
}
