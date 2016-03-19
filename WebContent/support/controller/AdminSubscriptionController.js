function AdminSubscriptionController($scope, $http, $compile) {
	this.scope = $scope;
	this.http = $http;
	this.util = new Util();
	this.compile = $compile;
	this.scope.master = {};
	this.shownDiscount = false;
	this.Country="IN";
	this.advSubCountry="IN";
	var current = new Date();
	this.advSubStartDate = new Date();
	this.fpSubStartDate = new Date();
	this.advSubEndDate = new Date();
	this.fpSubEndDate = new Date();
	this.advSubPayReceipt = new Date();
	this.fpSubPayReceipt = new Date();
	this.advSubEndDate.setFullYear(current.getFullYear()+1);
	this.fpSubEndDate.setFullYear(current.getFullYear()+1);
}

AdminSubscriptionController.prototype.fetchAdvTypes = function() {
	var self = this;
	this.util.block("Retrieving Advertisement Details");	
	this.http({
		method : 'POST',
		url : 'support/contents/AdvTypes.json',
		params : {
				},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		var sel = $("tbody[id=listAdv]");
		sel.empty();
		var advTable = "";
		if (data!=[]) {
			$.each(data, function(index){
				advTable += "<tr> <td>" + this.typeName + "</td> <td>"  + this.subRate + "</td> <td>" + this.startDate + "</td> <td>" + this.endDate + "</td>" +
				 "<td><a href='#' ng-click='aSubscription.deleteDetails(1,"+ this.id +")' ng-confirm='Are you sure you want to delete this request?'> <img src='support/images/delete.jpg' title='Delete' class='cursorP'/> </a> </td></tr>";
			});
		}	
		else 
		{
			advTable += "<tr> <td colspan='5'>No Records Found.</td></tr>";
		}
		advTable += "</tbody></table>";
		sel.append(self.compile(advTable)(self.scope));
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};


AdminSubscriptionController.prototype.saveAdvType= function(aSubscription) {
	var self = this;
	//this.util.block("Saving details");
	var AdvTypeJson = {
			 "advType" : aSubscription.AdvType,
			 "country" : aSubscription.Country,
			 "mediaWeb" :  aSubscription.mediaWeb,
			 "mediaMobile":aSubscription.mediaMobile,
			 "SubRate" :  aSubscription.SubRate,
			 "pStartDate" :  aSubscription.pStartDate,
			 "pEndDate" :  aSubscription.pEndDate,
			 "FileTypes" :  aSubscription.FileTypes,
			 "FileSize" :  aSubscription.FileSize,
			 "Dimension" : aSubscription.Dimension,
			 "SubDeadline" :aSubscription.SubDeadline,
			 "ItemSubmit" :aSubscription.ItemSubmit,
			 "ActiveFlag" :aSubscription.ActiveFlag,
			 "DiscountPolicy":aSubscription.DiscountPolicy,
			 "discountPercentage" :aSubscription.discountPercentage,
			 "comments" :aSubscription.comments
	};
	this.http({
		method : 'POST',
		url : 'support/contents/admin/Subscription/save_Details.json',
		params : {
			'AdvTypeJson' : AdvTypeJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Advertisement Info", data.reason, "success");
			self.scope.aSubscription.lastUpdatedDate = data.lastUpdatedDate;
		} else {
			self.util.notify("Advertisement Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};


AdminSubscriptionController.prototype.fetchAdvSubscription= function() {
	var self = this;
	this.util.block("Retrieving Details");	
	this.http({
		method : 'POST',
		url : 'support/contents/admin/Subscription/AdvSub.json',
		params : {
				},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		var sel = $("tbody[id=listAdvSub]");
		sel.empty();
		var advTable = "";
		if (data!=[]) {
			$.each(data, function(index){
				advTable += "<tr> <td>" + this.Name + "</td> <td>"  + this.ContactName + "</td> <td>" + this.Status  + "</td>" +
				 "<td><a href='#' ng-click='aSubscription.deleteDetails(2," + this.id +")' ng-confirm='Are you sure you want to delete this request?'> <img src='support/images/delete.jpg' title='Delete' class='cursorP'/> </a> </td></tr>";
			});
		}	
		else 
		{
			advTable += "<tr> <td colspan='4'>No Records Found.</td></tr>";
		}
		advTable += "</tbody></table>";
		sel.append(self.compile(advTable)(self.scope));
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

AdminSubscriptionController.prototype.saveadvSubscription = function(aSubscription) {
	var self = this;
	this.util.block("Save details");
	var AdvSubJson = {
			"advSubTarget":aSubscription.advSubTarget,
			"advSubStartDate":aSubscription.advSubStartDate,
			"advSubEndDate":aSubscription.advSubEndDate,
			"advSubType":aSubscription.advSubType,
			"advSubMediaWeb":aSubscription.advSubMediaWeb,
			"advSubMediaMobile":aSubscription.advSubMediaMobile,
			"advSubDuration":aSubscription.advSubDuration,
			"advSubCountry":aSubscription.advSubCountry,
			"advSubPaymentMode":aSubscription.advSubPaymentMode,
			"advSubPaymentTerms":aSubscription.advSubPaymentTerms,
			"advSubPayReceipt":aSubscription.advSubPayReceipt,
			"advSubCurrency":aSubscription.advSubCurrency,
			"advSubAmountReceived":aSubscription.advSubAmountReceived,
			"advSubRenewalFlag":aSubscription.advSubRenewalFlag,
			"advSubArtWorkStatus":aSubscription.advSubArtWorkStatus,
			"advSubActiveFlag":aSubscription.advSubActiveFlag,
			"advSubAgreedDate":aSubscription.advSubAgreedDate,
			"advSubComments":aSubscription.advSubComments
	};
	this.http({
		method : 'POST',
		url : 'support/contents/admin/Subscription/save_Details.json',
		params : {
			'AdvSubJson' : AdvSubJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Advertisement Info", data.reason, "success");
		} else {
			self.util.notify("Advertisement Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

AdminSubscriptionController.prototype.fetchFPTypes = function() {
	var self = this;
	this.util.block("Retrieving FP Details");	
	this.http({
		method : 'POST',
		url : 'support/contents/AdvTypes.json',
		params : {
				},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		var sel = $("tbody[id=listFPType]");
		sel.empty();
		var advTable = "";
		if (data!=[]) {
			$.each(data, function(index){
				advTable += "<tr> <td>" + this.typeName + "</td> <td>"  + this.subRate + "</td> <td>" + this.startDate + "</td> <td>" + this.endDate + "</td>" +
				 "<td><a href='#' ng-click='aSubscription.deleteDetails(3,"+ this.id +")' ng-confirm='Are you sure you want to delete this request?'> <img src='support/images/delete.jpg' title='Delete' class='cursorP'/> </a> </td></tr>";
			});
		}	
		else 
		{
			advTable += "<tr> <td colspan='5'>No Records Found.</td></tr>";
		}
		advTable += "</tbody></table>";
		sel.append(self.compile(advTable)(self.scope));
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

AdminSubscriptionController.prototype.saveFPType= function(fpSubscription) {
	var self = this;
	this.util.block("Saving details");
	var FPTypeJson = {
			 "advType" : fpSubscription.AdvType,
			 "country" : fpSubscription.Country,
			 "mediaWeb" :  fpSubscription.mediaWeb,
			 "mediaMobile":fpSubscription.mediaMobile,
			 "SubRate" :  fpSubscription.SubRate,
			 "pStartDate" :  fpSubscription.pStartDate,
			 "pEndDate" :  fpSubscription.pEndDate,
			 "FileTypes" :  fpSubscription.FileTypes,
			 "FileSize" :  fpSubscription.FileSize,
			 "Dimension" : fpSubscription.Dimension,
			 "SubDeadline" :fpSubscription.SubDeadline,
			 "ItemSubmit" :fpSubscription.ItemSubmit,
			 "ActiveFlag" :fpSubscription.ActiveFlag,
			 "DiscountPolicy":fpSubscription.DiscountPolicy,
			 "discountPercentage" :fpSubscription.discountPercentage,
			 "comments" :fpSubscription.comments
	};
	this.http({
		method : 'POST',
		url : 'support/contents/admin/Subscription/save_Details.json',
		params : {
			'FPTypeJson' : FPTypeJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Advertisement Info", data.reason, "success");
			self.scope.fpSubscription.lastUpdatedDate = data.lastUpdatedDate;
		} else {
			self.util.notify("Advertisement Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};


AdminSubscriptionController.prototype.fetchFPSubscription= function() {
	var self = this;
	this.util.block("Retrieving Details");	
	this.http({
		method : 'POST',
		url : 'support/contents/admin/Subscription/AdvSub.json',
		params : {
				},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		var sel = $("tbody[id=listFPSub]");
		sel.empty();
		var advTable = "";
		if (data!=[]) {
			$.each(data, function(index){
				advTable += "<tr> <td>" + this.Name + "</td> <td>"  + this.ContactName + "</td> <td>" + this.Status  + "</td>" +
				 "<td><a href='#' ng-click='aSubscription.deleteDetails(4,"+ this.id +")' ng-confirm='Are you sure you want to delete this request?'> <img src='support/images/delete.jpg' title='Delete' class='cursorP'/> </a> </td></tr>";
			});
		}	
		else 
		{
			advTable += "<tr> <td colspan='4'>No Records Found.</td></tr>";
		}
		advTable += "</tbody></table>";
		sel.append(self.compile(advTable)(self.scope));
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};
AdminSubscriptionController.prototype.saveFPSubscription = function(aSubscription) {
	var self = this;
	this.util.block("Save details");
	var FPSubJson = {
			"fpSubAgent":aSubscription.fpSubAgent,
			"fpSubStartDate":aSubscription.fpSubStartDate,
			"fpSubEndDate":aSubscription.fpSubEndDate,
			"fpSubType":aSubscription.fpSubType,
			"fpSubDuration":aSubscription.fpSubDuration,
			"fpSubCountry":aSubscription.fpSubCountry,
			"fpSubPaymentMode":aSubscription.fpSubPaymentMode,
			"fpSubPaymentTerms":aSubscription.fpSubPaymentTerms,
			"fpSubPayReceipt":aSubscription.fpSubPayReceipt,
			"fpSubCurrency":aSubscription.fpSubCurrency,
			"fpSubAmountReceived":aSubscription.advSubAmountReceived,
			"fpSubActiveFlag":aSubscription.fpSubActiveFlag,
			"fpSubComments":aSubscription.fpSubComments
	};
	this.http({
		method : 'POST',
		url : 'support/contents/admin/Subscription/save_Details.json',
		params : {
			'FPSubJson' : FPSubJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Advertisement Info", data.reason, "success");
		} else {
			self.util.notify("Advertisement Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};


AdminSubscriptionController.prototype.saveAdvRegistration= function(aSubscription) {
	var self = this;
	this.util.block("Save details");
	var AdvRegJson = {
				 "advRegName" :aSubscription.advRegName,
				 "advRegNumber":aSubscription.advRegNumber,
				 "advRegServiceCoverage":aSubscription.advRegServiceCoverage,
				 "advRegServiceCategory":aSubscription.advRegServiceCategory,
				 "advRegDomains":aSubscription.advRegDomains,
				 "advRegBlock":aSubscription.advRegBlock,
				 "advRegBuilding":aSubscription.advRegBuilding,
				 "advRegStreet":aSubscription.advRegStreet,
				 "advRegpostalCode":aSubscription.advRegpostalCode,
				 "advRegCity":aSubscription.advRegCity,
				 "advRegState":aSubscription.advRegState,
				 "advRegCountry":aSubscription.advRegCountry,
				 "advRegMobile":aSubscription.advRegMobile,
				"advRegLandline":aSubscription.advRegLandline,
				"advRegEmail":aSubscription.advRegEmail,
				"advRegprimaryFirstname":aSubscription.advRegprimaryFirstname,
				"advRegprimaryLastname":aSubscription.advRegprimaryLastname,
				"advRegContactMobile":aSubscription.advRegContactMobile,
				"advRegidPosition":aSubscription.advRegidPosition,
				"advRegIDproof":aSubscription.advRegIDproof,
				"advRegID":aSubscription.advRegID,
				"advRegComments":aSubscription.advRegComments
	};
	this.http({
		method : 'POST',
		url : 'support/contents/admin/Subscription/save_Details.json',
		params : {
			'AdvRegJson' : AdvRegJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Advertisement Info", data.reason, "success");
		} else {
			self.util.notify("Advertisement Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

AdminSubscriptionController.prototype.getGeoLocation = function(aSubscription) {
	var self = this;
	self.formattedAddress = "";
	self.http({
						method : 'POST',
						type : 'jsonp',
						url : 'http://maps.google.com/maps/api/geocode/json?components=postal_code:'
								+ aSubscription.advRegpostalCode + '&sensor=false',
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
									aSubscription.advRegCity = addressSplit[0];
									aSubscription.advRegState = addressSplit[1].replace(/[0-9]/g, '');
									aSubscription.advRegCountry = addressSplit[2];
								}
							}
						}
					}).error(function(data, status, headers, config) {
			});
	self.util.unblock();
};

AdminSubscriptionController.prototype.deleteDetails = function(group,Id){
	var self = this;
	var retVal = confirm("Are you sure you want to delete this request?");
	if (retVal == true) {
		this.util.block("Deleting Details");
        this.http({
    		method : 'POST',
    		url : 'support/contents/admin/Subscription/delete_Details.json',
    		params : {
    			"group":group,
    			"advId":Id
    		},
    		headers : {
    			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
    		}
        }).success(function(data, status, headers, config) {
        	if (data.status == "Success") {
    			self.util.notify("Advertisement Info", data.reason, "success");
    		} else {
    			self.util.notify("Advertisement Failure", data.reason, "failure");
    		}
    		self.util.unblock();
    	}).error(function(data, status, headers, config) {
    		self.util.unblock();
    	});
    } 
};

AdminSubscriptionController.prototype.discountCalculation = function(advType){
	var self = this;
	var AmountEntered = this.advSubAmountReceived;
	var discountAmount = 0;
		 this.http({
	    		method : 'POST',
	    		url : 'support/contents/admin/Subscription/discountDetails.json',
	    		params : {
	    			"advType":advType
	    		},
	    		headers : {
	    			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
	    		}
	        }).success(function(data, status, headers, config) {
	        	if (data.status == "Success") {
	        		discountAmount = AmountEntered * data.discountPercentage/100;
	        		if(discountAmount>0){
	        			self.advSubAmountReceived = AmountEntered - discountAmount;
	        			self.shownDiscount = true;
	        		}
	    		} else {
	    			self.util.notify("Advertisement Failure", data.reason, "failure");
	    		}
	    		self.util.unblock();
	    	}).error(function(data, status, headers, config) {
	    		self.util.unblock();
	    	});
};



AdminSubscriptionController.prototype.reset = function(aSubscription){
this.scope.aSubscription = angular.copy(this.scope.master);
};

