package com.diraj.coroutines.coroutine_context.contextProvider

import kotlin.coroutines.CoroutineContext

/*
We can create custom context for threading and error handling for fine grained control of coroutine execution
 */
class CoroutineContextProviderImpl(private val coroutineContext: CoroutineContext): CoroutineContextProvider {

    override fun context(): CoroutineContext = coroutineContext
}