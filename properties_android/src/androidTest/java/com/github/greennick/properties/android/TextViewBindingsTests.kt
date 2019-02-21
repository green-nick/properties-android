package com.github.greennick.properties.android

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.TextView
import com.github.greennick.properties.propertyOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class TextViewBindingsTests {
    lateinit var context: Context

    @Before
    fun initContext() {
        context = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun textView_text_changed_after_binding() {
        val initValue = "Hello!"
        val property = propertyOf(initValue)

        val textView = TextView(context)
        textView.bindText(property)

        assertEquals(textView.text.toString(), initValue)
    }
}
