package com.github.greennick.properties.android

import android.widget.ProgressBar
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription

fun ProgressBar.bindProgress(property: Property<Int>): ListenableSubscription =
    property.subscribe { progress = it }