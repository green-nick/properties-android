package com.github.green_nick.savedstate

import android.content.Context
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.github.greennick.properties.emptyProperty
import com.github.greennick.properties.propertyOf

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SavedStateHolderTests {

    private val context: Context get() = InstrumentationRegistry.getTargetContext()

    @Test
    fun bundle_contains_value_with_given_tag() {
        val initValue = 101
        val tag = "prop"
        val prop = propertyOf(initValue)
        val savedState = SavedStateHolderImpl(Strict.HARD)
        savedState.bind(tag, prop)

        val bundle = Bundle()
        savedState.onSaveInstanceState(context, bundle)

        checkIf(bundle.containsKey(tag))
        checkIf(bundle.getInt(tag) == initValue)
    }

    @Test
    fun savedstate_restore_properties_value() {
        val initValue = 101
        val tag = "prop"
        val prop = propertyOf(initValue)
        val savedState = SavedStateHolderImpl(Strict.HARD)
        savedState.bind(tag, prop)

        val bundle = Bundle()
        savedState.onSaveInstanceState(context, bundle)

        prop.value = 2143

        savedState.onRestoreInstanceState(context, bundle)

        checkIf(prop.value == initValue)
    }

    @Test
    fun nullable_property_with_nonull_value() {
        val initValue = "hello"
        val tag = "prop"
        val prop = emptyProperty(initValue)
        val savedState = SavedStateHolderImpl(Strict.HARD)
        savedState.bind(tag, prop)

        val bundle = Bundle()
        savedState.onSaveInstanceState(context, bundle)

        checkIf(bundle.getString(tag) == initValue)
    }

    @Test
    fun nullable_property_with_null_value() {
        val tag = "prop"
        val prop = emptyProperty<String>()
        val savedState = SavedStateHolderImpl(Strict.HARD)
        savedState.bind(tag, prop)

        val bundle = Bundle()
        savedState.onSaveInstanceState(context, bundle)

        checkIf(bundle.containsKey(tag))
        checkIf(bundle.getString(tag) == null)
    }

    @Test
    fun nullable_property_restore_value() {
        val tag = "prop"
        val prop = emptyProperty<String>()
        val savedState = SavedStateHolderImpl(Strict.HARD)
        savedState.bind(tag, prop)

        val bundle = Bundle()
        savedState.onSaveInstanceState(context, bundle)

        prop.value = "hello"
        savedState.onRestoreInstanceState(context, bundle)

        checkIf(prop.value == null)
    }

    @Test
    fun bundle_contains_all_values() {
        val intValue = 101
        val intTag = "intProp"
        val intProp = propertyOf(intValue)

        val stringValue = "hello"
        val stringTag = "stringProp"
        val stringProp = propertyOf(stringValue)

        val savedState = SavedStateHolderImpl(Strict.HARD)
        savedState.bind(intTag, intProp)
        savedState.bind(stringTag, stringProp)

        val bundle = Bundle()
        savedState.onSaveInstanceState(context, bundle)

        checkIf(bundle.containsKey(intTag) && bundle.containsKey(stringTag))
        checkIf(bundle.getInt(intTag) == intValue && bundle.getString(stringTag) == stringValue)
    }

    @Test(expected = IllegalStateException::class)
    fun broken_bundle_throws_illegal_state() {
        val initValue = 101
        val tag = "prop"
        val prop = propertyOf(initValue)
        val savedState = SavedStateHolderImpl(Strict.HARD)
        savedState.bind(tag, prop)

        val bundle = Bundle()
        savedState.onSaveInstanceState(context, bundle)

        bundle.putString(tag, "hello")

        checkIf(bundle.getString(tag) != null)
        checkIf(bundle.getInt(tag) != initValue)

        savedState.onRestoreInstanceState(context, bundle)

        prop.value.let {}
    }
}
