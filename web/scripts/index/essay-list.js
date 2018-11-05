Ajax.get(essayListUrl, function (res) {
    try {
        res = JSON.parse(res)
        addAllName(res, document.getElementById('essay_list'), 0)
    } catch (err) {
        res = {}
    }
})

