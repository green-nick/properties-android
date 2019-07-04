package com.github.greennick.properties.androidx

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.*
import com.github.greennick.properties.generic.Property

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindVisibility(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    bindVisibility(id, property, invisibilityMode).toEvent(this, bindTo)

fun FragmentActivity.bindEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    bindEnabled(id, property).toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindVisibility(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    view!!.findViewById<View>(id)
        .bindVisibility(property, invisibilityMode)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<View>(id)
        .bindEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
