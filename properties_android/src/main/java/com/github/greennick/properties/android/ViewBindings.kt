@file:Suppress("unused")

package com.github.greennick.properties.android

import android.view.View
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.generic.invoke

fun View.bindVisibility(property: Property<Boolean>) = property {
    visibility = if (it) View.VISIBLE else View.GONE
}

fun View.bindEnabled(property: Property<Boolean>) = property.subscribe(::setEnabled)