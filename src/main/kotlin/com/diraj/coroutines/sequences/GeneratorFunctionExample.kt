package com.diraj.coroutines.sequences

class GeneratorFunctionExample {

    fun generatorFunctionExample() {
        // 1
        val sequence = generatorFib().take(8)
         // 2
        sequence.forEach {
            println("$it")
        }
    }

    // 3
    private fun generatorFib() = sequence {
        // 4
        print("Suspending...")
        // 5
        yield(0)
        var cur = 0
        var next = 1
        while (true) {
            // 6
            print("Suspending...")
            // 7
            yield(next) //Sequence is stopped after this suspension point when count value is reached
            val tmp = cur + next
            cur = next
            next = tmp
        }
    }
}