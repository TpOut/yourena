function getList (sectionName) {
  var itemClass = 'devsite-nav-item-section-expandable'

  var folderName = document.getElementsByClassName(itemClass)

  var length = folderName.length
  var index = -1
  for ( var j = 0; j < length; j++) {
    if (folderName[j].innerHTML.equals(sectionName)) {
      index = j
    }
  }

  var list = folderName[index].getElementsByClassName('devsite-nav-title')

  var i = list.length
  for ( var j = 0; j < i; j++) {
    console.log(list[j].getElementsByTagName('span')[0].innerHTML)
  }
}

getList("Camera")
