package com.github.greennick.properties.androidx

import com.github.greennick.properties.generic.Property
import com.github.greennick.properties.subscriptions.ListenableSubscription
import com.google.android.material.snackbar.Snackbar

fun Snackbar.bindVisibility(property: Property<Boolean>): ListenableSubscription =
    property.subscribe { if (it) show() else dismiss() }
