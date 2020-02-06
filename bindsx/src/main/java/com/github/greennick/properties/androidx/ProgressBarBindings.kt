package com.github.greennick.properties.androidx

import android.widget.ProgressBar
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.bindProgress
import com.github.greennick.properties.generic.Property

/**
 * Binds [ProgressBar] to Property<Int>.
 * @see bindProgress
 *
 * @param property - progress value holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindProgress(
    progressBar: ProgressBar,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = progressBar.bindProgress(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Activity section
 */

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
fun ComponentActivity.bindProgress(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindProgress(find<ProgressBar>(id), property, bindTo)

/**
 * Fragment section
 */

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
): Unit = bindProgress(find<ProgressBar>(id), property, bindTo)
