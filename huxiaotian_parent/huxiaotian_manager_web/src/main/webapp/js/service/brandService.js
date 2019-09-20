//定义商品服务
app.service("brandService",function ($http) {
    //查询请求
    this.findAll=function () {
        return $http.get("../brand/findAll.do");
    };
    //分页请求
    this.findPage=function (page,size) {
        return $http.get("../brand/findPage.do?page="+page+"&size="+size);
    };
    //查询单个请求
    this.findOne=function (id) {
        return $http.get("../brand/findOne.do?id="+id);
    };
    //添加请求
    this.add=function (entity) {
        return $http.post("../brand/add.do",entity);
    };
    //修改请求
    this.update=function (entity) {
        return $http.post("../brand/update.do",entity);
    };
    //删除请求
    this.dele=function (ids) {
        return $http.get("../brand/delete.do?ids="+ids);
    };
    //条件查询请求
    this.search=function (page,size,searchEntity) {
        return $http.post("../brand/search.do?page="+page+"&size="+size,searchEntity)
    };
    //查询模板下拉选项
    this.selectOptionList=function () {
        return $http.get("../brand/option.do")
    }

});