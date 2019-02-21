package com.github.greennick.properties.android

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.CheckBox
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
class CheckBoxBindingsTests {
    lateinit var context: Context

    @Before
    fun initContext() {
        context = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun checkBox_checked_after_binding() {
        val checked = true
        val property = propertyOf(checked)

        val checkBox = CheckBox(context)
        checkBox.bindChecked(property)

        val checkBoxChecked = checkBox.isChecked

        assertEquals(checked, checkBoxChecked)
    }
}
