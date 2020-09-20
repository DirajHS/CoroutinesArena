package com.diraj.coroutines.channels

import com.diraj.coroutines.IChapterExecutor
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ChannelsExecutor: IChapterExecutor {

    @ExperimentalCoroutinesApi
    override fun startChapterExecution() {
        //ChannelsIntro().channelsIntro()
        //PipelineExample().pipelineExample()
        //FanOut().fanOutExample()
        //FanIn().fanIn()
        //BufferedChannelExample().bufferedChannelExample()
        //OfferExample().offerExample()
        //PollExample().pollExample()
        //ClosedReceiveChannelExceptionExample().closedReceiveChannelExceptionExample()
        //ClosedSendChannelExceptionExample().closedSendChannelExceptionExample()
        BlockingQueue().blockingQueue()
    }
}