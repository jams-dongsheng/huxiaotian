app.controller("adminController",function ($scope,adminService,$location) {
    $scope.login=function () {
     $scope.errorMessage=$location.search()['error']
        alert(errorMessage);
    }
});