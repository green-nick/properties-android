package com.github.greennick.properties.android

import android.widget.EditText
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.Subscription

fun EditText.bindError(property: Property<out String?>): Subscription =
    property.subscribe {
        error = it
        requestFocus()
    }