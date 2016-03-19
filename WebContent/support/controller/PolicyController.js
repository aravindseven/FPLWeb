function PolicyController($scope, $http, $compile){
	this.scope = $scope;
	this.http = $http;
	this.compile = $compile;
	this.util = new Util();
	this.policyId   = -1;   
	this.policyREId = -1;      
	this.policyCUID = -1;
	this.policyFPID = -1;
	this.policyCOID = -1;
	this.policyDOID = -1;
	this.policySTATUS = '';
	this.policyNUMBER = '';
	this.policyDATE = '';
	this.policyDURATION= '';
	this.policyFREQUENCY= '';
	this.policyAMOUNT= '';
	this.policyRENEWAL_DATE= '';
	this.policyADVANCE_ALERT= '';
	this.policyALERT_TYPE= '';
	this.policyCONTACT_NAME= '';
	this.policyCONTACT_NUMBER= '';
	this.policyRELATION= '';
	this.policyID_PROOF= '';
	this.policyNOTE= '';
	this.policyCREATED_DATE= '';
}



PolicyController.prototype.viewPolicy=function (policyNumber)
{
	var self = this;	
	alert(policyNumber);
	this.util.block("Retrieving Policy details");
	this.http({
		method : 'POST',
		url :  'support/contents/policy_view.json',
		params : {
			"policyNumber":policyNumber
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
			self.policyId   = data.PO_ID;  
	  		self.policyREId = data.PO_RE_ID;      
	  		self.policyCUID = data.PO_CU_ID;
	  		self.policyFPID = data.PO_FP_ID;
	  		self.policyCOID = data.PO_CO_ID;
			self.policyDOID = data.PO_DO_ID;
			self.policySTATUS = data.PO_STATUS;
			self.policyNUMBER = data.PO_NUMBER;
			self.policyDATE = data.PO_DATE;
			self.policyDURATION= data.PO_DURATION;
			self.policyFREQUENCY= data.PO_FREQUENCY;
			self.policyAMOUNT= data.PO_AMOUNT;
			self.policyRENEWAL_DATE= data.PO_RENEWAL_DATE;
			self.policyADVANCE_ALERT= data.PO_ADVANCE_ALERT;
			self.policyALERT_TYPE= data.PO_ALERT_TYPE;
			self.policyCONTACT_NAME= data.PO_CONTACT_NAME;
			self.policyCONTACT_NUMBER= data.PO_CONTACT_NUMBER;
			self.policyRELATION= data.PO_RELATION;
			self.policyID_PROOF= data.PO_ID_PROOF;
			self.policyNOTE= data.PO_NOTE;
			self.policyCREATED_DATE= data.PO_CREATED_DATE;
				
		$("#ViewPolicyModal").modal("show");
		var sel = $("#viewPolicyModalBody");
		$("#viewbuttondiv").remove();
		sel.append('<div class="clear registerBtnGroup pull-down" id="viewbuttondiv"><button data-dismiss="modal" class="btn btn-primary btn-medium" type="button">OK</button></div>');
		
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

PolicyController.prototype.acceptPolicy=function (policyNumber)
{
	var self = this;
	alert(policyNumber);
	this.util.block("Accept Policy");
	this.http({
		method : 'POST',
		url : 'policyAcceptReject.do',
		//url :  'support/contents/policy_accept_reject_success.json',
		params : {
			"policyId":policyNumber,
			"status":"Accept"
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Policy Info", data.reason, "success");
		} else {
			self.util.notify("Policy Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		alert("Failure");
		self.util.unblock();
	}); 
};	

PolicyController.prototype.rejectPolicy=function (policyNumber)
{
	var self = this;	
	this.util.block("Reject Policy");
	this.http({
		method : 'POST',
		url : 'policyAcceptReject.do',
		//url :  'support/contents/policy_accept_reject_failure.json',
		params : {
			"policyId":policyNumber,
			"status":"Reject"
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Policy Info", data.reason, "success");
		} else {
			self.util.notify("Policy Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};	


PolicyController.prototype.expressRenewalPolicy=function (policyNumber)
{
	var self = this;	
	this.util.block("Renewal Policy");
	this.http({
		method : 'POST',
		//url : 'policyAcceptReject.do',
		url :  'support/contents/policy_accept_reject_success.json',
		params : {
			"policyId":policyNumber
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "Success") {
			self.util.notify("Policy Info", data.reason, "success");
		} else {
			self.util.notify("Policy Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};
PolicyController.prototype.renewalPolicy=function (reqid)
{
	window.location.href = "initNewRequest.do?reqId=" + reqid;
};
