package domain

import domain.Number.*
import domain.Suit.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

class DeckTest : ExpectSpec({

    context(" Started는 카드패 상태에 따라 Deck의 구현체를 생성한다") {

        expect("카드패가 블랙잭이다") {
            val cards = Cards(mutableListOf(Card(DIAMOND, ACE), Card(SPADE, KING)))
            Started(cards).toDeck() shouldBe instanceOf<BlackJackGame>()
        }

        expect("블랙잭이 아니라면 힛이다") {
            val cards = Cards(mutableListOf(Card(DIAMOND, ACE), Card(SPADE, NINE)))
            Started(cards).toDeck() shouldBe instanceOf<Hit>()
        }

        expect("카드가 2장이 아니라면 생성할 수 없다") {
            val lessCards = Cards(mutableListOf(Card(DIAMOND, ACE)))
            shouldThrow<IllegalArgumentException> { Started(lessCards).toDeck() }

            val moreCards = Cards(mutableListOf(Card(DIAMOND, ACE), Card(SPADE, NINE), Card(HEART, TWO)))
            shouldThrow<IllegalArgumentException> { Started(moreCards).toDeck() }
        }
    }

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

        context("점수를 메길 때 ACE가 있다면, 1과 11중에 유리한 점수로 메긴다") {

            expect("ACE를 11로 할 때, 점수가 21 이하라면 11로 계산한다") {
                val cards = mutableListOf(Card(DIAMOND, ACE), Card(SPADE, THREE), Card(HEART, SEVEN))
                val stand = Stand(Cards(cards))
                stand.score() shouldBe 21
            }

            expect("ACE를 11로 할 때, 점수가 21 초과라면 1로 계산한다") {
                val cards = mutableListOf(Card(DIAMOND, ACE), Card(SPADE, THREE), Card(HEART, EIGHT))
                val stand = Stand(Cards(cards))
                stand.score() shouldBe 12
            }
        }
    }

    context("Bust 상태 행동을 검증한다") {

        expect("점수는 0점 처리된다") {
            val cards = mutableListOf(Card(DIAMOND, FIVE), Card(SPADE, EIGHT), Card(HEART, TEN))
            val bust = Bust(Cards(cards))
            bust.score() shouldBe 0
        }
    }

    context ("블랙잭 상태 행동을 검증한다") {

        expect("점수는 21점이다") {
            val cards = mutableListOf(Card(DIAMOND, ACE), Card(SPADE, KING))
            val blackJack = BlackJack(Cards(cards))
            blackJack.score() shouldBe 21
        }
    }
})
