package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../puzzle6/input.txt")
    var lines = file.readLines()

    val fish = lines[0].split(",").map{ it.toInt() }
    val fishCount = LongArray(10)
    for(i in fishCount.indices) {
        fishCount[i] = fish.filter { it -> it == i }.size.toLong()
    }

    for (days in 0 until 256) {
        var fishToCreate = fishCount[0]
        for(i in 1 until fishCount.size) {
            fishCount[i-1] = fishCount[i]
        }
        fishCount[6] = fishCount[6] + fishToCreate
        fishCount[8] = fishToCreate
    }

    var totalFish:Long = 0
    for (i in fishCount.indices){
        totalFish += fishCount[i]
    }
    println("number of fish: $totalFish")
}
