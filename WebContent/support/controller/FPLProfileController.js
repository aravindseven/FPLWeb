function FPLProfileController($scope, $http) {
	this.scope = $scope;
	this.scope.master = {};
	this.http = $http;
	this.util = new Util();
	
	this.showFPLProfileEdit = true;
	this.showFPLProfileSave = false;
	this.subsribe = false;
	this.fplfirstname1 ="";
	this.fpllastname1 ="";
	this.alternativeemail ="";
	this.fplgender ="";
	this.fpldob ="";
	this.fplidProof ="";
	this.fplproofNum ="";
	this.block ="";
	this.fplbuildingName ="";
	this.street ="";
	this.fplcity ="";
	this.postalCode ="";
	this.fplstate ="";
	this.country ="";
	this.fplmobile =0;
	this.fpllandLine ="";
	this.fplemail ="";
	this.fplregistration ="";
	this.fplregEntity ="";
	this.insuranceCompany="";
	this.fplId ="";
	this.fplIdText ="";
	this.fplCreationDate ="";
	this.fplexpiryDate ="";
	this.fplcoverage="";
	this.fpldomainList ="";
	this.fpldomain ="";
	this.fplspecialization1 ="";
	this.fplspecialization2 ="";
	this.fplagencyName ="";
	this.fplwebsite ="";
	this.fplsecondaryblock ="";
	this.fplsecondarybuildingname ="";
	this.fplsecondarystreet ="";
	this.fplsecondarycity ="";
	this.fplsecondarypostalcode ="";
	this.fplsecondarystate = "";
	this.fplsecondarycountry = "";

	
	this.secondaryfirstName ="";
	this.secondarylastName ="";
	this.secondaryidProof ="";
	this.secondaryproofNum ="";
	this.secondarymobile ="";
	this.secondarylandLine ="";
	this.secondaryemail ="";
	this.secondaryPref ="";
	
	this.validationStatus="";
	this.validationStatusDesc="";
	this.paymentStatusDesc="";

	this.validationDate="";
    this.latitude=0;
    this.longitude=0;

	this.countForEnteredValues = 0;
	this.percentageCompleted =0;
	this.formId=0;
	this.userType=0;
}



	

