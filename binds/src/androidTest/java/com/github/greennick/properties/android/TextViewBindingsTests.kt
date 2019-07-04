package com.github.greennick.properties.android

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
    private lateinit var textView: TextView

    @Before
    fun initContext() {
        textView = TextView(InstrumentationRegistry.getTargetContext())
    }

    @Test
    fun textView_text_changed_after_binding() {
        val initValue = "Hello!"
        val property = propertyOf(initValue)
        textView.bindText(property)

        assertEquals(textView.string, initValue)
    }

    @Test
    fun textView_text_changing() {
        val property = propertyOf("Hello!")
        textView.bindText(property)

        val newValue = "World"
        property.value = newValue
        assertEquals(textView.string, newValue)
    }

    @Test
    fun textView_stops_changing_after_unsubscribe() {
        val initValue = "Hello"
        val property = propertyOf(initValue)
        val subscription = textView.bindText(property)

        subscription.unsubscribe()
        val newValue = "World"
        property.value = newValue

        assertNotEquals(textView.string, newValue)
        assertEquals(textView.string, initValue)
    }

    @Test
    fun textView_changes_property() {
        val initValue = "Hello"
        val property = propertyOf(initValue)

        textView.bindTextBidirectionally(property)

        val enteredValue = "World"
        textView.text = enteredValue

        assertEquals(property.value, enteredValue)
    }

    @Test
    fun property_stops_changing_after_unsubscribe() {
        val property = propertyOf("Hello")

        val subscription = textView.bindTextBidirectionally(property)

        val enteredValue = "World"
        textView.text = enteredValue

        subscription.unsubscribe()
        textView.text = "!!!"

        assertEquals(property.value, enteredValue)
    }

    private val TextView.string get() = text.toString()
}
