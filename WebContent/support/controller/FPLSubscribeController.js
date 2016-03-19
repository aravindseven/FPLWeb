function FPLSubscribeController($scope, $http) {
	this.scope = $scope;
	this.scope.master = {};
	this.http = $http;
	this.util = new Util();
	this.formId=1;

	this.subtypes;
	this.typeId=0;
	this.typeName="";
	this.amount=0;
	this.duration=0;
	this.email="";
	this.validationDate="";
	this.startDate="";

	this.endDate="";

	this.subId="";
	this.idText="";
	this.fplId="";
	this.name="";
	this.mode="";
	this.token="";
	this.transRef="";
	this.action="";
	this.agreement="";
	this.paymentStatus="";
	this.isSubActive=false;
	this.paySuccess=false;
}


FPLSubscribeController.prototype.fetchSubscribeDetailsbyId= function(subId) {
	var self = this;
	
	this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'getFpSubscription.do?action=VIEW&subId='+subId,
		params : {},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			
			
			if(data.FinancialPlannerPV)
			{
				
				  self.fplId=data.FinancialPlannerPV.fplId;
				  
				if(data.FinancialPlannerPV.subscriptionListPVs)
				{
					self.name=data.FinancialPlannerPV.subscriptionListPVs.ContactName;
					self.subId=data.FinancialPlannerPV.subscriptionListPVs.id;
					self.idText=data.FinancialPlannerPV.subscriptionListPVs.idText;
					
					self.validationDate=data.FinancialPlannerPV.subscriptionListPVs.validationDate;
					self.typeId=data.FinancialPlannerPV.subscriptionListPVs.subMasterId;
					
					self.startDate=data.FinancialPlannerPV.subscriptionListPVs.startDate;
					self.endDate=data.FinancialPlannerPV.subscriptionListPVs.endDate;
					if(data.FinancialPlannerPV.subscriptionListPVs.subscriptionMasterPV)
					{
						self.duration=data.FinancialPlannerPV.subscriptionListPVs.subscriptionMasterPV.planFrequency;
						self.amount=data.FinancialPlannerPV.subscriptionListPVs.subscriptionMasterPV.rate;
					}
					
					self.token=data.FinancialPlannerPV.subscriptionListPVs.token;
					self.transRef=data.FinancialPlannerPV.subscriptionListPVs.transactionRef;
					self.mode=data.FinancialPlannerPV.subscriptionListPVs.mode;
					self.paymentStatus=data.FinancialPlannerPV.subscriptionListPVs.paymentStatus;
					
						self.updateActiveUI();
						self.isSubActive=true;						
						if(self.paymentStatus=='PR')
						{
							self.updateViewPayUI(true);
						}else
						{
							self.updateViewPayUI(false);	
						}
						
			
				}
			}
			
			if(data.SubscriptionMasterPVs){
				self.subtypes=data.SubscriptionMasterPVs;
			}
			if(data.UserLoginInfo)
			{
				self.email=data.UserLoginInfo.email;
			}
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
	
	
};



FPLSubscribeController.prototype.fetchSubscribeDetails = function() {
	var self = this;
	
	//this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'getFpSubscription.do',
		params : {},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			
			
			if(data.FinancialPlannerPV)
			{
				
				  self.fplId=data.FinancialPlannerPV.fplId;
				  
				if(data.FinancialPlannerPV.subscriptionListPVs)
				{
					self.name=data.FinancialPlannerPV.subscriptionListPVs.ContactName;
					self.subId=data.FinancialPlannerPV.subscriptionListPVs.id;
					self.idText=data.FinancialPlannerPV.subscriptionListPVs.idText;
					
					self.validationDate=data.FinancialPlannerPV.subscriptionListPVs.validationDate;
					self.typeId=data.FinancialPlannerPV.subscriptionListPVs.subMasterId;
					
					self.startDate=data.FinancialPlannerPV.subscriptionListPVs.startDate;
					self.endDate=data.FinancialPlannerPV.subscriptionListPVs.endDate;
					if(data.FinancialPlannerPV.subscriptionListPVs.subscriptionMasterPV)
					{
						self.duration=data.FinancialPlannerPV.subscriptionListPVs.subscriptionMasterPV.planFrequency;
						self.amount=data.FinancialPlannerPV.subscriptionListPVs.subscriptionMasterPV.rate;
					}
					
					self.token=data.FinancialPlannerPV.subscriptionListPVs.token;
					self.transRef=data.FinancialPlannerPV.subscriptionListPVs.transactionRef;
					self.mode=data.FinancialPlannerPV.subscriptionListPVs.mode;
					self.paymentStatus=data.FinancialPlannerPV.subscriptionListPVs.paymentStatus;
					
					if(self.paymentStatus=='PR' ||  self.paymentStatus=='CI' || self.paymentStatus=='CC')
					{
						self.isSubActive=true;
						self.updateActiveUI();
					}
					//data.FinancialPlannerPV.subscriptionListPVs.transactionPayerId;

				}
			}
			
			if(data.SubscriptionMasterPVs){
				self.subtypes=data.SubscriptionMasterPVs;
			}
			if(data.UserLoginInfo)
			{
				self.email=data.UserLoginInfo.email;
			}
		}
		if(self.paySuccess)
		{
			self.loadSecond(self);
		}	
	
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
	
	
};


