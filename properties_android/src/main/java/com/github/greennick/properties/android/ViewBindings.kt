@file:Suppress("unused")

package com.github.greennick.properties.android

import android.view.View
import com.github.greennick.properties.generic.Property

fun View.bindVisibility(property: Property<Boolean>) = property.subscribe {
    visibility = if (it) View.VISIBLE else View.GONE
}

fun View.bindEnabled(property: Property<Boolean>) = property.subscribe(::setEnabled)