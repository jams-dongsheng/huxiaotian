app.controller("checkinhotelController",function ($scope,$location,checkService) {

    $scope.user={}

    $scope.findOne = function () {
        checkService.findOne($location.search()["roomId"]).success(function (response) {
            $scope.room = response;
            $scope.user.userRoomId=response.roomId;
        });
    }

    $scope.saveUser = function () {
        checkService.saveUser($scope.user).success(function (response) {
            if(response.success){
                //成功跳转订单页面
                location.herf="order.html";
            }else {
                alert(response.message);
            }
        });
    }
})