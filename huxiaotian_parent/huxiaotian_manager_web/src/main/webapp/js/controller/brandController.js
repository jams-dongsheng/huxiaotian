//定义一个控制器
app.controller("brandController",function ($scope,brandService,$controller) {
    $controller("baseController",{$scope:$scope});//继承
    //查询数据
    $scope.findAll=function () {
        brandService.findAll().success(function (response) {
            $scope.list=response;
        });
    };
    //分页控件配置
   /* $scope.paginationConf={
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 5,
        perPageOptions:[5,10,20,30,40,50],
        onChange:function (){
            $scope.reloadList();
        }
    };
    //刷新列表
    $scope.reloadList=function(){
        $scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    };*/
    //分页
    $scope.findPage=function (page,size) {
        brandService.findPage(page,size).success(function (data) {
            $scope.list=data.rows;
            $scope.paginationConf.totalItems=data.total
        });
    };
    //添加、修改
    $scope.save = function () {
        var object = null;
        if($scope.entity.id!= null){
            object = brandService.update($scope.entity);
        }else{
            object = brandService.add($scope.entity);
        }
        object.success(function (response) {
            if(response.flag){
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        })
    };
    //查询单个
    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (response) {
            $scope.entity=response;
        })
    };
    //用户勾选复选框
    //用户勾选的ids集合
   /* $scope.selectIds=[];
    $scope.updateSelection=function ($event,id) {
        if($event.target.checked){
            $scope.selectIds.push(id);//向集合中添加id
        }else{
            var index =	$scope.selectIds.indexOf(id)//查找值的位置
            $scope.selectIds.splice(index,1)
        }

    };*/
    //删除
    $scope.dele=function () {
        brandService.dele($scope.selectIds).success(function (response) {
            if(response.flag){
                $scope.reloadList();
            }else{
                alert(response.message);
            }
        })
    };
    //条件查询
    $scope.searchEntity={};
    $scope.search=function (page,size) {
        brandService.search(page,size,$scope.searchEntity).success(function (data) {
            $scope.list=data.rows;
            $scope.paginationConf.totalItems=data.total
        });
    }
});