package com.github.greennick.properties.livedata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.greennick.properties.emptyProperty
import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.propertyOf
import org.junit.Rule
import org.junit.Test

class PropertyToLiveDataTests {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun `LiveData get init value from Property`() {
        val init = "Hello"
        val property = propertyOf(init)
        val livedata = property.asLiveData()

        assert(livedata.value == init)
    }

    @Test
    fun `LiveData receives updates from Property`() {
        val property = propertyOf("hello")
        val livedata = property.asLiveData()

        property.value = "world"
        assert(livedata.value == "world")

        property.value = "hello"
        assert(livedata.value == property.value)
    }

    @Test
    fun `Property changes its value when LiveData is being changed`() {
        val property = propertyOf("hello")
        val livedata = property.asLiveData()

        livedata.value = "world"
        assert(property.value == "world")
    }

    @Test
    fun `non-null Property doesn't change its value when set null to LiveData`() {
        val property = propertyOf("hello")
        val livedata = property.asLiveData()

        livedata.value = null
        assert(property.value == "hello")
    }

    @Test
    fun `nullable Property receives null value from mapped LiveData`() {
        val property = emptyProperty("hello")
        val livedata = property.asLiveData()

        livedata.value = null
        assert(property.value == null)
    }

    @Test
    fun `non-null immutable Property doesn't change its value when set any to LiveData`() {
        val property: Property<String> = propertyOf("hello")
        val livedata = property.asLiveData() as MutableLiveData<String>

        livedata.value = "world"
        assert(property.value == "hello")
        livedata.value = null
        assert(property.value == "hello")
    }

    @Test
    fun `nullable immutable Property doesn't change its value when set any to LiveData`() {
        val property: Property<String?> = emptyProperty("hello")
        val livedata = property.asLiveData() as MutableLiveData<String?>

        livedata.value = "world"
        assert(property.value == "hello")
        livedata.value = null
        assert(property.value == "hello")
    }
}
