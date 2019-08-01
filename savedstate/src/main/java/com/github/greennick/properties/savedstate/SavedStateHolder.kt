package com.github.greennick.properties.savedstate

import com.github.greennick.properties.generic.MutableProperty
import kotlin.reflect.KProperty0

interface SavedStateHolder {

    fun bind(tag: String, property: MutableProperty<*>)
}

fun <T, P : MutableProperty<T>> P.with(tag: String, savedState: SavedStateHolder): P {
    savedState.bind(tag, this)
    return this
}

fun <P : MutableProperty<*>> SavedStateHolder.connect(kProperty: KProperty0<P>) {
    bind(kProperty.name, kProperty.get())
}
