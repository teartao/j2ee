// pages/order/order.js
const HTTP_SERVER_URL = 'https://prep-new-vms.htd.cn/hcf/';
/* 业务功能js begin */
  /**
   * 初始化加载菜单
   */
  function initTodayDate($this){
    const DAY_ARR = ['星期日','星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
    const today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth()+1;
    var date = today.getDate();
    var day = today.getDay();
    $this.setData({ todayInfo: ('今天是:' + year + '年' + month + '月' + date+'日 ' + DAY_ARR[day])});
  }
  function loadMenus($this){
    wx.request({
      url: HTTP_SERVER_URL+'menus',
      method: 'GET',
      header: {
        'Content-Type': 'json'
      },
      success: function (res) {
        $this.setData({ menus: res.data.data });
      }
    })
  }
function submitOrderChoice($this, user, choice){
    wx.request({
      url: HTTP_SERVER_URL + 'order/' + user.nickName + '/' + choice.id,
      method: 'POST',
      header: {
        'Content-Type': 'json'
      },
      success: function (res) {
        // $this.setData({ orderInfo: res.data.data });
      }
    })
  }
/* 业务功能js end */
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

    initTodayDate(this);
    loadMenus(this);
    
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
    let that = this;
    const menus = that.data.menus;
    const choice = menus[e.detail.value];
    that.setData({ choosedOrder: choice});
  },
  getUserInfo: function (e) {
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  submitChoice:function(e){
    submitOrderChoice(this, this.data.userInfo, this.data.choosedOrder);    
  }
})