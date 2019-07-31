var foodList = "StoryList";
Ajax.get(baseUrl + foodList, function (res) {
    try {
        res = JSON.parse(res)
        addListToSection(res, "story")
    } catch (err) {
        res = {}
    }
    console.log(res)
})

