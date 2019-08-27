package com.github.greennick.properties.android

import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription

interface BoundAdapter<T> {

    fun update(newItems: List<T>)
}

fun <T> BoundAdapter<T>.bind(property: Property<List<T>>): ListenableSubscription =
    property.subscribe(this::update)
