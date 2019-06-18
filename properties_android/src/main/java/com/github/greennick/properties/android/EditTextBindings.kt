package com.github.greennick.properties.android

import android.app.Activity
import android.widget.EditText
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription

fun EditText.bindError(property: Property<out CharSequence?>): ListenableSubscription =
    property.subscribe { error = it }

fun Activity.bindError(id: Int, property: Property<out CharSequence?>): ListenableSubscription =
    findViewById<EditText>(id).bindError(property)
