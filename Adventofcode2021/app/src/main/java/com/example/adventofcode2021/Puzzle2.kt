package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle1/input.txt")
    var lines = file.readLines()

    var count = -1
    var prev = 0
    for (i in 0..lines.size-3) {
        var sum = lines[i].toInt() + lines[i+1].toInt() + lines[i+2].toInt()
        if (sum > prev) count++
        prev = sum
    }

    println("Number of Lines: ${lines.size}, number of increases: $count")
}