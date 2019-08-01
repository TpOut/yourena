
var categoryName = getRequest();

var servletName;
console.log(categoryName);
switch (categoryName) {
    case "blog":
        document.getElementById("header_img").src = "/imgs/icons/ic_story.png";
        document.getElementById("header_title").innerText = "Blog";
        servletName = "blog_list";
        break
}

Ajax.get(baseUrl+ servletName, function (res) {
    try {
        document.getElementById('content').innerHTML = marked(res);
    } catch(err) {

    }
})