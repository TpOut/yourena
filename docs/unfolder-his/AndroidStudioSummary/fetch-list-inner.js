function getList(index){

    var itemClass = "devsite-nav-item-section-expandable"

    var folderName = document.getElementsByClassName(itemClass)

    var list = folderName[index].getElementsByClassName("devsite-nav-title")

    var i = list.length
    for( var j = 0; j < i; j ++){
        console.log(list[j].getElementsByTagName("span")[0].innerHTML)
    }

}

getList(indexInTopList)
