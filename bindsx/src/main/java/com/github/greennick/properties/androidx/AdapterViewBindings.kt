package com.github.greennick.properties.androidx

import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.bindSelectionBidirectionally
import com.github.greennick.properties.generic.MutableProperty

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindSelectionBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event
): Unit =
    bindSelectionBidirectionally(id, property).toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindSelectionBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<AdapterView<*>>(id)
        .bindSelectionBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
