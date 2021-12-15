package com.example.adventofcode2021

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun wrap(num: Int) : Int {
    if (num > 9) {
        return num-9
    }
    return num
}

fun main(args: Array<String>) {
    var file = File("../day15/input.txt")
    var lines = file.readLines()

    numRows = lines.size
    numCols = lines[0].toCharArray().size
    val nodes = mutableMapOf<Pair<Int,Int>,Node>()
    for(i in lines.indices) {
        var numbers = lines[i].toCharArray().map { it.digitToInt() }.toMutableList()
        for(j in numbers.indices) {
            for (z in 0 until 5) {
                for (q in 0 until 5) {
                    nodes[Pair(i+numRows*z,j+numCols*q)] = Node(i+numRows*z,j+numCols*q,wrap(numbers[j]+z+q), Integer.MAX_VALUE)
                }
            }
        }
    }
    numRows *= 5
    numCols *= 5

    for(i in 0 until numRows) {
        for (j in 0 until numCols) {
            val node = nodes[Pair(i,j)]!!
            if (i > 0) {
                node.connectedNodes.add(nodes[Pair(i-1,j)]!!)
            }
            if (i < numRows-1) {
                node.connectedNodes.add(nodes[Pair(i+1,j)]!!)
            }
            if (j > 0) {
                node.connectedNodes.add(nodes[Pair(i,j-1)]!!)
            }
            if (j < numCols-1) {
                node.connectedNodes.add(nodes[Pair(i,j+1)]!!)
            }
        }
    }

//    for (i in 0 until numRows) {
//        for (j in 0 until numCols) {
//            print(nodes[Pair(i,j)]!!.cost)
//        }
//        println()
//    }

    var current = nodes[Pair(0,0)]!!
    current.distance = 0
    while (current != nodes[Pair(numRows-1,numCols-1)]) {
        // update distance to adjacent nodes
        current.connectedNodes.forEach {
            it.distance = min(it.distance, current.distance + it.cost)
        }
        current.visited = true
        current = nodes.values.filter { !it.visited }.minByOrNull { it -> it.distance }!!
    }

    println("minRisk: ${current.distance}")
}
