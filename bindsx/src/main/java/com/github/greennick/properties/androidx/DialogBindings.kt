package com.github.greennick.properties.androidx

import android.app.Dialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.bindVisibility
import com.github.greennick.properties.android.bindVisibilityBidirectionally
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivity section
 */

/**
 * Binds [Dialog] to Property<Boolean>.
 * @see bindVisibility
 *
 * @param property - show/hide holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindVisibility(
    dialog: Dialog,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): Unit =
    dialog.bindVisibility(property)
        .toEvent(this, bindTo)

/**
 * Binds [Dialog] to MutableProperty<Boolean>.
 * @see Dialog.bindVisibilityBidirectionally
 *
 * Works from user-interaction perspective
 * uses [DialogInterface.OnCancelListener] and [DialogInterface.OnShowListener]
 * For proper work, call [DialogInterface.cancel] on dialog's button callback.
 *
 * @param property - show/hide holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindVisibilityBidirectionally(
    dialog: Dialog,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): Unit =
    dialog.bindVisibilityBidirectionally(property)
        .toEvent(this, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [Dialog] to Property<Boolean>.
 * @see bindVisibility
 *
 * @param property - show/hide holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindVisibility(
    dialog: Dialog,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): Unit =
    dialog.bindVisibility(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [Dialog] to MutableProperty<Boolean>.
 * @see Dialog.bindVisibilityBidirectionally
 *
 * Works from user-interaction perspective
 * uses [DialogInterface.OnCancelListener] and [DialogInterface.OnShowListener]
 * For proper work, call [DialogInterface.cancel] on dialog's button callback.
 *
 * @param property - show/hide holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindVisibilityBidirectionally(
    dialog: Dialog,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): Unit =
    dialog.bindVisibilityBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
