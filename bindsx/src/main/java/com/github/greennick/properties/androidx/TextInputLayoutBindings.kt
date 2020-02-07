@file:Suppress("RemoveExplicitTypeArguments")

package com.github.greennick.properties.androidx

import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.generic.Property
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
 * Binds [TextInputLayout] to Property<Int>.
 * Uses [TextInputLayout.setError]
 *
 * @param property - error text id holder
 */
@JvmName("bindErrorById")
fun TextInputLayout.bindError(property: Property<Int?>): ListenableSubscription =
    property.subscribe { error = if (it != null) context.getString(it) else null }

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
 * Binds [TextInputLayout] to Property<Int>.
 * Uses [TextInputLayout.setHint]
 *
 * @param property - hint text id holder
 */
@JvmName("bindHintById")
fun TextInputLayout.bindHint(property: Property<Int?>): ListenableSubscription =
    property.subscribe { hint = if (it != null) context.getString(it) else null }

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * Uses [TextInputLayout.setHintEnabled]
 *
 * @param property - enabled/disabled hint holder
 */
fun TextInputLayout.bindHintEnabled(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { isHintEnabled = it }

/**
 * Binds [TextInputLayout] to Property<CharSequence?>.
 * @see bindError
 *
 * @param property - error text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindError(
    textInputLayout: TextInputLayout,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textInputLayout.bindError(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextInputLayout] to Property<Int>.
 * @see bindError
 *
 * @param property - error text id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
@JvmName("bindErrorById")
fun LifecycleOwner.bindError(
    textInputLayout: TextInputLayout,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textInputLayout.bindError(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * @see bindErrorEnabled
 *
 * @param property - enabled/disabled error holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindErrorEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textInputLayout.bindErrorEnabled(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextInputLayout] to Property<CharSequence?>.
 * @see bindHint
 *
 * @param property - hint text holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindHint(
    textInputLayout: TextInputLayout,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textInputLayout.bindHint(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextInputLayout] to Property<Int>.
 * @see bindHint
 *
 * @param property - hint text id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
@JvmName("bindHintById")
fun LifecycleOwner.bindHint(
    textInputLayout: TextInputLayout,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textInputLayout.bindHint(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Binds [TextInputLayout] to Property<Boolean>.
 * @see bindHintEnabled
 *
 * @param property - enabled/disabled hint holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 */
fun LifecycleOwner.bindHintEnabled(
    textInputLayout: TextInputLayout,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = textInputLayout.bindHintEnabled(property)
    .toEvent(suitableLifecycleOwner(), bindTo)

/**
 * Activity section
 */

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
fun ComponentActivity.bindTextInputLayoutError(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindError(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<Int>.
 * @see bindError
 *
 * @param id - [TextInputLayout]'s id
 * @param property - error text id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
@JvmName("bindTextInputLayoutErrorById")
fun ComponentActivity.bindTextInputLayoutError(
    id: Int,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindError(find<TextInputLayout>(id), property, bindTo)

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
fun ComponentActivity.bindTextInputLayoutErrorEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindErrorEnabled(find<TextInputLayout>(id), property, bindTo)

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
fun ComponentActivity.bindTextInputLayoutHint(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHint(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<Int>.
 * @see bindHint
 *
 * @param id - [TextInputLayout]'s id
 * @param property - hint text id holder
 * @param bindTo - lifecycle event for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
@JvmName("bindTextInputLayoutHintById")
fun ComponentActivity.bindTextInputLayoutHint(
    id: Int,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHint(find<TextInputLayout>(id), property, bindTo)

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
fun ComponentActivity.bindTextInputLayoutHintEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHintEnabled(find<TextInputLayout>(id), property, bindTo)

/**
 * Fragment section
 */

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
fun Fragment.bindTextInputLayoutError(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindError(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<Int>.
 * @see bindError
 *
 * @param id - [TextInputLayout]'s id
 * @param property - error text id holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
@JvmName("bindTextInputLayoutErrorById")
fun Fragment.bindTextInputLayoutError(
    id: Int,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindError(find<TextInputLayout>(id), property, bindTo)

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
fun Fragment.bindTextInputLayoutErrorEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindErrorEnabled(find<TextInputLayout>(id), property, bindTo)

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
fun Fragment.bindTextInputLayoutHint(
    id: Int,
    property: Property<CharSequence?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHint(find<TextInputLayout>(id), property, bindTo)

/**
 * Looking for [TextInputLayout] by given id and binds it to Property<Int>.
 * @see bindHint
 *
 * @param id - [TextInputLayout]'s id
 * @param property - hint text id holder
 * @param bindTo - lifecycle event of [Fragment.getViewLifecycleOwner] for unsubscribe,
 * [Lifecycle.Event.ON_DESTROY] is default
 *
 * @throws IllegalArgumentException if TextInputLayout with given id isn't found
 * @throws ClassCastException if found View isn't TextInputLayout
 */
@JvmName("bindTextInputLayoutHintById")
fun Fragment.bindTextInputLayoutHint(
    id: Int,
    property: Property<Int?>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHint(find<TextInputLayout>(id), property, bindTo)

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
fun Fragment.bindTextInputLayoutHintEnabled(
    id: Int,
    property: Property<Boolean>,
    bindTo: Lifecycle.Event = ON_DESTROY
): Unit = bindHintEnabled(find<TextInputLayout>(id), property, bindTo)
