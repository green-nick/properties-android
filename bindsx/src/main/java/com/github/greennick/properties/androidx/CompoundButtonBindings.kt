package com.github.greennick.properties.androidx

import android.widget.CompoundButton
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.bindChecked
import com.github.greennick.properties.android.bindCheckedBidirectionally
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property

/**
 * Binds [CompoundButton] to Property<Boolean>.
 * @see bindChecked
 *
 * @param property - checked/unchecked holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindChecked(
    compoundButton: CompoundButton,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = compoundButton.bindChecked(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [CompoundButton] to MutableProperty<Boolean>.
 * @see bindCheckedBidirectionally
 *
 * @param property - checked/unchecked holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindCheckedBidirectionally(
    compoundButton: CompoundButton,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = compoundButton.bindCheckedBidirectionally(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Activity section
 */

/**
 * Looking for [CompoundButton] by given id and binds it to Property<Boolean>.
 * @see bindChecked
 *
 * @param id - [CompoundButton]'s id
 * @param property - checked/unchecked holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if CompoundButton with given id isn't found
 * @throws ClassCastException if found View isn't CompoundButton
 */
fun ComponentActivity.bindChecked(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindChecked(find<CompoundButton>(id), property, bindTo)

/**
 * Looking for [CompoundButton] by given id and binds it to MutableProperty<Boolean>.
 * @see bindCheckedBidirectionally
 *
 * @param id - [CompoundButton]'s id
 * @param property - checked/unchecked holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if CompoundButton with given id isn't found
 * @throws ClassCastException if found View isn't CompoundButton
 */
fun ComponentActivity.bindCheckedBidirectionally(
    id: Int,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindCheckedBidirectionally(find<CompoundButton>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Looking for [CompoundButton] by given id and binds it to Property<Boolean>.
 * @see bindChecked
 *
 * @param id - [CompoundButton]'s id
 * @param property - checked/unchecked holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if CompoundButton with given id isn't found
 * @throws ClassCastException if found View isn't CompoundButton
 */
fun Fragment.bindChecked(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindChecked(find<CompoundButton>(id), property, bindTo)

/**
 * Looking for [CompoundButton] by given id and binds it to MutableProperty<Boolean>.
 * @see bindCheckedBidirectionally
 *
 * @param id - [CompoundButton]'s id
 * @param property - checked/unchecked holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if CompoundButton with given id isn't found
 * @throws ClassCastException if found View isn't CompoundButton
 */
fun Fragment.bindCheckedBidirectionally(
    id: Int,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindCheckedBidirectionally(find<CompoundButton>(id), property, bindTo)
