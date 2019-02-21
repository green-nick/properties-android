package com.github.greennick.properties.android

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
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
class ViewBindingsTests {
    lateinit var context: Context

    @Before
    fun initContext() {
        context = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun view_invisible_after_binding() {
        val visible = false
        val property = propertyOf(visible)

        val view = View(context)
        view.bindVisibility(property)

        val viewVisible = view.visibility == View.VISIBLE

        assertEquals(visible, viewVisible)
    }

    @Test
    fun view_disabled_after_binding() {
        val enable = false
        val property = propertyOf(enable)

        val view = View(context)
        view.bindEnabled(property)

        val viewEnable = view.isEnabled

        assertEquals(enable, viewEnable)
    }
}
