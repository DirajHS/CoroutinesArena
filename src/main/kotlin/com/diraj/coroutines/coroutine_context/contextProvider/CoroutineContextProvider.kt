package com.diraj.coroutines.coroutine_context.contextProvider

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {

    fun context(): CoroutineContext
}