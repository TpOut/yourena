Ajax.get(noteListUrl, function (res) {
    try {
        res = JSON.parse(res)
        addAllName(res, 0)
    } catch (err) {
        res = {}
    }
    console.log(res)
})

