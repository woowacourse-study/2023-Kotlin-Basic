package domain

import domain.Number.*
import domain.Suit.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

class DeckTest : ExpectSpec({

    context("Hit 상태 행동을 검증한다") {

        expect("카드를 뽑을 때 합이 21을 초과하면 버스트 상태를 반환한다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT))
            val hit = Hit(Cards(cards))
            hit.draw(Card(CLUB, NINE)) shouldBe instanceOf<Bust>()
        }

        expect("카드를 뽑을 때 합이 21 이하면 힛 상태를 반환한다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT))
            val hit = Hit(Cards(cards))
            hit.draw(Card(CLUB, EIGHT)) shouldBe instanceOf<Hit>()
        }

        expect("스테이하면 스탠드 상태를 반환한다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT))
            val hit = Hit(Cards(cards))
            hit.stay() shouldBe instanceOf<Stand>()
        }
    }

    context("Stand 상태 행동을 검증한다") {

        expect("카드를 더 뽑을 수 없다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT))
            val stand = Stand(Cards(cards))
            shouldThrow<IllegalStateException> { stand.draw(Card(CLUB, FIVE)) }
        }

        expect("스테이하면 스탠드 상태를 반환한다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT))
            val stand = Stand(Cards(cards))
            stand.stay() shouldBe instanceOf<Stand>()
        }
    }

    context("Bust 상태 행동을 검증한다") {

        expect("카드를 더 뽑을 수 없다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT), Card(HEART, TEN))
            val bust = Bust(Cards(cards))
            shouldThrow<IllegalStateException> { bust.draw(Card(CLUB, ACE)) }
        }

        expect("점수는 0점 처리된다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT), Card(HEART, TEN))
            val bust = Bust(Cards(cards))
            bust.score() shouldBe 0
        }

        expect("스테이하면 버스트 상태를 유지한다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT), Card(HEART, TEN))
            val bust = Bust(Cards(cards))
            bust.stay() shouldBe instanceOf<Bust>()
        }
    }

    context ("블랙잭 상태 행동을 검증한다") {

        expect("카드를 더 뽑을 수 없다") {
            val cards = mutableListOf(Card(DIAMOND, ACE), Card(SPADE, KING))
            val blackJack = BlackJack(Cards(cards))
            shouldThrow<IllegalStateException> { blackJack.draw(Card(CLUB, ACE)) }
        }

        expect("점수는 21점이다") {
            val cards = mutableListOf(Card(DIAMOND, ACE), Card(SPADE, KING))
            val blackJack = BlackJack(Cards(cards))
            blackJack.score() shouldBe 21
        }

        expect("스테이하면 블랙잭 상태 그대로다") {
            val cards = mutableListOf(Card(DIAMOND, ACE), Card(SPADE, KING))
            val blackJack = BlackJack(Cards(cards))
            blackJack.stay() shouldBe instanceOf<BlackJack>()
        }
    }
})
