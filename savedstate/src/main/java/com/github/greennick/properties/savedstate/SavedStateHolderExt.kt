package com.github.greennick.properties.savedstate

import com.github.greennick.properties.generic.MutableProperty
import kotlin.reflect.KProperty0

fun <T, P : MutableProperty<T>> P.putInto(tag: String, savedState: SavedStateHolder): P {
    savedState.put(tag, this)
    return this
}

fun <P : MutableProperty<*>> SavedStateHolder.put(kProperty: KProperty0<P>) {
    put(kProperty.name, kProperty.get())
}
