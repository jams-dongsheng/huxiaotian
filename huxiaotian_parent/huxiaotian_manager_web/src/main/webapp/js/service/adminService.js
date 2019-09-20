app.service("adminService",function ($http) {//登录验证
   this.loginCheck=function (entity) {
       return $http.post("/user/loginCheck.do",entity);
   }
});