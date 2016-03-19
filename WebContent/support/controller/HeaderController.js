function HeaderController($scope, $http) {
	this.scope = $scope;
	this.http = $http;
	this.scope.master = {};
	this.util = new Util();
	$scope.first_name;
	$scope.last_name;
	
}

HeaderController.prototype.logout = function(info) {
		var self = this;
		this.util.block("logout");
		alert("calledlogout");
		this.http({
			method : 'POST',
			url : 'userlogout.do',
			
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			if (data.status == "success") {
				self.util.notify("logout Success", data.reason, "success");
				window.location.href = "index1.jsp";
			} else {
				self.util.notify("logout Failure", data.reason, "failure");
			}
			
			self.util.unblock();
		}).error(function(data, status, headers, config) {
			self.util.unblock();
		});
	
};
HeaderController.prototype.userdetails = function() {
	var self = this;
	
	this.http({
		method : 'POST',
		url : 'getuserdetails.do',
		
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.Status == "Success") {
			self.util.notify("gotuserdetails", data, "Success");
			console.log(""+data.first_name);
			console.log(""+data.last_name);
			self.scope.first_name = data.first_name;
	        self.scope.last_name = data.last_name;
			
			
		} else {
			self.util.notify("logout Failure", data, "failure");
		}
		
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});

};
