package com.example.adventofcode2021

import java.io.File
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../day15/input.txt")
    var lines = file.readLines()

    numRows = lines.size
    numCols = lines[0].toCharArray().size
    val cost = mutableListOf<MutableList<Int>>()
    val nodes = mutableMapOf<Pair<Int,Int>,Node>()
    for(i in lines.indices) {
        var numbers = lines[i].toCharArray().map { it.digitToInt() }.toMutableList()
        cost.add(numbers)
        for(j in numbers.indices) {
            nodes[Pair(i,j)] = Node(i,j,numbers[j],Integer.MAX_VALUE)
        }
    }

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
    //print(grid)
    var current = nodes[Pair(0,0)]!!
    current.distance = 0
    while (current != nodes[Pair(numRows-1,numCols-1)]) {
        // update distance to adjacent nodes
        current.connectedNodes.forEach {
            it.distance = min(it.distance, current.distance + it.cost)
        }
        current.visited = true
        current = nodes.values.filter { it.visited == false }.minByOrNull { it -> it.distance }!!
    }

    println("minRisk: ${current.distance}")
}

data class Node(var row: Int, var col: Int, var cost: Int, var distance: Int, var visited: Boolean = false, val connectedNodes: MutableList<Node> = mutableListOf())

