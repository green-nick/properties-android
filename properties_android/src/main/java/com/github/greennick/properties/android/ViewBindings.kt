@file:Suppress("unused")

package com.github.greennick.properties.android

import android.app.Dialog
import android.view.View
import android.widget.*
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.Subscription

fun View.bindVisibility(property: Property<Boolean>): Subscription =
    property.subscribe { visibility = if (it) View.VISIBLE else View.GONE }

fun Dialog.bindVisibility(property: Property<Boolean>): Subscription =
    property.subscribe { if (it) show() else dismiss() }

fun Dialog.bindVisibilityBidirectionally(property: MutableProperty<Boolean>): Subscription {
    val subscription = property.subscribe { if (it) show() else dismiss() }

    setOnCancelListener { property.value = false }
    setOnShowListener { property.value = true }

    subscription.onUnsubscribe {
        setOnCancelListener(null)
        setOnShowListener(null)
    }

    return subscription
}

fun CompoundButton.bindChecked(property: Property<Boolean>): Subscription =
    property.subscribe(::setChecked)

fun View.bindEnabled(property: Property<Boolean>): Subscription =
    property.subscribe(::setEnabled)

fun EditText.bindError(property: Property<out String?>): Subscription =
    property.subscribe {
        error = it
        requestFocus()
    }

fun ProgressBar.bindProgress(property: Property<Int>): Subscription =
    property.subscribe { progress = it }

fun SeekBar.bindProgressBidirectionally(property: MutableProperty<Int>): Subscription {
    val subscription = property.subscribe {
        progress = it
    }
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                property.value = progress
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}

    })
    subscription.onUnsubscribe { setOnSeekBarChangeListener(null) }
    return subscription
}

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
    subscription.onUnsubscribe {
        onItemSelectedListener = null
    }
    return subscription
}

fun CompoundButton.bindCheckedBidirectionally(property: MutableProperty<Boolean>): Subscription {
    val subscription = property.subscribe(::setChecked)
    setOnCheckedChangeListener { _, checked -> property.value = checked }
    subscription.onUnsubscribe { setOnCheckedChangeListener(null) }
    return subscription
}