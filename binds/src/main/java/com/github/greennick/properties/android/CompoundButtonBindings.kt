package com.github.greennick.properties.android

import android.app.Activity
import android.widget.CompoundButton
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.github.greennick.properties.subscriptions.Subscription

fun CompoundButton.bindChecked(property: Property<Boolean>): ListenableSubscription =
    property.subscribe(::setChecked)

fun CompoundButton.bindCheckedBidirectionally(property: MutableProperty<Boolean>): Subscription {
    val subscription = bindChecked(property)
    setOnCheckedChangeListener { _, checked -> property.value = checked }
    subscription.onUnsubscribe { setOnCheckedChangeListener(null) }
    return subscription
}

fun Activity.bindChecked(id: Int, property: Property<Boolean>): ListenableSubscription =
    findViewById<CompoundButton>(id).bindChecked(property)

fun Activity.bindCheckedBidirectionally(id: Int, property: MutableProperty<Boolean>): Subscription =
    findViewById<CompoundButton>(id).bindCheckedBidirectionally(property)
