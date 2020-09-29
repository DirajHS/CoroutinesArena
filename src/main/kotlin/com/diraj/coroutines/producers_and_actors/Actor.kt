package com.diraj.coroutines.producers_and_actors

import kotlinx.coroutines.CompletionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlin.random.Random

@ObsoleteCoroutinesApi
class Actor {
    // 1
    object ActorCompletionHandler : CompletionHandler {
        override fun invoke(cause: Throwable?) {
            println("Completed!")
        }
    }

    fun actorExample() {
        // 2
        val actor = GlobalScope.actor<String>( onCompletion = ActorCompletionHandler, capacity = 10) {
            // 3
            for (data in channel) {
                println(data)
            }
        }
        // 4
        repeat((1..10).count()) {
            actor.offer(Random.nextInt(0, 20).toString())
        }
        // 5
        actor.close()
        // 6
        Thread.sleep(500L)
    }

    fun wareHouseExample() {
        val items = listOf(
                Package(1, "coffee"),
                Package(2, "chair"),
                Package(3, "sugar"),
                Package(4, "t-shirts"),
                Package(5, "pillowcases"),
                Package(6, "cellphones"),
                Package(7, "skateboard"),
                Package(8, "cactus plants"),
                Package(9, "lamps"),
                Package(10, "ice cream"),
                Package(11, "rubber duckies"),
                Package(12, "blankets"),
                Package(13, "glass")
        )

        val initialRobot = WareHouseRobot(1, items)

        initialRobot.organizeItems()
        Thread.sleep(5000)
    }

}