function UserProfileController($scope, $http) {
	this.scope = $scope;
	this.scope.master = {};
	this.http = $http;
	this.util = new Util();
	this.showProfileEdit = true;
	this.showProfileSave = false;
	this.imageURI=null;
    $scope.imageCropResult = null;
    $scope.showImageCropper = false;

    $scope.$watch('imageCropResult', function(newVal) {
      if (newVal) {
		
		
		
        console.log('imageCropResult', newVal);
        
      }

    });


    this.customerId="";
    
    this.customerIdText="";
    this.customerCreationDate="";
    
	this.primaryfirstName1 ="";
	this.primarylastName1 ="";
	this.alternativeemail ="";
	this.gender ="";
	this.dob ="";
	this.idProof ="";
	this.proofNum ="";
	this.confPreference="";
	this.block ="";
	this.buildingName ="";
	this.street ="";
	this.city ="";
	this.postalCode ="";
	this.state ="";
	this.country ="";
	this.primarymobile ="";
	this.landLine ="";
	this.email ="";
	this.secondaryfirstName ="";
	this.secondarylastName ="";
	this.secondarymobile ="";
	this.secondaryemail="";
	this.secondaryrelation ="";
	this.secondaryidProof ="";
	this.secondaryproofNum ="";
	this.secondaryblock ="";
	this.secondarybuildingName ="";
	this.secondarystreet ="";
	this.secondarycity ="";
	this.secondarypostalCode ="";
	this.secondarystate ="";
	this.secondarycountry ="";
	
	this.countForEnteredValues = 0;
	this.percentageCompleted = 0;
	this.formId=0;
	this.userType=0;
    this.latitude=0;
    this.longitude=0;

}

UserProfileController.prototype.fetchMyFp = function() {
	var self = this;
	//this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'getMyFplOrCustomer.do',
		//url : 'support/contents/my_fp_customer_success.json',
		params : {},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data.Status == "Success")
		{
				var sel = $("tbody[id=MyFPResult]");
				sel.empty();
				$.each(data.Result, function(index){
					if(this.userType == "FP_INDIVIDUAL" || this.userType == "FP_CORPORATE" || this.userType == "FP_ADMIN")
					{
						var strtbl ='<tr> <td>' + this.firstName +'  '+ this.lastName +  '</td>';
						if(this.online == true)
							strtbl += '<td>Online' + '</td>';
						else
							strtbl += '<td>Offline' + '</td>';
						strtbl += "<td><a href='#'><img src='support/images/chat.jpg'/></a>    " +
								"<a href='#'><img src='support/images/mail.jpg'/></a></td> </tr>" ;
						sel.append(strtbl);
					}
				});
		}
	   else
	   {
	   			var sel = $("tbody[id=MyFPResult]");
				sel.empty();
				sel.append('<tr> <td colspan= "3">No FP is assigned</td></tr>'); 
	   }
		//self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

UserProfileController.prototype.fetchMyCustomer = function() {
	var self = this;
	//this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'getMyFplOrCustomer.do',
		//url : 'support/contents/my_fp_customer_success.json',
		params : {},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data.Status == "Success")
		{
				var sel = $("tbody[id=MyCustomerResult]");
				sel.empty();
				$.each(data.Result, function(index){
					if(this.userType == "CUST_INDIVIDUAL" || this.userType == "CUST_CORPORATE")
					{
						var strtbl ='<tr> <td>' + this.firstName +'  '+ this.lastName +  '</td>';
						if(this.online == true)
							strtbl += '<td>Online' + '</td>';
						else
							strtbl += '<td>Offline' + '</td>';
						strtbl += "<td><a href='#'><img src='support/images/chat.jpg'/></a>    " +
								"<a href='#'><img src='support/images/mail.jpg'/></a></td> </tr>" ;
						sel.append(strtbl);
					}
				});
		}
	   else
	   {
	   			var sel = $("tbody[id=MyCustomerResult]");
				sel.empty();
				sel.append('<tr> <td colspan= "3">No Customer is assigned</td></tr>'); 
	   }
		//self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};


