```

public class LazyLoadFragment extends Fragment {

    private static final String TAG = LazyLoadFragment.class.getSimpleName();
    private final String tag = getClass().getSimpleName();

    private boolean viewHasCached;
    private View rootViewCache;

    private boolean hasLoaded;
    private boolean needLazyLoad;

    @Nullable
    protected View onCreateCacheView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    protected void onCacheViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!viewHasCached) {
            rootViewCache = onCreateCacheView(inflater, container, savedInstanceState);
        }

        return rootViewCache;
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (viewHasCached) {
            return;
        }

        onCacheViewCreated(view, savedInstanceState);
        viewHasCached = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewHasCached = false;
        rootViewCache = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!hasLoaded && needLazyLoad) {
            lazyLoad();
        }
    }

    @UiThread
    @CallSuper
    protected void lazyLoad() {
        needLazyLoad = false;
        hasLoaded = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            return;
        }

        if (hasLoaded) {
            return;
        }

        if (isResumed()) {
            lazyLoad();
        } else {
            needLazyLoad = true;
        }
    }

}
```
