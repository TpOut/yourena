FragmentContainerView   

setPrimaryNavigationFragment()   

`FragmentManager.setFragmentFactory(FragmentFactory)`

setMaxLifecycle      



事务   

replace / addBackStack / hide / detach  等  



动画  

```kotlin
supportFragmentManager.commit {
    setCustomAnimations(
        enter = R.anim.slide_in,
        exit = R.anim.fade_out,
        popEnter = R.anim.fade_in,
        popExit = R.anim.slide_out
    )
}
```



