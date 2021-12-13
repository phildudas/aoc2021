package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../puzzle11/input.txt")
    var lines = file.readLines()

    val octoGrid = mutableListOf<MutableList<Int>>()
    for(row in 0 until 10){
        val rowList = mutableListOf<Int>()
        octoGrid.add(rowList)
        val digits = lines[row].toCharArray()
        for(col in 0 until 10) {
            rowList.add(digits[col].digitToInt())
        }
    }
    printGrid(octoGrid)
    var step = 0
    var flashes = 0
    while(flashes < 100){
        step++
        flashes = flashStep(octoGrid)
    }
    println("step: $step")
}

fun flashStep(grid: MutableList<MutableList<Int>>): Int {
    // increment them all once
    var flashes = mutableSetOf<Pair<Int, Int>>()
    var flashCount = 0
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            grid[row][col]++
            if (grid[row][col] == 10) {
                flashes.add(Pair(row, col))
                flashCount++
            }
        }
    }
    while (flashes.isNotEmpty()) {
        val newFlashes = mutableSetOf<Pair<Int,Int>>()
        flashes.forEach { flashCoord ->
            for (row in max(flashCoord.first-1, 0) .. min(flashCoord.first+1, 9)) {
                for (col in max(flashCoord.second-1, 0) .. min(flashCoord.second+1, 9)) {
                    grid[row][col]++
                    if (grid[row][col] == 10) {
                        newFlashes.add(Pair(row, col))
                        flashCount++
                    }
                }
            }
        }
        flashes = newFlashes
    }
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            if (grid[row][col] >= 10) {
                grid[row][col] = 0
            }
        }
    }
    return flashCount
}

fun printGrid(grid: List<List<Int>>) {
    for(row in grid.indices) {
        for(col in grid[row].indices) {
            print(grid[row][col])
        }
        println()
    }
    println()
}
