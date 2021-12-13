package com.example.adventofcode2021

import java.io.File
val edgeMap2: MutableMap<String, MutableList<String>> = mutableMapOf()

fun main(args: Array<String>) {
    var file = File("../puzzle12/input.txt")
    var lines = file.readLines()

    lines.forEach {
        val vertices = it.trim().split("-")
        val from = vertices[0]
        val to = vertices[1]
        val fromList = getOrCreateList2(from)
        if(to != "start") fromList.add(to)
        val toList = getOrCreateList2(to)
        if(from != "start") toList.add(from)
    }

    val numPaths = walkGraph("start", "", false)
    println("numPaths: $numPaths")
}

fun walkGraph(node: String, pathSoFar: String, smallTwice: Boolean) : Int {
    if (node == "end") {
        println ("$pathSoFar-end-")
        return 1
    }
    val nodePathString = "-$node-"
    val newPathSoFar = "$pathSoFar$nodePathString"
    var newSmallTwice = smallTwice
    if (node.lowercase()==node && pathSoFar.contains(nodePathString)) {
        if (!smallTwice) {
            newSmallTwice = true
        } else {
            println("invalid path: $newPathSoFar")
            return 0
        }
    }
    var subPaths = 0
    edgeMap2[node]?.sorted()?.forEach() {
        subPaths += walkGraph(it, newPathSoFar, newSmallTwice)
    }
    return subPaths
}

fun getOrCreateList2(key: String) : MutableList<String> {
    if (!edgeMap2.containsKey(key)) {
        val list: MutableList<String> = mutableListOf()
        edgeMap2[key] = list
    }
    return edgeMap2.getValue(key)
}