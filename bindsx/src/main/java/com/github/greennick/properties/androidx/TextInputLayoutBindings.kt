package com.github.greennick.properties.androidx

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.bindError(property: Property<out CharSequence?>): ListenableSubscription =
    property.subscribe { error = it }

fun TextInputLayout.bindErrorEnabled(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { isErrorEnabled = it }

fun TextInputLayout.bindHint(property: Property<out CharSequence?>): ListenableSubscription =
    property.subscribe { hint = it }

fun TextInputLayout.bindHintEnabled(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { isHintEnabled = it }

/**
 * Activities section
 */

fun Activity.bindErrorToInputLayout(
    id: Int,
    property: Property<out CharSequence?>
): ListenableSubscription =
    findViewById<TextInputLayout>(id).bindError(property)

fun Activity.bindErrorEnabledToInputLayout(
    id: Int,
    property: Property<Boolean>
): ListenableSubscription =
    findViewById<TextInputLayout>(id).bindErrorEnabled(property)

fun Activity.bindHintToInputLayout(
    id: Int,
    property: Property<out CharSequence?>
): ListenableSubscription =
    findViewById<TextInputLayout>(id).bindHint(property)

fun Activity.bindHintEnabledToInputLayout(
    id: Int,
    property: Property<Boolean>
): ListenableSubscription =
    findViewById<TextInputLayout>(id).bindHintEnabled(property)

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindErrorToInputLayout(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event
): Unit =
    bindErrorToInputLayout(id, property).toEvent(this, bindTo)

fun FragmentActivity.bindErrorEnabledToInputLayout(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    bindErrorEnabledToInputLayout(id, property).toEvent(this, bindTo)

fun FragmentActivity.bindHintToInputLayout(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event
): Unit =
    bindHintToInputLayout(id, property).toEvent(this, bindTo)

fun FragmentActivity.bindHintEnabledToInputLayout(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    bindHintEnabledToInputLayout(id, property).toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindErrorToInputLayout(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextInputLayout>(id)
        .bindError(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindErrorEnabledToInputLayout(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextInputLayout>(id)
        .bindErrorEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindHintToInputLayout(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextInputLayout>(id)
        .bindHint(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindHintEnabledToInputLayout(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextInputLayout>(id)
        .bindHintEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
