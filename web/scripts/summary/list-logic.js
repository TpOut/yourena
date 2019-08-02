/**
 *
 * @param res 返回的json字符串
 * @param rootSectionId 要添加在哪个标签下面
 *
 *     <section>
 <p class="section_title">CS Books</p>
 <p class="section_describe">Language</p>
 <time class="section_time">2019.05.05</time>
 </section>
 *
 */

function addListToSection(res) {

    var linkA;
    var tagSp; //原创，翻译
    var titleP;
    var describeP;
    var timeTime;

    var mainItem = document.getElementById("main");

    var listRes = res['sub'];
    var length = listRes.length > 4 ? 5 : listRes.length;
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
    }

}