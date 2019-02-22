@file:Suppress("unused")

package com.github.greennick.properties.android

import android.app.Dialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.primitives.booleans.BooleanProperty
import com.github.greennick.properties.primitives.booleans.MutableBooleanProperty
import com.github.greennick.properties.primitives.doubles.DoubleProperty
import com.github.greennick.properties.primitives.ints.IntProperty
import com.github.greennick.properties.primitives.ints.MutableIntProperty
import com.github.greennick.properties.primitives.longs.LongProperty
import com.github.greennick.properties.subscriptions.Subscription

fun <T> TextView.bindText(property: Property<T>): Subscription =
    property.subscribe { text = it?.toString().orEmpty() }

fun <T> TextView.bindText(property: IntProperty): Subscription =
    property.subscribe { text = it.toString() }

fun <T> TextView.bindText(property: LongProperty): Subscription =
    property.subscribe { text = it.toString() }

fun <T> TextView.bindText(property: DoubleProperty): Subscription =
    property.subscribe { text = it.toString() }

fun <T> TextView.bindText(property: BooleanProperty): Subscription =
    property.subscribe { text = it.toString() }

@Deprecated("Deprecated, use function with BooleanProperty instead")
fun View.bindVisibility(property: Property<Boolean>): Subscription =
    property.subscribe { visibility = if (it) View.VISIBLE else View.GONE }

fun View.bindVisibility(property: BooleanProperty): Subscription =
    property.subscribe { visibility = if (it) View.VISIBLE else View.GONE }

@Deprecated("Deprecated, use function with BooleanProperty instead")
fun Dialog.bindVisibility(property: Property<Boolean>): Subscription =
    property.subscribe { if (it) show() else dismiss() }

fun Dialog.bindVisibility(property: BooleanProperty): Subscription =
    property.subscribe { if (it) show() else dismiss() }

@Deprecated("Deprecated, use function with BooleanProperty instead")
fun CompoundButton.bindChecked(property: Property<Boolean>): Subscription =
    property.subscribe(::setChecked)

fun CompoundButton.bindChecked(property: BooleanProperty): Subscription =
    property.subscribe(::setChecked)

@Deprecated("Deprecated, use function with BooleanProperty instead")
fun View.bindEnabled(property: Property<Boolean>): Subscription =
    property.subscribe(::setEnabled)

fun View.bindEnabled(property: BooleanProperty): Subscription =
    property.subscribe(::setEnabled)

fun EditText.bindError(property: Property<String?>): Subscription =
    property.subscribe {
        error = it
        requestFocus()
    }

@Deprecated("Deprecated, use function with MutableIntProperty instead")
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

fun SeekBar.bindProgressBidirectionally(property: MutableIntProperty): Subscription {
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

@Deprecated("Deprecated, use function with MutableIntProperty instead")
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

fun AdapterView<*>.bindSelectionBidirectionally(property: MutableIntProperty): Subscription {
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

@Deprecated("Deprecated, use function with MutableBooleanProperty instead")
fun CompoundButton.bindCheckedBidirectionally(property: MutableProperty<Boolean>): Subscription {
    val subscription = property.subscribe(::setChecked)
    setOnCheckedChangeListener { _, checked -> property.value = checked }
    subscription.onUnsubscribe { setOnCheckedChangeListener(null) }
    return subscription
}

fun CompoundButton.bindCheckedBidirectionally(property: MutableBooleanProperty): Subscription {
    val subscription = property.subscribe(::setChecked)
    setOnCheckedChangeListener { _, checked -> property.value = checked }
    subscription.onUnsubscribe { setOnCheckedChangeListener(null) }
    return subscription
}