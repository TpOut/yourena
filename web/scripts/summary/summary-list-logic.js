/**
 *
 * @param res 返回的json字符串
 * @param rootSectionId 要添加在哪个标签下面
 *
 */

function addList(res) {
    const listRes = res['sub'];
    const length = listRes.length;
    for (let i = 0; i < length; i++) {
        let itemRes = listRes[i];
        addFileList(getListItem(itemRes))
    }
}

function addFileList(itemRes) {
    let linkA;
    let tagSp; //原创，翻译
    let nameSp;
    let titleP;
    let summaryP;
    let timeTime;
    let itemSection;

    let mainItem = document.getElementsByTagName("main")[0];


    //表示文件
    linkA = document.createElement('a');
    // linkA.href = "./detail-container.html?path=" + itemRes['url'];
    console.log(itemRes['url'])

    if (itemRes['url'].split('.')[1] === "html") {
        linkA.href = "./" + itemRes['url'];
    } else {
        linkA.href = "./detail-container.html?path=" + itemRes['url'];
    }


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

    summaryP = document.createElement("p");
    summaryP.className = "article_summary";
    let item = itemRes['summary'];
    item = item.replace(/\\n/g, '<br>');
    summaryP.innerHTML = item;

    timeTime = document.createElement('time');
    timeTime.className = "article_time";
    timeTime.textContent = itemRes['time'];

    itemSection = document.createElement('section');
    itemSection.appendChild(titleP);
    itemSection.appendChild(summaryP);
    itemSection.appendChild(timeTime);

    linkA.appendChild(itemSection);
    mainItem.appendChild(linkA);
}