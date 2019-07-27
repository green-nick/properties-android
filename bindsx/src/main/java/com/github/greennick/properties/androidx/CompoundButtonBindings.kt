package com.github.greennick.properties.androidx

import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.bindChecked
import com.github.greennick.properties.android.bindCheckedBidirectionally
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindChecked(
    compoundButton: CompoundButton,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    compoundButton.bindChecked(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindCheckedBidirectionally(
    compoundButton: CompoundButton,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    compoundButton.bindCheckedBidirectionally(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindChecked(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindChecked(view<CompoundButton>(id), property, bindTo)

fun FragmentActivity.bindCheckedBidirectionally(
    id: Int,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindCheckedBidirectionally(view<CompoundButton>(id), property, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindChecked(
    compoundButton: CompoundButton,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    compoundButton.bindChecked(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindCheckedBidirectionally(
    compoundButton: CompoundButton,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    compoundButton.bindCheckedBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindChecked(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindChecked(view!!.findViewById<CompoundButton>(id), property, bindTo)

fun Fragment.bindCheckedBidirectionally(
    id: Int,
    property: MutableProperty<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindCheckedBidirectionally(view!!.findViewById<CompoundButton>(id), property, bindTo)
