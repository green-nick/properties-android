package com.github.greennick.properties.androidx

import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.bindError
import com.github.greennick.properties.generic.Property

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindError(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event
): Unit =
    bindError(id, property).toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindError(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<EditText>(id)
        .bindError(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
