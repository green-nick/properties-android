package com.github.greennick.properties.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.github.greennick.properties.subscriptions.Subscription

/**
 * Binds unsubscribe to set Lifecycle event.
 * Will automatically unsubscribe it when this event happens
 */
fun Subscription.toEvent(owner: LifecycleOwner, boundEvent: Lifecycle.Event) {
    owner.lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (boundEvent == event) {
                unsubscribe()
            }
        }
    })
}

/**
 * Bind unsubscribe to onPause lifecycle event of Lifecycle owner
 */
fun Subscription.toPause(owner: LifecycleOwner): Unit = toEvent(owner, Lifecycle.Event.ON_PAUSE)

/**
 * Bind unsubscribe to onPause lifecycle event of Lifecycle owner
 */
fun Subscription.toStop(owner: LifecycleOwner): Unit = toEvent(owner, Lifecycle.Event.ON_STOP)

/**
 * Bind unsubscribe to onDestroy lifecycle event of Lifecycle owner
 */
fun Subscription.toDestroy(owner: LifecycleOwner): Unit = toEvent(owner, Lifecycle.Event.ON_DESTROY)