UserProfileController.prototype.fetchCustomerDetails = function() {
	var self = this;
	
	//this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'redirectAjaxProfileCreation.do',
		//url : 'support/contents/customer_profile.json',
		params : {},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.CustomerPV){
				self.customerId=data.CustomerPV.customerId;
				self.customerIdText=data.CustomerPV.customerIdText;			
				self.customerCreationDate=data.CustomerPV.registrationDate;
				self.confPreference=data.CustomerPV.confPreference;
				
				
				if(data.CustomerPV.primaryPersonalData){
					
					self.primaryfirstName1 = data.CustomerPV.primaryPersonalData.firstName;
					self.primarylastName1 = data.CustomerPV.primaryPersonalData.lastName;
					self.gender = data.CustomerPV.primaryPersonalData.gender;
					self.dob = new Date(data.CustomerPV.primaryPersonalData.dob);
					self.idProof =data.CustomerPV.primaryPersonalData.idProof;
					self.proofNum =data.CustomerPV.primaryPersonalData.proofNum;
					self.primarymobile =data.CustomerPV.primaryPersonalData.mobile;
					self.landLine =data.CustomerPV.primaryPersonalData.landLine;
					self.email = data.CustomerPV.primaryPersonalData.email;
					
				} 
				if(data.CustomerPV.addressPV) {
					self.block = data.CustomerPV.addressPV.block;
					self.buildingName = data.CustomerPV.addressPV.buildingName;
					self.street = data.CustomerPV.addressPV.street;
					self.city = data.CustomerPV.addressPV.city;
					self.postalCode = data.CustomerPV.addressPV.postalCode;
					self.state = data.CustomerPV.addressPV.state;
					self.country= data.CustomerPV.addressPV.country;
					self.latitude=data.CustomerPV.addressPV.latitude;
					self.longitude=data.CustomerPV.addressPV.longitude;

				}
				if(data.CustomerPV.secondaryPersonalData) {
					self.secondaryfirstName = data.CustomerPV.secondaryPersonalData.firstName;
					self.secondarylastName =data.CustomerPV.secondaryPersonalData.lastName;
					self.secondarymobile =data.CustomerPV.secondaryPersonalData.mobile;
					self.secondaryrelation =data.CustomerPV.secondaryPersonalData.relation;
					self.secondaryidProof =data.CustomerPV.secondaryPersonalData.idProof;
					self.secondaryproofNum =data.CustomerPV.secondaryPersonalData.proofNum;
					self.secondaryemail=data.CustomerPV.secondaryPersonalData.email;
				}
				if(data.CustomerPV.permanentAddressPV) {
					self.secondaryblock =data.CustomerPV.permanentAddressPV.block;
					self.secondarybuildingName =data.CustomerPV.permanentAddressPV.buildingName;
					self.secondarystreet =data.CustomerPV.permanentAddressPV.street;
					self.secondarycity =data.CustomerPV.permanentAddressPV.city;
					self.secondarypostalCode =data.CustomerPV.permanentAddressPV.postalCode;
					self.secondarystate =data.CustomerPV.permanentAddressPV.state;
					self.secondarycountry =data.CustomerPV.permanentAddressPV.country;
				}
			}
			
		}
	
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
UserProfileController.prototype.SaveChanges = function(uProfile){
	var self = this;
	uProfile.formId=4;
    if((uProfile.userType==1 && uProfile.validateInd(uProfile)) || (uProfile.userType==2 && uProfile.validateIndOrg(uProfile))  )
	{
    	
    	var customerJson = {
				 "confPreference" : uProfile.confPreference
				
		};

		var primaryPersonalJson = {
				 "firstName" : uProfile.primaryfirstName1,
				 "lastName" : uProfile.primarylastName1,
				 "gender" :  uProfile.gender,
				 "dob" :  uProfile.dob,
				 "idProof" :  uProfile.idProof,
				 "mobile" :  uProfile.primarymobile,
				 "landLine" :  uProfile.landLine,
				 "email" :  uProfile.email,
				 "proofNum" : uProfile.proofNum,
				 "relation": uProfile.relation
		};
		var secondaryPersonalJson = {
				 "firstName" :  uProfile.secondaryfirstName,
				 "lastName" :  uProfile.secondarylastName,
				 "mobile" :  uProfile.secondarymobile,
				 "relation" :  uProfile.secondaryrelation,
				 "idProof" :   uProfile.secondaryidProof,
				 "email" :  uProfile.secondaryemail,
				 "proofNum" :  uProfile.secondaryproofNum
		};
		var addressJson = {
				 "block" :  uProfile.block,
				 "buildingName" :   uProfile.buildingName,
				 "street" :  uProfile.street,
				 "city" :   uProfile.city,
				 "postalCode" :  uProfile.postalCode,
				 "state" :  uProfile.state,
				 "country" :  uProfile.country,
				 "latitude" : uProfile.latitude,
				 "longitude" : uProfile.longitude
		};
		var permanentAddressJson = {
				 "block" :  uProfile.secondaryblock,
				 "buildingName" :  uProfile.secondarybuildingName,
				 "street" : uProfile.secondarystreet,
				 "city" : uProfile.secondarycity,
				 "postalCode" : uProfile.secondarypostalCode,
				 "state" : uProfile.secondarystate,
				 "country" :  uProfile.secondarycountry
		};
		var alternativeemailidJson={"alternativeemail" : uProfile.alternativeemail};
		
		
			this.util.block("Modifying details");
			this.http({
				method : 'POST',
				url : 'custAjaxProfileCreation.do',
				params : {
					'customerJson' :customerJson,
					'primaryPersonalJson' : primaryPersonalJson,
					'secondaryPersonalJson' : secondaryPersonalJson,
					'addressJson' : addressJson,
					'permanentAddressJson' : permanentAddressJson,
					'alternativeemailid' :uProfile.alternativeemail
				},
			
				headers : {
					'Content-Type' : undefined
				}
			}).success(function(data, status, headers, config) {
				if (data.status == "success") {
					self.util.notify("Profile Info", data.reason, "success");
					document.getElementById("successMSG").style.display="block";
					document.getElementById("Content").innerHTML=data.reason;
				} else {
					self.util.notify("Profile Failure", data.reason, "failure");
					document.getElementById("successMSG").style.display="block";
					document.getElementById("Content").innerHTML=data.reason;
				}
				self.util.unblock();
			}).error(function(data, status, headers, config) {
				document.getElementById("successMSG").style.display="block";
				document.getElementById("Content").innerHTML=data.reason;
				self.util.unblock();
			});
		//}	
	} else
	  {
		  document.getElementById('fplType_error3').style.display="block";
		  
	  }		
};

