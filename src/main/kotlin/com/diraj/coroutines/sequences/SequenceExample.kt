package com.diraj.coroutines.sequences

class SequenceExample {
    fun sequenceExample() {
        val list = listOf(1, 2, 3) // 1
        list.asSequence()
                .filter {
                    print("filter, ")
                    it > 0
                }.map {
                    print("map, ")
                }.forEach { _ ->
                    print("forEach, ")
                }
    }
}