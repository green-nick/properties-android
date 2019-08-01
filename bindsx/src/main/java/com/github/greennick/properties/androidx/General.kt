package com.github.greennick.properties.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.generic.*
import com.github.greennick.properties.lifecycle.toEvent

fun <T> FragmentActivity.bind(
    property: Property<T>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
) {
    property.subscribe(listener).toEvent(this, event)
}

fun <T> FragmentActivity.bindNonNull(
    property: Property<T?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
) {
    property.subscribeNonNull(listener).toEvent(this, event)
}

fun FragmentActivity.bindOnTrue(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onTrue: () -> Unit
) {
    property.subscribeOnTrue(onTrue).toEvent(this, event)
}

fun FragmentActivity.bindOnFalse(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onFalse: () -> Unit
) {
    property.subscribeOnFalse(onFalse).toEvent(this, event)
}

fun <T> Fragment.bind(
    property: Property<T>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
) {
    property.subscribe(listener).toEvent(this.viewLifecycleOwner, event)
}

fun <T> Fragment.bindNonNull(
    property: Property<T?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    listener: (T) -> Unit
) {
    property.subscribeNonNull(listener).toEvent(this.viewLifecycleOwner, event)
}

fun Fragment.bindOnTrue(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onTrue: () -> Unit
) {
    property.subscribeOnTrue(onTrue).toEvent(this.viewLifecycleOwner, event)
}

fun Fragment.bindOnFalse(
    property: Property<Boolean?>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    onFalse: () -> Unit
) {
    property.subscribeOnFalse(onFalse).toEvent(this.viewLifecycleOwner, event)
}
