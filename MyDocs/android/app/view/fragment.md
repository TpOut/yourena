FragmentContainerView（老版本）   

setPrimaryNavigationFragment()   

`FragmentManager.setFragmentFactory(FragmentFactory)`

setMaxLifecycle      



事务   

replace / addBackStack / hide / detach  等  

> 注意FragmentManager 的detach 和用户可调用的detach 不同  



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



