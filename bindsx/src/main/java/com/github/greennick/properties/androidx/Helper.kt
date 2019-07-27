package com.github.greennick.properties.androidx

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment

fun <V : View> Activity.view(id: Int): V =
    findViewById(id)
        ?: throw IllegalArgumentException("ID does not reference a View inside this Activity")

fun <V : View> Fragment.view(id: Int): V {
    val found: V? = requireView().findViewById(id)

    return found
        ?: throw IllegalArgumentException("ID does not reference a View inside this Activity")
}
