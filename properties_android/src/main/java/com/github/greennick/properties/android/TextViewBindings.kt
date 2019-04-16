package com.github.greennick.properties.android

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.generic.invoke
import com.github.greennick.properties.subscriptions.Subscription

fun <T> TextView.bindText(property: Property<T>) = property { text = it?.toString().orEmpty() }

fun TextView.bindTextBidirectionally(property: MutableProperty<String>): Subscription {
    val subscription = property {
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