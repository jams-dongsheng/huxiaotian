//定义商品服务
app.service("hotelRoomService",function ($http) {
    //添加房间
    this.add=function (yxtRoom) {
        return $http.post("../room/add.do",yxtRoom);
    };
    this.uploadFile=function () {
        var formData = new FormData;
        formData.append('file',file.files[0]);
        return $http({
            method:'post',
            url:'../upload/uploadFile.do',
            data:formData,
            headers:{"Content-Type":undefined},
            transformRequest: angular.identity

        });
    }

    this.update=function (yxtRoom) {
        return $http.post("../room/update.do",yxtRoom);
    }
    this.findPage=function (page,pageSize) {
        return $http.post("../user/findAllRoom.do?pageNum="+page+"&pageSize="+pageSize);
    }
    this.findTime=function (roomId) {
        return $http.post("../user/findTime.do?roomId="+roomId);
    }
});