package com.diraj.coroutines.broadcast_channels

import com.diraj.coroutines.IChapterExecutor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class BroadcastChannelsExecutor: IChapterExecutor {

    override fun startChapterExecution() {
        //RaceConditionChannel().raceConditionChannelExample()
        //BroadcastChannelOpenSubscription().broadcastChannelOpenSubscriptionExample()
        //BroadcastChannelExample().broadcastChannelExample()
        //ConflatedBroadcastChannelExample().conflatedBroadcastChannelExample()
        //RxSubjectExample().rxSubjectExample()
        RxBehaviourSubjectExample().rxBehaviourSubjectExample()
    }
}