- 属性动画

    `ValueAnimator` `ObjectAnimator` `AnimatorSet`

    ```kotlin
    // 从xml 中定义引用时
    AnimatorInflater.loadAnimator(this@MainActivity, R.animator.objanime)
    .apply {
                setTarget(view)
                start()
           }
    ```

- 视图动画

    - 补间动画

        通过`Animation` 对一个图像进行变换操作

        注意translate 可以填写绝对值、自身百分比和父view 百分比

        ```kotlin
        val image: ImageView = findViewById(R.id.image)
        val hyperspaceJump: Animation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump)
        image.startAnimation(hyperspaceJump)
        ```

        差值器`interpolator` ，系统提供了多种实现，且部分可以修改属性 

    - 帧动画

        通过`AnimationDrawable` 展示多张序列图像，属于一种drawable

        ```kotlin
        val rocketImage: ImageView = findViewById(R.id.rocket_image)
        rocketImage.setBackgroundResource(R.drawable.rocket_thrust)
        
        val rocketAnimation = rocketImage.background
        if (rocketAnimation is Animatable) {
            rocketAnimation.start()
        }
        ```

transitions 框架：

`Scene` `TransitionManager` `Fade` `ChangeBounds`  



`Animator` 暂停和继续  



`BitmapFactory` `getByteCount` `getAllocationByteCount` `inBitmap`  

`Bitrmap.Config`  

