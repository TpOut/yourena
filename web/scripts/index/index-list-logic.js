/**
 *
 * @param res 返回的json字符串
 * @param rootSectionId 要添加在哪个标签下面
 *
 */

function addListToSection(res, rootSectionId) {

    let listRes = res['sub'];
    //todo, 主页只最多展示5条，所以要添加一个点击标题跳转到Summary的逻辑
    let length = listRes.length > 4 ? 5 : listRes.length;
    for (let i = 0; i < length; i++) {
        let itemRes = listRes[i];
        addFileList(getListItem(itemRes), rootSectionId)
    }
}

function addFileList(itemRes, rootSectionId) {
    let linkA;
    let itemLi;
    let titleP;
    let tagSp; //原创，翻译
    let nameSp;
    let timeTime;

    let listElement = document.getElementById(rootSectionId).getElementsByTagName("ul")[0];

    linkA = document.createElement('a');

    if (itemRes['url'].split('.')[1] === "html") {
        linkA.href = "./" + itemRes['url'];
    } else {
        linkA.href = "./detail-container.html?path=" + itemRes['url'];
    }

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
    } else {
        tagSp.textContent = "原";
        tagSp.className = "origin";
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