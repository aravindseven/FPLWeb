function UserSettingsController($scope, $http) {
	this.scope = $scope;
	this.scope.master = {};
	this.http = $http;
	this.primaryEmailId="";
	this.alternateMailId="";
	this.mobilephone="";
	this.userType="";
	this.SecretQuestionAnswer="";
	this.util = new Util();
	this.showEdit = true;
	this.showSave = false;
	//this.pageType = (window.location.href.indexOf("users.jsp") != -1) ? "useredit" : "user";
	this.emailEdit = this.util.gup("email");
}

UserSettingsController.prototype.change = function(settings) {
	var self = this;
	if(this.showEdit)
	{
		this.showSave = true;
		this.showEdit = false;
		$("#primaryEmail").removeAttr('disabled');
		$("#alternateEmail").removeAttr('disabled');
		$("#mobilePhone").removeAttr('disabled');
		$("#SecretQuestionAnswer").removeAttr('disabled');
	}
	else
	{
		this.showEdit = true;
		this.showSave = false;
		$("#primaryEmail").attr('disabled','disabled');
		$("#alternateEmail").attr('disabled','disabled');
		$("#mobilePhone").attr('disabled','disabled');
		$("#SecretQuestionAnswer").attr('disabled','disabled');
			var settingsJson = {
				 "primaryEmailId" : settings.primaryEmailId,
				 "alternateMailId" : settings.alternateMailId,
				 "mobileNumber" : settings.mobilephone,
				 "userType" : settings.userType,
				 "secretAnswer" : settings.SecretQuestionAnswer
			};
			this.util.block("Modifying details");
			this.http({
				method : 'POST',
				url : 'updateLoginDetail.do',
				params : {
					'email' : self.emailEdit,
					'settingsInput' : settingsJson
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
				}
			}).success(function(data, status, headers, config) {
				if (data.status == "success") {
					self.util.notify("Profile Info", data.reason, "success");
				} else {
					self.util.notify("Profile Failure", data.reason, "failure");
				}
				self.util.unblock();
			}).error(function(data, status, headers, config) {
				self.util.unblock();
			});
		}	
};

UserSettingsController.prototype.fetch = function() {
	var self = this;
	this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'fetchLoginDetail.do',
		params : {
			email: self.emailEdit
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		self.primaryEmailId = data.primaryEmailId;
		self.alternateMailId=data.alternateMailId;
		self.mobilephone=data.mobileNumber;
		self.userType=data.userType;
		self.SecretQuestionAnswer=data.secretAnswer;
		
		self.util.unblock();
		$("#userType").val(data.userType);
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

UserSettingsController.prototype.verify = function() {
	var self = this;
	
	this.util.block("Sending verification email");
	this.http({
		method : 'POST',
		url : 'sendSecEmailOtp.do',
		params : {
			'email' : self.emailEdit,
			'secondaryMailId' : this.alternateMailId
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			self.util.notify("Profile Info", data.reason, "success");
		} else {
			self.util.notify("Profile Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

UserSettingsController.prototype.reset = function() {
	this.fetch();
};

UserSettingsController.prototype.typeChange = function(elename,type) {
	if(type == 'view')
		{
		if(elename == 'mobile')
			$("#mobilePhone").attr('type','text');
		else if(elename == 'SecQuestion')
			$("#SecretQuestionAnswer").attr('type','text');
		}
	else if (type == 'mask')
		{
		if(elename == 'mobile')
			$("#mobilePhone").attr('type','password');
		else if(elename == 'SecQuestion')
			$("#SecretQuestionAnswer").attr('type','password');
		}
};