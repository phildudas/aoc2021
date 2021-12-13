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
            if (cards[j].winsWith(calledNumbers[i])) {
                winner = cards[j]
                winningNumber = calledNumbers[i]
                break@numberLoop
            }
        }
    }

    println("found a winner: ")
    winner?.printCard()
    println("winning number: $winningNumber")
}

class Card (private val fiveLines: List<String>) {
    var hasWon = false
    var rows: List<MutableList<Int>>
    var cols: MutableList<MutableList<Int>> = mutableListOf()

    init {
        rows = fiveLines.map { it.trim().replace("  "," ").split(" ").map { str -> Integer.parseInt(str) }.toMutableList() }
        for(i in 0..4) {
            cols.add(mutableListOf(rows[0][i], rows[1][i], rows[2][i], rows[3][i], rows[4][i]))
        }
    }

    fun winsWith(nextNumber: Int) : Boolean {
        var winner = false
        rows.forEach {
            for (i in 0..4) {
                if (it[i] == nextNumber) it[i] = -1
            }
            winner = winner or (it.sum() == -5)
        }
        cols.forEach {
            for (i in 0..4) {
                if (it[i] == nextNumber) it[i] = -1
            }
            winner = winner or (it.sum() == -5)
        }

        return winner
    }

    fun printCard() {
        println("rows:")
        var total = 0
        rows.forEach {
            println(it)
            it.filter { num -> num > 0 }.map { total += it }
        }
        println("cols:")
        cols.forEach {
            println(it)
        }
        println("total: $total")
    }
}