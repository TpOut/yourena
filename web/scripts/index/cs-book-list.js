var foodList = "CsBookList";
Ajax.get(baseUrl + foodList, function (res) {
    try {
        res = JSON.parse(res)
        addListToSection(res, "cs_book")
    } catch (err) {
        res = {}
    }
    console.log(res)
})