package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

interface State {

    fun draw(card: Card): State

    fun stay(): State

    fun isRunning(): Boolean

    fun isFinished(): Boolean

    fun cards(): Cards

    fun earningRate(state: State): Double
}
