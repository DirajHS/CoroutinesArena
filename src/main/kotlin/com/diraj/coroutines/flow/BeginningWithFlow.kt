package com.diraj.coroutines.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class BeginningWithFlow {
    fun example1() {
        val flowOfStrings = flow {
            for (number in 0..100) {
                emit("Emitting: $number")
            }
        }

        GlobalScope.launch {
            flowOfStrings.collect { value ->
                println("Collector#1: $value")
            }
        }
        Thread.sleep(1000)
    }

    fun example2() {
        val flowOfStrings = flow {
            for (number in 0..100) {
                emit("Emitting: $number")
            }
        }

        GlobalScope.launch {
            flowOfStrings
                    .map { it.split(" ") }
                    .map { it.last() }
                    .onEach { delay(100) }
                    .collect { value ->
                        println(value)
                    }
        }
        Thread.sleep(1000)
    }

    fun example3() {
        val flowOfStrings = flow {
            for (number in 0..100) {
                emit("Emitting: $number")
            }
        }

        GlobalScope.launch {
            flowOfStrings
                    .map { it.split(" ") }
                    .map { it.last() }
                    .flowOn(Dispatchers.IO)
                    .onEach { delay(100) }
                    .flowOn(Dispatchers.Default)
                    .collect { value ->
                        println(value)
                    }
        }
        Thread.sleep(1000)
    }

    @ExperimentalCoroutinesApi
    fun example4() {
        val flowOfStrings = channelFlow<String> {
            for (number in 0..100) {
                /*
                Without using ChannelFlow, this switch would throw Exception as we cannot change Context like this,
                we need to use the flowOn operator to switch context in the preceding operators. This should be used only
                for concurrency. Also, flowOn context should not be bound to job, as it could break the API if they
                are lifeCycle aware.
                 */
                withContext(Dispatchers.IO) {
                    send("Emitting: $number")
                }
            }
        }

        GlobalScope.launch {
            flowOfStrings
                    .map { it.split(" ") }
                    .map { it.last() }
                    .flowOn(Dispatchers.IO)
                    .onEach { delay(100) }
                    .flowOn(Dispatchers.Default)
                    .collect { value ->
                        println(value)
                    }
        }
        Thread.sleep(1000)
    }

    fun example5() {
        val flowOfStrings = flow {
            emit("")
            for (number in 0..100) {
                emit("Emitting: $number")
            }
        }

        GlobalScope.launch {
            flowOfStrings
                    .map { it.split(" ") }
                    .map { it[1] }
                    .catch {
                        it.printStackTrace()
                        // send the fallback value or values
                        emit("Fallback")
                    }
                    .flowOn(Dispatchers.Default)
                    .collect {
                        println(it)
                    }
            println("The code still works!")
        }
        Thread.sleep(1000)
    }
}