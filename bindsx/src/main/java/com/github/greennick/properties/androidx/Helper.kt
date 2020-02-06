package com.github.greennick.properties.androidx

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

internal inline fun <reified V : View> Activity.find(id: Int): V {
    val found: View = findViewById(id)
        ?: throw IllegalArgumentException("ID does not reference a View inside this Activity $this")

    return if (found is V) {
        found
    } else {
        throw ClassCastException("View $found does not correspond required type ${V::class.java}")
    }
}

internal inline fun <reified V : View> Fragment.find(id: Int): V {
    val found: View = requireView().findViewById(id)
        ?: throw IllegalArgumentException("ID does not reference a View inside this Fragment $this")

    return if (found is V) {
        found
    } else {
        throw ClassCastException("View $found does not correspond required type ${V::class.java}")
    }
}

internal fun LifecycleOwner.suitableLifecycleOwner() = if (this is Fragment) viewLifecycleOwner else this
