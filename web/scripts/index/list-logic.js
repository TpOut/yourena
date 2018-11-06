/**
 * 第一次获得的最高，为0；后续递增
 * 为了处理标题样式
 * 目前的0级目录表示根目录，1级目录表示分类，2级目录表示具体文件
 */

function addAllName(res, mainElement, namePriority, timeChangeLine) {

    var title;
    var time;
    var tag;
    var realItem;

    if (namePriority == 0) {
    } else {
        realItem = document.createElement('li')
        realItem.className = "section" + namePriority;

        if (namePriority === 1) {

            title = document.createElement("p")
            title.innerText = res['name']

            realItem.appendChild(title)
        }
        if (namePriority === 2) {

            title = document.createElement("p")
            title.innerText = res['name']

            time = document.createElement("time")
            time.dateTime = "1977.01.01"
            if (timeChangeLine) {
                time.innerHTML = "1977<br>01-01"
            } else {
                time.textContent = "1977.01.01"
            }

            tag = document.createElement("p")
            tag.textContent = "原创"

            if (res['type'] === "normal") {
                var virtualItem = document.createElement('a')
                virtualItem.href = "./detail-container.html?path=" + res['url']
                virtualItem.appendChild(title)
                virtualItem.appendChild(time)
                virtualItem.appendChild(tag)

                realItem.appendChild(virtualItem)
            } else {
                realItem.appendChild(title)
                realItem.appendChild(time)
                realItem.appendChild(tag)
            }
        }

        mainElement.appendChild(realItem)
    }

    try {
        var sub = res['sub']

        var length = sub.length
        if (length > 0) {
            namePriority += 1
        }
        for (var i = 0; i < length; i++) {
            addAllName(sub[i], mainElement, namePriority, timeChangeLine)
        }
    } catch (error) {
    }
}