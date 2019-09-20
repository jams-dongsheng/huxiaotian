app.service('orderService',function($http){
	//回显
	this.findUserList=function(pageNum,pageSize){
		return $http.get('../user/findUserList.do?pageNum='+pageNum+'&pageSize='+pageSize);

	}
});