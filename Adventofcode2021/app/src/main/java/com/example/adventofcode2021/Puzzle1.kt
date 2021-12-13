package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle1/input.txt")
    var lines = file.readLines()

    var prev = lines[0].toInt()
    var count = 0
    lines.forEach {
        var newValue = it.toInt()
        if (newValue > prev) count++
        prev = newValue
    }

    println("Number of Lines: ${lines.size}, number of increases: $count")
}