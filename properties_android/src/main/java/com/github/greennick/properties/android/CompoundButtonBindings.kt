package com.github.greennick.properties.android

import android.widget.CompoundButton
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.Subscription

fun CompoundButton.bindChecked(property: Property<Boolean>) = property.subscribe(::setChecked)

fun CompoundButton.bindCheckedBidirectionally(property: MutableProperty<Boolean>): Subscription {
    val subscription = property.subscribe(::setChecked)
    setOnCheckedChangeListener { _, checked -> property.value = checked }
    subscription.onUnsubscribe { setOnCheckedChangeListener(null) }
    return subscription
}