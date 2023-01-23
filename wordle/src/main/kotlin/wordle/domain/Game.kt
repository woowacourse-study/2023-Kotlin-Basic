package wordle.domain

class Game(
    wordsReader: WordsReader,
    answerExtractor: AnswerExtractor = FixedAnswerExtractor(),
    private val ioProcessor: IOProcessor
) {

    private val answer = answerExtractor.extract(wordsReader.read())

    private var judgements = mutableListOf<Judgement>()

    fun play() {
        ioProcessor.start()

        while (isPlaying()) {
            val guess = ioProcessor.inputGuess()
            judgements.add(Judgement(answer, Word(guess)))

            if (isEnd()) {
                ioProcessor.end(judgeAll())
                return
            }

            ioProcessor.outputTiles(judgeAll())
        }

        ioProcessor.fail()
    }

    private fun isPlaying() = judgements.size < 6

    private fun isEnd() = judgements.isAllGreenTiles()

    private fun judgeAll() = judgements.map(Judgement::judge)

    private fun List<Judgement>.isAllGreenTiles() = map { it.judge() }
        .any { it.all(Tile::isGreen) }
}
