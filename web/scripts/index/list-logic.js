/**
 *
 * @param res 返回的json字符串
 * @param rootSectionId 要添加在哪个标签下面
 *
 */

function addListToSection(res, rootSectionId) {

    var linkA;
    var itemLi;
    var titleP;
    var tagSp; //原创，翻译
    var nameSp;
    var timeTime;

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

            tagSp = document.createElement("span");
            tagSp.textContent = itemRes['tag'];
            if (tagSp.textContent === "原") {
                tagSp.className = "origin";
            } else if (tagSp.textContent === "转") {
                tagSp.className = "index";
            } else if (tagSp.textContent === "译") {
                tagSp.className = "trans";
            }
            titleP.appendChild(tagSp);

            nameSp = document.createElement("span");
            nameSp.textContent = itemRes['name'];
            titleP.appendChild(nameSp);
            itemLi.appendChild(titleP);

            timeTime = document.createElement('time');
            timeTime.className = "article_time";
            timeTime.textContent = itemRes['time'];
            itemLi.appendChild(timeTime);

            linkA.appendChild(itemLi);
            listElement.appendChild(linkA);
        }
    }

}