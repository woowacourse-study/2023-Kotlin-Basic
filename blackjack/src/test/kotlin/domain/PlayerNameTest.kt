package domain

import domain.person.GamerName
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row

internal class PlayerNameTest : StringSpec({

    "should throw exception when name is blank" {
        forAll(
            row(""),
            row(" "),
        ) { name ->
            shouldThrow<IllegalArgumentException> {
                GamerName(name)
            }
        }
    }
})
