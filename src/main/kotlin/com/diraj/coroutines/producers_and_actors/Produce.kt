package com.diraj.coroutines.producers_and_actors

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

@ExperimentalCoroutinesApi
class Produce {
    fun producingValuesExample() {
        val producer = GlobalScope.produce(capacity = 10) {
            while (isActive) {
                if (!isClosedForSend) {
                    val number = Random.nextInt(0, 20)
                    if (offer(number)) {
                        println("$number sent")
                    } else {
                        println("$number discarded")
                    }
                }
            }
        }
        Thread.sleep(30)
    }

    fun checkFullAndProduceValuesExample() {
        val producer = GlobalScope.produce(capacity = 10) {
            while (isActive) {
                val number = Random.nextInt(0, 20)
                if (!isClosedForSend) {
                    if (offer(number)) {
                        println("$number sent")
                    }
                } else {
                    println("$number discarded")
                }
            }
        }
        Thread.sleep(30L)
    }

    fun suspendedProduceAndConsume() {
        val producer = GlobalScope.produce(capacity = 10) {
            while (isActive) {
                val number = Random.nextInt(0, 20)
                send(number)
                println("$number sent")
            }
        }

        /*while (!producer.isClosedForReceive) {
            val number = producer.poll()
            if (number != null) {
                println("$number received")
            }
        }*/

        /*
        // Better approach than the above
        GlobalScope.launch {
            producer.consumeEach {
                println("$it received")
            }
        }*/

        /*
        // Better approach than the above
        GlobalScope.launch {
            while (isActive) {
                val value = producer.receive()
                println("$value received")
            }
        }*/

        // Better approach than the above
        GlobalScope.launch {
            for (value in producer) {
                println("$value received")
            }
        }

        Thread.sleep(30L)


    }


}