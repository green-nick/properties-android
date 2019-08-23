package com.github.greennick.properties.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.BoundAdapter
import com.github.greennick.properties.android.bind
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivity section
 */

fun <T> FragmentActivity.bind(
    property: Property<List<T>>,
    adapter: BoundAdapter<T>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
) {
    adapter.bind(property).toEvent(this, event)
}

/**
 * Fragment section
 */

fun <T> Fragment.bind(
    property: Property<List<T>>,
    adapter: BoundAdapter<T>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
) {
    adapter.bind(property).toEvent(this.viewLifecycleOwner, event)
}
