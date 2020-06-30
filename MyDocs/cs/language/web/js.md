# js

语法和java类似  
严格意义等于 === , 严格意义不等于 !== ； == ！= 只比较值，不比较数据类型，不建议使用。

函数定义：

```javascript
    function checkGuess() {
        //doSomething
    }
```

常规函数调用：  
`checkGuess();` 事件函数传递：  
`guessSubmit.addEventListener('click', checkGuess);`

获取选择器:  
`document.querySelector()`  
属性和style值对应，只是横杠分割的方式变成了驼峰方式

日志，console.log\(\)

编写上，变量可以在被赋值之后再声明（实际解释上还是声明之后才赋值）

变量，由数字下划线字母组成，不允许数字开头，不建议下划线开头（内置变量），区分大小写

对象的定义：

```javascript
    //手动方式构造，最后一个变量不需要以逗号结尾，
        //dog称为对象的字面量(literal)，同时也作为命名空间namespace，约束这属性和方法    
      //name,breed就改叫属性property,greeting改叫方法（本来是函数）。  
    var dog = {  
          name : 'Spot',
          breed : 'Dalmatian',  
          greeting: function() {
            alert('Hi! I\'m ' + this.name[0] + '.');
        }
      };
```

调用方式除了常规的dog.name，也可以使用dog\['name'\]的方式，因为后者对象有时候被称作关联数组\(associative array\)，  
可以在外部直接添加成员变量  
\[\]的方式，可以传递变量名，如dog\['name'\]等同于 var v= 'name'; dog\[v\]

```javascript
    //构造函数；构造函数即其他语言的类  
    function Person(name) {
          this.name = name;
          this.greeting = function () {
                alert('Hi! I\'m ' + this.name + '.');
          };
    }
    //简化了常规的方式，myObj = func(){var object = {}, return object}
        //通过构造函数的方式叫做实例化  
        var person0 = new Person('Bob');

    //对象构造
        var person1 = new Object({
        name : 'Chris',
        age : 38,
        greeting : function() {
            alert('Hi! I\'m ' + this.name + '.');
        }
    })  
    //从现有对象复制对象，以 person1 为原型对象创建了 person2 对象
    var person2 = Object.create(person1);  

        var person3 = new person1.constructor('Karen', 'Stephenson', 26, 'female', ['playing drums', 'mountain climbing']);
```

继承的实现：

```javascript
    function Teacher(first, last, age, gender, interests, subject) {
        Person.call(this, first, last, age, gender, interests);
                Teacher.prototype = Object.create(Person.prototype);
                Teacher.prototype.constructor = Teacher;

        this.subject = subject;
    }
```

join\(\), shift\(\), unshift\(\)

匿名函数，可以看出来可以传递：  
myButton.onclick = function\(\) { alert\('hello'\); } 匿名函数也称为函数表达式。函数表达式与函数声明有一些区别。函数声明会进行声明提升（declaration hoisting），而函数表达式不会

可以使用addEventListener来快速定义事件，[https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/addEventListener](https://developer.mozilla.org/en-US/docs/Web/API/EventTarget/addEventListener)

```javascript
    //事件中可以传递event参数，event.target是事件所属的objects，    
    //还有一些函数有不同的参数    
    function bgChange(event) {
      var rndCol = 'rgb(' + random(255) + ',' + random(255) + ',' + random(255) + ')';
      event.target.style.backgroundColor = rndCol;
      console.log(event);   
    }  

    btn.addEventListener('click', bgChange);   

    //在表单中时可以阻止提交
    e.preventDefault();   
    //阻止事件冒泡  
    e.stopPropagation();
```

事件冒泡和捕获

解析json：  
为了载入 JSON 到页面中，我们将使用 一个名为XMLHTTPRequest的API

类结构：

```javascript
    |
    |__  constructor:
    |        |
    |         |__ prototype
    |     
    |__  __proto__
```

默认情况下, 所有函数的原型属性的 **proto** 就是 window.Object.prototype  
用new创建的对象，有点类似继承，但是以Person作为父

原型链中的“连接”被定义在一个内部属性中，在 JavaScript 语言标准中用 \[\[prototype\]\] 表示（参见 ECMAScript）。然而，大多数现代浏览器还是提供了一个名为 **proto** （前后各有2个下划线）的属性，其包含了对象的原型。疑问是，这里我看prototype是一个无限循环，**proto** 才是正常的  
只有在prototype中的元素才会被继承，如Object.prototype.valueOf\(\)

每一个函数对象（Function）都有一个prototype属性，并且只有函数对象有prototype属性，因为prototype本身就是定义在Function对象下的属性。当我们输入类似var person1=new Person\(...\)来构造对象时，JavaScript实际上参考的是Person.prototype指向的对象来生成person1。另一方面，Person\(\)函数是Person.prototype的构造函数，也就是说Person===Person.prototype.constructor

考虑到JavaScript的工作方式，由于原型链等特性的存在，在不同对象之间功能的共享通常被叫做 委托 - 特殊的对象将功能委托给通用的对象类型完成。这也许比将其称之为继承更为贴切，因为“被继承”了的功能并没有被拷贝到正在“进行继承”的对象中，相反它仍存在于通用的对象中。

#### 示例

```javascript
var myHeading = document.querySelectorAll('h1')
myHeading[0].textContent = 'Hello world!'

function setUserName () {
  var myName = prompt('Please enter your name.')
  localStorage.setItem('name', myName)
  myHeading[1].textContent = 'Mozilla is cool, ' + myName
}

if (localStorage.getItem('name') === 'null') {
  setUserName()
} else {
  var storedName = localStorage.getItem('name')
  myHeading[1].textContent = 'Mozilla is cool, ' + storedName
}

// 有许多方式可以获取 DOM 节点；在此我们获取表单本身和
// email 输入框，以及我们将放置错误信息的 span 元素。

var form  = document.getElementsByTagName('form')[0];
var email = document.getElementById('mail');
var error = document.querySelector('.error');

email.addEventListener("input", function (event) {
  // 当用户输入信息时，验证 email 字段
  if (email.validity.valid) {
    // 如果验证通过，清除已显示的错误消息
    error.innerHTML = ""; // 重置消息的内容
    error.className = "error"; // 重置消息的显示状态
  }
}, false);
form.addEventListener("submit", function (event) {
  // 当用户提交表单时，验证 email 字段
  if (!email.validity.valid) {

    // 如果验证失败，显示一个自定义错误
    error.innerHTML = "I expect an e-mail, darling!";
    error.className = "error active";
    // 还需要阻止表单提交事件，以取消数据传送
    event.preventDefault();
  }
}, false);
```

