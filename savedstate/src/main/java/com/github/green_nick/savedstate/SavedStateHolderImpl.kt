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
                "SavedStateHolder already has property with given tag: $tag. Don't put it again."
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
                is Byte -> bundle.putByte(tag, value)
                is ByteArray -> bundle.putByteArray(tag, value)
                is Short -> bundle.putShort(tag, value)
                is ShortArray -> bundle.putShortArray(tag, value)
                is Int -> bundle.putInt(tag, value)
                is IntArray -> bundle.putIntArray(tag, value)
                is Long -> bundle.putLong(tag, value)
                is LongArray -> bundle.putLongArray(tag, value)
                is Float -> bundle.putFloat(tag, value)
                is FloatArray -> bundle.putFloatArray(tag, value)
                is Double -> bundle.putDouble(tag, value)
                is DoubleArray -> bundle.putDoubleArray(tag, value)
                is Char -> bundle.putChar(tag, value)
                is CharArray -> bundle.putCharArray(tag, value)
                is String -> bundle.putString(tag, value)
                is CharSequence -> bundle.putCharSequence(tag, value)
                is Serializable -> bundle.putSerializable(tag, value)
                is Parcelable -> bundle.putParcelable(tag, value)
                else -> onSaveInstanceStateError(context, value)
            }.let {}
        }
    }

    fun onRestoreInstanceState(context: Context, bundle: Bundle) {
        boundProps.forEach { (tag, prop) ->

        }
    }

    private fun onSaveInstanceStateError(context: Context, value: Any?) {
        val message = "Cannot save value $value into Bundle"
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
}
