package com.github.greennick.properties.androidx

import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.bindSelectionBidirectionally
import com.github.greennick.properties.generic.MutableProperty

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindSelectionBidirectionally(
    adapterView: AdapterView<*>,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    adapterView.bindSelectionBidirectionally(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindSelectionBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event
): Unit =
    bindSelectionBidirectionally(findViewById<AdapterView<*>>(id), property, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindSelectionBidirectionally(
    adapterView: AdapterView<*>,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    adapterView.bindSelectionBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindSelectionBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindSelectionBidirectionally(view!!.findViewById<AdapterView<*>>(id), property, bindTo)
