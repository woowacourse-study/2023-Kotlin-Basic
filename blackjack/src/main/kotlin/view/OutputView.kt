package view

fun printInitDistribution(result: List<String>, initDistributeResult: String) {
    println("딜러와 ${result.joinToString(",")}에게 2장의 카드를 나누었습니다.")
    println(initDistributeResult)
}

fun printAfterReceive(roundResult: MutableMap<String, String>) {
    for (entry in roundResult.entries) {
        println("${entry.key} 카드: ${entry.value}")
    }
}

fun printResult(gameResult: MutableMap<String, String>) {
    for (entry in gameResult.entries) {
        println("${entry.key} ${entry.value}")
    }
}
