package com.example.adventofcode2021

import java.io.File

fun main(args: Array<String>) {
    var file = File("../puzzle14/input.txt")
    var lines = file.readLines()

    var chain = lines[0].trim().toCharArray().toMutableList()
    var rules = mutableMapOf<Pair<Char,Char>,Char>()
    for (i in 2 until lines.size) {
        var pieces = lines[i].trim().split(" -> ")
        var pairLetters = pieces[0].toCharArray()
        var pair = Pair(pairLetters[0],pairLetters[1])
        var result = pieces[1].toCharArray()[0]
        rules[pair] = result
    }

    for (step in 0 until 40) {
        doStep(chain, rules)
        println(chain)
    }

    val countMap = mutableMapOf<Char,Int>()
    for (letter in 'A' .. 'Z') {
        countMap[letter] = 0
    }
    chain.forEach { countMap[it] = countMap[it]!!+1 }
    var max = 0
    var min = Integer.MAX_VALUE
    countMap.values.forEach {
        if(it > max) max = it
        if(it < min && it > 0) min = it
    }
    println("max-min = ${max!!-min!!}")
}

// modifies the chain with the given rules
fun doStep(chain: MutableList<Char>, rules: Map<Pair<Char,Char>,Char>) {
    // find all the insertions
    val charsToInsert = mutableListOf<Char>()
    for (i in 0 until chain.size-1) {
        charsToInsert.add(rules[Pair(chain[i],chain[i+1])]!!)
    }
    for (i in 0 until charsToInsert.size) {
        chain.add(1+i*2,charsToInsert[i])
    }
}
