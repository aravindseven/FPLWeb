function ErecordsController($scope, $http, $compile){
	this.scope = $scope;
	this.http = $http;
	this.compile = $compile;
	this.util = new Util();
	
	$scope.policyREId='-1'
	$scope.policyREDate = -1;
	$scope.policyFname = '';  
	$scope.policyREQNUMBER='';
	$scope.policyNUMBER = '';
	$scope.policyTYPE = '';  
	$scope.policyINSURANCECOMPANY = '';
	$scope.policyDESC='';
	$scope.policyCUList = [];
	$scope.policyTYPEList = []; 
	$scope.policyDATE = '';
	$scope.policyDURATION= '';
	$scope.policyAMOUNT= '';
	$scope.policyPREMAMOUNT='';
	$scope.policyRENEWAL_DATE= '';
	$scope.policyFPname='';
	
	
	$scope.policy_nomineefname='';
	$scope.policy_nomineelname='';
	$scope.policy_nomineeIDprof='';
	$scope.policy_nomineeID='';
	$scope.policy_nomineemobile='';
	$scope.policy_nomineerelation='';
	
	
	$scope.policyCONFIDENTIALITY_PRE='';
	$scope.policyAdvancealert='';
	$scope.policyAlertsch='';
	$scope.policySchedule_Days='';
	$scope.policyComment='';
	
	$scope.erecordsform={};
	
	$scope.policyList=[];
	
	/*this.policyId   = -1;   
	
	$scope.erecordsform.policyCUID = -1;
	this.policyFPID = -1;
	this.policyCOID = -1;
	this.policyDOID = -1;
	this.policySTATUS = '';
	this.policyFREQUENCY= '';
	this.policyADVANCE_ALERT= '';
	this.policyALERT_TYPE= '';
	this.policyCONTACT_NAME= '';
	this.policyCONTACT_NUMBER= '';
	this.policyRELATION= '';
	this.policyID_PROOF= '';
	this.policyNOTE= '';
	this.policyCREATED_DATE= '';*/
}

