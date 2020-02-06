package com.github.greennick.properties.androidx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.generic.Property
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
 * Binds [Snackbar] to Property<Boolean>.
 * @see bindVisibility
 *
 * @param property - visible/invisible holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindVisibility(
    snackbar: Snackbar,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = snackbar.bindVisibility(property)
    .toEvent(suitableLifecycleOwner(), bindTo)
