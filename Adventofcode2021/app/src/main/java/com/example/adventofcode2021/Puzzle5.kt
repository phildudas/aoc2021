package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle3/input.txt")
    var lines = file.readLines()

    var numDigits = lines[0].trim().length
    var dividingLine = lines.size / 2
    var onesCount: MutableList<Int> = mutableListOf()
    for (i in 0..numDigits-1) {
        onesCount.add(0)
    }
    lines.forEach {
        val charArray = it.toCharArray()
        for (i in 0..numDigits-1) {
            if(charArray[i] == '1'){
                onesCount[i]++
            }
        }
    }

    var gammaString: String = ""
    var epsilonString: String = ""
    for (i in 0..numDigits-1) {
        if (onesCount[i]>dividingLine) {
            gammaString += '1'
            epsilonString += '0'
        } else {
            gammaString += '0'
            epsilonString += '1'
        }
    }

    println("gammaString: [$gammaString]  epsilonString: [$epsilonString]")
    val gammaRate = Integer.parseInt(gammaString,2)
    val epsilonRate = Integer.parseInt(epsilonString,2)
    println("gamma: ${gammaRate} epsilon: ${epsilonRate} power: ${gammaRate*epsilonRate}")

}