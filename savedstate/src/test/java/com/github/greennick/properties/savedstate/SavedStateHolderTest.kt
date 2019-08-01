package com.github.greennick.properties.savedstate

import com.github.greennick.properties.propertyOf
import org.junit.Test

class SavedStateHolderTest {
    private val prop = propertyOf(1)

    @Test
    fun test() {
        val savedState = SavedStateHolderImpl(Strict.NONE)

        savedState.connect(::prop)

        println(savedState)
    }
}