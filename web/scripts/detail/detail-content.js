function getRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = "";
    if (url.indexOf("?") !== -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            //现在只有一个参数
            if (i === 0) {
                theRequest = decodeURI(strs[i].split("=")[1])
            }
        }
    }
    return theRequest;
}

var notePath = getRequest();

//后端会把路经分隔符处理成"/"
var splits = notePath.split("/");
//标题，不包含后缀
var title = splits[splits.length - 1].split(".")[0];
//资源路经，如果是图片
var resPath = notePath.substring(0, notePath.indexOf(title));
document.getElementsByTagName("title")[0].innerText = title;
document.getElementById("title").innerText = title;

Ajax.get(baseUrl + notePath, function (res) {
    try {

        marked.setOptions({
            renderer: new marked.Renderer(),
            //高亮代码
            highlight: function (code, language) {
                const validLanguage = hljs.getLanguage(language) ? language : 'plaintext';
                return hljs.highlight(validLanguage, code).value;
            },
            pedantic: false,
            gfm: true,
            breaks: false,
            sanitize: false,
            smartLists: true,
            smartypants: false,
            xhtml: false
        });
        document.getElementById('content').innerHTML = marked(res);
        hljs.initHighlightingOnLoad() // 不加这句会没有背景

        //文档相对路经，处理一下
        var allA = document.getElementsByTagName('a');
        for (var i = 0; i < allA.length; i++) {
            var a = allA[i];

            //文档的地址
            var baseIndex = a.href.indexOf(baseUrl);
            if (baseIndex >= 0) {
                var sub = a.href.substring(a.href.indexOf(baseUrl) + baseUrl.length, a.href.length);
                a.href = baseUrl + "detail-container.html?path=" + resPath + sub;
            }
        }

        //图片相对路经，处理一下
        var allImg = document.getElementsByTagName("img");
        for (var i = 0; i < allImg.length; i++) {
            var img = allImg[i];

            var baseIndex = img.src.indexOf(baseUrl);
            if (baseIndex >= 0) {
                var sub = img.src.substring(img.src.indexOf(baseUrl) + baseUrl.length, img.src.length);
                //图片的地址
                if (img.src.indexOf("jpg") !== -1
                    || img.src.indexOf("png") !== -1
                ) {
                    img.src = baseUrl + resPath + sub;
                }
            }

        }

    } catch (err) {

    }
});