package com.github.greennick.properties.android

import android.app.Dialog
import android.content.DialogInterface
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.github.greennick.properties.subscriptions.Subscription

/**
 * Binds [Dialog] to Property<Boolean>.
 * Uses [Dialog.show] and [Dialog.dismiss]
 *
 * @param property - show/hide holder
 */
fun Dialog.bindVisibility(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { if (it) show() else dismiss() }

/**
 * Binds [Dialog] to MutableProperty<Boolean>.
 * Uses [Dialog.show] and [Dialog.dismiss]
 *
 * Works from user-interaction perspective
 * uses [DialogInterface.OnCancelListener] and [DialogInterface.OnShowListener]
 * For proper work, call [DialogInterface.cancel] on dialog's button callback.
 *
 * @param property - show/hide holder
 */
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
