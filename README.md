[![](https://jitpack.io/v/green-nick/properties-android.svg)](https://jitpack.io/#green-nick/properties-android)
# Android extensions for [Properties](https://github.com/green-nick/properties)
Small, lightweight library that allows to bind Android views and dialogs to properties.  
Written in pure Kotlin, without thirdparty dependenices, Kotlin oriented.

Could be usefull in MVVM patterns, when you need to bind `Views` and `ViewModels`.

## How to add to Project:
**Step 1.** Add the JitPack repository to your build file.  
Add this in your module's build.gradle at the end of repositories:  
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```
**Step 2.** Add the dependency
```
dependencies {
    implementation 'com.github.green-nick:properties-android:0.9'
}
```
