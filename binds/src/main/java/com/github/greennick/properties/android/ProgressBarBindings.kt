package com.github.greennick.properties.android

import android.app.Activity
import android.widget.ProgressBar
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription

fun ProgressBar.bindProgress(property: Property<Int>): ListenableSubscription =
    property.subscribe { progress = it }

fun Activity.bindProgress(id: Int, property: Property<Int>): ListenableSubscription =
        findViewById<ProgressBar>(id).bindProgress(property)
