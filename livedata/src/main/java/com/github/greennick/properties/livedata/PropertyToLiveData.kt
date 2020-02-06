package com.github.greennick.properties.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.greennick.properties.generic.MutableProperty
import com.github.greennick.properties.generic.Property

fun <T : Any?> Property<T>.asLiveData(): LiveData<T> = MutableLiveData(value).also { subscribe(it::setValue) }

fun <T> MutableProperty<T?>.asLiveData(): MutableLiveData<T?> = livedata {
    it.observeForever(this::set)
    this.subscribe(it::setValue)
}

@JvmName("asNonNullLiveData")
fun <T : Any> MutableProperty<T>.asLiveData(): MutableLiveData<T> = livedata {
    it.observeForever { value -> value?.let(this::set) }
    this.subscribe(it::setValue)
}
