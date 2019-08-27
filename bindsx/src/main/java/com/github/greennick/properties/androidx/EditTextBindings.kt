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
 * FragmentActivity section
 */

/**
 * Binds [EditText] to Property<CharSequence?>.
 * @see bindError
 *
 * @param property - error text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindError(
    editText: EditText,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    editText.bindError(property)
        .toEvent(this, bindTo)

/**
 * Looking for [EditText] by given id and binds it to Property<CharSequence?>.
 * @see bindError
 *
 * @param id - [EditText]'s id
 * @param property - error text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if EditText with given id isn't found
 * @throws ClassCastException if found View isn't EditText
 */
fun FragmentActivity.bindError(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindError(find<EditText>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [EditText] to Property<CharSequence?>.
 * @see bindError
 *
 * @param property - error text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindError(
    editText: EditText,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    editText.bindError(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Looking for [EditText] by given id and binds it to Property<CharSequence?>.
 * @see bindError
 *
 * @param id - [EditText]'s id
 * @param property - error text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if EditText with given id isn't found
 * @throws ClassCastException if found View isn't EditText
 */
fun Fragment.bindError(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindError(find<EditText>(id), property, bindTo)
