package com.github.greennick.properties.androidx

import android.text.TextWatcher
import android.widget.TextView
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.*
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property

/**
 * Binds [TextView] to Property<CharSequence?>.
 * @see TextView.bindText
 *
 * @param property - string holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindText(
    textView: TextView,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textView.bindText(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindText
 *
 * @param property - string resource id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
@JvmName("bindTextId")
fun LifecycleOwner.bindText(
    textView: TextView,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textView.bindText(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextView] to Property<CharSequence?>.
 * @see TextView.bindHint
 *
 * @param property - hint text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindHint(
    textView: TextView,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textView.bindHint(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindHint
 *
 * @param property - string resource id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
@JvmName("bindHintId")
fun LifecycleOwner.bindHint(
    textView: TextView,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textView.bindHint(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextView] to MutableProperty<String>.
 * @see TextView.bindTextBidirectionally
 *
 * @param property - text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindTextBidirectionally(
    textView: TextView,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textView.bindTextBidirectionally(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Activity section
 */

/**
 * Binds [TextView] to Property<CharSequence?>.
 * @see bindText
 *
 * @param id - [TextView]'s id
 * @param property - string holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun ComponentActivity.bindText(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindText(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindText
 *
 * @param id - [TextView]'s id
 * @param property - string resource id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
@JvmName("bindTextId")
fun ComponentActivity.bindText(
    id: Int,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindText(find<TextView>(id), property, bindTo)

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
fun ComponentActivity.bindHint(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHint(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindHint
 *
 * @param id - [TextView]'s id
 * @param property - string resource id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
@JvmName("bindHintId")
fun ComponentActivity.bindHint(
    id: Int,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHint(find<TextView>(id), property, bindTo)

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
fun ComponentActivity.bindTextBidirectionally(
    id: Int,
    property: MutableProperty<String>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindTextBidirectionally(find<TextView>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [TextView] to Property<CharSequence?>.
 * @see TextView.bindText
 *
 * @param id - [TextView]'s id
 * @param property - string holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
fun Fragment.bindText(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindText(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindText
 *
 * @param id - [TextView]'s id
 * @param property - string resource id holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
@JvmName("bindTextId")
fun Fragment.bindText(
    id: Int,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindText(find<TextView>(id), property, bindTo)

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
): Unit = bindHint(find<TextView>(id), property, bindTo)

/**
 * Binds [TextView] to Property<Int>.
 * @see TextView.bindHint
 *
 * @param id - [TextView]'s id
 * @param property - string resource id holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextView with given id isn't found
 * @throws ClassCastException if found View isn't TextView
 */
@JvmName("bindHintId")
fun Fragment.bindHint(
    id: Int,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHint(find<TextView>(id), property, bindTo)

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
): Unit = bindTextBidirectionally(find<TextView>(id), property, bindTo)

fun Fragment.textChanged(viewId: Int, action: (String) -> Unit): TextWatcher =
    find<TextView>(viewId).textChanged(action)

fun Fragment.actionListener(viewId: Int, listener: (Int) -> Unit): Unit =
    find<TextView>(viewId).actionListener(listener)
