/**
 *
 * @param res 返回的json字符串
 * @param rootSectionId 要添加在哪个标签下面
 *
 */

function addListToSection(res, rootSectionId) {

    var linkA;
    var titleP;
    var timeTime;
    var tagP; //原创，翻译
    var itemLi;

    var listElement = document.getElementById(rootSectionId).getElementsByTagName("ul")[0];

    var listRes = res['sub'];
    var length = listRes.length;
    for (var i = 0; i < length; i++) {
        var itemRes = listRes[i];
        //表示文件
        if (itemRes['type'] === "normal") {

            linkA = document.createElement('a');
            linkA.href = "./detail-container.html?path=" + itemRes['url'];

            itemLi = document.createElement('li');

            titleP = document.createElement('p');
            titleP.className = "article_title";
            titleP.innerText = itemRes['name'];

            tagP = document.createElement("span");
            tagP.textContent = itemRes['tag'];

            timeTime = document.createElement('time');
            timeTime.className = "article_time";
            timeTime.textContent = itemRes['time'];

            titleP.appendChild(tagP);

            itemLi.appendChild(titleP);
            itemLi.appendChild(timeTime);
            itemLi.appendChild(tagP);

            linkA.appendChild(itemLi);
            listElement.appendChild(linkA);
        }
    }

}