UserProfileController.prototype.reset = function(uProfile){
	 this.scope.uProfile = angular.copy(this.scope.master);
};

UserProfileController.prototype.fetchAddress = function(uProfile){
	
	var address=Convert_postCode_To_LatLng(uProfile);
	
}



UserProfileController.prototype.formatDate = function(date){
    var dateOut = new Date(date);
    return dateOut;
};



UserProfileController.prototype.validateInd = function(uProfile){
	
	var valid=true;
	var numericMobilePattern = /^\d{10}$/;
	var numericPostalPattern = /^\d{6}$/;
	document.getElementById('fplType_error1').style.display="none";
	document.getElementById('fplType_error2').style.display="none";
	document.getElementById('fplType_error3').style.display="none";	
	
	document.getElementById('primaryfirstname').style.border = '1px solid #e1e1e1';
	document.getElementById('primarylastname').style.border = '1px solid #e1e1e1';
	document.getElementById('alternativeemail').style.border = '1px solid #e1e1e1';
	document.getElementById('postalCode').style.border = '1px solid #e1e1e1';
	document.getElementById('state').style.border = '1px solid #e1e1e1';
	document.getElementById('primarymobile').style.border = '1px solid #e1e1e1';
	
	document.getElementById('Pref').style.border = '1px solid #e1e1e1';
	document.getElementById('secMobile').style.border = '1px solid #e1e1e1';
	document.getElementById('primaryLandline').style.border = '1px solid #e1e1e1';
	
	  
	
    document.getElementById('fplType_error_Postal').style.display="none";
	document.getElementById('fplType_error_Mobile').style.display="none";
	document.getElementById('fplType_error_Landline').style.display="none";
	document.getElementById('fplType_error_SecMobile').style.display="none";

	
	  
	if(uProfile.formId==2)
	   {
		  if(!uProfile.primaryfirstName1 || uProfile.primaryfirstName1.length<=0)
		  {
			  document.getElementById('primaryfirstname').style.border = '1px solid red';
			   valid=false; 
		  }
		
		  if(!uProfile.primarylastName1 || uProfile.primarylastName1.length<=0)
		  {
			  document.getElementById('primarylastname').style.border = '1px solid red';
			   valid=false; 
		  }
		  if(!uProfile.alternativeemail || uProfile.alternativeemail.length<=0)
		  {
			  document.getElementById('alternativeemail').style.border = '1px solid red';
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
}

UserProfileController.prototype.loadInd = function(uProfile){
	
	var valid=this.validateInd(uProfile);
	
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

		
	
}


UserProfileController.prototype.validateIndOrg = function(uProfile){
	
	  var valid=true;
 	  var numericMobilePattern = /^\d{10}$/;
	  var numericPostalPattern = /^\d{6}$/;

	  document.getElementById('fplType_error1').style.display="none";
	  document.getElementById('fplType_error2').style.display="none";
	  document.getElementById('fplType_error3').style.display="none";

      document.getElementById('fplType_error_Postal').style.display="none";
	  document.getElementById('fplType_error_Mobile').style.display="none";
	  document.getElementById('fplType_error_Landline').style.display="none";

	  document.getElementById('fplType_error_SecMobile').style.display="none";
	  document.getElementById('fplType_error_SecLandline').style.display="none";

	  
	  document.getElementById('primaryfirstName1').style.border = '1px solid #e1e1e1';
	  document.getElementById('proofNum').style.border = '1px solid #e1e1e1';
	  
	  document.getElementById('postalCode').style.border = '1px solid #e1e1e1';
	  document.getElementById('state').style.border = '1px solid #e1e1e1';
	  document.getElementById('primarymobile').style.border = '1px solid #e1e1e1';
	  document.getElementById('primarylandline').style.border = '1px solid #e1e1e1';
	  
	  document.getElementById('secondaryfirstName').style.border = '1px solid #e1e1e1';
	  document.getElementById('secondarylastName').style.border = '1px solid #e1e1e1';
	  document.getElementById('secondarymobile').style.border = '1px solid #e1e1e1';
	  document.getElementById('secondaryidProof').style.border = '1px solid #e1e1e1';
	  document.getElementById('secondaryemail').style.border = '1px solid #e1e1e1';
	  document.getElementById('secondarylandline').style.border = '1px solid #e1e1e1';
	  
	  
	if(uProfile.formId==2)
	   {
		  if(!uProfile.primaryfirstName1 || uProfile.primaryfirstName1.length<=0)
		  {
			  document.getElementById('primaryfirstName1').style.border = '1px solid red';
			   valid=false; 
		  }
		
		  if(!uProfile.proofNum || uProfile.proofNum.length<=0)
		  {
			  document.getElementById('proofNum').style.border = '1px solid red';
			   valid=false; 
		  }
	
		  
	 }
		else if(uProfile.formId==3)
	   {
		   if(!uProfile.postalCode || uProfile.postalCode.length<=0)
			{
				  document.getElementById('postalCode').style.border = '1px solid red';
				   valid=false; 
			}else if(!numericPostalPattern.test(uProfile.postalCode))
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
				  document.getElementById('primarylandline').style.border = '1px solid red';
				  document.getElementById('fplType_error_Landline').style.display="block";	 
      		  valid=false; 
			}
	   }else
		 {
		   
		   if(!uProfile.secondaryfirstName || uProfile.secondaryfirstName.length<=0)
			{
				  document.getElementById('secondaryfirstName').style.border = '1px solid red';
				   valid=false; 
			}
		   
		   if(!uProfile.secondarylastName || uProfile.secondarylastName.length<=0)
			{
				  document.getElementById('secondarylastName').style.border = '1px solid red';
				   valid=false; 
			}
		   
		   if(!uProfile.secondarymobile || uProfile.secondarymobile.length<=0)
			{
				  document.getElementById('secondarymobile').style.border = '1px solid red';
				   valid=false; 
			}else if(!numericMobilePattern.test(uProfile.secondarymobile))
			{
				 document.getElementById('secondarymobile').style.border = '1px solid red';
				 document.getElementById('fplType_error_SecMobile').style.display="block";	   
				 valid=false;
			}
		   
		   if(uProfile.secondarylandLine && !numericMobilePattern.test(uProfile.secondarylandLine))
			{
				  document.getElementById('secondarylandline').style.border = '1px solid red';
				  document.getElementById('fplType_error_SecLandline').style.display="block";	 
     		  valid=false; 
			}
		   
		   if(!uProfile.secondaryidProof || uProfile.secondaryidProof<0)
			{
				  document.getElementById('secondaryidProof').style.border = '1px solid red';
				   valid=false; 
			}
		   if(!uProfile.secondaryemail || uProfile.secondaryemail.length<=0)
			{
				  document.getElementById('secondaryemail').style.border = '1px solid red';
				   valid=false; 
			}
		   
		 }
	
  return valid;
}


UserProfileController.prototype.loadIndOrg = function(uProfile){
	
	var valid=this.validateIndOrg(uProfile);
	
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

		
	
}
