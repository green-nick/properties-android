package com.github.greennick.properties.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.google.android.material.snackbar.Snackbar

fun Snackbar.bindVisibility(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { if (it) show() else dismiss() }

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindVisibility(
    snackbar: Snackbar,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    snackbar.bindVisibility(property)
        .toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindVisibility(
    snackbar: Snackbar,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    snackbar.bindVisibility(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
