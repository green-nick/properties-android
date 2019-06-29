package com.github.green_nick.savedstate

import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.github.greennick.properties.propertyOf

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SavedStateHolderTests {
    private val prop = propertyOf(1)

    @Test
    fun useAppContext() {
        val savedState = SavedStateHolderImpl(Strict.NONE)

        savedState.connect(::prop)

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()

        val bundle = Bundle()
        savedState.onSaveInstanceState(appContext, bundle)
        if (!bundle.containsKey("prop")) {
            throw IllegalStateException()
        }
    }
}
