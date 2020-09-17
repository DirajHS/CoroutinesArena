package com.diraj.coroutines.exception_handling

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class TryCatch {
    fun handleAsyncException() {
        runBlocking {
            // Set this to 'true' to call await on the deferred variable
            val callAwaitOnDeferred = false

            val deferred = GlobalScope.async {
                // This statement will be printed with or without
                // a call to await()
                println("Throwing exception from async")
                throw ArithmeticException("Something Crashed")
                // Nothing is printed, relying on a call to await()
            }

            if (callAwaitOnDeferred) {
                try {
                    deferred.await()
                } catch (e: ArithmeticException) {
                    println("Caught ArithmeticException")
                }
            }
        }
    }
}