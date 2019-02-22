package com.github.greennick.properties.android

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.CheckBox
import com.github.greennick.properties.propertyOf

import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class CheckBoxBindingsTests {
    private lateinit var checkBox: CheckBox

    @Before
    fun initContext() {
        checkBox = CheckBox(InstrumentationRegistry.getTargetContext())
    }

    @Test
    fun checkbox_checked_after_binding() {
        val property = propertyOf(true)

        checkBox.bindChecked(property)

        assertTrue(checkBox.isChecked)
    }

    @Test
    fun checkbox_changed_checking() {
        val property = propertyOf(true)

        checkBox.bindChecked(property)

        assertTrue(checkBox.isChecked)

        property.value = false
        assertFalse(checkBox.isChecked)
    }

    @Test
    fun checkbox_stops_changing_after_unsubscribe() {
        val property = propertyOf(true)

        val subscription = checkBox.bindChecked(property)

        property.value = false
        assertFalse(checkBox.isChecked)

        subscription.unsubscribe()
        property.value = true
        assertFalse(checkBox.isChecked)
    }

    @Test
    fun checkbox_changes_property() {
        val property = propertyOf(false)

        checkBox.bindCheckedBidirectionally(property)

        assertFalse(checkBox.isChecked)

        checkBox.isChecked = true
        assertTrue(property.value)
    }

    @Test
    fun property_stop_changing_after_unsubscribe() {
        val property = propertyOf(false)
        val subscription = checkBox.bindCheckedBidirectionally(property)

        checkBox.isChecked = true

        subscription.unsubscribe()

        checkBox.isChecked = false
        assertTrue(property.value)
    }
}
