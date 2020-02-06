package com.github.greennick.properties.androidx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.generic.*

/**
 * Connect given listener and property with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 */
fun <T> LifecycleOwner.bind(
    property: Property<T>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
): Unit = property.subscribe(listener)
    .toEvent(suitableLifecycleOwner(), event)

/**
 * Connect given listener and property with lifecycle event.
 * Default is [Lifecycle.Event.ON_DESTROY]
 * Listener is being triggered only on non-null values
 */
fun <T> LifecycleOwner.bindNonNull(
    property: Property<T?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
): Unit = property.subscribeNonNull(listener)
    .toEvent(suitableLifecycleOwner(), event)

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
