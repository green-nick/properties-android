package com.github.greennick.properties.android

import android.os.Handler
import android.os.Looper
import com.github.greennick.properties.Cancellable
import com.github.greennick.properties.Executor
import com.github.greennick.properties.debouncePropertyOf
import com.github.greennick.properties.generic.MutableProperty

fun <T> handlerDebounceProperty(
    value: T,
    delay: Long,
    handler: Handler = Handler(Looper.getMainLooper())
): MutableProperty<T> = debouncePropertyOf(value, delay, HandlerExecutor(handler))

class HandlerExecutor(private val handler: Handler) : Executor {

    override fun invoke(delay: Long, action: () -> Unit): Cancellable {
        val runnable = Runnable { action() }

        handler.postDelayed(runnable, delay)

        return object : Cancellable {
            override fun invoke() = handler.removeCallbacks(runnable)
        }
    }
}