FPLSubscribeController.prototype.populatePlanDetails = function(fplSub) 
{
	$.each(fplSub.subtypes, function(index){
		
		if(fplSub.typeId==this.id)
			{
				fplSub.amount=this.rate;
				fplSub.duration=this.planFrequency;
				fplSub.startDate=this.startDate;
				fplSub.endDate=this.endDate;	
				fplSub.typeName=this.type;
			}
	});
};

FPLSubscribeController.prototype.formatDate = function(date){
    var dateOut = new Date(date);
    return dateOut;
};


FPLSubscribeController.prototype.payNow = function(fplSub){
	var self = this;
	alert(fplSub.paymentStatus);
	
	if(!fplSub.agreement && (fplSub.action=='GEN_TOKEN' || fplSub.action=='SAVE_CHECK'))
	{
		  document.getElementById('agree').style.border = '1px solid red';
		  document.getElementById('agree_error').style.display="block";

	}else
	{
		
		var financialPlannerSubscribeJson = {
				 "id" :  fplSub.subId,
				 "fplId" :  fplSub.fplId,
				 "subMasterId" :  fplSub.typeId,
				 "token" :  fplSub.token,
				 "mode"  : fplSub.mode,
				 "ContactName":fplSub.name,
		};
		var fpSubscribeMasterJson = {
				 "type" : fplSub.typeName,
				 "rate" : fplSub.amount,
		};
		
			this.util.block("Modifying details");
			this.http({
				method : 'POST',
				url : 'payFpSubsription.do?ACTION='+fplSub.action,
				//url : 'support/contents/save_customer_profile.json',
				params : {
					'financialPlannerSubscribeJson' : financialPlannerSubscribeJson,
					'fpSubscribeMasterJson' : fpSubscribeMasterJson,
					'paymentStatus':fplSub.paymentStatus,
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
				}
			}).success(function(data, status, headers, config) {
				
				self.util.unblock();
				if (data.TOKEN.token && fplSub.action=='GEN_TOKEN') {
					
					if(data.TOKEN.mode=='PAYPAL')
					{
						self.token=data.TOKEN.token;
						window.location.href="https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="+data.TOKEN.token;
					}else if(data.TOKEN.mode=='EWAY')
					{
						self.token=data.TOKEN.token;
						window.location.href=data.TOKEN.paymentUrl;
					}
					
					
				}else if (data.TOKEN.id && fplSub.action=='SAVE_CHECK') {
					
					self.subId=data.TOKEN.id;
					self.printProforma(self);
					
				}else
				{
					if(data.TOKEN.paymentStatus=="PR")
					{
						document.getElementById('inActiveBox').className="inner";
						document.getElementById('activeBox').className="innerActive";
					}else
					{
						document.getElementById('inActiveBox').className="innerActive";
						document.getElementById('activeBox').className="inner";
						
					}
					if(data.MSG)
					{
						alert(data.MSG);
					}	
				}
				
			}).error(function(data, status, headers, config) {
				self.util.unblock();
			});

	}	
};


FPLSubscribeController.prototype.loadSecond = function(fplSub){
	
	fplSub.formId=2;
	fplSub.load(fplSub);
}
FPLSubscribeController.prototype.load = function(fplSub){

	
	
  if(fplSub.formId==1)
   {
	  document.getElementById('form1').style.display="block";
	  document.getElementById('form2').style.display="none";
	  
   }
  else if(fplSub.formId==2)
   {
	  
	  if(fplSub.typeId>0)
	  {
    	document.getElementById('form1').style.display="none";
	    document.getElementById('form2').style.display="block";
	    
	    if(fplSub.mode=='CHEQUE')
		{
		  fplSub.displayCheque(fplSub);
		}
	    
	    if(fplSub.paymentStatus=='PR')
		{
	    	document.getElementById('inActiveBox').className="inner";
	    	document.getElementById('activeBox').className="innerActive";
	    	fplSub.updateActivePayUI();
	    	
		}else if(fplSub.paymentStatus=='CI'  || fplSub.paymentStatus=='CC')
		{
			
	    	fplSub.updateActiveChqUI();
	    			
		}
	    
	  }
	  else
		{
		  document.getElementById('fplType').style.border = '1px solid red';
		  document.getElementById('fplType_error').style.display="block";
		  
		}
   }
}




