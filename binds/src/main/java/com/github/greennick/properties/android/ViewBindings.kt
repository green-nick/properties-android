package com.github.greennick.properties.android

import android.app.Activity
import android.view.View
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription

enum class Invisibility(val viewValue: Int) { INVISIBLE(View.INVISIBLE), GONE(View.GONE) }

fun View.bindVisibility(
    property: Property<Boolean>,
    invisibilityMode: Invisibility = Invisibility.GONE
): ListenableSubscription = property.subscribe {
    visibility = if (it) View.VISIBLE else invisibilityMode.viewValue
}

fun View.bindEnabled(property: Property<Boolean>): ListenableSubscription =
    property.subscribe(::setEnabled)

fun Activity.bindVisibility(
    id: Int,
    property: Property<Boolean>,
    invisibilityMode: Invisibility = Invisibility.GONE
): ListenableSubscription =
    findViewById<View>(id).bindVisibility(property, invisibilityMode)

fun Activity.bindEnabled(id: Int, property: Property<Boolean>): ListenableSubscription =
    findViewById<View>(id).bindEnabled(property)
