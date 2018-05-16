// pages/films/films.js
const app = getApp();
Page({
  data:{
    newFilm:[],
    freeFilm: [],
    getSource: function (num) {
      console.log(Math.ceil(num))
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.loadNewFilm();
    this.loadFreeFilm();
  },
  
  loadNewFilm: function () {
    wx.request({
      url: "https://m.douban.com/rexxar/api/v2/subject_collection/movie_latest/items",
      data: {
        start: 0,
        count: 8
      },
      dataType: "json",
      success: ({ data: { subject_collection_items } }) => {
        this.setData({
          newFilm: subject_collection_items
        });
      }
    });
  },

  loadFreeFilm: function () {
    wx.request({
      url: "https://m.douban.com/rexxar/api/v2/subject_collection/movie_free_stream/items",
      data: {
        start: 0,
        count: 5
      },
      dataType: "json",
      success: ({ data: { subject_collection_items } }) => {
        this.setData({
          freeFilm: subject_collection_items
        });
      }
    });
  },
})