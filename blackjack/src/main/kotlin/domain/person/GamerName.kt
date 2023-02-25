package domain.person

import java.lang.IllegalArgumentException

class GamerName(
    val value: String
) {

    init {
        validate(value)
    }

    private fun validate(value: String) {
        if (value.isBlank()) {
            throw IllegalArgumentException("이름은 공백 또는 빈칸이면 안됩니다.")
        }
    }

    override fun toString(): String {
        return "$value"
    }
}
