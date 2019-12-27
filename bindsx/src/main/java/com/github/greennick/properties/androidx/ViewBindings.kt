package com.github.greennick.properties.androidx

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.*
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivity section
 */

/**
 * Binds [View] to Property<Boolean>.
 * @see bindVisibility
 *
 * @param property - visible/invisible holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 * @param invisibilityMode - set mode for invisibility from [Invisibility],
 * [Invisibility.GONE] is default
 */
fun FragmentActivity.bindVisibility(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    view.bindVisibility(property, invisibilityMode)
        .toEvent(this, bindTo)

/**
 * Binds [View] to Property<Boolean>.
 * @see bindEnabled
 *
 * @param property - enable/disable holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindEnabled(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    view.bindEnabled(property)
        .toEvent(this, bindTo)

/**
 * Looking for [View] by given id and binds it to Property<Boolean>.
 * @see bindVisibility
 *
 * @param id - [View]'s id
 * @param property - visible/invisible holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 * @param invisibilityMode - set mode for invisibility from [Invisibility],
 * [Invisibility.GONE] is default
 *
 * @throws IllegalArgumentException if View with given id isn't found
 */
fun FragmentActivity.bindVisibility(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    bindVisibility(find<View>(id), property, bindTo, invisibilityMode)

/**
 * Looking for [View] by given id and binds it to Property<Boolean>.
 * @see bindEnabled
 *
 * @param id - [View]'s id
 * @param property - enable/disable holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if View with given id isn't found
 */
fun FragmentActivity.bindEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindEnabled(find<View>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [View] to Property<Boolean>.
 * @see bindVisibility
 *
 * @param property - visible/invisible holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 * @param invisibilityMode - set mode for invisibility from [Invisibility],
 * [Invisibility.GONE] is default
 */
fun Fragment.bindVisibility(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    view.bindVisibility(property, invisibilityMode)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [View] to Property<Boolean>.
 * @see bindEnabled
 *
 * @param property - enable/disable holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindEnabled(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    view.bindEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Looking for [View] by given id and binds it to Property<Boolean>.
 * @see bindVisibility
 *
 * @param id - [View]'s id
 * @param property - visible/invisible holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 * @param invisibilityMode - set mode for invisibility from [Invisibility],
 * [Invisibility.GONE] is default
 *
 * @throws IllegalArgumentException if View with given id isn't found
 */
fun Fragment.bindVisibility(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    bindVisibility(find<View>(id), property, bindTo, invisibilityMode)

/**
 * Looking for [View] by given id and binds it to Property<Boolean>.
 * @see bindEnabled
 *
 * @param id - [View]'s id
 * @param property - enable/disable holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if View with given id isn't found
 */
fun Fragment.bindEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindEnabled(find<View>(id), property, bindTo)

fun Fragment.onClick(viewId: Int, action: (() -> Unit)? = null): Unit =
    find<View>(viewId).onClick(action)

fun Fragment.onLongClick(viewId: Int, action: (() -> Unit)? = null): Unit =
    find<View>(viewId).onLongClick(action)
