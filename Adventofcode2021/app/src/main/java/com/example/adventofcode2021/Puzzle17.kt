package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.min
val grid = mutableListOf<MutableList<Int>>()

fun main(args: Array<String>) {
    var file = File("../puzzle9/input.txt")
    var lines = file.readLines()

    // setup the whole grid
    for(i in lines.indices) {
        grid.add(mutableListOf())
        val digits = lines[i].toCharArray()
        for(j in digits.indices) {
            grid[i].add(digits[j].digitToInt())
        }
    }

    // sum up low points
    var lowPointTotal = 0
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            lowPointTotal += lowPointTotal(row, col)
        }
    }

    println("low point total: $lowPointTotal")
}

fun lowPointTotal(row: Int, col: Int):Int {
    val value = grid[row][col]
    if ((row > 0 && grid[row-1][col] <= value) ||
        (row < (grid.size-1) && grid[row+1][col] <= value) ||
        (col > 0 && grid[row][col-1] <= value) ||
        (col < (grid[row].size-1) && grid[row][col+1] <= value)) {
        return 0
    }
    println("found low point at $row $col")
    return value + 1
}