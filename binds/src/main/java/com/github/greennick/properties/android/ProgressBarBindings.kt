package com.github.greennick.properties.android

import android.widget.ProgressBar
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription

/**
 * Binds [ProgressBar] to Property<Int>.
 * Uses [ProgressBar.setProgress]
 *
 * @param property - progress value holder
 */
fun ProgressBar.bindProgress(property: Property<Int>): ListenableSubscription =
    property.subscribe { progress = it }
