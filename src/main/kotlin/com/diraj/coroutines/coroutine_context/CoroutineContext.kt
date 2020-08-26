package com.diraj.coroutines.coroutine_context

import com.diraj.coroutines.IChapterExecutor
import com.diraj.coroutines.coroutine_context.contextProvider.CoroutineContextProviderImpl
import kotlinx.coroutines.*

class CoroutineContext: IChapterExecutor {

    override fun startChapterExecution() {

        /*
        Coroutine is built upon context elements, which are:
        • Job: A cancellable piece of work, which has a defined lifecycle.
        • ContinuationInterceptor: A mechanism which listens to the continuation within a coroutine and intercepts its resumption.
        • CoroutineExceptionHandler: A construct which handles exceptions in coroutines.
         */
        val coroutineErrorHandler1 = CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace() // we just print the error here
        }

        val coroutineErrorHandler2 = CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.printStackTrace() // we just print the error here
        }

        val emptyParentJob = Job() //A job that can be tied to parent lifecycle CoroutineScope for easier cancellation

        val combinedContext1 = CoroutineContextProviderImpl(Dispatchers.Default + coroutineErrorHandler1 + emptyParentJob)
        val combinedContext2 = CoroutineContextProviderImpl(Dispatchers.Default + coroutineErrorHandler2 + emptyParentJob)

        GlobalScope.launch(context = combinedContext1.context()) {
            println(Thread.currentThread().name)
            //throw RuntimeException("Handle in coroutineErrorHandler1")
        }

        GlobalScope.launch(context = combinedContext2.context()) {
            println(Thread.currentThread().name)
            //throw IllegalStateException("Handle in coroutineErrorHandler2")
        }

        Thread.sleep(50)
    }
}