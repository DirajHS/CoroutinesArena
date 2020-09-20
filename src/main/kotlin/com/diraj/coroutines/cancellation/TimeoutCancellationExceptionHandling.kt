package com.diraj.coroutines.cancellation

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class TimeoutCancellationExceptionHandling {

    fun timeoutCancellationExceptionHandling() = runBlocking {
        try {
            val x = withTimeout(1500L) { repeat(1000) { i ->
                println("$i. Crunching numbers [Beep.Boop.Beep]...")
                delay(500L)
            }
            }
        } catch (e: TimeoutCancellationException) {
            println("Caught ${e.javaClass.simpleName}") }
    }
}