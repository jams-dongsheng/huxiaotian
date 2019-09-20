app.service("adminService",function ($http) {
   //添加管理员
   this.add=function (entity) {
      return $http.post("../admin/add.do",entity);
   };
   //查询所有
   this.findAll=function () {
      return $http.get("../admin/findAllNormal.do");
   };
   //分页请求
   this.findPage=function (page,size) {
      return $http.get("../admin/findPage.do?page="+page+"&size="+size);
   };
});