package com.diraj.coroutines.suspending_functions

import com.diraj.coroutines.IChapterExecutor
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SuspendingFunctions: IChapterExecutor {

    override fun startChapterExecution() {
        println("Started execution on thread ${Thread.currentThread().name}")
        executeBackground {
            val user = getValue { getUserFromNetwork("101") }
            println(user)
        }
        Thread.sleep(3000) //prevent application termination before coroutine termination
    }

     private fun getUserFromNetwork(userId: String): User {
         //This routine runs on worker thread, so we can execute time consuming tasks here
         println("Getting user from thread, ${Thread.currentThread().name}")
         return User(userId, "Filip")
    }

    suspend fun readFileSuspend(path: String): File =
            suspendCoroutine {
                readFile(path) { file ->
                    it.resume(file)
                }
            }

    private fun readFile(path: String, onReady: (File) -> Unit) {
        Thread.sleep(1000)
        // some heavy operation

        onReady(File(path))
    }
}