function getList(){

    var list = document.getElementsByClassName("devsite-section-nav")[0].getElementsByClassName("devsite-nav-item")
    var i = list.length
    for( var j = 0; j < i; j ++){
      if(list[j].parentNode.className.indexOf("devsite-nav-list") != -1){
        console.log(list[j].firstElementChild.firstElementChild.innerHTML)
      }
    }

}

getList()
