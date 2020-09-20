package com.diraj.coroutines.cancellation

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class JoinAllCoroutineExample {
    fun joinAllCoroutineExample() = runBlocking {
        val jobOne = launch {
            println("Job 1: Crunching numbers [Beep.Boop.Beep]...")
            delay(500L)
        }
        val jobTwo = launch {
            println("Job 2: Crunching numbers [Beep.Boop.Beep]...")
            delay(500L)
        }
        // waits for both the jobs to complete
        joinAll(jobOne, jobTwo)
        println("main: Now I can quit.") }
}