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