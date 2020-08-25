package com.diraj.coroutines.async_await

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class CustomScope(dispatcher: CoroutineDispatcher): CoroutineScope {

    //For apps with Main thread, can combine as Dispatchers.Main + parentJob for default threading
    private var parentJob = dispatcher + Job()

    override val coroutineContext: CoroutineContext = parentJob

    fun onStart() {
        parentJob = Job()
    }

    fun onStop() {
        parentJob.cancel()
    }
}