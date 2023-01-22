import domain.Answer
import domain.AnswerSelector
import util.readWords
import view.printGameStart
import java.time.LocalDate

fun main() {
    printGameStart()
    val answer = Answer.from(
        AnswerSelector.findTodayAnswer(LocalDate.now(), readWords())
    )

}
