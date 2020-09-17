package com.diraj.coroutines.exception_handling

import com.diraj.coroutines.IChapterExecutor

class ExceptionHandling: IChapterExecutor {

    override fun startChapterExecution() {
        //runBlocking {
            //CoroutineExceptionHandlingExample().catchSomeExceptions()
       //}
        //ExceptionHandlingForLaunch().handleLaunchExceptions()
        //GlobalExceptionHandler().handleExceptionsGlobally()
        //TryCatch().handleAsyncException()
        //ExceptionHandlingForChild().handleMultipleChildExceptions()
        CallbackWrapping().wrapCallbackWithCoroutine()
    }
}