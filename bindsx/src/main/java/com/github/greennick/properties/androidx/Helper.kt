package com.github.greennick.properties.androidx

import android.app.Activity
import android.view.View

fun <V : View> Activity.view(id: Int): V =
    findViewById(id)
        ?: throw IllegalArgumentException("ID does not reference a View inside this Activity")
