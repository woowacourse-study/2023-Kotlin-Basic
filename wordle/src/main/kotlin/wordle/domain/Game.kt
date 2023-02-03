package wordle.domain

import java.lang.RuntimeException

class Game(
    wordsReader: WordsReader,
    answerExtractor: AnswerExtractor = FixedAnswerExtractor(),
    private val ioProcessor: IOProcessor,
    private val judgements: Judgements = Judgements(),
) {

    private val words: Words = Words(wordsReader.read())
    private val answer: Word = answerExtractor.extract(words.value)

    fun play() {
        ioProcessor.start()

        while (judgements.isPlaying) {
            val guess = retryGetGuess()
            judgements.add(Judgement(answer, guess))

            if (judgements.isEnd) {
                ioProcessor.end(judgements.judgeAll())
                return
            }

            ioProcessor.outputTiles(judgements.judgeAll())
        }

        ioProcessor.fail("게임에 실패하였습니다.")
    }

    private tailrec fun retryGetGuess(): Word = try {
        val guess = Word(ioProcessor.inputGuess())
        words.validateNonExist(guess)
        guess
    } catch (e: RuntimeException) {
        ioProcessor.fail(e.message)
        retryGetGuess()
    }
}
