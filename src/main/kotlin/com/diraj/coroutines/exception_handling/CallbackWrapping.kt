package com.diraj.coroutines.exception_handling

import kotlinx.coroutines.runBlocking
import java.io.IOException
import kotlin.coroutines.suspendCoroutine

class CallbackWrapping {
    fun wrapCallbackWithCoroutine() {
        runBlocking {
            try {
                val data = getDataAsync()
                println("Data received: $data")
            } catch (e: Exception) {
                println("Caught ${e.javaClass.simpleName}")
            }
        }
    }

    // Callback Wrapping using Coroutine
    private suspend fun getDataAsync(): String {
        return suspendCoroutine { cont ->
            getData(object : AsyncCallback {
                override fun onSuccess(result: String) {
                    cont.resumeWith(Result.success(result))
                }

                override fun onError(e: Exception) {
                    cont.resumeWith(Result.failure(e))
                }

            })

        }
    }

    // Method to simulate a long running task
    private fun getData(asyncCallback: AsyncCallback) {
        // Flag used to trigger an exception
        val triggerError = true

        try {
            // Delaying the thread for 3 seconds
            Thread.sleep(3000)

            if (triggerError) {
                throw IOException()
            } else {
                // Send success
                asyncCallback.onSuccess("[Beep.Boop.Beep]")
            }
        } catch (e: Exception) {
            // send error
            asyncCallback.onError(e)
        }
    }

    // Callback
    interface AsyncCallback {
        fun onSuccess(result: String)
        fun onError(e: Exception)
    }
}