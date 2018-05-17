// pages/order/order.js
const app = getApp()
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


    var $this=this;
    wx.request({
      url: 'https://prep-new-vms.htd.cn/xxx/test.json',
      method: 'GET',
      header: {
        'Content-Type': 'json'
      },
      success: function (res) {
        $this.setData({ menus: res.data });
        console.log(res.data);
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
  selectRecipt: function (e) {
    var that = this;
    const menus = that.data.menus;
    const choice = menus[e.detail.value];
    // that.data.userInfo 
    // var xxx= { 
    //   "nickName":"陶呵呵",
    //  "gender":1, 
    //  "language":"zh_CN", 
    //  "city":"", "province":"Firenze", 
    //  "country":"Italy", 
    //  "avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEJrjrX8xptUibYibicn4dvplVIWBrRhTyDIIPTar7uHl86KMjX92HMIxIGZV92PCcTiaqy0gTP4jKxOWQ/132" 
    //  }
    
    console.log(that.data.userInfo.nickName+'选择了：');
    console.log(choice.name + ' ￥' + choice.price);
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})