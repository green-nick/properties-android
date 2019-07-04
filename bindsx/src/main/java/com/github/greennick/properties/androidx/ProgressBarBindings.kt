package com.github.greennick.properties.androidx

import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.bindProgress
import com.github.greennick.properties.generic.Property

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindProgress(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event
): Unit =
    bindProgress(id, property).toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindProgress(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<ProgressBar>(id)
        .bindProgress(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
