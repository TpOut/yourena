# simple

```python
from urllib import request

from bs4 import BeautifulSoup

response = request.urlopen("https://developer.mozilla.org/zh-CN/docs/Learn")
html = response.read()
html = html.decode("utf-8")
bs = BeautifulSoup(html, "lxml")
print(bs)
```

