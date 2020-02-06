package com.github.greennick.properties.livedata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.greennick.properties.generic.MutableProperty
import org.junit.Rule
import org.junit.Test

class LiveDataToPropertyTests {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Test
    fun `Property get init value from LiveData`() {
        val init = "Hello"
        val livedata = MutableLiveData(init)
        val property = livedata.asProperty()

        assert(property.value == init)
    }

    @Test
    fun `Property receives updates from LiveData`() {
        val livedata = MutableLiveData("hello")
        val property = livedata.asProperty()

        livedata.value = "world"
        assert(property.value == "world")

        livedata.value = "hello"
        assert(property.value == property.value)
    }

    @Test
    fun `LiveData changes its value when Property is being changed`() {
        val livedata = MutableLiveData("hello")
        val property = livedata.asProperty()

        property.value = "world"
        assert(livedata.value == "world")
    }

    @Test
    fun `non-null Property doesn't change its value when set null to LiveData`() {
        val livedata = MutableLiveData("hello")
        val property = livedata.asNonNullProperty()

        livedata.value = null
        assert(property.value == "hello")
    }

    @Test
    fun `non-null Property gets default value when mapped from empty LiveData`() {
        val livedata = MutableLiveData<String>()
        val property = livedata.asNonNullProperty("hello")

        livedata.value = null
        assert(property.value == "hello")
    }

    @Test(expected = IllegalStateException::class)
    fun `mapper throws Exception when try to get non-null Property from empty LiveData`() {
        val livedata = MutableLiveData<String>()
        val property: MutableProperty<String> = livedata.asNonNullProperty()

        livedata.value = null
        assert(property.value == "hello")
    }

    @Test
    fun `nullable Property receives null value from mapped LiveData`() {
        val livedata = MutableLiveData("hello")
        val property = livedata.asProperty()

        livedata.value = null
        assert(property.value == null)
    }

    @Test
    fun `immutable LiveData doesn't change its value when set any to mapped Property`() {
        val livedata = MutableLiveData("hello")
        val property = (livedata as LiveData<String?>).asProperty() as MutableProperty<String?>

        property.value = "world"
        assert(livedata.value == "hello")
        property.value = null
        assert(livedata.value == "hello")
    }
}
