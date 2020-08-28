package com.diraj.coroutines.context_switch

import com.diraj.coroutines.IChapterExecutor
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class ContextSwitch: IChapterExecutor {

    override fun startChapterExecution() {
        /*
        Dispatchers can be confined and unconfined and doesn't depend on fixed threading system. The 4 main dispatchers are:
        Default: Schedules on the default pool of worker threads
        IO: Schedules on the pool of threads for IO tasks
        Main: Schedules on the Main thread for rendering
        Unconfined: Runs on the thread in which this is code is called on

        Custom thread pools can be created using Executors and configured accordingly.
         */
        runBlocking {
            val workStealingExecutor = Executors.newWorkStealingPool(4)
                    .asCoroutineDispatcher()

            withContext(context = workStealingExecutor) {
                println("Printing this on thread: ${Thread.currentThread().name}")
            }
        }

    }
}