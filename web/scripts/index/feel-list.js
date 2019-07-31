var foodList = "FeelList";
Ajax.get(baseUrl + foodList, function (res) {
    try {
        res = JSON.parse(res)
        addListToSection(res, "feel")
    } catch (err) {
        res = {}
    }
    console.log(res)
})

