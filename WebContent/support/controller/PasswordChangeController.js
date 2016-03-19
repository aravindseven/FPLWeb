function PasswordChangeController($scope, $http) {
	this.scope = $scope;
	this.confirmPasswordError = false;
	this.http = $http;
	this.scope.master = {};
	this.util = new Util();
	this.showEdit = true;
	this.showSave = false;
}

PasswordChangeController.prototype.change = function(passwordInfo) {
	this.confirmPasswordError = !(this.matchConfirm(passwordInfo.newPassword, passwordInfo.confirmPassword));
	if (!this.confirmPasswordError) {
		var self = this;
		this.util.block("Changing Password");
		this.http({
			method : 'POST',
			url : 'changePassword.do',
			params : {
				'newPassword' : passwordInfo.newPassword
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			if (data.status == "success") {
				self.util.notify("Password Change Success", data.reason, "success");
			} else {
				self.util.notify("Password Change Failure", data.reason, "failure");
			}
			self.oldPassword = "";
			self.newPassword = "";
			self.confirmPassword = "";
			self.util.unblock();
		}).error(function(data, status, headers, config) {
			self.util.unblock();
		});
	} else {
		this.confirmPasswordError = true;
	}
};

PasswordChangeController.prototype.matchConfirm = function(pwd, confirmPwd) {
	return (pwd === confirmPwd);
}