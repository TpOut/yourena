## Android测试  
[Android注解](https://developer.android.google.cn/reference/android/support/annotation/package-summary.html)

由于集成测试内置于 APK 中（与您的应用 APK 分离），因此它们必须拥有自己的 AndroidManifest.xml 文件。 不过，由于 Gradle 会自动在构建时生成该文件，因此它在您的项目源集中不可见。 您可以在必要时（例如需要为 `minSdkVersion` 指定其他值或注册测试专用的运行侦听器时）添加自己的清单文件。 构建应用时，Gradle 会将多个清单文件合并成一个清单。
### 配置
其实现在新建一个项目，该有的配置都已经配置好了。  
junit4使用前提，设置测试默认运行器：
```
android {
    defaultConfig {
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
}

```
###AS创建快捷键
Ctrl+Shift+T
或者右键方法，找到gototest
### mock
前面提到过模拟，用的就是这个啦  
[mock说明-wiki](phttps://en.wikipedia.org/wiki/Mock_object)   
moke框架，如[mockito](https://github.com/mockito/mockito)
[mock api](https://static.javadoc.io/org.mockito/mockito-core/2.7.22/org/mockito/Mockito.html)

```java
//依赖
// Required -- JUnit 4 framework
testCompile 'junit:junit:4.12'
// Optional -- Mockito framework
testCompile 'org.mockito:mockito-core:1.10.19'

//常用
assertThat(),assertEqual()
is(),
@RunWith(MockitoJUnitRunner.class), @mock

```
感觉mock的方式是在测试的时候，通过更改固定代码的执行结果，来实现“模拟”实际的执行结果。
这个时候会出现无法mock的情况，即调用某些Android框架代码，会报错，如果想只返回0或null，则:
```java
android {
  ...
  testOptions {
    unitTests.returnDefaultValues = true
  }
}
```

**集成测试**需要在SDK管理器中安装Android Support Repository（新的应该默认安装的）
```java
//相关依赖
dependencies {
  androidTestCompile 'com.android.support.test:runner:0.4'
  // Set this dependency to use JUnit 4 rules
  androidTestCompile 'com.android.support.test:rules:0.4'
  // Set this dependency to build and run Espresso tests
  // 新版直接生成
  androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
    exclude group: 'com.android.support', module: 'support-annotations'
  })
  // Set this dependency to build and run UI Automator tests
  // minSDK为18
  androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.2'
}

```

z自动化UI测试
###[espresso](https://google.github.io/android-testing-support-library/docs/espresso/index.html)
h会在相同进程，同时打包安装测试APP，然后实现和应用APP的交互。
c自动检测主线程空闲状态，解放后台操作；
测试前在开发者选项中关闭动画（不知道以后会不会不需要），包括Window animation scale，Transition animation scale，Animator duration scale
[performing action list](https://developer.android.google.cn/reference/android/support/test/espresso/action/ViewActions.html)

[view assertion list](https://developer.android.google.cn/reference/android/support/test/espresso/assertion/ViewAssertions.html#doesNotExist())
```java
//常用
ViewInteraction.perform(ViewActions.actions()) or DataInteraction.perform(ViewActions.actions()) //主线程执行action,一般顺序执行，scrollTo优先
ViewAssertions

ActivityTestRule	//用来减少代码量，在@Before、@Test之前运行，在@After之后自动关闭

@RunWith(AndroidJUnit4.class)
@LargeTest、@Rule

// Type text and then press the button.
onView(withId(R.id.editTextUserInput))
  .perform(typeText(mStringToBetyped), closeSoftKeyboard());
onView(withId(R.id.changeTextBt)).perform(click());

// Check that the text was changed.
onView(withId(R.id.textToBeChanged))
  .check(matches(withText(mStringToBetyped)));

onView(withText(...))
//Android里资源ID有可能重复，
onView(allOf(withId(R.id.button_signin), not(withText("Sign-out"))));
containsString()、 instanceOf()
//adapterView中ID，使用onData
onData(allOf(is(instanceOf(Map.class)),
        hasEntry(equalTo(LongListActivity.ROW_TEXT), is("test input")));
```

```java
//espresso intent
dependencies {
  // Other dependencies ...
  androidTestCompile 'com.android.support.test.espresso:espresso-intents:2.2.2'
}

IntentsTestRule

//验证intent
// Verifies that the DisplayMessageActivity received an intent
// with the correct package name and message.
  intended(allOf(
    hasComponent(hasShortClassName(".DisplayMessageActivity")),
    toPackage(PACKAGE_NAME),
    hasExtra(MainActivity.EXTRA_MESSAGE, MESSAGE)));

```
###测试WebView
[WebDriver](http://docs.seleniumhq.org/docs/03_webdriver.jsp)
更多查看https://google.github.io/android-testing-support-library/docs/espresso/web/index.html
```java
dependencies {
  // Other dependencies ...
  androidTestCompile 'com.android.support.test.espresso:espresso-web:2.2.2'
}

@LargeTest
@RunWith(AndroidJUnit4.class)
public class WebViewActivityTest {

    private static final String MACCHIATO = "Macchiato";
    private static final String DOPPIO = "Doppio";

    @Rule
    public ActivityTestRule mActivityRule =
        new ActivityTestRule(WebViewActivity.class,
            false /* Initial touch mode */, false /*  launch activity */) {

        @Override
        protected void afterActivityLaunched() {
            // Enable JavaScript.
            onWebView().forceJavascriptEnabled();
        }
    }

    @Test
    public void typeTextInInput_clickButton_SubmitsForm() {
       // Lazily launch the Activity with a custom start Intent per test
       mActivityRule.launchActivity(withWebFormIntent());

       // Selects the WebView in your layout.
       // If you have multiple WebViews you can also use a
       // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
       onWebView()
           // Find the input element by ID
           .withElement(findElement(Locator.ID, "text_input"))
           // Clear previous input
           .perform(clearElement())
           // Enter text into the input element
           .perform(DriverAtoms.webKeys(MACCHIATO))
           // Find the submit button
           .withElement(findElement(Locator.ID, "submitBtn"))
           // Simulate a click via JavaScript
           .perform(webClick())
           // Find the response element by ID
           .withElement(findElement(Locator.ID, "response"))
           // Verify that the response page contains the entered text
           .check(webMatches(getText(), containsString(MACCHIATO)));
    }
}
```
###UI Automator
```
dependencies {
    ...
    androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.1'
}
```
可以用viewer查看是否可达到，
check that these components have visible text labels, android:contentDescription values
run <android-sdk>/tools/uiautomatorviewer
aAndroid官方view及其子类都有实现accessibility support，更多相关查看[Making Apps More Accessible](https://developer.android.google.cn/guide/topics/ui/accessibility/index.html)

f否则需要实现ExploreByTouchHelper、 setAccessibilityDelegate()

```java
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class ChangeTextBehaviorTest {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.android.testing.uiautomator.BasicSample";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // 获取device实例，可以模仿一些设备上的操作，旋转啊、返回键啊等等
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }
}
//uiObject使用
UiObject cancelButton = mDevice.findObject(new UiSelector()
        .text("Cancel"))
        .className("android.widget.Button"));
UiObject okButton = mDevice.findObject(new UiSelector()
        .text("OK"))
        .className("android.widget.Button"));

// Simulate a user-click on the OK button, if found.
if(okButton.exists() && okButton.isEnabled()) {
    okButton.click();
}


//使用UiSelector实现listview嵌套查找，不过最好别用text而用ID，原因不言而喻
UiObject appItem = new UiObject(new UiSelector()
        .className("android.widget.ListView")
        .instance(1)
        .childSelector(new UiSelector()
        .text("Apps")));

//常用
click(),dragTo(),setText()/clearTextField(),swipeUp()/swipeLeft()..

//装载一个activity
public void setUp() {
    ...

    // Launch a simple calculator app
    Context context = getInstrumentation().getContext();
    Intent intent = context.getPackageManager()
            .getLaunchIntentForPackage(CALC_PACKAGE);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // Clear out any previous instances
    context.startActivity(intent);
    mDevice.wait(Until.hasObject(By.pkg(CALC_PACKAGE).depth(0)), TIMEOUT);
}

//用UiCollection来处理同一个布局下的多个事件
UiCollection videos = new UiCollection(new UiSelector()
        .className("android.widget.FrameLayout"));

// Retrieve the number of videos in this collection:
int count = videos.getChildCount(new UiSelector()
        .className("android.widget.LinearLayout"));

// Find a specific video and simulate a user-click on it
UiObject video = videos.getChildByText(new UiSelector()
        .className("android.widget.LinearLayout"), "Cute Baby Laughing");
video.click();

// Simulate selecting a checkbox that is associated with the video
UiObject checkBox = video.getChild(new UiSelector()
        .className("android.widget.Checkbox"));
if(!checkBox.isSelected()) checkbox.click();

//
UiScrollable settingsItem = new UiScrollable(new UiSelector()
        .className("android.widget.ListView"));
UiObject about = settingsItem.getChildByText(new UiSelector()
        .className("android.widget.LinearLayout"), "About tablet");
about.click();

//结果确认
private static final String CALC_PACKAGE = "com.myexample.calc";

public void testTwoPlusThreeEqualsFive() {
    // Enter an equation: 2 + 3 = ?
    mDevice.findObject(new UiSelector()
            .packageName(CALC_PACKAGE).resourceId("two")).click();
    mDevice.findObject(new UiSelector()
            .packageName(CALC_PACKAGE).resourceId("plus")).click();
    mDevice.findObject(new UiSelector()
            .packageName(CALC_PACKAGE).resourceId("three")).click();
    mDevice.findObject(new UiSelector()
            .packageName(CALC_PACKAGE).resourceId("equals")).click();

    // Verify the result = 5
    UiObject result = mDevice.findObject(By.res(CALC_PACKAGE, "result"));
    assertEquals("5", result.getText());
}

```

BroadcastReceiver的测试没有单独的类，但是可以获取intent对象然后通过InstrumentationRegistry.getTargetContext()创建广播接收器对象。然后调用回调方法

service和content provider的测试，
```java
//常用
ServiceTestRule
d不包括IntentService对象，需要自己封装成单元测试
 @RunWith(AndroidJUnit4.class)
@Test
public void testWithBoundService() throws TimeoutException {
    // Create the service Intent.
    Intent serviceIntent =
            new Intent(InstrumentationRegistry.getTargetContext(),
                LocalService.class);

    // Data can be passed to the service via the Intent.
    serviceIntent.putExtra(LocalService.SEED_KEY, 42L);

    // Bind the service and grab a reference to the binder.
    IBinder binder = mServiceRule.bindService(serviceIntent);

    // Get the reference to the service, or you can call
    // public methods on the binder directly.
    LocalService service =
            ((LocalService.LocalBinder) binder).getService();

    // Verify that the service is working correctly.
    assertThat(service.getRandomInt(), is(any(Integer.class)));
}

```
provider，这一章讲了一些关于测试provider的考虑。也同样适用于其他
```java
RunWith(AndroidJUnit4.class)
ProviderTestCase2 //用来作为测试类的父类
IsolatedContext and MockContentResolver

@Override
protected void setUp() throws Exception {
    setContext(InstrumentationRegistry.getTargetContext());
    super.setUp();
}
```