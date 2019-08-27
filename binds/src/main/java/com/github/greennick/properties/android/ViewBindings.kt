package com.github.greennick.properties.android

import android.view.View
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription

/**
 * Internal representation and holder of Android's View invisibility modes:
 * [View.INVISIBLE] and [View.GONE]
 */
enum class Invisibility(val viewValue: Int) { INVISIBLE(View.INVISIBLE), GONE(View.GONE) }

/**
 * Binds [View] to Property<Boolean>.
 * Uses [View.setVisibility]
 *
 * @param property - visible/invisible holder
 * @param invisibilityMode - set mode for invisibility from [Invisibility],
 * [Invisibility.GONE] is default
 */
fun View.bindVisibility(
    property: Property<Boolean>,
    invisibilityMode: Invisibility = Invisibility.GONE
): ListenableSubscription = property.subscribe {
    visibility = if (it) View.VISIBLE else invisibilityMode.viewValue
}

/**
 * Binds [View] to Property<Boolean>.
 * Uses [View.setEnabled]
 *
 * @param property - enable/disable holder
 */
fun View.bindEnabled(property: Property<Boolean>): ListenableSubscription =
    property.subscribe(::setEnabled)
