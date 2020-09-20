package com.diraj.coroutines.sequences

class IteratorExample {
    fun iteratorExample() {
        // 1
        val list = listOf(1, 2, 3)
        // 2
        list.filter {
            // 3
            print("filter, ")
            // 4
            it > 0
        // 5
        }.map {
            // 6
            print("map, ")
            // 7
        }.forEach { _ ->
            // 8
            print("forEach, ")
        }
    }
}