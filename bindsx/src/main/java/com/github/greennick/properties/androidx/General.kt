package com.github.greennick.properties.androidx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.generic.*

/**
 * See [bind] with [Lifecycle.Event.ON_DESTROY] event
 */
fun <T> LifecycleOwner.bind(
    property: Property<T>,
    listener: (T) -> Unit
): Unit = bind(property, Lifecycle.Event.ON_DESTROY, listener)

/**
 * Connect given listener and property with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 */
fun <T> LifecycleOwner.bind(
    property: Property<T>,
    event: Lifecycle.Event,
    listener: (T) -> Unit
): Unit = property.subscribe(listener)
    .toEvent(suitableLifecycleOwner(), event)

/**
 * See [bindNonNull] with [Lifecycle.Event.ON_DESTROY] event
 */
fun <T> LifecycleOwner.bindNonNull(
    property: Property<T?>,
    listener: (T) -> Unit
): Unit = bindNonNull(property, Lifecycle.Event.ON_DESTROY, listener)

/**
 * Connect given listener and property with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on non-null values
 */
fun <T> LifecycleOwner.bindNonNull(
    property: Property<T?>,
    event: Lifecycle.Event,
    listener: (T) -> Unit
): Unit = property.subscribeNonNull(listener)
    .toEvent(suitableLifecycleOwner(), event)

/**
 * See [bindOnTrue] with [Lifecycle.Event.ON_DESTROY] event
 */
fun LifecycleOwner.bindOnTrue(
    property: Property<Boolean?>,
    onTrue: () -> Unit
): Unit = bindOnTrue(property, Lifecycle.Event.ON_DESTROY, onTrue)

/**
 * Connect given listener and Property<Boolean?> with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on `true` values
 */
fun LifecycleOwner.bindOnTrue(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onTrue: () -> Unit
): Unit = property.subscribeOnTrue(onTrue)
    .toEvent(suitableLifecycleOwner(), event)

/**
 * See [bindOnFalse] with [Lifecycle.Event.ON_DESTROY] event
 */
fun LifecycleOwner.bindOnFalse(
    property: Property<Boolean?>,
    onFalse: () -> Unit
): Unit = bindOnFalse(property, Lifecycle.Event.ON_DESTROY, onFalse)

/**
 * Connect given listener and Property<Boolean?> with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on `false` values
 */
fun LifecycleOwner.bindOnFalse(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onFalse: () -> Unit
): Unit = property.subscribeOnFalse(onFalse)
    .toEvent(suitableLifecycleOwner(), event)
