package com.diraj.coroutines.producers_and_actors

import com.diraj.coroutines.IChapterExecutor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class ProducersActorsExecutor: IChapterExecutor {

    override fun startChapterExecution() {
        //Produce().producingValuesExample()
        //Produce().checkFullAndProduceValuesExample()
        //Produce().suspendedProduceAndConsume()
        //Actor().actorExample()
        Actor().wareHouseExample()
    }
}