package com.diraj.coroutines.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BufferedChannelExample {
    fun bufferedChannelExample() {
        val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes", "Strawberry")

        val kotlinBufferedChannel = Channel<String>(2)

        runBlocking {
            launch {
                for (fruit in fruitArray) {
                    kotlinBufferedChannel.send(fruit)
                    println("Produced: $fruit")
                }
                kotlinBufferedChannel.close()
            }

            launch {
                for (fruit in kotlinBufferedChannel) {
                    println("Consumed: $fruit")
                    delay(1000)
                }
            }
        }
    }
}