package domain

import domain.Number.*
import domain.Suit.*
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf

class CardsTest : ExpectSpec({

    context("카드패 상태에 따라 Deck의 구현체를 생성한다") {
        expect("카드패가 블랙잭이다") {
            val cards = Cards(mutableListOf(Card(DIAMOND, ACE), Card(SPADE, KING)))
            cards.toDeck() shouldBe instanceOf<BlackJack>()
        }

        expect("카드패 숫자 합이 21을 초과해 버스트다") {
            val cards = Cards(mutableListOf(Card(DIAMOND, FOUR), Card(SPADE, EIGHT), Card(HEART, TEN)))
            cards.toDeck() shouldBe instanceOf<Bust>()
        }

        expect("두 경우를 제외하면 힛이다") {
            val cards = Cards(mutableListOf(Card(DIAMOND, FOUR), Card(SPADE, EIGHT), Card(HEART, NINE)))
            cards.toDeck() shouldBe instanceOf<Hit>()
        }
    }
})
