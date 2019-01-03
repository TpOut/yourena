Ajax.get(accessUrl, function (res) {
    try {
        res = JSON.parse(res)
        updateAccessNum(res)
    } catch (err) {
        res = {}
    }
})

function updateAccessNum(res) {
    document.getElementById("index_access_num").innerText = res["num"];
}