package com.github.greennick.properties.androidx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.BoundAdapter
import com.github.greennick.properties.android.bind
import com.github.greennick.properties.generic.Property

fun <T> LifecycleOwner.bindAdapter(
    property: Property<List<T>>,
    adapter: BoundAdapter<T>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
): Unit = adapter.bind(property)
    .toEvent(suitableLifecycleOwner(), event)
