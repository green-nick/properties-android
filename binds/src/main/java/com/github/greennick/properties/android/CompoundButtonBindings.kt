package com.github.greennick.properties.android

import android.widget.CompoundButton
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.github.greennick.properties.subscriptions.Subscription

/**
 * Binds [CompoundButton] to Property<Boolean>.
 * Uses [CompoundButton.setChecked]
 *
 * @param property - checked/unchecked holder
 */
fun CompoundButton.bindChecked(property: Property<Boolean>): ListenableSubscription =
    property.subscribe(::setChecked)

/**
 * Binds [CompoundButton] to MutableProperty<Boolean>.
 * Uses [CompoundButton.setChecked]
 *
 * Works the same from user-interaction perspective.
 * Uses [CompoundButton.OnCheckedChangeListener]
 *
 * @param property - checked/unchecked holder
 */
fun CompoundButton.bindCheckedBidirectionally(property: MutableProperty<Boolean>): Subscription {
    val subscription = bindChecked(property)
    setOnCheckedChangeListener { _, checked -> property.value = checked }
    subscription.onUnsubscribe { setOnCheckedChangeListener(null) }
    return subscription
}