FPLProfileController.prototype.fetchFPLDetails = function() {
	var self = this;
	this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'redirectAjaxProfileCreation.do',
		//url : 'support/contents/fpl_profile.json',
		params : {},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.FinancialPlannerPV){
				if(data.FinancialPlannerPV.personalDataPV){
					self.fplfirstname1 = data.FinancialPlannerPV.personalDataPV.firstName;
					self.fpllastname1 = data.FinancialPlannerPV.personalDataPV.lastName;
					self.fplgender =data.FinancialPlannerPV.personalDataPV.gender;
					self.fpldob = new Date(data.FinancialPlannerPV.personalDataPV.dob);
					self.fplidProof =data.FinancialPlannerPV.personalDataPV.idProof;
					self.fplproofNum =data.FinancialPlannerPV.personalDataPV.proofNum;
					self.fplmobile =data.FinancialPlannerPV.personalDataPV.mobile;
					
					self.fpllandLine =data.FinancialPlannerPV.personalDataPV.landLine;
					self.fplemail = data.FinancialPlannerPV.personalDataPV.email;
				}
				if(data.FinancialPlannerPV.secondaryDataPV){
					self.secondaryfirstName = data.FinancialPlannerPV.secondaryDataPV.firstName;
					self.secondarylastName = data.FinancialPlannerPV.secondaryDataPV.lastName;
					self.secondaryPref =data.FinancialPlannerPV.secondaryDataPV.gender;
					
					self.secondaryidProof =data.FinancialPlannerPV.secondaryDataPV.idProof;
					self.secondaryproofNum =data.FinancialPlannerPV.secondaryDataPV.proofNum;
					self.secondarymobile =data.FinancialPlannerPV.secondaryDataPV.mobile;
					self.secondarylandLine =data.FinancialPlannerPV.secondaryDataPV.landLine;
					self.secondaryemail = data.FinancialPlannerPV.secondaryDataPV.email;
				}
				if(data.FinancialPlannerPV.addressPV){
					self.block =data.FinancialPlannerPV.addressPV.block;
					self.fplbuildingName =data.FinancialPlannerPV.addressPV.buildingName;
					self.street =data.FinancialPlannerPV.addressPV.street;
					self.fplcity =data.FinancialPlannerPV.addressPV.city;
					self.postalCode =data.FinancialPlannerPV.addressPV.postalCode;
					self.fplstate =data.FinancialPlannerPV.addressPV.state;
					self.country =data.FinancialPlannerPV.addressPV.country;
					self.latitude=data.FinancialPlannerPV.addressPV.latitude;
					self.longitude=data.FinancialPlannerPV.addressPV.longitude;

				}
				
				self.fplregistration = data.FinancialPlannerPV.registration;
				self.fplregEntity =new Date(data.FinancialPlannerPV.regEntity);
				self.fplexpiryDate =new Date(data.FinancialPlannerPV.expiryDate);
				self.fplcoverage =data.FinancialPlannerPV.coverage;
				self.fpldomain =data.FinancialPlannerPV.domainList;
				self.fpldomainList = data.FinancialPlannerPV.domains;
				self.fplspecialization1 =data.FinancialPlannerPV.specialization1;
				self.fplspecialization2 =data.FinancialPlannerPV.specialization2;
				self.fplagencyName =data.FinancialPlannerPV.agencyName;
				self.fplwebsite =data.FinancialPlannerPV.website;
				self.fplCreationDate=data.FinancialPlannerPV.creationDate;
				self.fplId=data.FinancialPlannerPV.fplId;
				self.fplIdText=data.FinancialPlannerPV.fplIdText;
				self.insuranceCompany=data.FinancialPlannerPV.insuranceCompany;
				
				if(data.FinancialPlannerPV.permanentAddressPV){
					self.fplsecondaryblock =data.FinancialPlannerPV.permanentAddressPV.block;
					self.fplsecondarybuildingname =data.FinancialPlannerPV.permanentAddressPV.buildingName;
					self.fplsecondarystreet =data.FinancialPlannerPV.permanentAddressPV.street;
					self.fplsecondarycity =data.FinancialPlannerPV.permanentAddressPV.city;
					self.fplsecondarypostalcode =data.FinancialPlannerPV.permanentAddressPV.postalCode;
					self.fplsecondarystate =data.FinancialPlannerPV.permanentAddressPV.state;
					self.fplsecondarycountry =data.FinancialPlannerPV.permanentAddressPV.country;
				}
				
				if(data.FinancialPlannerPV.subscriptionListPVs){
				
					self.validationStatus=data.FinancialPlannerPV.subscriptionListPVs.Status;
					self.validationStatusDesc=data.FinancialPlannerPV.subscriptionListPVs.statusDesc;
					self.paymentStatusDesc=data.FinancialPlannerPV.subscriptionListPVs.paymentStatusDesc;
					
					if(self.validationStatus=='AC')
					{
						
						self.validationStatusDesc='Active';
						self.updateActiveUI();
						
					}
					else if(self.validationStatus=='CI')
					{
						self.validationStatusDesc=self.paymentStatusDesc;
						self.updateActiveUI();
					}
					else
					{
						self.validationStatusDesc='Inactive';
					}
					
					self.validationDate=data.FinancialPlannerPV.subscriptionListPVs.validationDate;
				}

			}
			
			var sel = $("select[name=fpldomainList]");
			sel.empty();
			sel.append('<option value="0">Select Type..</option>');
			$.each(data.FinancialPlannerPV.domains, function(index){
				sel.append('<option value="' + this.id + '" ' + ((this.name == self.fpldomain) ? "selected" : "") + '>' + this.name + '</option>');
			});
			
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};


