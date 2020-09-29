package com.diraj.coroutines.producers_and_actors

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

@ObsoleteCoroutinesApi
class WareHouseRobot(private val robotId: Int,
                     private var packages: List<Package>) {

    fun organizeItems() {
        val itemsToProcess = packages.take(ROBOT_CAPACITY)
        val leftoverItems = packages.drop(ROBOT_CAPACITY)
        packages = itemsToProcess
        val packageIds = packages.map { it.id }
                .fold("") { acc, item -> "$acc$item " }
        if (leftoverItems.isNotEmpty()) { GlobalScope.launch {
            val helperRobot = WareHouseRobot(robotId.inc(), leftoverItems)
            helperRobot.organizeItems()
        } }
        processItems(itemsToProcess)
        println("Robot #$robotId processed following packages:$packageIds")
    }

    private fun processItems(items: List<Package>) {
        val actor = GlobalScope.actor<Package>( capacity = ROBOT_CAPACITY) {
            var hasProcessedItems = false
            while (packages.isNotEmpty()) {
                val currentPackage = poll()
                currentPackage?.run {
                    organize(this)
                    packages -= currentPackage
                    hasProcessedItems = true
                }
                if (hasProcessedItems && currentPackage == null) {
                    cancel()
                }
            }
        }
        items.forEach { actor.offer(it) }
    }

    private fun organize(warehousePackage: Package) =
            println("Organized package " + "${warehousePackage.id}:" + warehousePackage.name)

    companion object {
        private const val ROBOT_CAPACITY = 3
    }
}