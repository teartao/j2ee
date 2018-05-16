const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

String.prototype.replaceAll = function (s1, s2) {
  return this.replace(new RegExp(s1, "gm"), s2);
};

function getCNWord(str) {
  var cnReg = /[\u4e00-\u9fa5]+.*[\u4e00-\u9fa5]/g;
  var words = str.match(cnReg);
  if (words != null && words.length > 0) {
    return words[0];
  } else {
    return null;
  }
}

function getNumber(str) {
  var cnReg = /[0-9]+/g;
  var nums = str.match(cnReg);
  if (nums != null && nums.length > 0) {
    return Number(nums[0]);
  } else {
    return null;
  }
}
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
function calcPrice(menus, orders) {
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
  return price;
}


module.exports = {
  formatTime: formatTime,
  getNumber: getNumber,
  getCNWord: getCNWord,
  getMenu: getMenu,
  getOrders: getOrders,
  calcPrice: calcPrice
}
