package com.github.greennick.properties.android

import android.os.Handler
import android.os.Looper
import com.github.greennick.properties.debounce.Cancellable
import com.github.greennick.properties.debounce.Executor
import com.github.greennick.properties.debouncePropertyOf
import com.github.greennick.properties.generic.MutableProperty

private val mainHandler = Handler(Looper.getMainLooper())

/**
 * Extension for [debouncePropertyOf] which allows to use
 * [Handler] for scheduling and process updates
 *
 * @param delay - threshold which has to be passed, before new value will be set.
 * @param handler - [Handler] used to process updates, default is [mainHandler] based on MainLooper
 */
fun <T> debouncePropertyOf(
    value: T,
    delay: Long,
    handler: Handler = mainHandler
): MutableProperty<T> = debouncePropertyOf(value, delay, HandlerExecutor(handler))

private class HandlerExecutor(private val handler: Handler) : Executor {

    override fun invoke(delay: Long, action: () -> Unit): Cancellable {
        val runnable = Runnable { action() }

        handler.postDelayed(runnable, delay)

        return object : Cancellable {
            override fun invoke() = handler.removeCallbacks(runnable)
        }
    }
}

/**
 * Schedule [value] to be set into Property on Main UI thread
 */
fun <T> MutableProperty<T>.postSet(value: T) {
    if (Looper.getMainLooper().thread === Thread.currentThread()) {
        set(value)
    } else {
        mainHandler.post { set(value) }
    }
}
