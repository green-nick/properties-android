package com.github.greennick.properties.android

import android.widget.ProgressBar
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.Subscription

fun ProgressBar.bindProgress(property: Property<Int>): Subscription =
    property.subscribe { progress = it }