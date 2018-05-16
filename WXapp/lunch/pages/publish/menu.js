// pages/menu/menu.js

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
Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },
  bindTextAreaBlur: function (e) {
    getCNWord(e.detail.value);
    var menus = getMenu(e.detail.value);
    this.setData({ menus: menus});
    console.log(menus)
  },
  submitMenu:function(e){

  }, 
  selectRecipt:function(e){

  }
})

