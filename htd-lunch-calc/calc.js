function getMenu(menuTxt) {
    const menu = [];
    if (menuTxt != null && menuTxt !== '') {
        const menus = menuTxt.split('\n');
        for (let i = 1; i < menus.length; i++) {
            const aMenu = menus[i];
            if (aMenu != null && aMenu.trim() !== '') {
                const price = getNumber(aMenu);
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
    const orders = [];
    if (orderTxt != null && orderTxt !== '') {
        const ordersArr = orderTxt.split('\n');
        for (let i = 0; i < ordersArr.length; i++) {
            const order = ordersArr[i];
            if (order != null && order.trim() !== '') {
                const count = getNumber(order);
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
    const priceDom = document.getElementById('price');
    const menuDom = document.getElementById('menu');
    const orderDom = document.getElementById('order');

    const menus = getMenu(menuDom.value);
    const orders = getOrders(orderDom.value);
    if (menus == null || menus.length <= 0) {
        alert('填写菜单啊');
        return;
    } else if (orders == null || orders.length <= 0) {
        alert('不点吃个P啊');
    } else {
        let price = 0;
        for (let i = 0; i < orders.length; i++) {
            const order = orders[i];
            for (let j = 0; j < menus.length; j++) {
                const menu = menus[j];
                if (menu.name.indexOf(order.name) >= 0) {
                    price += (menu.price * order.count);
                    break;
                }
            }
        }
    }
    priceDom.innerText = price;
}