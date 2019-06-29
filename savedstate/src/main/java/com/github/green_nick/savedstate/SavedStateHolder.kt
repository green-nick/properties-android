package com.github.green_nick.savedstate

import com.github.greennick.properties.generic.MutableProperty
import kotlin.reflect.KProperty

interface SavedStateHolder {

    fun bind(tag: String, property: MutableProperty<*>)
}

fun <T, P : MutableProperty<T>> P.with(tag: String, savedState: SavedStateHolder): P {
    savedState.bind(tag, this)
    return this
}

fun <P : MutableProperty<*>> SavedStateHolder.connect(kProperty: KProperty<P>) {
    bind(kProperty.name, kProperty.call())
}
