package com.diraj.coroutines.broadcast_channels

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class ConflatedBroadcastChannelExample {
    fun conflatedBroadcastChannelExample() {
        val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes",
                "Strawberry")

        val kotlinChannel = ConflatedBroadcastChannel<String>()

        runBlocking {

            // Producer
            // Send data in channel
            kotlinChannel.apply {
                send(fruitArray[0])
                send(fruitArray[1])
                send(fruitArray[2])
            }

            // Consumers
            GlobalScope.launch {
                kotlinChannel.consumeEach { value ->
                    println("Consumer 1: $value")
                }
            }
            GlobalScope.launch {
                kotlinChannel.consumeEach { value ->
                    println("Consumer 2: $value")
                }
            }

            kotlinChannel.apply {
                send(fruitArray[3])
                send(fruitArray[4])
            }

            // Wait for a keystroke to exit the program
            println("Press a key to exit...")
            readLine()

            // Close the channel so as to cancel the consumers on it too
            kotlinChannel.close()
        }
    }
}