[![](https://jitpack.io/v/green-nick/properties-android.svg)](https://jitpack.io/#green-nick/properties-android)
# Android extensions for [Properties](https://github.com/green-nick/properties)
Small, lightweight library that allows to bind Android views and dialogs to properties.
Main purpose is binding `Views` to `ViewModels` in MVVM patterns.

## Usage:
There is main project called `binds` and extension `bindsx` 
which supports androidx views and 
such androidx-related functionality as binding `Subscription` to `LifecycleOwner`
### List of available bindings:
```
View.bindVisibility(...)
View.bindEnabled(...)

TextView.bindText(...)
TextView.bindTextId(...)
TextView.bindHint(...)
TextView.bindHintId(...)
TextView.bindTextBidirectionally(...)

EditText.bindError(...)

TextInputLayout.bindError(...)
TextInputLayout.bindErrorEnabled(...)
TextInputLayout.bindHint(...)
TextInputLayout.bindHintEnabled(...)

CompoundButton.bindChecked(...)
CompoundButton.bindCheckedBidirectionally(...)

ProgressBar.bindProgress(...)

SeekBar.bindProgressBidirectionally(...)

AdapterView.bindSelectionBidirectionally(...)

Snackbar.bindVisibility(...)

Dialog.bindVisibility(...)
Dialog.bindVisibilityBidirectionally(...)

```
### Examples for `binds`:
```
val emailEnabled = propertyOf(true)
val email = findViewById<TextView>(R.id.user_email)

email.bindEnabled(emailEnabled)

// email TextView is enabled now
// but if we will do this:

emailEnabled.value = false

// emailField will become disabled
```
Also you can bind views without finding them explicitly, only by passing its ids.
You can do this only in scope of Activity class.
But be aware of its existing and correct view type:
```
val emailEnabled = propertyOf(true)

bindEnabled(R.id.user_email, emailEnabled) // this works the same as in example above

// but this will crash:

val emailChecked = propertyOf(true)

bindChecked(R.id.user_email, email)

// this happens because bindChecked expects CompoundButton or its descendant
// but you pass TextView's id to it
```
***Don't forget about lifecycle!***

Every bind method returns `Subscription` object which you have to keep 
and call `unsubscribe()` when you don't want to receive updates 
or keep reference to bound views.

For example in cases when Activity is recreating after rotation 
but your ViewModel with properties staying the same.
To prevent memory leaks use that Subscriptions to unsubscribe in `onDestroy` method:
```
class UserActivity: Activity() {
    val viewModel: ViewModel = ... // some VM that staying the same after Activity recreation
    var subscription: Subscription? = null // subscription that will be unsubscribed
    
    fun onCreate {
        setContentView(R.layout.activity_user)
        subscription = bindText(R.id.user_email, viewModel.email)
        ...
    }
    
    fun onDestroy {
        subscription?.unsubscribe() // this will prevent from memory leaks
    }
}
```
Also you can use `CompositeSubscriptions` for unsubscribe many subscriptions at once:
```
val subscriptions = CompositeSubscription()

subscriptions += bindVisibility(...)
subscriptions += bindText(...)
subscriptions += bindEnabled(...)

subscriptions.unsubscribe() // unscubscribe all added subscriptions
```
### Examples for `bindsx`:
Use LifecycleOwner to automatically unsubscribe at appropriate lifecycle event:
```
bindVisibility(emailField, vm.email).toEvent(this /* LifecycleOwner */, Lifecycle.Event.ON_DESTROY)

// or

bindVisibility(emailField, vm.email).toDestroy(this /* LifecycleOwner */)
```
Besides that you can also use simplifier bindings (with View's id only):
```
bindEnabled(R.id.email_field, vm.email, Lifecycle.Event.ON_DESTROY)
```
This function automatically handles View finding and binding to proper lifecycle event.
## How to add to Project:
**Step 1.** Add the JitPack repository to your build file.  
Add this in your module's build.gradle at the end of repositories:  
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```
**Step 2.** Add the dependencies
```
implementation "com.github.green-nick.properties-android:binds:{latest version}"

// if you also want bindsx extensions:
implementation "com.github.green-nick.properties-android:bindsx:{latest version}"
```
