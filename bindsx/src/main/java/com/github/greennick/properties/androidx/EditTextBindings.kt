package com.github.greennick.properties.androidx

import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.bindError
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivities section
 */

fun FragmentActivity.bindError(
    editText: EditText,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    editText.bindError(property)
        .toEvent(this, bindTo)

fun FragmentActivity.bindError(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindError(view<EditText>(id), property, bindTo)

/**
 * Fragments section
 */

fun Fragment.bindError(
    editText: EditText,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    editText.bindError(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

fun Fragment.bindError(
    id: Int,
    property: Property<out CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindError(view!!.findViewById<EditText>(id), property, bindTo)
