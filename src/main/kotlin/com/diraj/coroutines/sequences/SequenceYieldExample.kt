package com.diraj.coroutines.sequences

class SequenceYieldExample {
    fun sequenceYieldExample() {
        // 1
        val sequence = singleValueExample()
        // 2
        sequence.forEach {
            println(it)
        }
    }
    // 3
    private fun singleValueExample() = sequence {
        // 4
        println("Printing first value")
        // 5
        yield("Apple")
        // 6
        println("Printing second value")
        // 7
        yield("Orange")
        // 8
        println("Printing third value")
        // 9
        yield("Banana")
    }
}