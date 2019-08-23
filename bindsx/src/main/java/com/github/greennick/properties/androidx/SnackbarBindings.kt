package com.github.greennick.properties.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.google.android.material.snackbar.Snackbar

/**
 * Binds [Snackbar] to Property<Boolean>.
 * Uses [Snackbar.show] and [Snackbar.dismiss]
 *
 * @param property - visible/invisible holder
 */
fun Snackbar.bindVisibility(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { if (it) show() else dismiss() }

/**
 * FragmentActivity section
 */

/**
 * Binds [Snackbar] to Property<Boolean>.
 * @see bindVisibility
 *
 * @param property - visible/invisible holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindVisibility(
    snackbar: Snackbar,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    snackbar.bindVisibility(property)
        .toEvent(this, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [Snackbar] to Property<Boolean>.
 * @see bindVisibility
 *
 * @param property - visible/invisible holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindVisibility(
    snackbar: Snackbar,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    snackbar.bindVisibility(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
