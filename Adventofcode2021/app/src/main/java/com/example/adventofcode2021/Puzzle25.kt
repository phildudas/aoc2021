package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle13/input.txt")
    var lines = file.readLines()

    var dotLines = mutableListOf<String>()
    var foldLines = mutableListOf<String>()
    lines.forEach { line ->
        if (line.contains(",")) {
            dotLines.add(line)
        } else if (line.contains("fold")){
            foldLines.add(line)
        }
    }

    val dotSet = mutableSetOf<Dot>()
    dotLines.forEach {
        var coords = it.trim().split(",").map{it.toInt()}
        dotSet.add(Dot(coords[1],coords[0]))
    }

    foldLines.forEach { firstFold ->
        val foldCoord = firstFold.split("=")[1].toInt()
        if (firstFold.contains("x")){
            // horizontal
            dotSet.filter { it.col >= foldCoord }.map { dotSet.remove(it); dotSet.add(Dot(it.row, foldCoord - (it.col-foldCoord)))}
        } else {
            // vertical
            dotSet.filter { it.row >= foldCoord }.map { dotSet.remove(it); dotSet.add(Dot(foldCoord - (it.row-foldCoord),it.col))}
        }
    }

    println("set size after fold: ${dotSet.size}")

    for (row in 0..40){
        for(col in 0..40) {
            if (dotSet.contains(Dot(row,col))) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }
}

data class Dot(val row: Int, val col: Int)