package com.github.greennick.properties.android

import android.widget.EditText
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription

/**
 * Binds [EditText] to Property<CharSequence?>.
 * Uses [EditText.setError]
 *
 * @param property - error text holder
 */
fun EditText.bindError(property: Property<CharSequence?>): ListenableSubscription =
    property.subscribe { error = it }

/**
 * Binds [EditText] to Property<Int>.
 * Uses [EditText.setError]
 *
 * @param property - error text id holder
 */
@JvmName("bindErrorById")
fun EditText.bindError(property: Property<Int?>): ListenableSubscription =
    property.subscribe { error = if (it != null) context.getString(it) else null }