FPLProfileController.prototype.SaveFPLChanges = function(fplProfile){
	var self = this;
	fplProfile.formId=4;
    if((fplProfile.userType==1 && fplProfile.validateFPL(fplProfile)) || (fplProfile.userType==2 && fplProfile.validateFPLOrg(fplProfile)) )
	{
		var financialPlannerJson = {
				 "registration" :  fplProfile.fplregistration,
				 "regEntity" :  fplProfile.fplregEntity,
				 "expiryDate" :  fplProfile.fplexpiryDate,
				 "coverage" :  fplProfile.fplcoverage,
				 /*"fpldomain" :   [{
					 fplProfile.fpldomain,
				 }],*/
				 "specialization1" :  fplProfile.fplspecialization1,
				 "specialization2" :  fplProfile.fplspecialization2,
				 "agencyName" :  fplProfile.fplagencyName,
				 "insuranceCompany":fplProfile.insuranceCompany,
				 "website" : fplProfile.fplwebsite
		};
		var personalDataJson = {
				 "firstName" : fplProfile.fplfirstname1,
				 "lastName" : fplProfile.fpllastname1,
				 "gender" :  fplProfile.fplgender,
				 "dob" :  fplProfile.fpldob,
				 "idProof" :  fplProfile.fplidProof,
				 "proofNum" : fplProfile.fplproofNum,
				 "mobile" :  fplProfile.fplmobile,
				 "landLine" :  fplProfile.fpllandLine,
				 "email" :  fplProfile.fplemail
		};
		
		var secondaryDataJson = {
				 "firstName" : fplProfile.secondaryfirstName,
				 "lastName" : fplProfile.secondarylastName,
				 "gender" :  fplProfile.secondaryPref,
				 "idProof" :  fplProfile.secondaryidProof,
				 "proofNum" : fplProfile.secondaryproofNum,
				 "mobile" :  fplProfile.secondarymobile,
				 "landLine" :  fplProfile.secondarylandLine,
				 "email" :  fplProfile.secondaryemail
		};
		
	
		var addressJson = {
				 "block" :  fplProfile.block,
				 "buildingName" :   fplProfile.fplbuildingName,
				 "street" :  fplProfile.street,
				 "city" :   fplProfile.fplcity,
				 "postalCode" :  fplProfile.postalCode,
				 "state" :  fplProfile.fplstate,
				 "country" :  fplProfile.country,
				 "latitude" : fplProfile.latitude,
				 "longitude" : fplProfile.longitude

		};
		var permanentAddressJson = {
				 "block" : fplProfile.fplsecondaryblock,
				 "buildingName" : fplProfile.fplsecondarybuildingname,
				 "street" : fplProfile.fplsecondarystreet,
				 "city" :  fplProfile.fplsecondarycity,
				 "postalCode" : fplProfile.fplsecondarypostalcode,
				 "state" : fplProfile.fplsecondarystate,
				 "country" : fplProfile.fplsecondarycountry
		};
		
			this.util.block("Modifying details");
			this.http({
				method : 'POST',
				url : 'fplAjaxProfileCreation.do',
				//url : 'support/contents/save_customer_profile.json',
				params : {
					'financialPlannerJson' : financialPlannerJson,
					'personalDataJson' : personalDataJson,
					'addressJson' : addressJson,
					'permanentAddressJson' : permanentAddressJson,
					'secondaryDataJson':secondaryDataJson,
					'alternativeemailid' :fplProfile.alternativeemail
					
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
				}
			}).success(function(data, status, headers, config) {
				
				if (data.status == "success") {
					self.util.notify("Profile Info", data.reason, "success");
					
					document.getElementById("successMSG").style.display="block";
					document.getElementById("Content").innerHTML=data.reason;

					
					if(fplProfile.subsribe)
					{
						window.location.href= "subScribeNow.jsp";
					}
				} else {
					self.util.notify("Profile Failure", data.reason, "failure");

					
					document.getElementById("successMSG").style.display="block";
					document.getElementById("Content").innerHTML=data.reason;
}
				self.util.unblock();
				
			}).error(function(data, status, headers, config) {
				self.util.unblock();
				document.getElementById("successMSG").style.display="block";
				document.getElementById("Content").innerHTML=data.reason;

			});
	}
    else
	  {
		  document.getElementById('fplType_error3').style.display="block";
		  
	  }
};

FPLProfileController.prototype.fetchAddress = function(uProfile){
	
	var address=Convert_postCode_To_LatLng(uProfile);
	
}

FPLProfileController.prototype.formatDate = function(date){
    var dateOut = new Date(date);
    return dateOut;
};


FPLProfileController.prototype.subscribe = function(uProfile){
	
	uProfile.subsribe=true;
	
	this.SaveFPLChanges(uProfile);	
	
}



