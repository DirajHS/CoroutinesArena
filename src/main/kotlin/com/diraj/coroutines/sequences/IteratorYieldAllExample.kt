package com.diraj.coroutines.sequences

class IteratorYieldAllExample {
    fun iteratorYieldAllExample() {
        // 1
        val sequence = iterableExample()
        // 2
        sequence.forEach {
            print("$it ")
        }
    }
    // 3
    private fun iterableExample() = sequence {
        // 4
        println("yielding")
        yieldAll(1..5)
    }
}