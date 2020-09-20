package com.diraj.coroutines.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ClosedSendChannelExceptionExample {
    fun closedSendChannelExceptionExample() {
        val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes", "Strawberry")

        val kotlinChannel = Channel<String>()

        runBlocking {
            launch {
                for (fruit in fruitArray) {
                    try {
                        //if(!kotlinChannel.isClosedForSend) //We can have this check also before sending to avoid having try/catch
                        kotlinChannel.send(fruit)
                    } catch (e: Exception) {
                        println("Exception raised: ${e.javaClass.simpleName}")
                    }
                }

                println("Done!")
            }

            repeat(fruitArray.size - 1) {
                val fruit = kotlinChannel.receive()
                // Conditional close
                if (fruit == "Grapes") {
                    // Signal that closure of channel
                    kotlinChannel.close()
                }
                println(fruit)
            }
        }
    }
}