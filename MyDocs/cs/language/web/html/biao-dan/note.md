# note

html5支持表单外使用相关部件，只需要添加form属性即可。但是浏览器的支持率还不高  
一个部件可以有多个label，但是最好还是不用

action指定提交目的URL，默认（不写）为当前页面URL; method 指定提交方法

#### 校验

required ; pattern\(部分无需\)  
minlength/maxlength min/max ;

**自定义** 通过js实现，如果旧版不支持js的约束api，可以使用:  
[https://hyperform.js.org/](https://hyperform.js.org/)

novalidate 关闭浏览器本身校验  
:valid、:invalid、:in-range 、:out-of-range CSS伪类不会被关闭

aria-live 展示给包括使用ScreenReader的人

#### 小部件

```text
<label>,
<input>, <textarea>, and <button>
```

对于大多数表单部件，一旦表单提交，所有具有name属性的小部件都会被发送，即使没有任何值被填。对于单选框和复选框，只有在勾选时才发送它们的值。如果他们没有被勾选，就不会发送任何东西，甚至连他们的名字也没有。

**通用属性**  
autofocus,disabled,form,name,value

**文本输入**  
readonly,placeholder,size,length

* input

  传递数据的时候，会把文本转换成单行文本。   

  如果指定了type，但是某个浏览器不兼容，会解读成默认值text

* textarea   

  cols, rows, wrap  

  和Input的主要区别是，支持回车换行。  

  html内容只是被显示成纯文本　　

**按钮**  
button类似于input，但是html值可以被处理。

图像按钮在提交时，不会提交图片，而是提交点击位置，如：  
[http://foo.com?pos.x=123&pos.y=456](http://foo.com?pos.x=123&pos.y=456)

**下拉框**  
select box，autocomplete box

**meter** 可以使用optimum，配合min,max,high,low来快速确定最优值、平均值

#### 示例

```markup
<form action="/my-handling-form-page" method="post"
    enctype="multipart/form-data">
    <!-- label的for属性值和input的id属性值需要对应 -->
    <div>
        <label for="name">Name:</label>
        <input type="text" value="TpOut" id="name" name="user_name" />
    </div>
    <div>
        <label for="mail">E-mail:</label>
        <input type="email" id="mail" name="user_email" required />
    </div>
    <div>
        <label for="msg">Message:</label>
        <textarea id="msg" name="user_message"><h1>write something</h1></textarea>
    </div>
    <!-- <input>元素只允许纯文本作为其标签，而<button>元素允许完整的HTML内容，允许更复杂、更有创意的按钮文本 -->
    <div class="button">
        <button type="submit">Send your message</button>
        <input type="submit" name="tempsubmit" id="btninput" />
    </div>
</form>

<form>
    <!-- 使用filedset包裹一套类目，ScreenReader会分别读取“Fruit juice size small”，“Fruit juice size medium” ... -->
    <fieldset>
        <legend>Fruit juice size</legend>
        <p>
            <label for="size_1">Small</label>
            <input type="radio" name="size" id="size_1" value="small">
        </p>
        <p>
            <label for="size_2">Medium</label>
            <input type="radio" name="size" id="size_2" value="medium">
        </p>
        <p>
            <label for="size_3">Large</label>
            <input type="radio" name="size" id="size_3" value="large">
        </p>
    </fieldset>
</form>

<form>
    <h1>Payment form</h1>
    <p>Required fields are followed by <strong><abbr title="required">*</abbr></strong>.</p>
    <section>
        <h2>Contact information</h2>
        <fieldset>
            <legend>Title</legend>
            <ul>
                <li>
                    <label for="title_1">Mister</label>
                    <input type="radio" id="title_1" name="title"
                        value="M.">
                </li>
                <li>
                    <!-- 此处使用了嵌套，chrome上显示会有些问题 -->
                    <label for="title_2">
                        <input type="radio" id="title_2" name="title"
                            value="Ms.">
                        Miss
                    </label>
                </li>
            </ul>
        </fieldset>
        <!-- 没看出来这里加<p>标签有什么用 -->
        <p>
            <label for="name">
                <span>Name: </span>
                <strong><abbr title="required">*</abbr></strong>
            </label>
            <input type="text" id="name" name="username">
        </p>
        <p>
            <label for="mail">
                <span>E-mail: </span>
                <strong><abbr title="required">*</abbr></strong>
            </label>
            <input type="email" id="mail" name="usermail">
        </p>
        <p>
            <label for="pwd">
                <span>Password: </span>
                <strong><abbr title="required">*</abbr></strong>
            </label>
            <input type="password" id="pwd" name="password">
        </p>
    </section>

    <section>
        <h2>Payment information</h2>
        <p>
            <label for="card">
                <span>Card type:</span>
            </label>
        </p><p>
            <select id="card" name="usercard">
                <option value="visa">Visa</option>
                <option value="mc">Mastercard</option>
                <option value="amex">American Express</option>
            </select>
        </p>
        <p>
            <label for="number">
                <span>Card number:</span>
                <strong><abbr title="required">*</abbr></strong>
            </label>
            <input type="text" id="number" name="cardnumber">
        </p>
        <p>
            <label for="date">
                <span>Expiration date:</span>
                <strong><abbr title="required">*</abbr></strong>
                <em>formatted as mm/yy</em>
            </label>
            <input type="text" id="date" name="expiration">
        </p>
    </section>
    <p> <button type="submit">Validate the payment</button> </p>
</form>
<meter min="0" max="100" value="75" low="33" high="66" optimum="50">32</meter>
<!-- <datalist>元素是HTML表单的最新补充，因此浏览器的支持比我们之前看到的要少一些。
    最值得注意的是，它在10以下的IE版本中不受支持，Safari在写作时仍然不支持它
    下面是一个最简单的兼容方案-->
<label for="myFruit">What is your favorite fruit? (With fallback)</label>
<input type="text" id="myFruit" name="fruit" list="fruitList">

<datalist id="fruitList">
    <label for="suggestion">or pick a fruit</label>
    <select id="suggestion" name="altFruit">
        <option>Apple</option>
        <option>Banana</option>
        <option>Blackberry</option>
        <option>Blueberry</option>
        <option>Lemon</option>
        <option>Lychee</option>
        <option>Peach</option>
        <option>Pear</option>
    </select>
</datalist>


<a href="./hello.html" target="_blank" title="float tip">jump to hello</a>

<form novalidate>
    <p>
        <label for="mail">
            <span>Please enter an email address:</span>
            <input type="email" id="mail" name="mail">
            <span class="error" aria-live="polite"></span>
        </label>
    </p>
    <button>Submit</button>
</form>
```

