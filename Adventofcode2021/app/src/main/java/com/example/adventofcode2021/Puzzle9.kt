package com.example.adventofcode2021

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../puzzle5/input.txt")
    var lines = file.readLines()
    var pairs = lines.map{ line ->
        line.replace(" -> ","%").split("%").map { coord ->
            coord.split(",").map {
                it.toInt()
            }
        }
    }
    var ndPairs = pairs.filter { (it[0][0] != it[1][0]) xor (it[0][1] != it[1][1]) }

    println("${ndPairs.size} reduced from ${pairs.size}")
    println(ndPairs)

    var maxX = 0
    var maxY = 0
    ndPairs.forEach {
        maxX = max(maxX, max(it[0][0], it[1][0]))
        maxY = max(maxY, max(it[0][1], it[1][1]))
    }
    maxX++
    maxY++

    println("maxX: $maxX maxY: $maxY")

    var grid: MutableList<MutableList<Int>> = mutableListOf()
    for(i in 0 until maxX){
        val innerList = mutableListOf<Int>()
        for(j in 0 until maxY){
            innerList.add(0)
        }
        grid.add(innerList)
    }

    var twoCount = 0
    ndPairs.forEach { coordPair ->
        val from = coordPair[0]
        val to = coordPair[1]
        if (from[0] == to[0]) {
            val x = from[0]
            for (y in min(from[1],to[1]) .. max(to[1],from[1])) {
                grid[x][y]++
                if(grid[x][y] == 2) {
                    twoCount++
                }
            }
        } else {
            val y = from[1]
            for (x in min(from[0],to[0]) .. max(to[0],from[0])) {
                grid[x][y]++
                if(grid[x][y] == 2) {
                    twoCount++
                }
            }
        }
    }

    println("found: $twoCount with >=2")
        // println(grid)

}

