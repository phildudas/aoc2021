package com.example.adventofcode2021

import java.io.File
val edgeMap: MutableMap<String, MutableList<String>> = mutableMapOf()

fun main(args: Array<String>) {
    var file = File("../puzzle12/input.txt")
    var lines = file.readLines()

    lines.forEach {
        val vertices = it.trim().split("-")
        val from = vertices[0]
        val to = vertices[1]
        val fromList = getOrCreateList(from)
        if(to != "start") fromList.add(to)
        val toList = getOrCreateList(to)
        if(from != "start") toList.add(from)
    }

    val numPaths = walkGraph("start", "")
    println("numPaths: $numPaths")
}

fun walkGraph(node: String, pathSoFar: String) : Int {
    if (node == "end") {
        println ("$pathSoFar-end-")
        return 1
    }
    val nodePathString = "-$node-"
    val newPathSoFar = "$pathSoFar$nodePathString"
    if (node.lowercase()==node && pathSoFar.contains(nodePathString)) {
        println ("invalid path: $newPathSoFar")
        return 0
    }
    var subPaths = 0
    edgeMap[node]?.sorted()?.forEach() {
        subPaths += walkGraph(it, newPathSoFar)
    }
    return subPaths
}

fun getOrCreateList(key: String) : MutableList<String> {
    if (!edgeMap.containsKey(key)) {
        val list: MutableList<String> = mutableListOf()
        edgeMap[key] = list
    }
    return edgeMap.getValue(key)
}