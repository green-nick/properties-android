package com.github.greennick.properties.androidx

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.android.*
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent

/**
 * FragmentActivity section
 */

/**
 * Binds [TextView] to Property<Any?>.
 * @see TextView.bindText
 *
 * @param property - object holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindText(
    textView: TextView,
    property: Property<Any?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindText(property)
        .toEvent(this, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindTextId
 *
 * @param property - string resource id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindTextId(
    textView: TextView,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindTextId(property)
        .toEvent(this, bindTo)

/**
 * Binds [TextView] to Property<CharSequence?>.
 * @see TextView.bindHint
 *
 * @param property - hint text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindHint(
    textView: TextView,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindHint(property)
        .toEvent(this, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindHintId
 *
 * @param property - string resource id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindHintId(
    textView: TextView,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindHintId(property)
        .toEvent(this, bindTo)

/**
 * Binds [TextView] to MutableProperty<String>.
 * @see TextView.bindTextBidirectionally
 *
 * @param property - text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindTextBidirectionally(
    textView: TextView,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindTextBidirectionally(property)
        .toEvent(this, bindTo)

/**
 * Binds [TextView] to Property<Any?>.
 * @see bindText
 *
 * @param id - [TextView]'s id
 * @param property - object holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun FragmentActivity.bindText(
    id: Int,
    property: Property<Any?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindText(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindTextId
 *
 * @param id - [TextView]'s id
 * @param property - string resource id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun FragmentActivity.bindTextId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindTextId(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<CharSequence?>.
 * @see TextView.bindHint
 *
 * @param id - [TextView]'s id
 * @param property - hint text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun FragmentActivity.bindHint(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHint(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindHintId
 *
 * @param id - [TextView]'s id
 * @param property - string resource id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun FragmentActivity.bindHintId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHintId(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to MutableProperty<String>.
 * @see TextView.bindTextBidirectionally
 *
 * @param id - [TextView]'s id
 * @param property - text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun FragmentActivity.bindTextBidirectionally(
    id: Int,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindTextBidirectionally(find<TextView>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [TextView] to Property<Any?>.
 * @see TextView.bindText
 *
 * @param property - object holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindText(
    textView: TextView,
    property: Property<Any?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindText(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindTextId
 *
 * @param property - string resource id holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindTextId(
    textView: TextView,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindTextId(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [TextView] to Property<CharSequence?>.
 * @see TextView.bindHint
 *
 * @param property - hint text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindHint(
    textView: TextView,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindHint(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindHintId
 *
 * @param property - string resource id holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindHintId(
    textView: TextView,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindHintId(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [TextView] to MutableProperty<String>.
 * @see TextView.bindTextBidirectionally
 *
 * @param property - text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindTextBidirectionally(
    textView: TextView,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textView.bindTextBidirectionally(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [TextView] to Property<Any?>.
 * @see TextView.bindText
 *
 * @param id - [TextView]'s id
 * @param property - object holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun Fragment.bindText(
    id: Int,
    property: Property<Any?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindText(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindTextId
 *
 * @param id - [TextView]'s id
 * @param property - string resource id holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun Fragment.bindTextId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindTextId(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<CharSequence?>.
 * @see TextView.bindHint
 *
 * @param id - [TextView]'s id
 * @param property - hint text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun Fragment.bindHint(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHint(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindHintId
 *
 * @param id - [TextView]'s id
 * @param property - string resource id holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun Fragment.bindHintId(
    id: Int,
    property: Property<Int>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHintId(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to MutableProperty<String>.
 * @see TextView.bindTextBidirectionally
 *
 * @param id - [TextView]'s id
 * @param property - text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun Fragment.bindTextBidirectionally(
    id: Int,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindTextBidirectionally(find<TextView>(id), property, bindTo)
