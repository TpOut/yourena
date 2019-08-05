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

function addList(res) {

    var linkA;
    var tagSp; //原创，翻译
    var nameSp;
    var titleP;
    var summaryP;
    var timeTime;
    var itemSection;

    var mainItem = document.getElementsByTagName("main")[0];

    var listRes = res['sub'];
    var length = listRes.length;
    for (var i = 0; i < length; i++) {
        var itemRes = listRes[i];
        //表示文件
        if (itemRes['type'] === "normal") {

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
            var item = itemRes['summary'];
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
    }

}