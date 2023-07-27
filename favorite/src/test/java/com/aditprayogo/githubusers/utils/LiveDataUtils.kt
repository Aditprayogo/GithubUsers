package com.aditprayogo.githubusers.utils

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by Aditiya Prayogo on 4/10/2023.
 */
fun <T> LiveData<T>.getAwaitOrValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T? {
    var value: T? = null
    val latch = CountDownLatch(1)
    observeForever {
        value = it
        latch.countDown()
    }
    return try {
        runBlocking {
            withContext(Dispatchers.IO) {
                latch.await(time, timeUnit)
            }
        }
        value
    } finally {
        removeObserver {}
    }
}