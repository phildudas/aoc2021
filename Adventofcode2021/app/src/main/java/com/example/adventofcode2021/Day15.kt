package com.example.adventofcode2021

import java.io.File
import kotlin.math.min

var numRows: Int = 0
var numCols: Int = 0
var minRisk = Integer.MAX_VALUE

fun main(args: Array<String>) {
    var file = File("../day15/easyinput.txt")
    var lines = file.readLines()

    numRows = lines.size
    numCols = lines[0].toCharArray().size
    val grid = mutableListOf<MutableList<Int>>()
    for(i in lines.indices) {
        grid.add(lines[i].toCharArray().map { it.digitToInt() }.toMutableList())
    }

    print(grid)

    calculateMinRisk(grid,0,0,"", -grid[0][0])
    println("minRisk: $minRisk")
}

data class Node (val value:Int, val row:Int, val col: Int, val nodes: List<Node>)

fun calculateMinRisk(grid: List<List<Int>>, row: Int, col: Int, pathSoFar: String, riskSoFar: Int): Int {
    //println("checking: $row,$col")
    //print(".")
    val nextRiskSoFar = riskSoFar + grid[row][col]
    if (pathSoFar.contains("[$row,$col]")) return Integer.MAX_VALUE
    if (row==numRows-1 && col==numCols-1) {
        minRisk = min(minRisk, nextRiskSoFar)
        println("at $row,$col minRisk: $minRisk")
        return grid[row][col]
    }
    val newPathSoFar = "$pathSoFar[$row,$col]"
    var minFromHere = Integer.MAX_VALUE
    if (row > 0) {
        minFromHere = min(minFromHere, calculateMinRisk(grid, row-1, col, newPathSoFar, nextRiskSoFar))
    }
    if (row < numRows-1) {
        minFromHere = min(minFromHere, calculateMinRisk(grid, row+1, col, newPathSoFar, nextRiskSoFar))
    }
    if (col > 0) {
        minFromHere = min(minFromHere, calculateMinRisk(grid, row, col-1, newPathSoFar, nextRiskSoFar))
    }
    if (col < numCols-1) {
        minFromHere = min(minFromHere, calculateMinRisk(grid, row, col+1, newPathSoFar, nextRiskSoFar))
    }
    return grid[row][col] + minFromHere
}