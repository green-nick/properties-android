package com.github.greennick.properties.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.BoundAdapter
import com.github.greennick.properties.android.bind
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivities section
 */

fun <T> FragmentActivity.bind(
    property: Property<List<T>>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    adapter: BoundAdapter<T>
) {
    adapter.bind(property).toEvent(this, event)
}

/**
 * Fragments section
 */

fun <T> Fragment.bind(
    property: Property<List<T>>,
    event: Lifecycle.Event = Lifecycle.Event.ON_DESTROY,
    adapter: BoundAdapter<T>
) {
    adapter.bind(property).toEvent(this.viewLifecycleOwner, event)
}
