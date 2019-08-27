[![](https://jitpack.io/v/green-nick/properties-android.svg)](https://jitpack.io/#green-nick/properties-android)
# Android extensions for [Properties](https://github.com/green-nick/properties)
Small, lightweight library that allows to bind Android views and dialogs to properties.
Main purpose is binding `Views` to `ViewModels` in MVVM patterns.

## Structure:
There is main project called `binds` and extensions: `bindsx`, `lifecycle`
which supports androidx views and lifecycle bindings.

## List of available bindings:
```
View bindVisibility -> Property<Boolean>
View bindEnabled -> Property<Boolean>

TextView bindText -> Property<Any?>
TextView bindTextId -> Property<Int>
TextView bindHint -> Property<CharSequence?>
TextView bindHintId -> Property<Int>
TextView bindTextBidirectionally <-> MutableProperty<String>

EditText bindError -> Property<CharSequence?>

TextInputLayout bindError -> Property<CharSequence?>   || bindInputLayoutError
TextInputLayout bindErrorEnabled -> Property<Boolean>  || bindInputLayoutErrorEnabled
TextInputLayout bindHint -> Property<CharSequence?>    || bindInputLayoutHint
TextInputLayout bindHintEnabled -> Property<Boolean>   || bindInputLayoutHintEnabled

CompoundButton bindChecked -> Property<Boolean>
CompoundButton bindCheckedBidirectionally <-> MutableProperty<Boolean>

ProgressBar bindProgress -> Property<Int>

SeekBar bindProgressBidirectionally <-> MutableProperty<Int>

AdapterView bindSelectionBidirectionally <-> MutableProperty<Int>

Snackbar bindVisibility -> Property<Boolean>

Dialog bindVisibility -> Property<Boolean>
Dialog bindVisibilityBidirectionally <-> MutableProperty<Boolean>
```
### View binding:
This is example for `binds` module:
```
val userEmail: TextView
val emailEnabled: MutableProperty<Boolean> = propertyOf(true)

userEmail.bindEnabled(emailEnabled)

// email TextView is enabled now
// but if we will do this:

emailEnabled.value = false

// emailField will become disabled
```
Besides that there is another way for binding using `bindsx` module:
```
val userEmail: TextView
val emailEnabled: MutableProperty<Boolean> = propertyOf(true)

bindEnabled(userEmail, emailEnabled, Lifecycle.Event.ON_PAUSE)

// or even simpler forms:

bindEnabled(userEmail, emailEnabled) // ON_DESTROY is default
bindEnabled(R.id.user_email, emailEnabled) // you can pass View's id only
```
The main differences from previous are:
1. You can call them only in scope of Fragment/FragmentActivity (and descendants)
2. You can pass View's id instead of View, it uses `findViewById` under the hood.  
But notice, that it can throw `IllegalArgumentException` or `ClassCastException` 
in case of wrong View type or View with given id is missed on attached layout.
3. These binds handle unsubscribe automatically:   
they perform unsubscribe at ON_DESTROY lifecycle event by default  
(happened at Activity's `onDestroy()` or Fragment's `onDestroyView()`)  
or at passed one.

## Lifecycle handling

Every bind method from `binds` module returns `Subscription` object which you have to keep 
and call `unsubscribe()` when you don't want to receive updates 
or keep reference to bound views.

For example in case when Activity is recreating after rotation 
but your ViewModel with properties staying the same.  
To prevent memory leaks use that Subscriptions to unsubscribe in `onDestroy` method:
```
class UserActivity: Activity() {
    val viewModel: ViewModel = ... // some VM that staying the same after Activity recreation
    var subscription: Subscription? = null // subscription that will be unsubscribed
    
    fun onCreate {
        setContentView(R.layout.activity_user)
        
        val emailField: TextView = findViewById(R.id.user_email)
        subscription = emailField.bindText(viewModel.email)
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

subscriptions.unsubscribe() // unsubscribe all added subscriptions
```
As said previously, you don't need to handle lifecycle for `bindsx` bindings.  
They'll do this automatically.
### `lifecycle` module extensions:
You can connect `lifecycle` module to simplify lifecycle handling for regular binds.  
Extensions use LifecycleOwner to automatically unsubscribe at appropriate lifecycle 
event without handling `Subscription`'s manually:
```
emailField.bindVisibility(vm.email).toEvent(this /* LifecycleOwner */, Lifecycle.Event.ON_DESTROY)

// or

emailField.bindVisibility(vm.email).toDestroy(this /* LifecycleOwner */)
```
`toStop` and `toPause` are also available.
## Additions
### `binds` module:
There is extension for `debouncePropertyOf` that schedules updates on given `Handler`:

`handlerDebouncePropertyOf`

By default it uses `Handler` based on main `Looper`, so you can dispatch updates  
on UI thread with no worries. 
### `bindsx` module:
If you have to bind some custom data but don't want to handle lifecycle,
you can use `bind` function  
(also available only in scope of `FragmentActivity` or `Fragment` class)
```
// vm.items - Property<List<Item>>
// adapter - RecyclerView's adapter that works with Items

bind(vm.items, Lifecycle.Event.ON_STOP) { // ON_DESTROY is default
    adapter.update(it)
}
```
Besides that, there are also `bindNonNull`, `bindOnTrue`, `bindOnFalse` extensions

Also you can implement BoundAdapter interface in your adapters 
for more simplified usage:
```
// adapter - RecyclerView's adapter that works with Items
// and implements BoundAdapter

bind(vm.items, adapter) // attached to ON_DESTROY event by default
```
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
implementation "com.github.green-nick.properties-android:bindsx:{latest version}"
implementation "com.github.green-nick.properties-android:lifecycle:{latest version}"
```