ErecordsController.prototype.initnewpolicy=function ()
{
		var self = this;
	this.util.block("Retrieving Policy ");
	this.http({
		method : 'POST',
		url :  'initnewpolicy.do',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		self.scope.erecordsform.policyREId = data.uniqueID;
		self.scope.erecordsform.policyREDate = data.Recorddate; 
		self.scope.erecordsform.policyCUList=data.companylist;
		self.scope.erecordsform.policyTYPEList=data.policytype; 
		self.scope.erecordsform.policyFname=data.fname;
		console.log(self.scope.erecordsform.policyREId);
		/*$("#ViewPolicyModal").modal("show");
		var sel = $("#viewPolicyModalBody");
		$("#viewbuttondiv").remove();
		sel.append('<div class="clear registerBtnGroup pull-down" id="viewbuttondiv"><button data-dismiss="modal" class="btn btn-primary btn-medium" type="button">OK</button></div>');
		
		self.util.unblock();*/
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
	
};
ErecordsController.prototype.loadpolicylist=function ()
{
	var self = this;	
	this.util.block("Retrieving Policy details");
	this.http({
		method : 'POST',
		url :  'initPolicy2.do',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		self.scope.policyList=data.policydetail;
		console.log(self.scope.policyList);
		
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
	
};


ErecordsController.prototype.viewPolicy=function (policyNumber)
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


ErecordsController.prototype.CreatePolicy=function (upolicy)
{
	var self = this;
	//alert("finalsave called");
	console.log(upolicy);
	//upolicy.formId=4;
    /*if((upolicy.userType==1 && upolicy.validateInd(upolicy)) || (upolicy.userType==2 && upolicy.validateIndOrg(upolicy))  )
	{
    	*/
		var formJson = {
	
    
		};
		
		
			this.http({
				method : 'POST',
				url : 'PolicyCreation2.do',
				params : {
					"policyREId": upolicy.policyREId,
				    "policyREDate":upolicy.policyREDate,
					"policyFname":upolicy.policyFname, 
					"policyREQNUMBER":upolicy.policyREQNUMBER,
					"policyNUMBER":upolicy.policyNUMBER,
					"policyTYPE":upolicy.policyTYPE, 
					"policyINSURANCECOMPANY":upolicy.policyINSURANCECOMPANY,
					"policyDESC":upolicy.policyDESC,
					//"policyCUList":upolicy.policyCUList,
					//"policyTYPEList":upolicy.policyTYPEList, 
					"policyDATE":upolicy.policyDATE,
					"policyDURATION":upolicy.policyDURATION,
					"policyAMOUNT":upolicy.policyAMOUNT,
					"policyPREMAMOUNT":upolicy.policyPREMAMOUNT,
					"policyRENEWAL_DATE":upolicy.policyRENEWAL_DATE,
					"policyFPname":upolicy.policyFPname,
					
					 "policy_nomineefname"	: upolicy.policy_nomineefname,
				     "policy_nomineelname" : upolicy.policy_nomineelname,
				     "policy_nomineeIDprof" : upolicy.policy_nomineeIDprof,
				     "policy_nomineeID" : upolicy.policy_nomineeID,
				     "policy_nomineemobile" : upolicy.policy_nomineemobile,
				     "policy_nomineerelation" : upolicy.policy_nomineerelation,		
				     
				     
				     "policyCONFIDENTIALITY_PRE":	upolicy.policyCONFIDENTIALITY_PRE,
				     "policyAdvancealert":  upolicy.policyAdvancealert,
				     "policyAlertsch":   upolicy.policyAlertsch,
				     "policySchedule_Days":   upolicy.policySchedule_Days,
				     "policyComment" :  upolicy.policyComment
					
					
				},
			
				headers : {
					'Content-Type' : undefined
				}
			}).success(function(data, status, headers, config) {
				if (data.status == "success") {
					self.util.notify("Profile Info", data.reason, "success");
					
					window.location.href="erecord.jsp?type=list";
					//document.getElementById("successMSG").style.display="block";
					//document.getElementById("Content").innerHTML=data.reason;
				} else {
					self.util.notify("Profile Failure", data.reason, "failure");
					//document.getElementById("successMSG").style.display="block";
					//document.getElementById("Content").innerHTML=data.reason;
				}
				self.util.unblock();
			}).error(function(data, status, headers, config) {
				//document.getElementById("successMSG").style.display="block";
				//document.getElementById("Content").innerHTML=data.reason;
				//self.util.unblock();
			});
		//}	
			/*} else
	  {
		 // document.getElementById('fplType_error3').style.display="block";
		  
	  }	*/	

};

/*ErecordsController.prototype.loadInd = function(uProfile){
	console.log("load");
	
	var valid=true; //this.validateInd(uProfile);
	
	  if(uProfile.formId==1)
	   {
			  document.getElementById('form1').style.display="block";
			  document.getElementById('form2').style.display="none";
			  document.getElementById('form3').style.display="none";
	   }
	  else if(uProfile.formId==2)
	   {
		  if(valid)
		  {
			  document.getElementById('form1').style.display="none";
			  document.getElementById('form2').style.display="block";
			  document.getElementById('form3').style.display="none";
		  }else
		  {
			  console.log("form");
			  document.getElementById('fplType_error1').style.display="block";
			 
		  }
	   }else if(uProfile.formId==3)
	   {
		  
		   if(valid)
		   {
			  document.getElementById('form1').style.display="none";
			  document.getElementById('form2').style.display="none";
			  document.getElementById('form3').style.display="block";
		   }else
		   {
				  document.getElementById('fplType_error2').style.display="block";
				  
		   }	  
	   }
};*/
/*ErecordsController.prototype.validateInd = function(uProfile){
	console.log("load");
	console.log(uProfile);
			var valid=true;
			var numericMobilePattern = /^\d{10}$/;
			var numericPostalPattern = /^\d{6}$/;
			document.getElementById('fplType_error1').style.display="none";
			document.getElementById('fplType_error2').style.display="none";
			document.getElementById('fplType_error3').style.display="none";	
			
			document.getElementById('cname').style.border = '1px solid #e1e1e1';
			document.getElementById('policynumber').style.border = '1px solid #e1e1e1';
			document.getElementById('insurancecompany').style.border = '1px solid #e1e1e1';
			document.getElementById('policytype').style.border = '1px solid #e1e1e1';
			document.getElementById('policydesc').style.border = '1px solid #e1e1e1';
			document.getElementById('policyamount').style.border = '1px solid #e1e1e1';
			document.getElementById('policyduration').style.border = '1px solid #e1e1e1';
			
			document.getElementById('secMobile').style.border = '1px solid #e1e1e1';
			document.getElementById('primaryLandline').style.border = '1px solid #e1e1e1';
			
			  
			
		    document.getElementById('fplType_error_Postal').style.display="none";
			document.getElementById('fplType_error_Mobile').style.display="none";
			document.getElementById('fplType_error_Landline').style.display="none";
			document.getElementById('fplType_error_SecMobile').style.display="none";

			if(uProfile.formId==2)
			   {
				  if(!uProfile.policyFname || uProfile.policyFname.length<=0 || uProfile.policyFname=='undefined')
				  {
					  document.getElementById('cname').style.border = '1px solid red';
					   valid=false; 
				  }
				  console.log("insideform")
				  console.log(uProfile.policyNUMBER)
				  if(!uProfile.policyNUMBER || uProfile.policyNUMBER.length<=0 || uProfile.policyNUMBER=='undefined')
				  {
					  console.log("insidepnumber")
					  document.getElementById('policynumber').style.border = '1px solid red';
					   valid=false; 
				  }
				  if(!uProfile.policyINSURANCECOMPANY || uProfile.policyINSURANCECOMPANY.length<=0 || uProfile.policyINSURANCECOMPANY=='undefined')
				  {
					  document.getElementById('insurancecompany').style.border = '1px solid red';
					   valid=false; 
				  }
				  if(!uProfile.policyTYPE || uProfile.policyTYPE.length<=0 || uProfile.policyTYPE=='undefined')
				  {
					  document.getElementById('policytype').style.border = '1px solid red';
					   valid=false; 
				  }
				  if(!uProfile.policyDESC || uProfile.policyDESC.length<=0)
				  {
					  document.getElementById('policydesc').style.border = '1px solid red';
					   valid=false; 
				  }
				  if(!uProfile.policyAMOUNT || uProfile.policyAMOUNT.length<=0)
				  {
					  document.getElementById('policyamount').style.border = '1px solid red';
					   valid=false; 
				  }
				  if(!uProfile.policyDURATION || uProfile.policyDURATION.length<=0)
				  {
					  document.getElementById('policyduration').style.border = '1px solid red';
					   valid=false; 
				  }
			 }
				else if(uProfile.formId==3)
			   {
				   if(!uProfile.postalCode || uProfile.postalCode.length<=0)
					{
						  document.getElementById('postalCode').style.border = '1px solid red';
						   valid=false; 
					} else if(!numericPostalPattern.test(uProfile.postalCode))
					{
						
						 document.getElementById('postalCode').style.border = '1px solid red';
						 document.getElementById('fplType_error_Postal').style.display="block";
						 valid=false;  
					}
				   
				   if(!uProfile.state || uProfile.state.length<=0)
					{
						  document.getElementById('state').style.border = '1px solid red';
						   valid=false; 
					}
				   
				   if(!uProfile.primarymobile || uProfile.primarymobile.length<=0)
					{
						  document.getElementById('primarymobile').style.border = '1px solid red';
						   valid=false; 
					}else if(!numericMobilePattern.test(uProfile.primarymobile))
					{
						 document.getElementById('primarymobile').style.border = '1px solid red';
						 document.getElementById('fplType_error_Mobile').style.display="block";	   
						 valid=false;
					}
				   
				   if(uProfile.landLine && !numericMobilePattern.test(uProfile.landLine))
					{
						  document.getElementById('primaryLandline').style.border = '1px solid red';
						  document.getElementById('fplType_error_Landline').style.display="block";	 
		       		  valid=false; 
					}
			   }else
			   {
				
				   if(!uProfile.confPreference || uProfile.confPreference<0)
					{
						  document.getElementById('Pref').style.border = '1px solid red';
						   valid=false; 
					}
				   
				   if(uProfile.secondarymobile && !numericMobilePattern.test(uProfile.secondarymobile))
					{
						  document.getElementById('secMobile').style.border = '1px solid red';
						  document.getElementById('fplType_error_SecMobile').style.display="block";	 
		      		  valid=false; 
					}
			   }
				
		  return valid;
		};
*/
ErecordsController.prototype.acceptPolicy=function (policyNumber)
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

ErecordsController.prototype.rejectPolicy=function (policyNumber)
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


ErecordsController.prototype.expressRenewalPolicy=function (policyNumber)
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
ErecordsController.prototype.renewalPolicy=function (reqid)
{
	window.location.href = "initNewRequest.do?reqId=" + reqid;
};



