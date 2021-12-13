package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.min
val grid2 = mutableListOf<MutableList<Int>>()

fun main(args: Array<String>) {
    var file = File("../puzzle9/input.txt")
    var lines = file.readLines()

    // setup the whole grid
    for(i in lines.indices) {
        grid2.add(mutableListOf())
        val digits = lines[i].toCharArray()
        for(j in digits.indices) {
            grid2[i].add(digits[j].digitToInt())
        }
    }

    // sum up low points
    val basins = mutableListOf<Int>()
    for (row in grid2.indices) {
        for (col in grid2[row].indices) {
            val size = basinSize(row, col)
            if (size > 0) basins.add(size)
        }
    }

    println("basins: ${basins.sorted()}")
}

fun basinSize(row: Int, col: Int):Int {
    val value = grid2[row][col]
    if ((row > 0 && grid2[row-1][col] <= value) ||
        (row < (grid2.size-1) && grid2[row+1][col] <= value) ||
        (col > 0 && grid2[row][col-1] <= value) ||
        (col < (grid2[row].size-1) && grid2[row][col+1] <= value)) {
        return 0
    }
    val basinSet = mutableSetOf(Pair(row,col))
    val size = 1 + recursiveBasinSize(row, col-1, basinSet) + recursiveBasinSize(row, col+1, basinSet) + recursiveBasinSize(row-1,col, basinSet) + recursiveBasinSize(row+1,col, basinSet)
    println("found low point at $row $col, size = $size")
    return size
}

fun recursiveBasinSize(row: Int, col: Int, basinSet: MutableSet<Pair<Int,Int>>):Int {
    if(row<0 || col<0 || row>=grid2.size || col>=grid2[row].size || grid2[row][col]==9 || basinSet.contains(Pair(row,col))) {
        return 0
    }
    basinSet.add(Pair(row,col))
    return 1 + recursiveBasinSize(row, col-1, basinSet) + recursiveBasinSize(row, col+1, basinSet) + recursiveBasinSize(row-1,col, basinSet) + recursiveBasinSize(row+1,col, basinSet)
}