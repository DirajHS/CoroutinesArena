package com.diraj.coroutines.channels

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.util.concurrent.LinkedBlockingQueue

class BlockingQueue {
    fun blockingQueue() {
        val queue = LinkedBlockingQueue<Int>()
        runBlocking {

            launch {
                (1..5).forEach {
                    queue.put(it)
                    yield()
                    println("Produced ${it}")
                }
            }

            launch {
                while (true) {
                    println("Consumed ${queue.take()}")
                    yield()
                }
            }
        }
    }
}