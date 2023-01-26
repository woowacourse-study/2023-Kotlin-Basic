package wordle.domain

class Game(
    private val wordsReader: WordsReader,
    private val ioProcessor: IOProcessor,
    private val answerExtractor: AnswerExtractor = FixedAnswerExtractor(),
    private var judgements: MutableList<Judgement> = mutableListOf(),
) {

    private val words: Words = Words(wordsReader.read())
    private val answer: Word = answerExtractor.extract(words.value)

    fun play() {
        ioProcessor.start()

        while (isPlaying()) {
            val guess = retryGetGuess()
            judgements.add(Judgement(answer, guess))

            if (isEnd()) {
                ioProcessor.end(judgeAll())
                return
            }

            ioProcessor.outputTiles(judgeAll())
        }

        ioProcessor.fail()
    }

    private tailrec fun retryGetGuess(): Word = try {
        val guess = Word(ioProcessor.inputGuess())
        words.validateNonExist(guess)
        guess
    } catch (e: Exception) {
        retryGetGuess()
    }

    private fun isPlaying() = judgements.size < 6

    private fun isEnd() = judgements.isAllGreenTiles()

    private fun judgeAll() = judgements.map(Judgement::judge)

    private fun List<Judgement>.isAllGreenTiles() = map { it.judge() }
        .any { it.all(Tile::isGreen) }
}