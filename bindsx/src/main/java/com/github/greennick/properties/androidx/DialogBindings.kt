package com.github.greennick.properties.androidx

import android.app.Dialog
import android.content.DialogInterface
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.bindVisibility
import com.github.greennick.properties.android.bindVisibilityBidirectionally
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property

/**
 * Binds [Dialog] to Property<Boolean>.
 * @see bindVisibility
 *
 * @param property - show/hide holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindVisibility(
    dialog: Dialog,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): Unit = dialog.bindVisibility(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

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
fun LifecycleOwner.bindVisibilityBidirectionally(
    dialog: Dialog,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): Unit = dialog.bindVisibilityBidirectionally(property)
    .toEvent(suitableLifecycleOwner(), bindTo)
