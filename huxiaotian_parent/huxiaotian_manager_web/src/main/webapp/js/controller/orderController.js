app.controller("orderController",function ($scope,orderService) {

    $scope.reloadList = function () {
        //$scope.findPage($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
        $scope.findUserList($scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    };

    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 5,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    };
    $scope.findUserList = function (currPage,pageSize) {
        orderService.findUserList(currPage,pageSize).success(function (response) {
            $scope.userList = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    }
})