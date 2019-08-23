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

