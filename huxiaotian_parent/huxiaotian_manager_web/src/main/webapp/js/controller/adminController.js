app.controller("adminController",function ($scope,adminService,$location) {
    $scope.loginCheck=function () {
     $scope.errorStatus=$location.search()['error'];
    };

});