package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../puzzle8/input.txt")
    var lines = file.readLines()

    var totalOutputCount = 0
    lines.forEach { line ->
        val pieces = line.trim().split("|")
        val inputs = pieces[0].trim().split(" ")
        val outputs = pieces[1].trim().split(" ")
        val numberStrings = arrayOfNulls<String>(10)

        // decode the ones we know by size alone
        val fives = mutableListOf<String>()
        val sixes = mutableListOf<String>()
        inputs.forEach { str ->
            when(str.length) {
                2 -> numberStrings[1] = str
                4 -> numberStrings[4] = str
                3 -> numberStrings[7] = str
                7 -> numberStrings[8] = str
                5 -> fives.add(str)
                6 -> sixes.add(str)
            }
        }

        // now we can pick apart the others
        sixes.filter { it.containsChars(numberStrings[4]!!) }.map { numberStrings[9] = it; sixes.remove(it) }
        sixes.filter { it.containsChars(numberStrings[7]!!) }.map { numberStrings[0] = it; sixes.remove(it) }
        numberStrings[6] = sixes[0]
        fives.filter { it.containsChars(numberStrings[7]!!) }.map { numberStrings[3] = it; fives.remove(it) }
        fives.filter { numberStrings[9]!!.containsChars(it) }.map { numberStrings[5] = it; fives.remove(it) }
        numberStrings[2] = fives[0]

        for (i in 0 until 10) {
            println("[$i]: ${numberStrings[i]}")
        }

        var outputString = ""
        outputs.forEach { output ->
            numberStrings.filter { it!!.permutEquals(output) }.map { outputString += numberStrings.indexOf(it).toString() }
        }
        val output = outputString.toInt()
        println("$outputString: ${output}")
        totalOutputCount += output
    }
    println("totalOutputCount: $totalOutputCount")
}

fun String.containsChars(other: String):Boolean {
    var chars = other.toCharArray()
    var containsAll = true
    chars.forEach {
        if (!this.contains(it)) {
            containsAll = false
        }
    }
    return containsAll
}

fun String.permutEquals(other: String):Boolean {
    return length == other.length && containsChars(other)
}