package com.github.greennick.properties.android

import android.widget.SeekBar
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.subscriptions.Subscription

/**
 * Binds [SeekBar] to MutableProperty<Int>.
 * Uses [bindProgress]
 *
 * From user-interaction perspective
 * Uses [SeekBar.OnSeekBarChangeListener]
 * Set new value to property only if it's came from user.
 *
 * @param property - progress value holder
 */
fun SeekBar.bindProgressBidirectionally(property: MutableProperty<Int>): Subscription {
    val subscription = bindProgress(property)
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                property.value = progress
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}

    })
    subscription.onUnsubscribe { setOnSeekBarChangeListener(null) }
    return subscription
}
