app.service('checkService',function($http){

	//回显
	this.findOne=function(roomId){
		return $http.get('room/findOne.do?roomId='+roomId);
	}
	//入住
	this.saveUser = function (user) {
		return $http.post('user/add.do',user);
	}
});