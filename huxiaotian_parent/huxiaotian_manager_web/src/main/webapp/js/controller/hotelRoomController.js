//定义一个控制器
app.controller("hotelRoomController",function ($scope,hotelRoomService,$controller) {
    $controller("baseController",{$scope:$scope});//继承
    //查询数据
    $scope.findAll=function () {
        hotelRoomService.findAll().success(function (response) {
            $scope.roomlist=response;
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

    $scope.findTime=function (roomId) {
        hotelRoomService.findTime(roomId).success(
            function (response) {
                if (response!=null){

                    allsecond =Math.floor( (  new Date(response.checkOut).getTime()- (new Date().getTime())) /1000); //总秒数
                    var timeString = convertTimeString(allsecond);
                    return timeString;
                }else {
                    return '';
                }
            }
        )
    }
    //转换秒为 天小时分钟秒格式  XXX天 10:22:33
    convertTimeString=function(allsecond){
        var days= Math.floor( allsecond/(60*60*24));//天数
        var hours= Math.floor( (allsecond-days*60*60*24)/(60*60) );//小数数
        var minutes= Math.floor(  (allsecond -days*60*60*24 - hours*60*60)/60    );//分钟数
        var timeString="";
        if(days>0){
            timeString=days+"天 ";
        }
        return timeString+hours+":"+minutes;
    }


    //分页
    $scope.roomIds=[];
    $scope.time=[];
    $scope.findPage=function (page,size) {
        hotelRoomService.findPage(page,size).success(function (data) {
            $scope.roomList=data.rows;
            $scope.paginationConf.totalItems=data.total;
        });
    };


    //添加
    $scope.save = function () {
        hotelRoomService.add($scope.yxtRoom).success(
            function (response) {
                alert(response.message)
            }
        )
    };

    //查询数据
    $scope.add=function () {
        hotelRoomService.add($scope.yxtRoom).success(function (response) {
            alert(response.message)
        });
    };

    $scope.roomSelectss=['单人间','双人间','大床房'];
    $scope.yxtRoom={roomPicture:'',roomId:''}
    $scope.uploadFile=function () {
        hotelRoomService.uploadFile().success(
            function (reponse) {
                $scope.yxtRoom.roomPicture=reponse.message;
                if (reponse.success){
                    $scope.yxtRoom.roomPicture=reponse.message;
                } else {
                    alert(reponse.message)
                }

            }
        )
    }


});