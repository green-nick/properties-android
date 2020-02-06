package com.github.greennick.properties.lifecycle

import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.Event.*

class MockedLifecycleOwner: LifecycleOwner {
    private val registry = LifecycleRegistry(this)

    override fun getLifecycle() = registry

    fun onCreate() = registry.handleLifecycleEvent(ON_CREATE)

    fun onStart() = registry.handleLifecycleEvent(ON_START)

    fun onResume() = registry.handleLifecycleEvent(ON_RESUME)

    fun onPause() = registry.handleLifecycleEvent(ON_PAUSE)

    fun onStop() = registry.handleLifecycleEvent(ON_STOP)

    fun onDestroy() = registry.handleLifecycleEvent(ON_DESTROY)
}
