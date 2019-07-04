package com.github.greennick.properties.androidx

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import com.github.greennick.properties.android.*
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindText(
    id: Int,
    property: Property<out Any?>,
    bindTo: Lifecycle.Event
): Unit =
    bindText(id, property).toEvent(this, bindTo)

fun FragmentActivity.bindTextId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event
): Unit =
    bindTextId(id, property).toEvent(this, bindTo)

fun FragmentActivity.bindHint(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event
): Unit =
    bindHint(id, property).toEvent(this, bindTo)

fun FragmentActivity.bindHintId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event
): Unit =
    bindHintId(id, property).toEvent(this, bindTo)

fun FragmentActivity.bindTextBidirectionally(
    id: Int,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event
): Unit =
    bindTextBidirectionally(id, property).toEvent(this, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindText(
    id: Int,
    property: Property<out Any?>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextView>(id)
        .bindText(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindTextId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextView>(id)
        .bindTextId(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindHint(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextView>(id)
        .bindHint(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindHintId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextView>(id)
        .bindHintId(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindTextBidirectionally(
    id: Int,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event
): Unit =
    view!!.findViewById<TextView>(id)
        .bindTextBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)
