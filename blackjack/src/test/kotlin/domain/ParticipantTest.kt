package domain

import domain.Number.*
import domain.Suit.*
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

    context("플레이어는 딜러의 패와 비교해 받을 금액을 알 수 있다") {

        context("플레이어가 블랙잭일 때") {

            expect("플레이어만 블랙잭이면 1.5배의 추가 금액을 받는다") {
                val playerCards = mutableListOf(Card(CLUB, ACE), Card(DIAMOND, QUEEN))
                val player = Player("cat", 10_000, Cards(playerCards))

                val dealerCards = mutableListOf(Card(CLUB, EIGHT), Card(HEART, QUEEN))
                val dealer = Dealer(Cards(dealerCards))

                player.winning(dealer) shouldBe 25_000
            }

            expect("플레이어와 딜러 둘 다 블랙잭이면 1배의 추가 금액을 받는다") {
                val playerCards = mutableListOf(Card(CLUB, ACE), Card(DIAMOND, QUEEN))
                val player = Player("cat",10_000, Cards(playerCards))

                val dealerCards = mutableListOf(Card(DIAMOND, ACE), Card(HEART, JACK))
                val dealer = Dealer(Cards(dealerCards))

                player.winning(dealer) shouldBe 20_000
            }
        }


        context("플레이어와 딜러 둘 다 스탠드일 때") {

            expect("플레이어의 숫자 합이 더 크면 1배의 추가 금액을 받는다") {
                val playerCards = mutableListOf(Card(CLUB, NINE), Card(DIAMOND, QUEEN))
                val player = Player("cat",10_000, Cards(playerCards))
                player.finish()

                val dealerCards = mutableListOf(Card(DIAMOND, EIGHT), Card(HEART, JACK))
                val dealer = Dealer(Cards(dealerCards))

                player.winning(dealer) shouldBe 20_000
            }

            expect("딜러의 숫자 합이 더 크면 돈을 받을 수 없다") {
                val playerCards = mutableListOf(Card(CLUB, SEVEN), Card(DIAMOND, QUEEN))
                val player = Player("cat",10_000, Cards(playerCards))
                player.finish()

                val dealerCards = mutableListOf(Card(DIAMOND, EIGHT), Card(HEART, JACK))
                val dealer = Dealer(Cards(dealerCards))

                player.winning(dealer) shouldBe 0
            }
        }

        context("플레이어가 버스트 일 때") {

            expect("딜러도 버스트라면 1배의 추가 금액을 받는다") {
                val playerCards = mutableListOf(Card(CLUB, SEVEN), Card(DIAMOND, QUEEN))
                val player = Player("cat",10_000, Cards(playerCards))
                player.draw(Card(SPADE, FIVE))

                val dealerCards = mutableListOf(Card(DIAMOND, FIVE), Card(HEART, JACK))
                val dealer = Dealer(Cards(dealerCards))
                dealer.draw(Card(SPADE, EIGHT))

                player.winning(dealer) shouldBe 20_000
            }

            expect("딜러는 버스트가 아니라면 돈을 받을 수 없다") {
                val playerCards = mutableListOf(Card(CLUB, SEVEN), Card(DIAMOND, QUEEN))
                val player = Player("cat",10_000, Cards(playerCards))
                player.draw(Card(SPADE, FIVE))

                val dealerCards = mutableListOf(Card(DIAMOND, FIVE), Card(HEART, JACK))
                val dealer = Dealer(Cards(dealerCards))
                dealer.draw(Card(HEART, FIVE))

                player.winning(dealer) shouldBe 0
            }
        }
    }
})
