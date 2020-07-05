//app.js
App({
  ROOT_URL: 'http://127.0.0.1:8080',
  onLaunch: function () {
  },
  getUserInfo:function(cb){
  },
  globalData:{
     cacheKey:'3rdSession'
  },

  login:function(){
    let _this = this
    wx.login({
        success:(res)=>{
          if(res.code) {
            wx.request({
              method:'GET',
              url: _this.ROOT_URL + '/api/wechat/user/login/' +res.code,
              success:(res)=>{
                  // 获取数据到缓存
                  wx.setStorageSync(_this.globalData.cacheKey, res)
              }
            })
          }
        }
    })
      
  },
})