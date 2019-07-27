package com.github.greennick.properties.androidx

import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.bindProgressBidirectionally
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindProgressBidirectionally(
    seekBar: SeekBar,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    seekBar.bindProgressBidirectionally(property).toEvent(this, bindTo)

fun FragmentActivity.bindProgressBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindProgressBidirectionally(find<SeekBar>(id), property, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindProgressBidirectionally(
    seekBar: SeekBar,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    seekBar.bindProgressBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindProgressBidirectionally(
    id: Int,
    property: MutableProperty<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindProgressBidirectionally(find<SeekBar>(id), property, bindTo)
