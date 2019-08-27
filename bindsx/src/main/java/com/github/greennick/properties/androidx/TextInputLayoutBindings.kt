@file:Suppress("RemoveExplicitTypeArguments")

package com.github.greennick.properties.androidx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.lifecycle.toEvent
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.google.android.material.textfield.TextInputLayout

/**
 * Binds [TextInputLayout] to Property<CharSequence?>.
 * Uses [TextInputLayout.setError]
 *
 * @param property - error text holder
 */
fun TextInputLayout.bindError(property: Property<CharSequence?>): ListenableSubscription =
    property.subscribe { error = it }

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * Uses [TextInputLayout.setErrorEnabled]
 *
 * @param property - enabled/disabled error holder
 */
fun TextInputLayout.bindErrorEnabled(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { isErrorEnabled = it }

/**
 * Binds [TextInputLayout] to Property<CharSequence?>.
 * Uses [TextInputLayout.setHint]
 *
 * @param property - hint text holder
 */
fun TextInputLayout.bindHint(property: Property<CharSequence?>): ListenableSubscription =
    property.subscribe { hint = it }

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * Uses [TextInputLayout.setHintEnabled]
 *
 * @param property - enabled/disabled hint holder
 */
fun TextInputLayout.bindHintEnabled(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { isHintEnabled = it }

/**
 * FragmentActivity section
 */

/**
 * Binds [TextInputLayout] to Property<CharSequence?>.
 * @see bindError
 *
 * @param property - error text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindError(
    textInputLayout: TextInputLayout,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindError(property)
        .toEvent(this, bindTo)

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * @see bindErrorEnabled
 *
 * @param property - enabled/disabled error holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindErrorEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindErrorEnabled(property)
        .toEvent(this, bindTo)

/**
 * Binds [TextInputLayout] to Property<CharSequence?>.
 * @see bindHint
 *
 * @param property - hint text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindHint(
    textInputLayout: TextInputLayout,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindHint(property)
        .toEvent(this, bindTo)

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * @see bindHintEnabled
 *
 * @param property - enabled/disabled hint holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun FragmentActivity.bindHintEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindHintEnabled(property)
        .toEvent(this, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<CharSequence?>.
 * @see bindError
 *
 * @param id - [TextInputLayout]'s id
 * @param property - error text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
fun FragmentActivity.bindInputLayoutError(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindError(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<Boolean>.
 * @see bindErrorEnabled
 *
 * @param id - [TextInputLayout]'s id
 * @param property - enabled/disabled error holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
fun FragmentActivity.bindInputLayoutErrorEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindErrorEnabled(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<CharSequence?>.
 * @see bindHint
 *
 * @param id - [TextInputLayout]'s id
 * @param property - hint text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
fun FragmentActivity.bindInputLayoutHint(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHint(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<Boolean>.
 * @see bindHintEnabled
 *
 * @param id - [TextInputLayout]'s id
 * @param property - enabled/disabled hint holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
fun FragmentActivity.bindInputLayoutHintEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHintEnabled(find<TextInputLayout>(id), property, bindTo)

/**
 * Fragment section
 */

/**
 * Binds [TextInputLayout] to Property<CharSequence?>.
 * @see bindError
 *
 * @param property - error text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindError(
    textInputLayout: TextInputLayout,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindError(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * @see bindErrorEnabled
 *
 * @param property - enabled/disabled error holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindErrorEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindErrorEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [TextInputLayout] to Property<CharSequence?>.
 * @see bindHint
 *
 * @param property - hint text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindHint(
    textInputLayout: TextInputLayout,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindHint(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * @see bindHintEnabled
 *
 * @param property - enabled/disabled hint holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun Fragment.bindHintEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    textInputLayout.bindHintEnabled(property)
        .toEvent(this.viewLifecycleOwner, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<CharSequence?>.
 * @see bindError
 *
 * @param id - [TextInputLayout]'s id
 * @param property - error text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
fun Fragment.bindInputLayoutError(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindError(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<Boolean>.
 * @see bindErrorEnabled
 *
 * @param id - [TextInputLayout]'s id
 * @param property - enabled/disabled error holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
fun Fragment.bindInputLayoutErrorEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindErrorEnabled(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<CharSequence?>.
 * @see bindHint
 *
 * @param id - [TextInputLayout]'s id
 * @param property - hint text holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
fun Fragment.bindInputLayoutHint(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHint(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<Boolean>.
 * @see bindHintEnabled
 *
 * @param id - [TextInputLayout]'s id
 * @param property - enabled/disabled hint holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
fun Fragment.bindInputLayoutHintEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit =
    bindHintEnabled(find<TextInputLayout>(id), property, bindTo)
