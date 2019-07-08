@file:Suppress("RemoveExplicitTypeArguments")

package com.github.greennick.properties.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent
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
 * FragmentActivities section
 */

fun FragmentActivity.bindError(
    textInputLayout: TextInputLayout,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindError(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindErrorEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindErrorEnabled(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindHint(
    textInputLayout: TextInputLayout,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindHint(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindHintEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindHintEnabled(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindInputLayoutError(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindError(findViewById<TextInputLayout>(id), property, bindTo)

fun FragmentActivity.bindInputLayoutErrorEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindErrorEnabled(findViewById<TextInputLayout>(id), property, bindTo)

fun FragmentActivity.bindInputLayoutHint(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHint(findViewById<TextInputLayout>(id), property, bindTo)

fun FragmentActivity.bindInputLayoutHintEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHintEnabled(findViewById<TextInputLayout>(id), property, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindError(
    textInputLayout: TextInputLayout,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindError(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindErrorEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindErrorEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindHint(
    textInputLayout: TextInputLayout,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindHint(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindHintEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindHintEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindInputLayoutError(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindError(view!!.findViewById<TextInputLayout>(id), property, bindTo)

fun Fragment.bindInputLayoutErrorEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindErrorEnabled(view!!.findViewById<TextInputLayout>(id), property, bindTo)

fun Fragment.bindInputLayoutHint(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHint(view!!.findViewById<TextInputLayout>(id), property, bindTo)

fun Fragment.bindInputLayoutHintEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHintEnabled(view!!.findViewById<TextInputLayout>(id), property, bindTo)
