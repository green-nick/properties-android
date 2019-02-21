@file:Suppress("unused")

package com.github.greennick.properties.android

import android.app.Dialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.github.greennick.properties.MutableProperty
import com.github.greennick.properties.Property
import com.github.greennick.properties.Subscription

fun <T> TextView.bindText(property: Property<T>): Subscription =
    property.subscribe { text = it?.toString().orEmpty() }

fun View.bindVisibility(property: Property<Boolean>): Subscription =
    property.subscribe { visibility = if (it) View.VISIBLE else View.GONE }

fun Dialog.bindVisibility(property: Property<Boolean>): Subscription =
    property.subscribe { if (it) show() else dismiss() }

fun CompoundButton.bindChecked(property: Property<Boolean>): Subscription =
    property.subscribe(::setChecked)

fun View.bindEnabled(property: Property<Boolean>): Subscription =
    property.subscribe(::setEnabled)

fun EditText.bindError(property: Property<String?>): Subscription =
    property.subscribe {
        error = it
        requestFocus()
    }

fun SeekBar.bindProgressBidirectionally(property: MutableProperty<Int>): Subscription {
    val subscription = property.subscribe {
        progress = it
    }
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                property.set(progress)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}

    })
    subscription.onUnsubscribe { setOnSeekBarChangeListener(null) }
    return subscription
}

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
            property.set(s?.toString().orEmpty())
        }
    }
    addTextChangedListener(watcher)
    subscription.onUnsubscribe { removeTextChangedListener(watcher) }
    return subscription
}

fun AdapterView<*>.bindSelectionBidirectionally(property: MutableProperty<Int>): Subscription {
    val subscription = property.subscribe {
        println("Property value $it, adapter has ${adapter.count} items")
        if (it >= 0 && adapter.count >= it) setSelection(it)
    }

    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            println("Property selected $position")
            property.set(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            println("Property nothing selected")
            property.set(-1)
        }
    }
    subscription.onUnsubscribe {
        println("Property unsubscribing")
        onItemSelectedListener = null
    }
    return subscription
}

fun CompoundButton.bindCheckedBidirectionally(property: MutableProperty<Boolean>): Subscription {
    val subscription = property.subscribe(::setChecked)
    setOnCheckedChangeListener { _, checked -> property.set(checked) }
    subscription.onUnsubscribe { setOnCheckedChangeListener(null) }
    return subscription
}