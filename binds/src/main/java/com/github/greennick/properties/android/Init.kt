package com.github.greennick.properties.android

import android.os.Handler
import android.os.Looper
import com.github.greennick.properties.debounce.*
import com.github.greennick.properties.debouncePropertyOf
import com.github.greennick.properties.generic.MutableProperty

/**
 * Extension for [debouncePropertyOf] which allows to use
 * [Handler] for scheduling and process updates
 *
 * @param delay - threshold which has to be passed, before new value will be set.
 * @param handler - [Handler] used to process updates, default based on [Looper.getMainLooper]
 */
fun <T> handlerDebouncePropertyOf(
    value: T,
    delay: Long,
    handler: Handler = Handler(Looper.getMainLooper())
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
