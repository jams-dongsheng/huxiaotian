app.controller("adminController",function ($scope,adminService,$location) {
    $scope.loginCheck=function () {
     $scope.errorStatus=$location.search()['error'];
    };


    //添加管理员
    $scope.add=function () {
        adminService.add($scope.entity).success(function (response) {
            if(response.success){
                alert(response.message);
            }else{
                alert(response.message);
            }
        })
    };
    //查询所有
    $scope.findAll=function () {
        adminService.findAll().success(function (response) {
            $scope.list=response;
            $scope.reloadList();
        });
    };
    $scope.paginationConf={
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 5,
        perPageOptions:[5,10,20,30,40,50],
        onChange:function (){
            $scope.reloadList();
        }
    };
    $scope.reloadList=function(){
        $scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    };
    //分页查询
    $scope.findPage=function (page,size) {
        adminService.findPage(page,size).success(function (response) {
            $scope.userList=response.list;
            $scope.paginationConf.totalItems=response.total
        });
    };


});