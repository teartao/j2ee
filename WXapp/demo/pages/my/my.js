// pages/comments/comments.js
Page({

  /* 页面的初始数据*/
  data: {
    userInfo:{
      nikeName:"未授权",
      avatarUrl:"../../imgs/me-close.png"
    }
  },

  /* 生命周期函数--监听页面加载*/
  onLoad: function () {
    wx.getUserInfo({
      success:({userInfo})=>{
        console.log(userInfo);
        this.setData({
          userInfo:userInfo
        })
      },
      fail:()=>{
        console.log(11);
      }
    })
  },

  onReady:function(){
    console.log(wx.getStorageInfoSync("film"))
  },
  


})