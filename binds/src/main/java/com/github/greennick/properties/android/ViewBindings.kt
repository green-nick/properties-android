package com.github.greennick.properties.android

import android.app.Activity
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
): ListenableSubscription =
    property.subscribe {
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

fun View.onClick(action: (() -> Unit)? = null) {
    if (action == null) {
        setOnClickListener(null)
    } else {
        setOnClickListener { action() }
    }
}

fun View.onLongClick(action: (() -> Unit)? = null) {
    if (action == null) {
        setOnLongClickListener(null)
    } else {
        setOnLongClickListener {
            action()
            true
        }
    }
}

fun Activity.onClick(viewId: Int, action: (() -> Unit)? = null): Unit =
    find<View>(viewId).onClick(action)

fun Activity.onLongClick(viewId: Int, action: (() -> Unit)? = null): Unit =
    find<View>(viewId).onLongClick(action)
