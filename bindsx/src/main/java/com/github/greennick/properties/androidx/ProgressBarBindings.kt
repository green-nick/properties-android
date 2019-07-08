package com.github.greennick.properties.androidx

import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.bindProgress
import com.github.greennick.properties.generic.Property

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindProgress(
    progressBar: ProgressBar,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    progressBar.bindProgress(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindProgress(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindProgress(findViewById<ProgressBar>(id), property, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindProgress(
    progressBar: ProgressBar,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    progressBar.bindProgress(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindProgress(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindProgress(view!!.findViewById<ProgressBar>(id), property, bindTo)
