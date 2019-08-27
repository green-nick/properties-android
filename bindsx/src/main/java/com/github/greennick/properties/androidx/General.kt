package com.github.greennick.properties.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.generic.*
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivity section
 */

/**
 * Connect given listener and property with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 */
fun <T> FragmentActivity.bind(
    property: Property<T>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
) {
    property.subscribe(listener).toEvent(this, event)
}

/**
 * Connect given listener and property with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on non-null values
 */
fun <T> FragmentActivity.bindNonNull(
    property: Property<T?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
) {
    property.subscribeNonNull(listener).toEvent(this, event)
}

/**
 * Connect given listener and Property<Boolean?> with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on `true` values
 */
fun FragmentActivity.bindOnTrue(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onTrue: () -> Unit
) {
    property.subscribeOnTrue(onTrue).toEvent(this, event)
}

/**
 * Connect given listener and Property<Boolean?> with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on `false` values
 */
fun FragmentActivity.bindOnFalse(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onFalse: () -> Unit
) {
    property.subscribeOnFalse(onFalse).toEvent(this, event)
}

/**
 * Fragment section
 */

/**
 * Connect given listener and property with lifecycle event on [Fragment.getViewLifecycleOwner].
 * Default is [Lifecycle.Event.ON_DESTROY]
 */
fun <T> Fragment.bind(
    property: Property<T>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
) {
    property.subscribe(listener).toEvent(this.viewLifecycleOwner, event)
}

/**
 * Connect given listener and property with lifecycle event on [Fragment.getViewLifecycleOwner].
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on non-null values
 */
fun <T> Fragment.bindNonNull(
    property: Property<T?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
) {
    property.subscribeNonNull(listener).toEvent(this.viewLifecycleOwner, event)
}

/**
 * Connect given listener and Property<Boolean?> with lifecycle event on [Fragment.getViewLifecycleOwner].
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on `true` values
 */
fun Fragment.bindOnTrue(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onTrue: () -> Unit
) {
    property.subscribeOnTrue(onTrue).toEvent(this.viewLifecycleOwner, event)
}

/**
 * Connect given listener and Property<Boolean?> with lifecycle event on [Fragment.getViewLifecycleOwner].
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on `false` values
 */
fun Fragment.bindOnFalse(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onFalse: () -> Unit
) {
    property.subscribeOnFalse(onFalse).toEvent(this.viewLifecycleOwner, event)
}
