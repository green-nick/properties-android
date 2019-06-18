package com.github.greennick.properties.android

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.github.greennick.properties.subscriptions.Subscription

fun TextView.bindText(property: Property<out Any?>): ListenableSubscription =
    property.subscribe { text = it?.toString().orEmpty() }

fun TextView.bindTextId(property: Property<Int>): ListenableSubscription =
    property.subscribe { setText(it) }

fun TextView.bindHint(property: Property<out CharSequence?>): ListenableSubscription =
    property.subscribe { hint = it }

fun TextView.bindHintId(property: Property<Int>): ListenableSubscription =
    property.subscribe { setHint(it) }

fun TextView.bindTextBidirectionally(property: MutableProperty<String>): Subscription {
    val subscription = property.subscribe {
        if (text?.toString() != it) {
            text = it
        }
    }
    val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {

        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            property.value = s?.toString().orEmpty()
        }
    }
    addTextChangedListener(watcher)
    subscription.onUnsubscribe { removeTextChangedListener(watcher) }
    return subscription
}

fun Activity.bindText(id: Int, property: Property<out Any?>): ListenableSubscription =
    findViewById<TextView>(id).bindText(property)

fun Activity.bindTextId(id: Int, property: Property<Int>): ListenableSubscription =
    findViewById<TextView>(id).bindTextId(property)

fun Activity.bindHint(id: Int, property: Property<out CharSequence?>): ListenableSubscription =
    findViewById<TextView>(id).bindHint(property)

fun Activity.bindHintId(id: Int, property: Property<Int>): ListenableSubscription =
    findViewById<TextView>(id).bindHintId(property)

fun Activity.bindTextBidirectionally(id: Int, property: MutableProperty<String>): Subscription =
    findViewById<TextView>(id).bindTextBidirectionally(property)
