package com.github.greennick.properties.android

import android.app.Activity
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
fun TextView.bindText(property: Property<CharSequence?>): ListenableSubscription =
    property.subscribe { text = it }

/**
 * Binds [TextView] to Property<Int>.
 * Uses [TextView.setText]
 *
 * @param property - string resource id holder
 */
@JvmName("bindTextId")
fun TextView.bindText(property: Property<Int?>): ListenableSubscription =
    property.subscribe { if (it != null) setText(it) else text = null }

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
@JvmName("bindHintId")
fun TextView.bindHint(property: Property<Int?>): ListenableSubscription =
    property.subscribe { if (it != null) setHint(it) else text = null }

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
    val watcher = textChanged { property.value = it }
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

fun Activity.textChanged(viewId: Int, action: (String) -> Unit): TextWatcher =
    find<TextView>(viewId).textChanged(action)

fun TextView.actionListener(listener: ((Int) -> Unit)? = null) {
    if (listener == null) {
        setOnEditorActionListener(null)
    } else {
        setOnEditorActionListener { _, actionId, _ ->
            listener(actionId)
            true
        }
    }
}

fun Activity.actionListener(viewId: Int, listener: ((Int) -> Unit)? = null): Unit =
    find<TextView>(viewId).actionListener(listener)
