package com.diraj.coroutines.suspending_functions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

suspend fun <T : Any> getValue(provider: () -> T): T =
        suspendCoroutine { continuation ->
            /*
            resumeWith would try to run the continuation and produce a result which can be success or failure
             */
            continuation.resumeWith(Result.runCatching { provider() })
        }

fun executeBackground(action: suspend () -> Unit) {
    GlobalScope.launch { action() }
}

fun executeMain(action: suspend () -> Unit) {
    GlobalScope.launch(context = Dispatchers.Main) { action() }
}