FPLProfileController.prototype.validateFPL = function(uProfile){
	
	var valid=true;
	var numericMobilePattern = /^\d{10}$/;
	var numericPostalPattern = /^\d{6}$/;
	document.getElementById('fplType_error').style.display="none";
	document.getElementById('fplType_error2').style.display="none";
	document.getElementById('fplType_error3').style.display="none";
	document.getElementById('fplType_error_Postal').style.display="none";
	document.getElementById('fplType_error_Mobile').style.display="none";
	document.getElementById('fplType_error_Landline').style.display="none";
	document.getElementById('firstName').style.border = '1px solid #e1e1e1';
	document.getElementById('lastName').style.border = '1px solid #e1e1e1';
	document.getElementById('alternativeemail').style.border = '1px solid #e1e1e1';
	document.getElementById('proofType').style.border = '1px solid #e1e1e1';
	document.getElementById('postal').style.border = '1px solid #e1e1e1';
	document.getElementById('unit').style.border = '1px solid #e1e1e1';
	document.getElementById('mobnum').style.border = '1px solid #e1e1e1';
	document.getElementById('fplregistration').style.border = '1px solid #e1e1e1';
	document.getElementById('fplexpiryDate').style.border = '1px solid #e1e1e1';
	document.getElementById('insuranceCompany').style.border = '1px solid #e1e1e1';
	  
	if(uProfile.formId==2)
	   {
		  if(!uProfile.fplfirstname1 || uProfile.fplfirstname1.length<=0)
		  {
			  document.getElementById('firstName').style.border = '1px solid red';
			   valid=false; 
		  }
		
		  if(!uProfile.fpllastname1 || uProfile.fpllastname1.length<=0)
		  {
			  document.getElementById('lastName').style.border = '1px solid red';
			   valid=false; 
		  }
	
		  if(!uProfile.fplidProof || uProfile.fplidProof<0)
		  {
			  document.getElementById('proofType').style.border = '1px solid red';
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
			   
				  document.getElementById('postal').style.border = '1px solid red';
				   valid=false; 
			}
		   else if(!numericPostalPattern.test(uProfile.postalCode))
			{
				 document.getElementById('postal').style.border = '1px solid red';
				 document.getElementById('fplType_error_Postal').style.display="block";	   
				  valid=false;
			}
		   
		   if(!uProfile.fplstate || uProfile.fplstate.length<=0)
			{
				  document.getElementById('unit').style.border = '1px solid red';
				   valid=false; 
			}
		   
		   if(!uProfile.fplmobile || uProfile.fplmobile.length<=0)
			{
				  document.getElementById('mobnum').style.border = '1px solid red';
				   valid=false; 
			}
		   else if(!numericMobilePattern.test(uProfile.fplmobile))
			{
				 document.getElementById('mobnum').style.border = '1px solid red';
				 document.getElementById('fplType_error_Mobile').style.display="block";	   
				 valid=false;
			}
		   
		   if(uProfile.fpllandLine && !numericMobilePattern.test(uProfile.fpllandLine))
			{
				  document.getElementById('landline').style.border = '1px solid red';
				  document.getElementById('fplType_error_Landline').style.display="block";	 
         		  valid=false; 
			}
		  
		   
	   }else
		 {
		   if(!uProfile.fplregistration || uProfile.fplregistration.length<=0)
			{
				  document.getElementById('fplregistration').style.border = '1px solid red';
				   valid=false; 
			}
		   
		   if(!uProfile.fplexpiryDate)
			{
				  document.getElementById('fplexpiryDate').style.border = '1px solid red';
				   valid=false; 
			}
		   
		   if(!uProfile.insuranceCompany || uProfile.insuranceCompany.length<=0)
			{
				  document.getElementById('insuranceCompany').style.border = '1px solid red';
				   valid=false; 
			}
		 }
  return valid;
}

