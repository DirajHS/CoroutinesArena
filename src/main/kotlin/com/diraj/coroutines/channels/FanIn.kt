package com.diraj.coroutines.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class FanIn {

    data class Fruit(override val name: String, override val color: String) : Item
    data class Vegetable(override val name: String, override val color: String) : Item

    fun fanIn() {
        runBlocking {

            // Initialize the Channels
            val destinationChannel = Channel<Item>()

            val fruitsChannel = Channel<Item>()
            val vegetablesChannel = Channel<Item>()

            var eitherSourceCompleted = false

            // Launch the coroutine in order to produce items that are fruits
            launch {
                produceItems().forEach {
                    if (isFruit(it)) {
                        fruitsChannel.send(it)
                    }
                }
                fruitsChannel.close()
            }

            // Launch the coroutine in order to produce items that are vegetables
            launch {
                produceItems().forEach {
                    if (isVegetable(it)) {
                        vegetablesChannel.send(it)
                    }
                }
                vegetablesChannel.close()
            }


            // Multiplex values into the common destination channel
            launch {
                for (item in fruitsChannel) {
                    destinationChannel.send(item)
                }
                if(eitherSourceCompleted)
                    destinationChannel.close()
                else
                    eitherSourceCompleted = true
            }

            // Multiplex values into the common destination channel
            launch {
                for (item in vegetablesChannel) {
                    destinationChannel.send(item)
                }
                if(eitherSourceCompleted)
                    destinationChannel.close()
                else
                    eitherSourceCompleted = true
            }

            // Consume the channel
            destinationChannel.consumeEach {
                if (isFruit(it)) {
                    println("${it.name} is a fruit")
                } else if (isVegetable(it)) {
                    println("${it.name} is a vegetable")
                }
            }


            // Cancel everything
            coroutineContext.cancelChildren()
        }
    }

    // ------------ Helper Methods ------------
    private fun isFruit(item: Item) = item is Fruit

    private fun isVegetable(item: Item) = item is Vegetable

    // Produces a finite number of items
    // which are either a fruit or vegetable
    private fun produceItems(): ArrayList<Item> {
        val itemsArray = ArrayList<Item>()
        itemsArray.add(Fruit("Apple", "Red"))
        itemsArray.add(Vegetable("Zucchini", "Green"))
        itemsArray.add(Fruit("Grapes", "Green"))
        itemsArray.add(Vegetable("Radishes", "Red"))
        itemsArray.add(Fruit("Banana", "Yellow"))
        itemsArray.add(Fruit("Cherries", "Red"))
        itemsArray.add(Vegetable("Broccoli", "Green"))
        itemsArray.add(Fruit("Strawberry", "Red"))
        itemsArray.add(Vegetable("Red bell pepper", "Red"))
        return itemsArray
    }

}