package com.diraj.coroutines.getting_started

import com.diraj.coroutines.IChapterExecutor
import com.diraj.coroutines.utils.Logger
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Level

class GettingStarted: IChapterExecutor {

    private val logger = Logger.getLogger(this.javaClass
    )
    override fun startChapterExecution() {
        launchCoroutines()

        dependentJobs()

        jobHierarchy()

        doorOpener()
    }

    private fun launchCoroutines() {
        logger.debug { "launchCoroutines" }
        (1..10000).forEach {
            /*
            CoroutineStart modes:
            • DEFAULT: Immediately schedules a coroutine for execution according to its context.
            • LAZY: Starts coroutine lazily.
            • ATOMIC: Same as DEFAULT but cannot be cancelled before it starts.
            • UNDISPATCHED: Runs the coroutine until its first suspension point.
             */
            GlobalScope.launch {
                val threadName = Thread.currentThread().name
                println("$it printed on thread $threadName")
            }
        }
        /*
        If we do not block the main thread, then the application is finished and all the running coroutines are also
        cancelled.
         */
        Thread.sleep(1000)
    }

    private fun dependentJobs() {
        java.util.logging.Logger.getLogger("lala").log(Level.INFO, "dependentJobs")
        logger.debug { "dependentJobs" }
        val job1 = GlobalScope.launch(start = CoroutineStart.LAZY) {
            delay(200)
            println("Pong")
            delay(200)
        }
        GlobalScope.launch {
            delay(200)
            println("Ping")
            job1.join() //job1 starts executing now and this statement is suspended until its completed
            println("Ping")
            delay(200)
        }
        Thread.sleep(1000)
    }

    private fun jobHierarchy() {
        with(GlobalScope) {
            val parentJob = launch {
                delay(200)
                println("I’m the parent")
                delay(200)
            }
            //To remove parent-child context merge, launch the below coroutine without passing parentJob context
            launch(context = parentJob) {
                delay(200)
                println("I’m a child")
                delay(200)
            }
            if (parentJob.children.iterator().hasNext()) {
                println("The Job has children ${parentJob.children}")
            } else {
                println("The Job has NO children")
            }
            Thread.sleep(1000) }
    }

    private fun doorOpener() {
        var isDoorOpen = false
        println("Unlocking the door... please wait.\n")
        GlobalScope.launch {
            delay(3000)
            isDoorOpen = true
        }
        GlobalScope.launch {
            repeat(4) {
                println("Trying to open the door...\n")
                delay(800)
                if (isDoorOpen) {
                    println("Opened the door!\n")
                } else {
                    println("The door is still locked\n")
                }
            }
        }
        Thread.sleep(5000)
    }
}