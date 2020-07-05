const app = getApp();
const root_url = app.ROOT_URL
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    score: 0,
    show: false
  },
  bindGetUserInfo: function (res) {
      if(res.detail.userInfo) {
         this.setData({
           userInfo:res.detail.userInfo,
           show:false
         })
         app.login()
      }
  },

  userAuthorization: function(){
      let _this = this;
      wx.getSetting({
        success:(res)=>{
          if (res.authSetting['scope.userInfo']) {
            wx.navigateTo({
              // 已授权 跳转积分页
              url: '../logs/logs',
            })
          } else {
            _this.authModal()
          }
        }
      })
  },


  
  onClose: function (res) {
      this.setData({
        show:false
      })
      wx.showToast({
        title: '未授权，部分功能不可用',
        icon:'none',
        duration:1500
      })


  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let _this = this
    wx.getSetting({
      success:(res)=>{
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success:(res)=>{
              _this.setData({
                userInfo:res.userInfo
              })
            }
          })
          wx.checkSession({
            success:(res)=>{
              console.log(res)
            },
            fail: () => {
              console.log('error')
                app.login()
            },
          })
        }
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

  authModal:function() {
    let _this = this;
    wx.showModal({
      title:'请授权',
      content:'该功能需要登陆后使用',
      showCancel:false,
      success:(res)=>{
        _this.setData({
          show:true
        })
      }
    })
  }
})