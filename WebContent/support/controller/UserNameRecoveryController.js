function UserNameRecoveryController($scope, $http) {
	this.scope = $scope;
	this.http = $http;
	this.scope.master = {};
	this.util = new Util();
	this.scope.valid=false;
	
}

UserNameRecoveryController.prototype.Recovery = function(credentials) {
	document.getElementById("Content3").style.display="none";
	document.getElementById("Content4").style.display="none";
	
	var self = this;
	var recoveryJson = {
		"firstname" : credentials.firstname,
		 "lastname" : credentials.lastname,
		 "AltEmail" : credentials.email,
		"secretQuestion" : credentials.answer
	};
	
	this.util.block("Recovering Email");
	this.http({
		method : 'POST',
		url : 'recoveryUserName.do',
		params : {
			'recoveryInput' : recoveryJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			self.util.notify("Recovery Info", data.reason, "success");
			document.getElementById("successMSG2").style.display="block";
			document.getElementById("Content3").style.display="inline-block";
			document.getElementById("Content3").innerHTML = document.getElementById("Content3").innerHTML + data.reason;

			
		} else {
			self.util.notify("Recovery Failure", data.reason, "failure");
			document.getElementById("successMSG2").style.display="block";
			document.getElementById("Content4").style.display="inline-block";
			document.getElementById("Content4").innerHTML = document.getElementById("Content4").innerHTML + data.reason;

			
		}
		self.reset();
		$("#forgotUsername").trigger("click");
		$('#loginModal').modal('hide');
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
 
UserNameRecoveryController.prototype.reset = function() {
	var self=this;
	this.scope.credentials = angular.copy(this.scope.master);
	self.scope.userForm.$setPristine();
};