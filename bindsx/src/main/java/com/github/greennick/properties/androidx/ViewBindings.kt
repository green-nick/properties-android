package com.github.greennick.properties.androidx

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.*
import com.github.greennick.properties.android.*
import com.github.greennick.properties.generic.Property

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindVisibility(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    view.bindVisibility(property, invisibilityMode).toEvent(this, bindTo)

fun FragmentActivity.bindEnabled(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    view.bindEnabled(property).toEvent(this, bindTo)

fun FragmentActivity.bindVisibility(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    bindVisibility(findViewById<View>(id), property, bindTo, invisibilityMode)

fun FragmentActivity.bindEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindEnabled(findViewById<View>(id), property, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindVisibility(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    view.bindVisibility(property, invisibilityMode)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindEnabled(
    view: View,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    view.bindEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindVisibility(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY,
    invisibilityMode: Invisibility = Invisibility.GONE
): Unit =
    bindVisibility(view!!.findViewById<View>(id), property, bindTo, invisibilityMode)

fun Fragment.bindEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindEnabled(view!!.findViewById<View>(id), property, bindTo)
