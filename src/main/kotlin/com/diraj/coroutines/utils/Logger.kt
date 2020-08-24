package com.diraj.coroutines.utils

import mu.KLogger
import mu.KotlinLogging

object Logger {

    fun<T> getLogger(classRef: Class<T>): KLogger {
        return KotlinLogging.logger(classRef::class.java.simpleName)
    }
}