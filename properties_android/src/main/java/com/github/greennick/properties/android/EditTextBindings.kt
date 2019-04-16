package com.github.greennick.properties.android

import android.widget.EditText
import com.github.greennick.properties.generic.invoke
import com.github.greennick.properties.generic.Property

fun EditText.bindError(property: Property<out String?>) = property {
    error = it
    requestFocus()
}