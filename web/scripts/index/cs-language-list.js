var foodList = "CsLanguageList";
Ajax.get(baseUrl + foodList, function (res) {
    try {
        res = JSON.parse(res)
        addListToSection(res, "cs_language")
    } catch (err) {
        res = {}
    }
    console.log(res)
})