package com.github.greennick.properties.android

import android.widget.SeekBar
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.invoke
import com.github.greennick.properties.subscriptions.Subscription

fun SeekBar.bindProgressBidirectionally(property: MutableProperty<Int>): Subscription {
    val subscription = property {
        progress = it
    }
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