package com.github.greennick.properties.androidx

import android.widget.AdapterView
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.bindSelectionBidirectionally
import com.github.greennick.properties.generic.MutableProperty

/**
 * Binds [AdapterView] to MutableProperty<Int>.
 * @see bindSelectionBidirectionally
 *
 * @param property - selection holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindSelectionBidirectionally(
    adapterView: AdapterView<*>,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = adapterView.bindSelectionBidirectionally(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Activity section
 */

/**
 * Looking for [AdapterView] by given id and binds it to MutableProperty<Int>.
 * @see bindSelectionBidirectionally
 *
 * @param id - [AdapterView]'s id
 * @param property - selection holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if AdapterView with given id isn't found
 * @throws ClassCastException if found View isn't AdapterView
 */
fun ComponentActivity.bindSelectionBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event
): Unit = bindSelectionBidirectionally(find<AdapterView<*>>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Looking for [AdapterView] by given id and binds it to MutableProperty<Int>.
 * @see bindSelectionBidirectionally
 *
 * @param id - [AdapterView]'s id
 * @param property - selection holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if AdapterView with given id isn't found
 * @throws ClassCastException if found View isn't AdapterView
 */
fun Fragment.bindSelectionBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindSelectionBidirectionally(find<AdapterView<*>>(id), property, bindTo)
