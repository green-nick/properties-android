package com.github.greennick.properties.savedstate

import com.github.greennick.properties.propertyOf
import org.junit.Test

class SavedStateHolderTest {
    private val awesomeCoolProperty = propertyOf(1)

    @Test
    fun test() {
        val savedState = SavedStateHolder(Strict.NONE)

        savedState.put(::awesomeCoolProperty)

        println(savedState)
    }
}
