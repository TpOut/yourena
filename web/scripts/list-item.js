/**
 * 服务器获取列表项，
 * 如果是文件夹，即认为是文集，入口是 -index后缀的文件
 * 否则就是文件
 *
 * @param itemRes
 * @returns {*}
 */

function getListItem(itemRes) {
    if (itemRes['type'] === "directory") {
        let subListRes = itemRes['sub'];

        const subLength = subListRes.length;
        for (let subI = 0; subI < subLength; subI++) {
            let subItemRest = subListRes[subI];
            if (subItemRest['type'] === "normal" && subItemRest['name'].indexOf("-index") !== -1) {
                //用文件夹的名字更好
                subItemRest['name'] = itemRes['name'];
                return subItemRest;
            }
        }
    } else if (itemRes['type'] === "normal") {
        return itemRes;
    }
};
