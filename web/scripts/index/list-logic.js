/**
 * 第一次获得的最高，为0；后续递增
 * 为了处理标题样式
 * 目前的0级目录表示根目录，1级目录表示分类，2级目录表示具体文件
 */

function addAllName(res, mainElement, namePriority) {
    console.log("start" + namePriority + " : " + mainElement.toLocaleString())
    var item;
    var title;
    var time;
    var tag;

    if (namePriority == 0) {

    }
    if (namePriority === 1) {
        item = document.createElement('li')
        title = document.createElement("p")

        title.className = "section" + namePriority
        title.innerText = res['name']

        item.appendChild(title)
        mainElement.appendChild(item)
    }
    if (namePriority === 2) {
        item = document.createElement('li')
        title = document.createElement("p")
        time = document.createElement("time")
        // tag = document.createElement("")

        time.dateTime = "1977.01.01"
        title.className = "section" + namePriority
        title.innerText = res['name']

        item.appendChild(title)
        item.appendChild(time)

        if (res['type'] === "normal") {
            var link = document.createElement('a')
            link.href = "./detail-container.html?path=" + res['url']
            link.appendChild(item)
            mainElement.appendChild(link)
        } else {
            mainElement.appendChild(item)
        }
    }

    try {
        var sub = res['sub']

        var length = sub.length
        if (length > 0) {
            namePriority += 1
        }
        for (var i = 0; i < length; i++) {
            addAllName(sub[i], mainElement, namePriority)
        }
    } catch (error) {
    }
}