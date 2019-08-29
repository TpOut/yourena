|device|platform|exception|
|:----:|:---:|:---:|
|OPPO A59m|5.1|mTv.setText cause a nullPointException|

```
public class Activity ...{
    @BindView(R.id.tv)
    TextView mTv;

    CountDownTimer mTimer;
    ...

    void start(){
        mTimer = (...)->{
            @Override
            public void onTick(long millisUntilFinished){
                mTv.setText("");
            }
            ...
        }.start();
    }

    onDestroy(){
        mTimer.cancel();
        binder.unbind();
    }
}
```
