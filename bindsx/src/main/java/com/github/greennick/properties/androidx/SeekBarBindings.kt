package com.github.greennick.properties.androidx

import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.bindProgressBidirectionally
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivity section
 */

/**
 * Binds [SeekBar] to MutableProperty<Int>.
 * @see bindProgressBidirectionally
 *
 * @param property - progress value holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindProgressBidirectionally(
    seekBar: SeekBar,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    seekBar.bindProgressBidirectionally(property).toEvent(this, bindTo)

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
fun FragmentActivity.bindProgressBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindProgressBidirectionally(find<SeekBar>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [SeekBar] to MutableProperty<Int>.
 * @see bindProgressBidirectionally
 *
 * @param property - progress value holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindProgressBidirectionally(
    seekBar: SeekBar,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    seekBar.bindProgressBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

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
): Unit =
    bindProgressBidirectionally(find<SeekBar>(id), property, bindTo)
