package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../puzzle7/input.txt")
    var lines = file.readLines()

    var positions = lines[0].split(",").map { it.toInt() }
    var min = Integer.MAX_VALUE
    var max = 0
    positions.forEach { if(it > max) max = it else if (it < min) min = it}
    var minFuel = Integer.MAX_VALUE
    for(i in min .. max) {
        var deltaSum = 0
        positions.forEach { deltaSum += calculateFuel(abs(it - i))}
        minFuel = min(minFuel, deltaSum)
    }

    println("Found min fuel use of: $minFuel")
}

fun calculateFuel(distance: Int) : Int {
    var distanceLeft = distance
    var fuelUse = 0
    while (distanceLeft > 0) {
        fuelUse += distanceLeft
        distanceLeft--
    }
    return fuelUse
}
