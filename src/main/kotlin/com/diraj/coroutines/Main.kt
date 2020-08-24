package com.diraj.coroutines

import java.io.File
import java.util.logging.Level
import java.util.logging.Logger

fun main(vararg args: String) {
    val logger = Logger.getLogger("Main")
    if(args.isNotEmpty()) {
        File(args[0])
            .readLines()
            .forEach { entry ->
                val entryInfo = entry.split(" ")
                if(entryInfo[1] == "yes") {
                    val fullyQualifiedClass = entryInfo[0]
                    val classRef = Class.forName(fullyQualifiedClass)
                    val classRefObject = classRef.newInstance() as IChapterExecutor
                    logger.log(Level.INFO, "Executing content for : $fullyQualifiedClass")
                    classRefObject.startChapterExecution()
                }
            }
    }
}