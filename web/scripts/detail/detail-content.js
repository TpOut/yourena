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

//获取最后的名称
var splits = notePath.split("/");
var title = splits[splits.length - 1].split(".")[0];
document.getElementsByTagName("title")[0].innerText = title;
document.getElementById("title").innerText = title;

Ajax.get(baseUrl + notePath, function (res) {
    try {
        document.getElementById('content').innerHTML = marked(res);

        //如果是相对路经，都需要处理一下
        var allA = document.getElementsByTagName('a');
        for (var i = 0; i < allA.length; i++) {
            var a = allA[i];
            var baseIndex = a.href.indexOf(baseUrl);
            if (baseIndex >= 0) {
                var sub = a.href.substring(a.href.indexOf(baseUrl) + baseUrl.length, a.href.length);
                a.href = "detail-container.html?path=docs/" + sub;
            }
        }
    } catch (err) {

    }
});