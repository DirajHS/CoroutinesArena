package com.diraj.coroutines.cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

class WithTimeout {
    fun withTimeoutExample() = runBlocking {
        withTimeout(1500) {
            repeat(1000) {
                println("$it. Crunching numbers [Beep.Boop.Beep]...")
                delay(500L)
            }
        }
    }
}