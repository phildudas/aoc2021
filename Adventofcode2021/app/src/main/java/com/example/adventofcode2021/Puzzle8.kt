package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle4/input.txt")
    var lines = file.readLines()

    val calledNumbersLine = lines[0]
    val calledNumbers = calledNumbersLine.split(",").map { it -> Integer.parseInt(it) }
    val cards = mutableListOf<Card>()
    val numCards = (lines.size - 1)/6
    for (i in 0 until numCards) {
        val idx = 2 + i * 6
        val fiveLines = mutableListOf<String>()
        for (j in idx until idx+5) {
            fiveLines.add(lines[j])
        }
        cards.add(Card(fiveLines))
    }

    var winner: Card? = null
    var winningNumber: Int = 0
    numberLoop@ for (i in calledNumbers.indices ) {
        for (j in cards.indices) {
            if (!cards[j].hasWon && cards[j].winsWith(calledNumbers[i])) {
                if (winner != null) {
                    winningNumber = calledNumbers[i]
                    break@numberLoop
                }
                cards[j].hasWon = true
                if (cards.filter{ !it.hasWon }.size == 1) {
                    winner = cards.filter{!it.hasWon}[0]
                }
            }
        }
    }

    println("found a winner: ")
    winner?.printCard()
    println("winning number: $winningNumber")
}

