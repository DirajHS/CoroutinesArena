package com.diraj.coroutines.async_await

import com.diraj.coroutines.IChapterExecutor
import kotlinx.coroutines.*
import java.io.File
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.coroutineContext

class AsyncAwait: IChapterExecutor {

    /*
    Problems in existing approaches for asynchronous operations:
    - Promises: Rely on call-chain structure, can be broken if no return statement, has to be executed on MainThread only
    - Futures: Blocks the current thread, otherwise keep checking for completion
     */
    override fun startChapterExecution() {
        runBlocking {
            println("Thread: ${Thread.currentThread().name}")
            val dispatcher = coroutineContext[ContinuationInterceptor]
                    as CoroutineDispatcher
            val scope = CustomScope(dispatcher) //Create custom scope with Main thread as default threading

            scope.launch(context = Dispatchers.IO) {
                println("Launching in custom scope, ${Thread.currentThread().name}")
                multipleAwaitExample(this)
            }

            delay(5000) // to prevent app exit
            //scope.onStop() --> We run this when app is exiting to cancel all the child coroutines with this scope
        }
    }

    private suspend fun multipleAwaitExample(scope: CoroutineScope) {
        val userId = 992 // the ID of the user we want

        val userDeferred = getUserByIdFromNetworkAsync(userId, scope)
        val usersFromFileDeferred = readUsersFromFileAsync("users.txt", scope)

        if (coroutineContext.isActive) {
            println("Finding user")
            val userStoredInFile = checkUserExists(
                    userDeferred.await(),
                    usersFromFileDeferred.await()
            )

            if (userStoredInFile) {
                println("Found user in file!")
            } else {
                println("User not found")
            }
        }

    }

    private fun checkUserExists(user: User, users: List<User>): Boolean {
        return user in users
    }

    private fun getUserByIdFromNetworkAsync(
            userId: Int,
            scope: CoroutineScope) =
            scope.async {
                if (!scope.isActive) {
                    return@async User(0, "", "")
                }
                println("Retrieving user from network")
                delay(3000)
                println("Still in the coroutine")

                User(userId, "Filip", "Babic") // we simulate the network call
            }

    private fun readUsersFromFileAsync(
            filePath: String,
            scope: CoroutineScope) =
            scope.async {
                println("Reading the file of users")
                delay(1000)

                if (!scope.isActive) {
                    return@async emptyList<User>()
                }

                File(filePath)
                        .readLines()
                        .asSequence()
                        .filter { it.isNotEmpty() }
                        .map {
                            val data = it.split(" ") // [id, name, lastName]

                            if (data.size == 3) data else emptyList()
                        }
                        .filter {
                            it.isNotEmpty()
                        }
                        .map {
                            val userId = it[0].toInt()
                            val name = it[1]
                            val lastName = it[2]

                            User(userId, name, lastName)
                        }
                        .toList()
            }
}