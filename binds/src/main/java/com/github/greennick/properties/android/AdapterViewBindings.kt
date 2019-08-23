package com.github.greennick.properties.android

import android.view.View
import android.widget.AdapterView
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.subscriptions.Subscription

/**
 * Binds [AdapterView] to MutableProperty<Int>.
 * It skips any value less than 0 and sets other through [AdapterView.setSelection].
 * On the other hand, it sets selected position to Property or -1 if nothing selected.
 * Uses [AdapterView.OnItemSelectedListener]
 *
 * @param property - selection holder
 */
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
