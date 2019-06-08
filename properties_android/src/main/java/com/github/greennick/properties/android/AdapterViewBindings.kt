package com.github.greennick.properties.android

import android.view.View
import android.widget.AdapterView
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.subscriptions.Subscription

fun AdapterView<*>.bindSelectionBidirectionally(property: MutableProperty<Int>): Subscription {
    val subscription = property.subscribe {
        if (it >= 0 && adapter.count >= it) setSelection(it)
    }

    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            property.value = position
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            property.value = -1
        }
    }
    subscription.onUnsubscribe { onItemSelectedListener = null }
    return subscription
}