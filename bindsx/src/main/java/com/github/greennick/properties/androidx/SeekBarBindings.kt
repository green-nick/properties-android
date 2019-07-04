package com.github.greennick.properties.androidx

import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.bindProgressBidirectionally
import com.github.greennick.properties.generic.MutableProperty

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindProgressBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event
): Unit =
    bindProgressBidirectionally(id, property).toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindProgressBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<SeekBar>(id)
        .bindProgressBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