FPLSubscribeController.prototype.displayCheque = function(fplSub){
	  fplSub.mode='CHEQUE';
	  document.getElementById('instPay').style.display="none";
	  document.getElementById('chequePay').style.display="block";
	  document.getElementById('payButton').style.display="none";
	  document.getElementById('chequeButton').style.display="block";
	  document.getElementById('chqdiv').className="t-s-ins t-show";
	  document.getElementById('instdiv').className="t-s-chq";
}


FPLSubscribeController.prototype.displayInstant = function(fplSub){
	
	  if(!fplSub.mode)
	  {
		  fplSub.mode='PAYPAL';
	  }
	  document.getElementById('instPay').style.display="block";
	  document.getElementById('chequePay').style.display="none";
	  document.getElementById('payButton').style.display="block";
	  document.getElementById('chequeButton').style.display="now";
	  document.getElementById('instdiv').className="t-s-ins t-show";
	  document.getElementById('chqdiv').className="t-s-chq";
}

FPLSubscribeController.prototype.generateProForma = function(fplSub){
	
	if(!fplSub.agreement && !fplSub.paymentStatus=='PR')
	{
		  document.getElementById('agree').style.border = '1px solid red';
		  document.getElementById('agree_error').style.display="block";
	}else
	{
		if(fplSub.paymentStatus=='PR')
		{
			fplSub.action='PRINT_INVOICE';	
		}else
		{
			fplSub.action='SAVE_CHECK';
		}
		
		fplSub.payNow(fplSub);
	
	}
}



FPLSubscribeController.prototype.printProforma = function(fplSub){
	var myWindow = window.open("downloadProInvoice.html?ACTION=PRINT_PROFORMA&fplSubId="+fplSub.subId, "", "width=800, height=800");
	}

FPLSubscribeController.prototype.generateInvoice = function(fplSub){
   var myWindow = window.open("downloadProInvoice.html?ACTION=PRINT_INVOICE&fplSubId="+fplSub.subId, "", "width=800, height=800");
}



FPLSubscribeController.prototype.updateViewPayUI = function(isActive){

	
	document.getElementById('payButton').style.display="none";
	document.getElementById('chequeButton').style.display="none";
	if(isActive)
	{
		document.getElementById('invoiceButton').style.display="block";
	}else
	{
		document.getElementById('invoiceButton').style.display="none";
		document.getElementById('backButton').style.left="150px";
		document.getElementById('cancelButton').style.left="350px";
	}	

	document.getElementById('AGREESEC').style.display="none";
	document.getElementById('PAYPAL').disabled=true;
	document.getElementById('EWAY').disabled=true;
	document.getElementById('MASTER').disabled=true;
}


FPLSubscribeController.prototype.updateActivePayUI = function(){

	
	document.getElementById('payButton').style.display="none";
	document.getElementById('chequeButton').style.display="none";
	document.getElementById('invoiceButton').style.display="block";

	document.getElementById('AGREESEC').style.display="none";
	document.getElementById('PAYPAL').disabled=true;
	document.getElementById('EWAY').disabled=true;
	document.getElementById('MASTER').disabled=true;
}

FPLSubscribeController.prototype.updateActiveChqUI = function(){

	
	document.getElementById('payButton').style.display="none";
	document.getElementById('chequeButton').style.display="none";
	document.getElementById('invoiceButton').style.display="none";
	document.getElementById('proformaButton').style.display="block";
	

	document.getElementById('AGREESEC').style.display="none";
	document.getElementById('PAYPAL').disabled=true;
	document.getElementById('EWAY').disabled=true;
	document.getElementById('MASTER').disabled=true;
}

FPLSubscribeController.prototype.updateActiveUI = function(){

	document.getElementById('fplType').disabled=true;
	document.getElementById('FPLNAME').disabled=true;
	
	document.getElementById('PAY').value="View Details";
	document.getElementById('SAE').style.display="none";
	
	document.getElementById('PAY').style.left="225px";
	document.getElementById('CANCEL').style.left="260px";
	//CANCEL
}

FPLSubscribeController.prototype.viewSubscriptionCancelAction = function(src,search1,search2){

	if(src=="ADMIN")
	{
		window.location.href='listSubscribeDetails.jsp?SEARCH1='+search1+"&SEARCH2="+search2;
	}else
	{
		window.location.href='listFPLSubscribeDetails.jsp';
	}

}