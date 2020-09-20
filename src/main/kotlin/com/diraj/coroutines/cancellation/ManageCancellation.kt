package com.diraj.coroutines.cancellation

import com.diraj.coroutines.IChapterExecutor

class ManageCancellation: IChapterExecutor {

    override fun startChapterExecution() {
        //CancelCoroutine().cancelCoroutine()
        //CancellationExceptionExample().cancellationExample()
        //JoinCoroutineExample().joinCoroutineExample()
        //JoinAllCoroutineExample().joinAllCoroutineExample()
        //CancelAndJoinCoroutineExample().cancelAndJoinCoroutineExample()
        //CancelChildren().cancelChildren()
        //WithTimeout().withTimeoutExample()
        //TimeoutCancellationExceptionHandling().timeoutCancellationExceptionHandling()
        WithTimeoutOrNullExample().withTimeoutOrNullExample()
    }
}