package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../puzzle8/input.txt")
    var lines = file.readLines()

    var specificSegmentCount = 0
    lines.forEach {
        val outputs = it.trim().split("|")[1].trim().split(" ")
        outputs.forEach { str ->
            println("got output str: $str")
            when(str.length) {
                2,3,4,7 -> specificSegmentCount++
            }
        }
    }
    println("specificSegmentCount: $specificSegmentCount")
}