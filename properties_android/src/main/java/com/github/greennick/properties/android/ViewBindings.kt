@file:Suppress("unused")

package com.github.greennick.properties.android

import android.view.View
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.Subscription

fun View.bindVisibility(property: Property<Boolean>): Subscription =
    property.subscribe { visibility = if (it) View.VISIBLE else View.GONE }

fun View.bindEnabled(property: Property<Boolean>): Subscription =
    property.subscribe(::setEnabled)