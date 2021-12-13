package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle3/input.txt")
    var lines = file.readLines()

    var oxygenList: List<String> = ArrayList(lines)
    var carbonDioxideList: List<String> = ArrayList(lines)

    var index = 0
    while(oxygenList.size > 1) {
        var onesCount = 0
        oxygenList.forEach {
            val charArray = it.toCharArray()
            if(charArray[index] == '1') onesCount++
        }

        var zerosCount = oxygenList.size - onesCount
        if (onesCount < zerosCount) {
            // keep zeros
            oxygenList = oxygenList.filter { it.toCharArray()[index] == '0' }
        } else {
            // keep ones
            oxygenList = oxygenList.filter { it.toCharArray()[index] == '1' }
        }
        index++
    }

    index = 0
    while(carbonDioxideList.size > 1) {
        var onesCount = 0
        carbonDioxideList.forEach {
            val charArray = it.toCharArray()
            if(charArray[index] == '1') onesCount++
        }

        var zerosCount = carbonDioxideList.size - onesCount
        if (onesCount < zerosCount) {
            // keep ones
            carbonDioxideList = carbonDioxideList.filter { it.toCharArray()[index] == '1' }
        } else {
            // keep zeros
            carbonDioxideList = carbonDioxideList.filter { it.toCharArray()[index] == '0' }
        }
        index++
    }

    val oxygen = Integer.parseInt(oxygenList[0],2)
    val co2 = Integer.parseInt(carbonDioxideList[0],2)
    println("oxygen: ${oxygen} co2: ${co2}: life support: ${oxygen*co2}")
}