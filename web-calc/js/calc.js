$(function () {
    $('td').on('mousedown', function () {
        $(this).css('background-color', 'gray');
    });
    $('td').on('mouseup', function () {
        $(this).css('background-color', '#cccccc');
    });

});

function convertToList(ordersData) {
    let displayOrderObj = {};
    for (let userName in ordersData) {
        const orderedMenuItem = ordersData[userName];
        let userList = displayOrderObj[orderedMenuItem.name];
        if (userList == null) {
            displayOrderObj[orderedMenuItem.name] = {
                userList: [userName],
                price: orderedMenuItem.price
            };
        } else {
            userList.userList.push(userName);
        }
    }
    return displayOrderObj;
}

function getFinalOrderTxt(displayOrderList) {
    let finalString = '';
    for (let menuName in displayOrderList) {
        finalString += menuName + displayOrderList[menuName].userList.length;
    }
    return finalString;
}