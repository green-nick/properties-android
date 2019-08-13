package com.github.greennick.properties.savedstate

import com.github.greennick.properties.generic.MutableProperty
import java.io.Serializable

enum class Strict { NONE, ERROR }

private const val logTag = "SavedStateHolder"

class SavedStateHolder(private val strictMode: Strict) {

    private val boundProperties = mutableMapOf<String, MutableProperty<*>>()

    fun put(tag: String, property: MutableProperty<*>) {
        boundProperties[tag] = property
    }

    fun onSave(bundle: MutableMap<String, Any?>) {
        boundProperties.forEach { (tag, property) -> bundle[tag] = property.value }
    }

    @Suppress("UNCHECKED_CAST")
    fun onRestore(bundle: Map<String, Any?>) {
        val keys = bundle.keys
        boundProperties.forEach { (tag, property) ->
            if (tag !in keys) return@forEach

            when (val value = bundle.getValue(tag)) {
                is Int -> trySet(property, value)
                is Boolean -> trySet(property, value)
                is String -> trySet(property, value)
                is String? -> trySet(property, value)
                is CharSequence -> trySet(property, value)
                is CharSequence? -> trySet(property, value)
                is Long -> trySet(property, value)
                is Serializable -> trySet(property, value)
                is Serializable? -> trySet(property, value)
                is Double -> trySet(property, value)
                is Float -> trySet(property, value)
                is Int? -> trySet(property, value)
                is Boolean? -> trySet(property, value)
                is Long? -> trySet(property, value)
                is Double? -> trySet(property, value)
                is Float? -> trySet(property, value)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> trySet(property: MutableProperty<*>, newValue: T) {
        try {
            val casted = (property as MutableProperty<T>)
            casted.value.check()
            casted.value = newValue
        } catch (e: ClassCastException) {
            if (strictMode == Strict.ERROR) throw e
        }
    }

    @Suppress("unused")
    private fun <T> T.check() {
    }

    override fun toString() = "SavedStateHolder: Strict: $strictMode\n" +
            boundProperties.entries.joinToString("\n")
}
