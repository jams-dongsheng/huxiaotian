app.controller("baseController",function ($scope) {
    $scope.paginationConf={
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 5,
        perPageOptions:[5,10,20,30,40,50],
        onChange:function (){
            $scope.reloadList();
        }
    };
// //刷新列表
    $scope.reloadList=function(){
        $scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
    };

    //用户勾选复选框
    //用户勾选的ids集合
    $scope.selectIds=[];
    $scope.updateSelection=function ($event,id) {
        if($event.target.checked){
            $scope.selectIds.push(id);//向集合中添加id
        }else{
            var index =	$scope.selectIds.indexOf(id)//查找值的位置
            $scope.selectIds.splice(index,1)
        }

    };
    $scope.jsonToString=function (jsonString,key) {
        var json=JSON.parse(jsonString);
        var value="";
        for(var i = 0 ; i<json.length;i++){
            if(i>0){
                value+="，";
            }
            value+=json[i][key];
        }
        return value;

    }
});





