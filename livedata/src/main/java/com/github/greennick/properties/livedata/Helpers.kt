package com.github.greennick.properties.livedata

import androidx.lifecycle.MutableLiveData
import com.github.greennick.properties.generic.Property

internal inline fun <T : Any?> Property<T>.livedata(block: (MutableLiveData<T>) -> Unit): MutableLiveData<T> =
    MutableLiveData(value).apply(block)
