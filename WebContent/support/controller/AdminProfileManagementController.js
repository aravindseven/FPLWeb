function AdminProfileManagementController($scope, $http) {
	this.scope = $scope;
	//this.scope.master = {};
	this.http = $http;
	this.util = new Util();
	$scope.userList = [];
}

AdminProfileManagementController.prototype.searchUser = function(searchuser) {
	var self = this;	
	this.util.block("Searching");
	var searchInput = JSON.stringify(searchuser);
	this.http({
		method : 'POST',
		url : 'support/contents/admin_user_list.json',
		type : 'json',
		params : {
			'searchInput' : searchInput
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
			self.scope.userList = eval(data);
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

AdminProfileManagementController.prototype.getGeoLocation = function(uProfile) {
	var self = this;
	self.formattedAddress = "";
	self.http({
						method : 'POST',
						type : 'jsonp',
						url : 'http://maps.google.com/maps/api/geocode/json?components=postal_code:'
								+ uProfile.thirdPartyPostalCode + '&sensor=false',
						params : {},
						headers : {
							'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
						}
					})
			.success(
					function(data, status, headers, config) {
						if(data){
							if(data.results.length > 0){
								self.formattedAddress = data.results[0].formatted_address;
								var addressSplit = self.formattedAddress.split(",");
								if(addressSplit.length == 3){
									uProfile.thirdPartyCity = addressSplit[0];
									uProfile.thirdPartyState = addressSplit[1].replace(/[0-9]/g, '');
									uProfile.thirdPartyCountry = addressSplit[2];
								}
							}
						}
					}).error(function(data, status, headers, config) {
			});
	self.util.unblock();
};

AdminProfileManagementController.prototype.activateAccount = function(email) {
	var self = this;	
	this.util.block("Activating Account");
	this.http({
		method : 'POST',
		url : 'activateProfile.do',
		params : {
			'email' : email
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

AdminProfileManagementController.prototype.deactivateAccount = function(email) {
	var self = this;
	this.util.block("Deactivating Account");
	this.http({
		method : 'POST',
		url : 'deactivateProfile.do',
		params : {
			'email' : email
		},
		headers : {
			
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			self.util.notify("Info", data.reason, "success");
		} else {
			self.util.notify("Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

AdminProfileManagementController.prototype.editUser = function(email) {
	window.location.href = "users.jsp?email=" + email;
};

AdminProfileManagementController.prototype.saveThirdParty= function(uProfile) {
	var self = this;
	this.util.block("Save details");
	var ThirdPartyJson = {
			"thirdPartyName":uProfile.thirdPartyName,
			"thidPartyRegNumber":uProfile.thidPartyRegNumber,
			"thirdPartyLocation":uProfile.thirdPartyLocation,
			"thirdPartyWebsite":uProfile.thirdPartyWebsite,
			"thirdPartyDomain":uProfile.thirdPartyDomain,
			"thirdPartyBlock":uProfile.thirdPartyBlock,
			"thirdPartyBuilding":uProfile.thirdPartyBuilding,
			"thirdPartyStreet":uProfile.thirdPartyStreet,
			"thirdPartyCity":uProfile.thirdPartyCity,
			"thirdPartyState":uProfile.thirdPartyState,
			"thirdPartyCountry":uProfile.thirdPartyCountry,
			"thirdPartyMobile":uProfile.thirdPartyMobile,
			"thirdPartyLandline":uProfile.thirdPartyLandline,
			"thirdPartyEmail":uProfile.thirdPartyEmail,
			"thirdPartyprimaryFirstname":uProfile.thirdPartyprimaryFirstname,
			"thirdPartyprimaryLastname":uProfile.thirdPartyprimaryLastname,
			"thirdPartyContactMobile":uProfile.thirdPartyContactMobile,
			"thirdPartyPosition":uProfile.thirdPartyPosition,
			"thirdPartyIdProof":uProfile.thirdPartyIdProof,
			"thirdPartyIdNumber": uProfile.thirdPartyIdNumber,
			"thirdPartyComments": uProfile.thirdPartyComments,
			"thirdPartyValidateStatus": uProfile.thirdPartyValidateStatus,
			"thirdPartyValidateDate": uProfile.thirdPartyValidateDate
	};
	this.http({
		method : 'POST',
		url : 'support/contents/admin/Subscription/save_Details.json',
		params : {
			'ThirdPartyJson' : ThirdPartyJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Profile Info", data.reason, "success");
		} else {
			self.util.notify("Profile Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
AdminProfileManagementController.prototype.reset = function() {
	this.scope.user = angular.copy(this.scope.master);
};