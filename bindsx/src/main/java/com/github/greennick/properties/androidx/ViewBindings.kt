package com.github.greennick.properties.androidx

import android.view.View
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.*
import com.github.greennick.properties.generic.Property

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
fun LifecycleOwner.bindVisibility(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit = view.bindVisibility(property, invisibilityMode)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [View] to Property<Boolean>.
 * @see bindEnabled
 *
 * @param property - enable/disable holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindEnabled(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = view.bindEnabled(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Activity section
 */

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
fun ComponentActivity.bindVisibility(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit = bindVisibility(find<View>(id), property, bindTo, invisibilityMode)

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
fun ComponentActivity.bindEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindEnabled(find<View>(id), property, bindTo)

/**
 * Fragment section
 */

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
): Unit = bindVisibility(find<View>(id), property, bindTo, invisibilityMode)

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
): Unit = bindEnabled(find<View>(id), property, bindTo)

fun Fragment.onClick(viewId: Int, action: (() -> Unit)? = null): Unit =
    find<View>(viewId).onClick(action)

fun Fragment.onLongClick(viewId: Int, action: (() -> Unit)? = null): Unit =
    find<View>(viewId).onLongClick(action)
