package com.diraj.coroutines.broadcast_channels

import io.reactivex.subjects.BehaviorSubject

class RxBehaviourSubjectExample {
    fun rxBehaviourSubjectExample() {
        val fruitArray = arrayOf("Apple", "Banana", "Pear", "Grapes",
            "Strawberry")

        val subject = BehaviorSubject.create<String>()

        // Producer
        // Send data to subscribers
        subject.apply {
            onNext(fruitArray[0])
            onNext(fruitArray[1])
            onNext(fruitArray[2])
        }

        // Consumers
        subject.subscribe {
            println("Consumer 1: $it")
        }

        subject.subscribe {
            println("Consumer 2: $it")
        }

        subject.apply {
            onNext(fruitArray[3])
            onNext(fruitArray[4])
        }

        // Wait for a keystroke to exit the program
        println("Press a key to exit...")
        readLine()

        // Signal completion
        subject.onComplete()
    }
}