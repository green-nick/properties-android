package com.github.greennick.properties.androidx

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import java.lang.ClassCastException

inline fun <reified V : View> Activity.find(id: Int): V {
    val found: View = findViewById(id)
        ?: throw IllegalArgumentException("ID does not reference a View inside this Activity $this")

    return try {
        found as V
    } catch (e: ClassCastException) {
        throw ClassCastException("View $found does not correspond required type ${V::class.java}")
    }
}

inline fun <reified V : View> Fragment.find(id: Int): V {
    val found: View = requireView().findViewById(id)
        ?: throw IllegalArgumentException("ID does not reference a View inside this Fragment $this")

    return try {
        found as V
    } catch (e: ClassCastException) {
        throw ClassCastException("View $found does not correspond required type ${V::class.java}")
    }
}
