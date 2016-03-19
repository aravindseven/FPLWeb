function PasswordResetController($scope, $http) {
	this.scope = $scope;
	this.conformPasswordError = false;
	this.http = $http;
	this.scope.master = {};
	this.util = new Util();
}

PasswordResetController.prototype.resetPassword = function(passwordInfo) {
	this.conformPasswordError = !(this.matchConfirm(passwordInfo.newPassword, passwordInfo.conformPassword));
	if (!this.conformPasswordError) {
		var self = this;
		var resetJson = {
			"email" : passwordInfo.email,
			"newPassword" : passwordInfo.newPassword
		}
		this.util.block("Resetting Password");
		this.http({
			method : 'POST',
			url : 'passwordReset.do',
			params : {
				'resetInput' : resetJson
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			if (data.status == "success") {
				self.util.notify("Recovery Info", data.reason, "success");
			} else {
				self.util.notify("Recovery Failure", data.reason, "failure");
			}
			self.util.unblock();
		}).error(function(data, status, headers, config) {
			self.util.unblock();
		});
	} else {
		this.conformPasswordError = true;
	}
};

PasswordResetController.prototype.matchConfirm = function(pwd, confirmPwd) {
	return (pwd === confirmPwd);
}