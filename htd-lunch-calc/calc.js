function getMenu(menuTxt) {
    var menu = [];
    if (menuTxt != null && menuTxt !== '') {
        var menus = menuTxt.split('\n');
        for (var i = 1; i < menus.length; i++) {
            var aMenu = menus[i];
            if (aMenu != null && aMenu != '') {
                var price = getNumber(aMenu);
                menu.push({
                    name: getCNWord(aMenu),
                    price: price == null ? 10 : price
                });
            }
        }
    }
    return menu;
}


function getOrders(orderTxt) {
    var orders = [];
    if (orderTxt != null && orderTxt !== '') {
        var ordersArr = orderTxt.split('\n');
        for (var i = 0; i < ordersArr.length; i++) {
            var order = ordersArr[i];
            if (order != null && order != '') {
                var count = getNumber(order);
                orders.push({
                    name: getCNWord(order),
                    count: count == null ? 1 : count
                });
            }
        }
    }

    return orders;
}


function calcPrice() {
    var priceDom = document.getElementById('price');
    var menuDom = document.getElementById('menu');
    var orderDom = document.getElementById('order');

    var menus = getMenu(menuDom.value);
    var orders = getOrders(orderDom.value);
    if (menus == null || menus.length <= 0) {
        alert('填写菜单啊');
        return;
    } else if (orders == null || orders.length <= 0) {
        alert('不点吃个P啊');
    } else {
        var price = 0;
        for (var i = 0; i < orders.length; i++) {
            var order = orders[i];
            for (var j = 0; j < menus.length; j++) {
                var menu = menus[j];
                if (menu.name.indexOf(order.name) >= 0) {
                    price += (menu.price * order.count);
                    break;
                }
            }
        }
    }
    priceDom.innerText = price;
}