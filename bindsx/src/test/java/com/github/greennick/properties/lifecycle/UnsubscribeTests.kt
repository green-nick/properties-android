package com.github.greennick.properties.lifecycle

import com.github.greennick.properties.androidx.*
import com.github.greennick.properties.propertyOf
import org.junit.Test

class UnsubscribeTests {

    @Test
    fun `unsubscribe on onPause event`() {
        val owner = MockedLifecycleOwner()
        val property = propertyOf(0)
        val missedValue = 100

        var lastValue = property.value

        property.subscribe {
            lastValue = it
        }.toPause(owner)

        owner.onCreate()
        owner.onStart()
        owner.onResume()
        owner.onPause()

        property.value = missedValue

        assert(lastValue != missedValue)
    }

    @Test
    fun `unsubscribe on onStop event`() {
        val owner = MockedLifecycleOwner()
        val property = propertyOf(0)
        val missedValue = 100

        var lastValue = property.value

        property.subscribe {
            lastValue = it
        }.toStop(owner)

        owner.onCreate()
        owner.onStart()
        owner.onResume()
        owner.onPause()
        owner.onStop()

        property.value = missedValue

        assert(lastValue != missedValue)
    }

    @Test
    fun `unsubscribe on onDestroy event`() {
        val owner = MockedLifecycleOwner()
        val property = propertyOf(0)
        val missedValue = 100

        var lastValue = property.value

        property.subscribe {
            lastValue = it
        }.toDestroy(owner)

        owner.onCreate()
        owner.onStart()
        owner.onResume()
        owner.onPause()
        owner.onStop()
        owner.onDestroy()

        property.value = missedValue

        assert(lastValue != missedValue)
    }
}
