package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../puzzle6/input.txt")
    var lines = file.readLines()

    val fish = lines[0].split(",").map{ it.toInt() }.toMutableList()

    for (days in 0 until 80) {
        var fishToCreate = 0
        for (i in 0 until fish.size) {
            if (fish[i] > 0){
                fish[i]--
            } else {
                fish[i] = 6
                fishToCreate++
            }
        }
        for (i in 0 until fishToCreate) {
            fish.add(8)
        }
    }

    println("number of fish: ${fish.size}")
}
