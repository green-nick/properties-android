package com.github.greennick.properties.androidx

import android.widget.EditText
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.android.bindError
import com.github.greennick.properties.generic.Property

/**
 * Binds [EditText] to Property<CharSequence?>.
 * @see bindError
 *
 * @param property - error text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindError(
    editText: EditText,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = editText.bindError(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Activity section
 */

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
fun ComponentActivity.bindError(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindError(find<EditText>(id), property, bindTo)

/**
 * Fragment section
 */

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
): Unit = bindError(find<EditText>(id), property, bindTo)
