const app = getApp();
const root_url = app.ROOT_URL
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl:'',
    nickName:'',
    score: 0
  },
  bindUserInfo:function(e){
      let _this = this;
      this.data.avatarUrl = e.detail.userInfo.avatarUrl
      console.log(e.detail.userInfo)
      console.log(this.data)
  },
  userLogin:function(params) {
    wx.login({
      success(res) {
        if(res.code) {
          wx.request({
            url: root_url + '/api/wechat/user/login/' + res.code,
            success:(res)=>{
              console.log(res.data)
            }
          })
          console.log(res.code)
        } else{
          console.log("登陆失败")
        }
      },
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
     // 判断是否授权
    wx.getSetting({
      success: function(res) {
        console.log(res)
        if (res.authSetting['scope.userInfo']) {
            console.log('用户授权了')
            // 调用接口获取用户信息
        } else{
            // 用户未授权 跳到授权页
            wx.navigateTo({
              url: 'login',
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
    
  }
})