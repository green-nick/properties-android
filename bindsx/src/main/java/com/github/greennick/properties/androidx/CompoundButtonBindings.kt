package com.github.greennick.properties.androidx

import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.bindChecked
import com.github.greennick.properties.android.bindCheckedBidirectionally
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindChecked(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    bindChecked(id, property).toEvent(this, bindTo)

fun FragmentActivity.bindCheckedBidirectionally(
    id: Int,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    bindCheckedBidirectionally(id, property).toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindChecked(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<CompoundButton>(id)
        .bindChecked(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindCheckedBidirectionally(
    id: Int,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<CompoundButton>(id)
        .bindCheckedBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
