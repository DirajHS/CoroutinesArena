package com.diraj.coroutines.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class PipelineExample {

    internal data class Fruit(override val name: String, override val color: String) : Item
    internal data class Vegetable(override val name: String, override val color: String) : Item

    fun pipelineExample() {
        runBlocking {
            // Setup the pipeline
            val itemsChannel = produceItems()
            val fruitsChannel = isFruit(itemsChannel)
            val redChannel = isRed(fruitsChannel)

            // Print out the fruits that are red in color
            for (item in redChannel) {
                print("${item.name}, ")
            }

            // Cancel all the coroutines for good measure
            redChannel.cancel()
            fruitsChannel.cancel()
            itemsChannel.cancel()

            println("Done!")
        }
    }

    // ------------ Helper Methods ------------
    private fun isFruit(item: Item): Boolean = item is Fruit

    private fun isRed(item: Item): Boolean = (item.color == "Red")


    // ------------ Pipeline ------------
    // Channel 1: Produces a finite number of items
    // which are either a fruit or vegetable
    private fun produceItems() = GlobalScope.produce {
        val itemsArray = ArrayList<Item>()
        itemsArray.add(Fruit("Apple", "Red"))
        itemsArray.add(Vegetable("Zucchini", "Green"))
        itemsArray.add(Fruit("Grapes", "Green"))
        itemsArray.add(Vegetable("Radishes", "Red"))
        itemsArray.add(Fruit("Banana", "Yellow"))
        itemsArray.add(Fruit("Cherries", "Red"))
        itemsArray.add(Vegetable("Broccoli", "Green"))
        itemsArray.add(Fruit("Strawberry", "Red"))

        // Send each item in the channel
        itemsArray.forEach {
            send(it)
        }
    }

    // Channel 2: Produces only the items that are a fruit
    private fun isFruit(items: ReceiveChannel<Item>) = GlobalScope.produce {
        for (item in items) {
            // Send each item in the channel only if it is a fruit
            if (isFruit(item)) {
                send(item)
            }
        }
    }

    // Channel 3: Produces only the items that red in color
    private fun isRed(items: ReceiveChannel<Item>) = GlobalScope.produce {
        for (item in items) {
            // Send each item in the channel only if it is red in color
            if (isRed(item)) {
                send(item)
            }
        }
    }
}