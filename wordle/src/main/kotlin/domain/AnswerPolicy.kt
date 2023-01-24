package domain

fun interface AnswerPolicy {
    fun getWord(): String
}

class FromFileByDate: AnswerPolicy {

    override fun getWord(): String {
        TODO("Not yet implemented")
    }

}
