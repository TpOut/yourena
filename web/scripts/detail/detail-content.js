function getRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = "";
    if (url.indexOf("?") !== -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            //现在只有一个参数
            if(i === 0){
                theRequest = decodeURI(strs[i].split("=")[1])
            }
        }
    }
    return theRequest;
}
var notePath = getRequest();

//获取最后的名称
var splits = notePath.split("/");
var title =  splits[splits.length - 1].split(".")[0];
document.getElementsByTagName("title")[0].innerText = title;
document.getElementById("title").innerText = title;

Ajax.get(baseUrl+ notePath, function (res) {
    try {
        document.getElementById('content').innerHTML = marked(res);
    } catch(err) {

    }
})