package com.github.greennick.properties.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.propertyOf

fun <T : Any?> LiveData<out T>.asProperty(): Property<T?> = propertyOf(value).also { observeForever(it::set) }

@Throws(IllegalStateException::class)
fun <T> LiveData<out T?>.asNonNullProperty(): Property<T> =
    propertyOf(value ?: throw IllegalStateException("Required value was null.")).also { property ->
        observeForever { it?.let(property::set) }
    }

fun <T : Any> LiveData<out T?>.asNonNullProperty(defaultValue: T): Property<T> =
    propertyOf(value ?: defaultValue).also { property ->
        observeForever { it?.let(property::set) }
    }

fun <T : Any?> MutableLiveData<T>.asProperty(): MutableProperty<T?> = propertyOf(value).also {
    this.observeForever(it::set)
    it.subscribe(this::setValue)
}

@Throws(IllegalStateException::class)
fun <T : Any> MutableLiveData<T>.asNonNullProperty(): MutableProperty<T> =
    propertyOf(checkNotNull(value))

@JvmName("asNonNullPropertyX")
@Throws(IllegalStateException::class)
fun <T : Any> MutableLiveData<T?>.asNonNullProperty(): MutableProperty<T> =
    propertyOf(checkNotNull(value)).also { property ->
        this.observeForever { it?.let(property::set) }
        property.subscribe(this::setValue)
    }

fun <T : Any> MutableLiveData<T>.asNonNullProperty(defaultValue: T): MutableProperty<T> =
    propertyOf(value ?: defaultValue).also { property ->
        this.observeForever { it?.let(property::set) }
        property.subscribe(this::setValue)
    }

@JvmName("asNonNullPropertyX")
fun <T : Any> MutableLiveData<T?>.asNonNullProperty(defaultValue: T): MutableProperty<T> =
    propertyOf(value ?: defaultValue).also { property ->
        this.observeForever { it?.let(property::set) }
        property.subscribe(this::setValue)
    }
