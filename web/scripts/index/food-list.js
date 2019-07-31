var foodList = "FoodList";
Ajax.get(baseUrl + foodList, function (res) {
    try {
        res = JSON.parse(res)
        addListToSection(res, "food")
    } catch (err) {
        res = {}
    }
    console.log(res)
})

