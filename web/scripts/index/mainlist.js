var mainElement = document.querySelector('main')

Ajax.get(baseUrl + 'NotesList', function (res) {
  try {
    res = JSON.parse(res)
    addAllName(res, 0)
  } catch(err) {
    res = {}
  }
  console.log(res)
})

/**
 * 第一次获得的最高，为0；后续递增
 * 为了处理标题样式  
 * 目前的0级目录表示根目录，1级目录表示分类，2级目录表示具体文件
 */

function addAllName (res, namePriority) {
  if (namePriority === 0) {
  }else if (namePriority === 1) {
    var namePara = document.createElement('nav')
    namePara.textContent = res['name']
    namePara.className = 'section' + namePriority

    mainElement.appendChild(namePara)
  }else {
    var namePara = document.createElement('p')
    namePara.className = 'section' + namePriority

    if(res['type'] === "normal"){
      var nameLink = document.createElement('a')
      nameLink.textContent = res['name']
      nameLink.href = "./detail-container.html?path=" + res['url']
      namePara.appendChild(nameLink)
    }else{
      namePara.textContent = res['name']
    }
 
    var allNav = document.querySelectorAll('nav')
    var parentNav = allNav[allNav.length - 1]

    parentNav.appendChild(namePara)
  }

  try {
    var sub = res['sub']

    var length = sub.length
    if (length > 0) {
      namePriority += 1
    }
    for (var i = 0;i < length; i++) {
      addAllName(sub[i], namePriority)
    }
  } catch (error) {}
}
