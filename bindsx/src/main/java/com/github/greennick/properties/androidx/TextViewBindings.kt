package com.github.greennick.properties.androidx

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.*
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindText(
    textView: TextView,
    property: Property<out Any?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindText(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindTextId(
    textView: TextView,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindTextId(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindHint(
    textView: TextView,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindHint(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindHintId(
    textView: TextView,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindHintId(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindTextBidirectionally(
    textView: TextView,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindTextBidirectionally(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindText(
    id: Int,
    property: Property<out Any?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindText(findViewById<TextView>(id), property, bindTo)

fun FragmentActivity.bindTextId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindTextId(findViewById<TextView>(id), property, bindTo)

fun FragmentActivity.bindHint(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHint(findViewById<TextView>(id), property, bindTo)

fun FragmentActivity.bindHintId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHintId(findViewById<TextView>(id), property, bindTo)

fun FragmentActivity.bindTextBidirectionally(
    id: Int,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindTextBidirectionally(findViewById<TextView>(id), property, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindText(
    textView: TextView,
    property: Property<out Any?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindText(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindTextId(
    textView: TextView,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindTextId(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindHint(
    textView: TextView,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindHint(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindHintId(
    textView: TextView,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindHintId(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindTextBidirectionally(
    textView: TextView,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindTextBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindText(
    id: Int,
    property: Property<out Any?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindText(view!!.findViewById<TextView>(id), property, bindTo)

fun Fragment.bindTextId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindTextId(view!!.findViewById<TextView>(id), property, bindTo)

fun Fragment.bindHint(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHint(view!!.findViewById<TextView>(id), property, bindTo)

fun Fragment.bindHintId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHintId(view!!.findViewById<TextView>(id), property, bindTo)

fun Fragment.bindTextBidirectionally(
    id: Int,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindTextBidirectionally(view!!.findViewById<TextView>(id), property, bindTo)
