function PasswordRecoveryController($scope, $http) {
	this.scope = $scope;
	this.ValueEnteredFlag = false;
	this.http = $http;
	this.scope.master = {};
	this.util = new Util();
} 

PasswordRecoveryController.prototype.Recovery = function(credentials) {
	document.getElementById("Content0").style.display="none";
	document.getElementById("Content2").style.display="none";
	var self = this;
	var recoveryJson = {
			"firstname" : credentials.firstname,
			 "lastname" : credentials.lastname,
			 "AltEmail" : credentials.email,
			"secretQuestion" : credentials.answer
	}
	this.util.block("Recovering Password");
	this.http({
		method : 'POST',
		url : 'recoveryPassword.do',
		params : {
			'recoveryInput' : recoveryJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			self.util.notify("Recovery Info", data.reason, "success");
			document.getElementById("successMSG1").style.display="block";
			document.getElementById("Content0").style.display="inline-block";
			document.getElementById("Content0").innerHTML = document.getElementById("Content0").innerHTML + data.reason;
			//document.getElementById("jquery-msg-content").
		} else {
			self.util.notify("Recovery Failure", data.reason, "failure");
			document.getElementById("successMSG1").style.display="block";
			document.getElementById("Content2").style.display="inline-block";
			document.getElementById("Content2").innerHTML = document.getElementById("Content2").innerHTML + data.reason;
		}
		self.reset();
		$("#forgotPassword").trigger("click");
		$('#loginModal').modal('hide');
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
}

PasswordRecoveryController.prototype.reset = function() {
	var self=this;
	this.scope.credentials = angular.copy(this.scope.master);
	self.scope.userForm.$setPristine();

	
	/*var self=this;
	self.scope.credentials = angular.copy(this.scope.master);
	//$scope.submitted = true;
    if ($scope.userForm.$invalid) {
      return;
    }
    $scope.userForm.$setPristine();

    $scope.submitted = false;*/
    return;
};
