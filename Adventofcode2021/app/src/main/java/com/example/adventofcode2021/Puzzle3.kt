package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle2/input.txt")
    var lines = file.readLines()

    var depth = 0
    var horizontal = 0
    for (i in 0..lines.size-1) {
        val parts = lines[i].split(" ")
        val value = parts[1].toInt()
        when(parts[0]) {
            "forward" -> horizontal += value
            "up" -> depth -= value
            "down" -> depth += value
        }
    }

    println("Number of Lines: ${lines.size}, hor: $horizontal depth: $depth: mult: ${depth * horizontal}")
}