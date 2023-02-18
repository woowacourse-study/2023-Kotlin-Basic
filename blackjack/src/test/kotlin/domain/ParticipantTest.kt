package domain

import domain.Number.*
import domain.Suit.CLUB
import domain.Suit.DIAMOND
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

class ParticipantTest : ExpectSpec({

    context("딜러는 첫 카드패에 따라 행동이 제한된다") {

        expect("첫 카드패 숫자 합이 16 이하면, 카드를 더 뽑을 수 있고, 스탠드가 된다") {
            val cards = mutableListOf(Card(CLUB, TEN), Card(DIAMOND, SIX))
            val dealer = Dealer(Cards(cards))
            dealer.isFinished() shouldBe false

            shouldNotThrowAny { dealer.draw(Card(CLUB, NINE)) }
            dealer.isFinished() shouldBe true
        }

        expect("첫 카드패 숫자 합이 17 이상이면, 카드를 더 뽑을 수 없다") {
            val cards = mutableListOf(Card(CLUB, TEN), Card(DIAMOND, SEVEN))
            val dealer = Dealer(Cards(cards))
            dealer.isFinished() shouldBe true
            shouldThrow<IllegalStateException> { dealer.draw(Card(CLUB, ACE)) }
        }
    }
})
