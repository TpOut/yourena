var categoryName = getRequest();

var servletName;

switch (categoryName) {
    case "blog":
        document.getElementById("header_img").src = "/imgs/icons/ic_story.png";
        document.getElementById("header_title").innerText = "Blog";
        servletName = "BlogList";
        break
    case "novel":
        document.getElementById("header_img").src = "/imgs/icons/ic_story.png";
        document.getElementById("header_title").innerText = "Novel";
        servletName = "NovelList";
}

Ajax.get(baseUrl + servletName, function (res) {
    try {
        res = JSON.parse(res);
        console.log(res)
        addList(res)
    } catch (err) {
        res = {}
    }
    console.log(res)
})