FPLProfileController.prototype.loadFPL = function(uProfile){
	
	alert("callednextpage");
	var valid=this.validateFPL(uProfile);
	
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
			  document.getElementById('fplType_error').style.display="block";
			  
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


FPLProfileController.prototype.validateFPLOrg = function(uProfile){
	
	  var numericMobilePattern = /^\d{10}$/;
	  var numericPostalPattern = /^\d{6}$/;
	  var valid=true;
	  var postalValid=true;
	  document.getElementById('fplType_error1').style.display="none";
	  document.getElementById('fplType_error2').style.display="none";
	  document.getElementById('fplType_error3').style.display="none";
	  document.getElementById('fplType_error_Postal').style.display="none";
	  document.getElementById('fplType_error_Mobile').style.display="none";
	  document.getElementById('fplType_error_Landline').style.display="none";
	  document.getElementById('fplType_error_SecMobile').style.display="none";
	  document.getElementById('fplType_error_SecLandline').style.display="none";

	  document.getElementById('agentname').style.border = '1px solid #e1e1e1';
	  document.getElementById('regnum').style.border = '1px solid #e1e1e1';
	  document.getElementById('idProof').style.border = '1px solid #e1e1e1';
	  document.getElementById('proofnumber').style.border = '1px solid #e1e1e1';
	  document.getElementById('postal').style.border = '1px solid #e1e1e1';
	  document.getElementById('unit').style.border = '1px solid #e1e1e1';
	  document.getElementById('mobnum').style.border = '1px solid #e1e1e1';
	  document.getElementById('fname').style.border = '1px solid #e1e1e1';
	  document.getElementById('lname').style.border = '1px solid #e1e1e1';
	  document.getElementById('secmobnum').style.border = '1px solid #e1e1e1';
	  document.getElementById('secidProof').style.border = '1px solid #e1e1e1';
	  document.getElementById('secproofnumber').style.border = '1px solid #e1e1e1';
	  document.getElementById('altmail').style.border = '1px solid #e1e1e1';
	  document.getElementById('landline').style.border = '1px solid #e1e1e1';
	  
	if(uProfile.formId==2)
	   {
		  if(!uProfile.fplagencyName || uProfile.fplagencyName.length<=0)
		  {
			  document.getElementById('agentname').style.border = '1px solid red';
			   valid=false; 
		  }
		
		  if(!uProfile.fplregistration || uProfile.fplregistration.length<=0)
		  {
			  document.getElementById('regnum').style.border = '1px solid red';
			   valid=false; 
		  }
	
		  if(!uProfile.fplidProof || uProfile.fplidProof<0)
		  {
			  document.getElementById('idProof').style.border = '1px solid red';
			   valid=false; 
		  }
		  if(!uProfile.fplproofNum || uProfile.fplproofNum.length<=0)
		  {
			  document.getElementById('proofnumber').style.border = '1px solid red';
			   valid=false; 
		  }
		  
	 }
		else if(uProfile.formId==3)
	   {
			
		   if(!uProfile.postalCode || uProfile.postalCode.length<=0)
			{
				  document.getElementById('postal').style.border = '1px solid red';
				   valid=false; 
			} else if(!numericPostalPattern.test(uProfile.postalCode))
			{
				
				 document.getElementById('postal').style.border = '1px solid red';
				 document.getElementById('fplType_error_Postal').style.display="block";
				 valid=false;  
			}
		   
		   if(!uProfile.fplstate || uProfile.fplstate.length<=0)
			{
				  document.getElementById('unit').style.border = '1px solid red';
				   valid=false; 
			}
		   
		   if(!uProfile.fplmobile || uProfile.fplmobile.length<=0)
			{
				  document.getElementById('mobnum').style.border = '1px solid red';
				   valid=false; 
			}else if(!numericMobilePattern.test(uProfile.fplmobile))
			{
				 document.getElementById('mobnum').style.border = '1px solid red';
				 document.getElementById('fplType_error_Mobile').style.display="block";	   
				 valid=false;
			}
		   
		   if(uProfile.fpllandLine && !numericMobilePattern.test(uProfile.fpllandLine))
			{
				  document.getElementById('landline').style.border = '1px solid red';
				  document.getElementById('fplType_error_Landline').style.display="block";	 
        		  valid=false; 
			}

		   
	   }else
		 {
		   
		   if(!uProfile.secondaryfirstName || uProfile.secondaryfirstName.length<=0)
			{
				  document.getElementById('fname').style.border = '1px solid red';
				   valid=false; 
			}
		   
		   if(!uProfile.secondarylastName || uProfile.secondarylastName.length<=0)
			{
				  document.getElementById('lname').style.border = '1px solid red';
				   valid=false; 
			}
		   
		   if(!uProfile.secondarymobile || uProfile.secondarymobile.length<=0)
			{
				  document.getElementById('secmobnum').style.border = '1px solid red';
				   valid=false; 
			}else if(!numericMobilePattern.test(uProfile.secondarymobile))
			{
				 document.getElementById('secmobnum').style.border = '1px solid red';
				 document.getElementById('fplType_error_SecMobile').style.display="block";	   
				 valid=false;
			}
		   
		   if(uProfile.secondarylandLine && !numericMobilePattern.test(uProfile.secondarylandLine))
			{
				 document.getElementById('seclandline').style.border = '1px solid red';
				 document.getElementById('fplType_error_SecLandline').style.display="block";	   
				 valid=false;
			}
		   
		   if(!uProfile.secondaryidProof || uProfile.secondaryidProof<0)
			{
				  document.getElementById('secidProof').style.border = '1px solid red';
				   valid=false; 
			}
		   if(!uProfile.secondaryproofNum || uProfile.secondaryproofNum.length<=0)
			{
				  document.getElementById('secproofnumber').style.border = '1px solid red';
				   valid=false; 
			}
		   if(!uProfile.secondaryemail || uProfile.secondaryemail.length<=0)
			{
				  document.getElementById('altmail').style.border = '1px solid red';
				   valid=false; 
			}
		 }
	
  return valid;
}


FPLProfileController.prototype.loadFPLOrg = function(uProfile){
	
	var valid=this.validateFPLOrg(uProfile);
	
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


FPLProfileController.prototype.updateActiveUI = function(){
	
	document.getElementById('SUBSCRIBE').value="View Subscription";
	
	
}