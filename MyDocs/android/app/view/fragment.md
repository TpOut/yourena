FragmentContainerView（老版本）   

setPrimaryNavigationFragment()   

`FragmentManager.setFragmentFactory(FragmentFactory)`

setMaxLifecycle      



fragment 之间传递数据可以用viewModel  

甚至现在已经可以类似做到setResult 了  



甚至activity 的appbar ，menu 之类的回调也关联起来了，

当然也可以单独fragment 实例化appbar   



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



