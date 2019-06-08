package com.github.greennick.properties.android

import android.app.Dialog
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.github.greennick.properties.subscriptions.Subscription

fun Dialog.bindVisibility(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { if (it) show() else dismiss() }

fun Dialog.bindVisibilityBidirectionally(property: MutableProperty<Boolean>): Subscription {
    val subscription = bindVisibility(property)

    setOnCancelListener { property.value = false }
    setOnShowListener { property.value = true }

    subscription.onUnsubscribe {
        setOnCancelListener(null)
        setOnShowListener(null)
    }

    return subscription
}