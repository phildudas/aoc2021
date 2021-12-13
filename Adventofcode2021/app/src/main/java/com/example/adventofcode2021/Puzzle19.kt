package com.example.adventofcode2021

import java.io.File
import kotlin.math.abs
import kotlin.math.min

fun main(args: Array<String>) {
    var file = File("../puzzle10/input.txt")
    var lines = file.readLines()

    var syntaxErrorScore = 0
    var autocompleteScores = mutableListOf<Long>()
    lines.forEach {
        var bracketStack = mutableListOf<Char>()
        var lineChars = it.toCharArray()
        var breakFound = false
        lineChars.forEach { bracket ->
            if (!breakFound) {
                when (bracket) {
                    '{','(','[','<' -> bracketStack.add(bracket)
                    '}'-> if (!popBracket(bracketStack,'{')) {
                        breakFound = true
                        syntaxErrorScore += 1197
                    }
                    ')'-> if (!popBracket(bracketStack,'(')) {
                        breakFound = true
                        syntaxErrorScore += 3
                    }
                    ']'-> if (!popBracket(bracketStack,'[')) {
                        breakFound = true
                        syntaxErrorScore += 57
                    }
                    '>'-> if (!popBracket(bracketStack,'<')) {
                        breakFound = true
                        syntaxErrorScore += 25137
                    }
                }
            }
        }
        if(!breakFound) {
            autocompleteScores.add(determineScore(bracketStack))
        }
    }

    print(autocompleteScores.sorted())
}

fun determineScore(bracketStack: MutableList<Char>) : Long {
    var stack = bracketStack
    var score = 0L
    while(stack.isNotEmpty()) {
        val bracket = stack.removeLast()
        when(bracket) {
            '(' -> { score *= 5; score++ }
            '[' -> { score *= 5; score+=2}
            '{' -> { score *= 5; score+=3}
            '<' -> { score *= 5; score+=4}
        }
    }
    return score
}

fun popBracket(stack: MutableList<Char>, bracket: Char):Boolean {
    if(stack.last() != bracket){
        return false
    }
    stack.removeLast()
    return true
}

