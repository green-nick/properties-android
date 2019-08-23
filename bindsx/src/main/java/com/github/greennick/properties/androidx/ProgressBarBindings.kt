package com.github.greennick.properties.androidx

import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.bindProgress
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivity section
 */

/**
 * Binds [ProgressBar] to Property<Int>.
 * @see bindProgress
 *
 * @param property - progress value holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindProgress(
    progressBar: ProgressBar,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    progressBar.bindProgress(property)
        .toEvent(this, bindTo)

/**
 * Looking for [ProgressBar] by given id and binds it to Property<Int>.
 * @see bindProgress
 *
 * @param id - [ProgressBar]'s id
 * @param property - progress value holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if ProgressBar with given id isn't found
 * @throws ClassCastException if found View isn't ProgressBar
 */
fun FragmentActivity.bindProgress(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindProgress(find<ProgressBar>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [ProgressBar] to Property<Int>.
 * @see bindProgress
 *
 * @param property - progress value holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindProgress(
    progressBar: ProgressBar,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    progressBar.bindProgress(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Looking for [ProgressBar] by given id and binds it to Property<Int>.
 * @see bindProgress
 *
 * @param id - [ProgressBar]'s id
 * @param property - progress value holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if ProgressBar with given id isn't found
 * @throws ClassCastException if found View isn't ProgressBar
 */
fun Fragment.bindProgress(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindProgress(find<ProgressBar>(id), property, bindTo)
