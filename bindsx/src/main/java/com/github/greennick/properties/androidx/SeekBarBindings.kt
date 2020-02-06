package com.github.greennick.properties.androidx

import android.widget.SeekBar
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.bindProgressBidirectionally
import com.github.greennick.properties.generic.MutableProperty

/**
 * Binds [SeekBar] to MutableProperty<Int>.
 * @see bindProgressBidirectionally
 *
 * @param property - progress value holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindProgressBidirectionally(
    seekBar: SeekBar,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = seekBar.bindProgressBidirectionally(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Activity section
 */

/**
 * Looking for [SeekBar] by given id and binds it to MutableProperty<Int>.
 * @see bindProgressBidirectionally
 *
 * @param id - [SeekBar]'s id
 * @param property - progress value holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if SeekBar with given id isn't found
 * @throws ClassCastException if found View isn't SeekBar
 */
fun ComponentActivity.bindProgressBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindProgressBidirectionally(find<SeekBar>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Looking for [SeekBar] by given id and binds it to MutableProperty<Int>.
 * @see bindProgressBidirectionally
 *
 * @param id - [SeekBar]'s id
 * @param property - progress value holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if SeekBar with given id isn't found
 * @throws ClassCastException if found View isn't SeekBar
 */
fun Fragment.bindProgressBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindProgressBidirectionally(find<SeekBar>(id), property, bindTo)
