package com.diraj.coroutines.sequences

class SequenceYieldAllExample {
    fun sequenceYieldAllExample() {
        val sequence = sequenceExample().take(10)
        sequence.forEach {
            print("$it ")
        }
    }

    private fun sequenceExample() = sequence {
        yieldAll(generateSequence(2) { it * 2 })
    }
}