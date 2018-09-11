// pages/order/order.js
const HTTP_SERVER_URL = 'https://prep-new-vms.htd.cn/hcf/';
/* 业务功能js begin */
function loadMyOrder($this,userName) {
  wx.request({
    url: HTTP_SERVER_URL + 'order/'+userName,
    method: 'GET',
    header: {
      'Content-Type': 'json'
    },
    success: function (res) {
      $this.setData({ menu: res.data.data });
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
    this.setData({
      imgalist: [
        'http://static-cdn.htd.cn/prep/2018911/image/jpeg/de97a4284ad74139bb7211082135db91.jpeg',
        'http://static-cdn.htd.cn/prep/2018911/image/jpeg/7b92c0e5e242442898d70fa1faa44c55.jpeg'
      ]
    });
    this.setData({
      alipayCode:'bbYelk73yJ'
    });
    loadMyOrder(this, options.name);
    console.log(options)//可以打印一下option看查看参数
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
  copyToClipBoard:function(){
    console.log(this.data.alipayCode);
    wx.setClipboardData({
      data: this.data.alipayCode,
      success: function (res) {
        wx.showToast({title: '复制成功'});
          wx.getClipboardData({
            success: function (res) {}
          })
        }
      })
  },
  previewImage: function (e) {
    wx.previewImage({
      current: e.target.dataset.src, // 当前显示图片的http链接   
      urls: this.data.imgalist // 需要预览的图片http链接列表   
    })
    // wx.getImageInfo({// 获取图片信息（此处可不要）
    //   src: e.target.dataset.src,
    //   success: function (res) {
    //     console.log(res.width)
    //     console.log(res.height)
    //   }
    // })
  }

})