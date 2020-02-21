[![](https://jitpack.io/v/green-nick/properties-android.svg)](https://jitpack.io/#green-nick/properties-android)
# Android extensions for [Properties](https://github.com/green-nick/properties)
Small, lightweight library that allows binding Android views and dialogs to Properties.
Main purpose is binding `Views` to `ViewModels` in MVVM patterns.

## Structure:
There is main project called `binds` and extensions: `bindsx` and `livedata`
which supports androidx views, Jetpack Lifecycle bindings and Livedata fields.

## `binds` Module
### View binding:
```
class LoginViewModel {
    val email = propertyOf("")
    val emailEnabled = propertyOf(true)
    ...
}

class LoginActivity : Activity() {
    val vm: LoginViewModel = ...
    
    // somewhere in onCreate
    
    val emailField: EditText = findViewById(R.id.email)
    
    emailField.bindEnabled(vm.emailEnabled)
    emailField.bindTextBidirectionally(vm.email)
}
```
After these bindings, our email EditText will respond on changes of `email` and `emailEnabled` properties.  
`bindTextBidirectionally` is two-way binding, so it will change View's attribute and will be changed itself when User enter the text to field.

### Lifecycle handling

Every bind method from `binds` module returns `Subscription` object which you have to keep
and call `unsubscribe()` when you don't want to receive updates
or keep reference to bound views.

For example in case when Activity is recreating after rotation
but your ViewModel with properties has been kept.  
To prevent memory leaks use that Subscriptions to unsubscribe in `onDestroy` (just for example) method:
```
class LoginActivity : Activity() {
    val vm: LoginViewModel = ...
    var subscription: Subscription? = null

    // somewhere in onCreate

    val emailField: TextView = findViewById(R.id.user_email)

    subscription = emailField.bindTextBidirectionally(vm.email)

    override fun onDestroy() {
        subscription?.unsubscribe() // this will prevent from memory leaks
    }
}
```
But keeping every separate subscription is uncomfortably.
So better to use `CompositeSubscriptions` for keeping many subscriptions and unsubscribe from them at once:
```
val subscriptions = CompositeSubscription()

subscriptions += bindVisibility(...)
subscriptions += bindText(...)
subscriptions += bindEnabled(...)

subscriptions.unsubscribe() // unsubscribe all added subscriptions
```

### Additions
There are few additions in this module that could be useful:

1. `debouncePropertyOf` based on Handler with Main Looper (Handler could be changed).
2. `postSet` extension function of MutableProperty that will set given value on UI Thread only, no matter where has it been called.
3. `onclick` and `onLongClick` extensions of View and Activity. Allows you to set lambda-callbacks without View parameter. Version for Activity allows to pass only View's id instead of View itself.
4. `BoundAdapter` - for nicer binding any Adapter to Property which contains List of items.
5. `textChanged` extension of TextView and Activity. Simpler analog of TextWatcher::onTextChanged. Version for Activity allows to pass only TextView's id instead of View itself.
6. `actionListener` extension of TextView and Activity. Simpler analog of OnEditorActionListener. Version for Activity allows to pass only TextView's id instead of View itself.

## `bindsx` Module
### View binding:
```
class LoginViewModel {
    val email = propertyOf("")
    val emailEnabled = propertyOf(true)
    val password = propertyOf("")
    ...
}

class LoginActivity : AppCompatActivity() {
    val vm: LoginViewModel = ...
    
    // somewhere in onCreate
    
    val emailField: EditText = findViewById(R.id.email)
    
    bindEnabled(emailField, vm.emailEnabled)
    bindTextBidirectionally(emailField, vm.email)
    
    bindTextBidirectionally(R.id.password, password)
}
```
There are few differences between these bindings and from `binds` module:
1. Automatic subscription handling.
2. Ability to pass only View's id instead of View.
3. Fragments support.
4. Support of Snackbar and TextInputLayout bindings from Material library.

**Pay attention**  
If you want to use binding by View's id - it will throw exception if View with given id won't be found in Activity/Fragment or it has inappropriate type.

### Lifecycle handling

All bindings from this module that receives Views as parameters are extensions of `LifecycleOwner`.  
Therefore, they "know" when unsubscribe. By default this will be happened on `ON_DESTROY` event\*.  
But you can override this behaviour by passing desired event into `bind...` method:
```
bindEnabled(emailField, vm.emailEnabled, Lifecycle.Event.ON_STOP)
```
In this case, subscription will be cancelled when `ON_STOP` event happens.

\* -  If given `LifecycleOwner` is Fragment, binding will use it's `viewLifecycleOwner` instead of Fragment itself.  
That's because Fragment's lifecycle is wider, than lifecycle of it's Views.  
If by some reason you need to use Fragment as actual LifecycleOwner, please use `asLifecycleOwner()` extension:
```
class LoginFragment: Fragment() {
    
    // somewhere in onViewCreated

    val emailField: EditText = view.findViewById(R.id.email)

    asLifecycleOwner().bindEnabled(emailField, vm.emailEnabled)
}
```

Besides that, if you're using bindings from `binds` module, you can also simplify lifecycle handling by using these extensions of Subscription object:
```
toEvent(LifecycleOwner, Lifecycle.Event)

toPause(LifecycleOwner)

toStop(LifecycleOwner)

toDestroy(LifecycleOwner)
```
For example:
```
class LoginActivity : AppCompatActivity() {
    val vm: LoginViewModel = ...
    
    // somewhere in onCreate
    
    val emailField: EditText = findViewById(R.id.email)
    
    emailField.bindEnabled(vm.emailEnabled).toDestroy(this)
    emailField.bindTextBidirectionally(vm.email).toDestroy(this)
}
```
These bindings will unsubscribe automatically, when `ON_DESTROY` event happens.

### Additions
There are few additions in this module that could be useful:

1. `bind`, `bindNonNull`, `bindOnTrue`, `bindOnFalse` - similar to generic extensions from `Property` library, but lifecycle-aware.
2. `onclick` and `onLongClick` - the same as above in `binds` module but for Fragments.
3. `textChanged` - the same as above in `binds` module but for Fragments.
4. `actionListener` - the same as above in `binds` module but for Fragments.

## List of available bindings:
| View type       | Binding name                  | Module        | Binding direction | Property                  | Comments                                                              |
|-----------------|-------------------------------|---------------|-------------------|---------------------------|-----------------------------------------------------------------------|
| View            | bindVisibility                | binds, bindsx |         ->        | Property\<Boolean>        |                                                                       |
| View            | bindEnabled                   | binds, bindsx |         ->        | Property\<Boolean>        |                                                                       |
| TextView        | bindText                      | binds, bindsx |         ->        | Property<CharSequence?>   |                                                                       |
| TextView        | bindText                      | binds, bindsx |         ->        | Property<Int?>            | Uses String resource. If null - sets null as text.                    |
| TextView        | bindHint                      | binds, bindsx |         ->        | Property<CharSequence?>   |                                                                       |
| TextView        | bindHint                      | binds, bindsx |         ->        | Property<Int?>            | Uses String resource. If null - sets null as text.                    |
| TextView        | bindTextBidirectionally       | binds, bindsx |        <->        | MutableProperty<String>   |                                                                       |
| EditText        | bindError                     | binds, bindsx |         ->        | Property<CharSequence?>   |                                                                       |
| EditText        | bindError                     | binds, bindsx |         ->        | Property<Int?>            | Uses String resource. If null - sets null as text.                    |
| TextInputLayout | bindError                     | bindsx        |         ->        | Property<CharSequence?>   | bindTextInputLayoutError - version with View id                       |
| TextInputLayout | bindError                     | bindsx        |         ->        | Property<Int?>            | Uses String resource. bindTextInputLayoutError - version with View id |
| TextInputLayout | bindErrorEnabled              | bindsx        |         ->        | Property\<Boolean>        | bindTextInputLayoutErrorEnabled - version with View id                |
| TextInputLayout | bindHint                      | bindsx        |         ->        | Property<CharSequence?>   | bindTextInputLayoutHint - version with View id                        |
| TextInputLayout | bindHint                      | bindsx        |         ->        | Property<Int?>            | Uses String resource. bindTextInputLayoutHint - version with View id  |
| TextInputLayout | bindHintEnabled               | bindsx        |         ->        | Property\<Boolean>        | bindTextInputLayoutHintEnabled - version with View id                 |
| CompoundButton  | bindChecked                   | binds, bindsx |         ->        | Property\<Boolean>        |                                                                       |
| CompoundButton  | bindCheckedBidirectionally    | binds, bindsx |        <->        | MutableProperty\<Boolean> |                                                                       |
| ProgressBar     | bindProgress                  | binds, bindsx |         ->        | Property\<Int>            |                                                                       |
| SeekBar         | bindProgressBidirectionally   | binds, bindsx |        <->        | MutableProperty\<Int>     |                                                                       |
| AdapterView     | bindSelectionBidirectionally  | binds, bindsx |        <->        | MutableProperty\<Int>     |                                                                       |
| Snackbar        | bindVisibility                | bindsx        |         ->        | Property\<Boolean>        |                                                                       |
| Dialog          | bindVisibility                | binds, bindsx |         ->        | Property\<Boolean>        |                                                                       |
| Dialog          | bindVisibilityBidirectionally | binds, bindsx |        <->        | MutableProperty\<Boolean> |                                                                       |

## `livedata` module

This module contains LiveData to Property transformations and vice versa.  
Available transformations:
* Property to LiveData: use `asLiveData()` extension.
* Livedata to Property: use `asProperty()` (will produce nullable Properties) or `asNonNullProperty()` with optional default value.


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
implementation "com.github.green-nick.properties-android:livedata:{latest version}"
```
