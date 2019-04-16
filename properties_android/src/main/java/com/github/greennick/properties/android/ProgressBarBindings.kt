package com.github.greennick.properties.android

import android.widget.ProgressBar
import com.github.greennick.properties.generic.invoke
import com.github.greennick.properties.generic.Property

fun ProgressBar.bindProgress(property: Property<Int>) = property { progress = it }