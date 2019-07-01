package com.github.green_nick.savedstate

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import com.github.greennick.properties.generic.MutableProperty
import java.io.Serializable

enum class Strict { NONE, SOFT, WARN, HARD }

private const val logTag = "SavedStateHolder"

class SavedStateHolderImpl(private val strictMode: Strict) : SavedStateHolder {
    private val boundProps = mutableMapOf<String, MutableProperty<*>>()

    override fun bind(tag: String, property: MutableProperty<*>) {
        val contains = boundProps.contains(tag)
        if (contains) {
            val message =
                "SavedStateHolder: Property with tag $tag already exists. Don't put it again."
            when (strictMode) {
                Strict.NONE -> return // do nothing
                Strict.SOFT -> Log.w(logTag, message)
                Strict.WARN -> Log.w(logTag, message)
                Strict.HARD -> throw IllegalStateException(message)
            }.let {}
        } else {
            boundProps[tag] = property
        }
    }

    fun onSaveInstanceState(context: Context, bundle: Bundle) {
        boundProps.forEach { (tag, prop) ->
            when (val value = prop.value) {
                is Int -> bundle.putInt(tag, value)
                is Long -> bundle.putLong(tag, value)
                is String? -> bundle.putString(tag, value)
                is CharSequence? -> bundle.putCharSequence(tag, value)
                is Serializable? -> bundle.putSerializable(tag, value)
                is Double -> bundle.putDouble(tag, value)
                is Float -> bundle.putFloat(tag, value)
                is Parcelable? -> bundle.putParcelable(tag, value)
                is Byte -> bundle.putByte(tag, value)
                is ByteArray? -> bundle.putByteArray(tag, value)
                is Short -> bundle.putShort(tag, value)
                is ShortArray? -> bundle.putShortArray(tag, value)
                is IntArray? -> bundle.putIntArray(tag, value)
                is LongArray? -> bundle.putLongArray(tag, value)
                is FloatArray? -> bundle.putFloatArray(tag, value)
                is DoubleArray? -> bundle.putDoubleArray(tag, value)
                is Char -> bundle.putChar(tag, value)
                is CharArray? -> bundle.putCharArray(tag, value)
                else -> unsupportedTypeError(context, value)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun onRestoreInstanceState(context: Context, bundle: Bundle) {
        val keys = bundle.keySet()
        boundProps.forEach { (tag, prop) ->
            if (tag in keys) {
                val value = bundle.get(tag)
//                try {
                when (value) {
                    is Int -> (prop as MutableProperty<Int>).value = value
                    is Long -> (prop as MutableProperty<Long>).value = value
                    is String -> (prop as MutableProperty<String>).value = value
                    is String? -> (prop as MutableProperty<String?>).value = value
                    is CharSequence -> (prop as MutableProperty<CharSequence>).value = value
                    is CharSequence? -> (prop as MutableProperty<CharSequence?>).value = value
                    is Serializable -> (prop as MutableProperty<Serializable>).value = value
                    is Serializable? -> (prop as MutableProperty<Serializable?>).value = value
                    is Double -> (prop as MutableProperty<Double>).value = value
                    is Float -> (prop as MutableProperty<Float>).value = value
                    is Parcelable -> (prop as MutableProperty<Parcelable>).value = value
                    is Parcelable? -> (prop as MutableProperty<Parcelable?>).value = value
                    is Byte -> (prop as MutableProperty<Byte>).value = value
                    is ByteArray -> (prop as MutableProperty<ByteArray>).value = value
                    is ByteArray? -> (prop as MutableProperty<ByteArray?>).value = value
                    is Short -> (prop as MutableProperty<Short>).value = value
                    is ShortArray -> (prop as MutableProperty<ShortArray>).value = value
                    is ShortArray? -> (prop as MutableProperty<ShortArray?>).value = value
                    is IntArray -> (prop as MutableProperty<IntArray>).value = value
                    is IntArray? -> (prop as MutableProperty<IntArray?>).value = value
                    is LongArray -> (prop as MutableProperty<LongArray>).value = value
                    is LongArray? -> (prop as MutableProperty<LongArray?>).value = value
                    is FloatArray -> (prop as MutableProperty<FloatArray>).value = value
                    is FloatArray? -> (prop as MutableProperty<FloatArray?>).value = value
                    is DoubleArray -> (prop as MutableProperty<DoubleArray>).value = value
                    is DoubleArray? -> (prop as MutableProperty<DoubleArray?>).value = value
                    is Char -> (prop as MutableProperty<Char>).value = value
                    is CharArray -> (prop as MutableProperty<CharArray>).value = value
                    is CharArray? -> (prop as MutableProperty<CharArray?>).value = value
                    else -> unsupportedTypeError(context, value)
                }.let {}
                prop.value.let {}
//                } catch (e: ClassCastException) {
//                    wrongValueInBundleError(context, tag, value)
//                }
            }
        }
    }

    private fun unsupportedTypeError(context: Context, value: Any?) {
        val message = "SavedStateHolder: value $value is not supported to work with Bundle"
        when (strictMode) {
            Strict.NONE -> return // do nothing
            Strict.SOFT -> Log.w(logTag, message)
            Strict.WARN -> {
                Log.w(logTag, message)
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
            Strict.HARD -> throw IllegalStateException(message)
        }
    }

    private fun wrongValueInBundleError(context: Context, tag: String, value: Any?) {
        val message = "SavedStateHolder: [$tag] value $value is not correspond to any property"
        when (strictMode) {
            Strict.NONE -> return // do nothing
            Strict.SOFT -> Log.w(logTag, message)
            Strict.WARN -> {
                Log.w(logTag, message)
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
            Strict.HARD -> throw IllegalStateException(message)
        }
    }

    override fun toString() = "SavedStateHolder: Strict: $strictMode\n" +
            boundProps.entries.joinToString("\n")
}
