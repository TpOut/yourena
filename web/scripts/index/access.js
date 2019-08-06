if (isDeploy) {
    Ajax.get(accessUrl, function (res) {
        try {
            res = JSON.parse(res)
            updateAccessNum(res)
        } catch (err) {
            res = {}
        }
    })

    function updateAccessNum(res) {
        console.log(res)
    }
}

