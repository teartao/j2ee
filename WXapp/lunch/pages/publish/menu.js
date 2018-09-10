String.prototype.replaceAll = function (s1, s2) {
  return this.replace(new RegExp(s1, "gm"), s2);
};
String.prototype.trim = function () {
  return this.replace(/(^\s*)|(\s*$)/g, '');
};

// pages/menu/menu.js
const app = getApp()
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
  var menuTitle = '';
  if (menuTxt != null && menuTxt !== '') {
    var menus = menuTxt.split('\n');
    for (var i = 1; i < menus.length; i++) {
      var aMenu = menus[i];
      if (aMenu != null && aMenu.trim() != '') {
        var price = getNumber(aMenu);
        menu.push({
          name: getCNWord(aMenu),
          price: price == null ? 10 : price
        });
      }
    }
  }
  return {
    title:menuTitle,
    selectionList:menu
  };
}
function saveMenu($this){
  wx.request({
    url: 'https://47.96.160.85:8080/hcf/saveMenu',
    method: 'POST',
    header: {
      'Content-Type': 'json'
    },
    data: JSON.stringify($this.data.menus),
    success: function (res) {
      console.log('save menus success ');
      console.log(res);
    }
  })
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
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
          console.log('current user :');
          console.log(res.userInfo);
        }
      })
    }


    var $this = this;
    wx.request({
      url: 'https://47.96.160.85:8080/hcf/user',
      method: 'POST',
      header: {
        'Content-Type': 'json'
      },
      data: JSON.stringify(app.globalData.userInfo),
      success: function (res) {
        $this.setData({
          loginUser: res
        })
      }
    })
  
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
    this.setData({ menuTxt: e.detail.value });
    console.log("menuTxt:\n" + this.data.menuTxt)
    
    // getCNWord(e.detail.value);
    var menus = getMenu(e.detail.value);
    
    this.setData({ menus: menus });
    console.log("menu: \n")
    console.log(menus);

    saveMenu(this);
  },
  submitMenu:function(e){
    saveMenu(this);
  }
})

