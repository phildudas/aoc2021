package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle14/input.txt")
    var lines = file.readLines()

    var chain = lines[0].trim().toCharArray().toMutableList()
    var rules = mutableMapOf<Pair<Char,Char>,Rule>()
    for (i in 2 until lines.size) {
        var pieces = lines[i].trim().split(" -> ")
        var pairLetters = pieces[0].toCharArray()
        var pair = Pair(pairLetters[0],pairLetters[1])
        var result = pieces[1].toCharArray()[0]
        rules[pair] = Rule(pair, Pair(pair.first, result), Pair(result, pair.second))
    }

    var countsMap = mutableMapOf<Pair<Char,Char>,Long>()
    rules.keys.forEach { countsMap[it] = 0}
    for (i in 0 until chain.size-1) {
        var pair = Pair(chain[i],chain[i+1])
        countsMap[pair] = countsMap[pair]!! + 1
    }

    countsMap.keys.forEach {
        println("$it -> ${countsMap[it]}")
    }

    for (step in 0 until 40) {
        var newCountsMap = mutableMapOf<Pair<Char,Char>,Long>()
        rules.keys.forEach { newCountsMap[it] = 0}
        countsMap.forEach {
            var rule = rules[it.key]!!
            newCountsMap[rule.a] = newCountsMap[rule.a]!! + it.value
            newCountsMap[rule.b] = newCountsMap[rule.b]!! + it.value
        }
        countsMap = newCountsMap
    }

    println("AT ENDD>>>>>>>")
    countsMap.keys.forEach {
        println("$it -> ${countsMap[it]}")
    }

    val countMap = mutableMapOf<Char,Long>()
    for (letter in 'A' .. 'Z') {
        countMap[letter] = 0
    }
    countMap['K'] = 1
    countsMap.forEach {
        countMap[it.key.first] = countMap[it.key.first]!! + it.value
    }
    var max = 0L
    var min = Long.MAX_VALUE
    countMap.values.forEach {
        if(it > max) max = it
        if(it < min && it > 0) min = it
    }
    println("$max-$min = ${max!!-min!!}")
}

data class Rule (val source: Pair<Char,Char>, val a: Pair<Char,Char>, val b: Pair<Char,Char>)