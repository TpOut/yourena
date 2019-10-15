# notice

学习了教程的first steps。

basic concepts还没开动

### 概览

Scrapy最开始设计用途是网络抓取（Web scraping），但是可以用来提取API数据（如[https://affiliate-program.amazon.com/gp/advertising/api/detail/main.html），或者作为常规的网络爬虫](https://affiliate-program.amazon.com/gp/advertising/api/detail/main.html），或者作为常规的网络爬虫)  
内置支持从HTML/XML 选择和提取数据 ，只要使用CSS 选择器和XPath语法。  
内置支持生成feed exports为多种格式，包括json,csv,xml;或者保存为FTP,S3,本地fs 更多查看[https://docs.scrapy.org/en/latest/intro/overview.html\#what-else](https://docs.scrapy.org/en/latest/intro/overview.html#what-else)

**快速示例**

```python
import scrapy

class QuotesSpider(scrapy.Spider):
    # 作为spider的标记，项目内不能重复
    name = "quotes"
    #　起始地址方式1
    start_urls = [
        'http://quotes.toscrape.com/tag/humor/',
    ]
    # 起始地址方式2
    def start_requests(self):
        urls = [
            'http://quotes.toscrape.com/page/1/',
            'http://quotes.toscrape.com/page/2/',
        ]
        for url in urls:
            yield scrapy.Request(url=url, callback=self.parse)

    # 解析方式1，response是TextResponse类型
    def parse(self, response):
            for quote in response.css('div.quote'):
                yield {
                    'text': quote.css('span.text::text').extract_first(),
                    'author': quote.css('small.author::text').extract_first(),
                    'tags': quote.css('div.tags a.tag::text').extract(),
                }

            page = response.url.split("/")[-2]
            filename = 'quotes-%s.html' % page
            with open(filename, 'wb') as f:
                f.write(response.body)

            next_page = response.css('li.next a::attr(href)').extract_first()
            if next_page is not None:
                # 请求方法1，Request不支持相对地址，需要urljoin预处理
                next_page = response.urljoin(next_page)
                yield scrapy.Request(next_page, callback=self.parse)

                # 请求方法2，follow支持相对地址，不需要urljoin
                # follow 中第一个参数可以传递selecotr, 而且直接传递a 的时候，会自动提取href  
                yield response.follow(next_page, self.parse)

    # 解析方式2
    def parse(self, response):



    # response对象
    response.url
            .body
            .follow
            .urljoin
    scrapy.Request
```

```text
scrapy runspider quotes_spider.py -o quotes.json  
# quotes is name
scrapy crawl quotes -o quotes.json
```

### 安装

CPython  
python 3.4及以上（当前3.7）  
PyPy 5.9开始

```text
# 使用Anaconda或者 Miniconda安装    
conda install -c conda-forge scrapy  
# 使用pip安装  
pip install Scrapy
```

scrapy纯Python实现，并基于库：  
lxml , parsel, w3lib, twisted, cryptography, pyOpenSSL

**虚拟环境**  
创建一个python项目的时候，建议使用virtualenv创建一个虚拟环境，这样可以在虚拟环境里安装和卸载依赖，和其他项目独立不冲突。  
\(virtualenv创建查看索引\)

（conda安装之后，自动创建了base的virtualenv--在aconda安装目录的bin下存放activate--并激活进入）

ubuntu上不要使用Python-scrapy包安装，因为此源更新比较慢，如下安装即可：

```text
sudo apt-get install python-dev python-pip libxml2-dev libxslt1-dev zlib1g-dev libffi-dev libssl-dev
# 如果是python3
sudo apt-get install python3 python3-dev
# 如果在virtualenv
pip install scrapy
```

最新的版本PyPy在linux测试过，OS X会有问题，win没测过

### 第一个spider（爬虫）

在scrapy框架中，爬虫其实就是继承了scrapy.Spider的类，你可以定义一些东西：起始请求页，后续请求页面的过滤，以及怎么解析\(可以对照上面的快速示例查看\)

在获取页面之后，你会得到html, 需要去分析他的selector。

这整一个过程最好的方式是通过scrapy shell,如执行：

```text
scrapy shell 'http://quotes.toscrape.com/page/1/'
# 会输出一些请求的状态，对象; 你可以快速的获取selectorList,如：  
>>> response.css('title')
[<Selector xpath='descendant-or-self::title' data='<title>Quotes to Scrape</title>'>]   
>>> response.css('title').extract()
['<title>Quotes to Scrape</title>']
# 或者直接获取第一个， ::text表示取标签内的内容， extract_first更优雅因为做了判空处理不会报错
>>> response.css('title::text').extract_first()
>>> response.css('title::text')[0].extract()
'Quotes to Scrape'  
# 还可以对获取的selectorList使用正则：
>>> response.css('title::text').re(r'(\w+) to (\w+)')
['Quotes', 'Scrape']

# 同样支持XPath,实际上css在底层也是转化成XPath  
>>> response.xpath('//title')
[<Selector xpath='//title' data='<title>Quotes to Scrape</title>'>]
>>> response.xpath('//title/text()').extract_first()
'Quotes to Scrape'
```

div.tags a.tag::text 表示类tags的div 下面 类tag的a，css的语法？

对于XPath，除了能够解析结构走向，还能知悉文本内容。所以相对于爬取的目的，更鼓励取使用XPath解析

#### 存储

```text
scrapy crawl quotes -o quotes.json
```

历史原因，输出文档是以append而不是覆盖的形式添加到指定文件。所以json格式重复输出可能导致问题，而jl格式不会。

#### 更多

```text
# 解析作者详情并输出
import scrapy


class AuthorSpider(scrapy.Spider):
    name = 'author'

    start_urls = ['http://quotes.toscrape.com/']

    def parse(self, response):
        # follow links to author pages
        for href in response.css('.author + a::attr(href)'):
            yield response.follow(href, self.parse_author)

        # follow pagination links
        for href in response.css('li.next a::attr(href)'):
            yield response.follow(href, self.parse)

    def parse_author(self, response):
        def extract_with_css(query):
            return response.css(query).extract_first().strip()

        yield {
            'name': extract_with_css('h3.author-title::text'),
            'birthdate': extract_with_css('.author-born-date::text'),
            'bio': extract_with_css('.author-description::text'),
        }
```

对于访问连接，Scrapy默认会去重处理，可以在DUPEFILTER\_CLASS中处理

```text
# -a 后面的参数会被spider的 __init__ 方法接收，并变成spider属性
# 可以在spider内部，通过 getattr(self, 'tag', None)方法获取  
scrapy crawl quotes -o quotes-humor.json -a tag=humor
```

