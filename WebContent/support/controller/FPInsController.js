function FPInsController($scope, $http, $interval){
	this.scope = $scope;
	this.http = $http;
	this.scope.master = {};
	this.util = new Util();
	$scope.date = new Date();
	
    $interval(function(){
		$scope.date = new Date();
	}, 1000);
}

FPInsController.prototype.saveEntity = function(entity){
	//var expDate = entity.expiryDate;
	//alert(expDateStr);
	/*var day  = parseInt(expDateStr.split("-")[1]);
	var month= parseInt(expDateStr.split("-")[0]);
	var year = parseInt(expDateStr.split("-")[2]);*/
	
	//var expDate = new Date(year,month,day);
	//var date = new Date();
	//date input type works only for chrome so we need to check isChrome
	//if(isChrome() && typeof expDate == "object" && expDate>date){
		var self = this;
		this.http({
			method : 'POST',
			url : 'support/contents/saveFPIns.json',
			params : {
				'fplInput' : JSON.stringify(entity)
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			if (data.status == "Success") {
				self.util.notify("Info", data.reason, "success");
			} else {
				self.util.notify("Failure", data.reason, "failure");
			}
			self.util.unblock();
		}).error(function(data, status, headers, config) {
			self.util.unblock();
		});
	//}
}

/*FPInsController.prototype.fetchFPLDB = function(entity){
	var self = this;
	self.util.block("Retrieving details");
	self.http({
		method : 'POST',
		url : 'support/contents/fplDB.json',
		params : {},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			self.scope.entity = data;
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
}*/


FPInsController.prototype.getGeoLocation = function(entity) {
	var self = this;
	self.formattedAddress = "";
	self.http({
						method : 'POST',
						type : 'jsonp',
						url : 'http://maps.google.com/maps/api/geocode/json?components=postal_code:'
								+ entity.postalCode + '&sensor=false',
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
									entity.city = addressSplit[0];
									entity.state = addressSplit[1].replace(/[0-9]/g, '');
									entity.country = addressSplit[2];
								}
							}
						}
					}).error(function(data, status, headers, config) {
			});
	self.util.unblock();
};

FPInsController.prototype.reset = function(entity){
	this.scope.entity = this.scope.master;
};