package com.github.greennick.properties.android

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.github.greennick.properties.subscriptions.Subscription

/**
 * Binds [TextView] to Property<Any?>.
 * Uses [TextView.setText]
 * Will map object through `toString` or set empty string if null
 *
 * @param property - object holder
 */
fun TextView.bindText(property: Property<Any?>): ListenableSubscription =
    property.subscribe { text = it?.toString().orEmpty() }

/**
 * Binds [TextView] to Property<Int>.
 * Uses [TextView.setText]
 *
 * @param property - string resource id holder
 */
fun TextView.bindTextId(property: Property<Int>): ListenableSubscription =
    property.subscribe { setText(it) }

/**
 * Binds [TextView] to Property<CharSequence?>.
 * Uses [TextView.setHint]
 *
 * @param property - hint text holder
 */
fun TextView.bindHint(property: Property<CharSequence?>): ListenableSubscription =
    property.subscribe { hint = it }

/**
 * Binds [TextView] to Property<Int>.
 * Uses [TextView.setHint]
 *
 * @param property - string resource id holder
 */
fun TextView.bindHintId(property: Property<Int>): ListenableSubscription =
    property.subscribe { setHint(it) }

/**
 * Binds [TextView] to MutableProperty<String>.
 * Uses [TextView.setText]
 * From user perspective uses [TextWatcher.onTextChanged].
 * Received CharSequence converted with `toString` or empty string if null
 *
 * @param property - text holder
 */
fun TextView.bindTextBidirectionally(property: MutableProperty<String>): Subscription {
    val subscription = property.subscribe {
        if (text?.toString() != it) {
            text = it
        }
    }
    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            property.value = s?.toString().orEmpty()
        }

        override fun afterTextChanged(s: Editable) {
        }
    }
    addTextChangedListener(watcher)
    subscription.onUnsubscribe { removeTextChangedListener(watcher) }
    return subscription
}

fun TextView.textChanged(action: (String) -> Unit): TextWatcher {
    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            action(s?.toString().orEmpty())
        }

        override fun afterTextChanged(s: Editable) {

        }
    }
    addTextChangedListener(watcher)
    return watcher
}
