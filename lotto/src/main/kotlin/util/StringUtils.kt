package util

fun String.isNumeric(): Boolean = chars().allMatch { Character.isDigit(it) }
