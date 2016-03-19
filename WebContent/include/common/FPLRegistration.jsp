<div id="REG_CONTROLLER" class="modal-dialog registerUser" ng-controller="UserRegisterController as uRegister">
<div class="mobmenuicon">
	<img src="support/icons/menu.png">
</div>
<div class="dropshadow d-hide"></div>
<div class="register r-hide" >
	<div class="r-logo">
		<img src="support/icons/logo.png">
	</div>
	<div class="r-crights">
		<p>All rights reserved to FPL 2015</p>
	</div>
	<div class="r-closer">
		<p>X</p>
	</div>
	<!-- Roles : Customer/Financial Planner -->

	<div class="role">
		<div class="r-head">
			<p>I AM A</p>
		</div>
		<div class="r-roles">
			<div class="r-cust" onClick="displayCustomer()">
				<img src="support/icons/customer.png">
				<p>Customer</p>
			</div>
			<div class="r-finplan" onClick="displayFPL()">
				<img src="support/icons/finplan.png">
				<p >Financial Planner</p>
			</div>
		</div>
	</div>
	<form name="registerForm" ng-submit="uRegister.register(user)" autocomplete="off" ng-init="uRegister.checkCountry(user)" style="height:80%;">
	<table border="0">
	<tr>
	<td>
	<!-- Cutomer register form -->
	
	<div id="regDiv" class="regform custform form-hide">
		<div class="f-back">
			<img src="support/icons/back.png">
		</div>
		<div id="customer" style="display:none">
		<div class="f-head">
			<img src="support/icons/customer.png">
			<p>CUSTOMER REGISTRAITON</p>
		</div>
		<p class="f-indicator">You are registering as</p>
		<div class="f-type">
			<div class="f-t-ind" ng-click="type='cust_individual';" onClick="resetOther(1)">
				<div class="outer">
					<div id="CUST_IND" class="inner"></div>
				</div>
				<p>Individual</p>
			</div>
			<div class="f-t-cor" ng-click="type='cust_corporate';" onClick="resetOther(2)">
				<div class="outer">
					<div id="CUST_ORG" class="inner"></div>
				</div>
				<p>Corporate</p>
			</div>
		</div>
		</div>
		<div id="FPL">
		  		<div class="f-head">
			<img src="support/icons/finplan.png">
			<p>FINANCIAL PLANNER REGISTRAITON</p>
		</div>
		<p class="f-indicator">You are registering as</p>
		<div class="f-type">
			<div class="f-t-ind" ng-click="type='fp_individual';" onClick="resetOther(3)">
				<div class="outer">
					<div id="FPL_IND" class="innerInactive"></div>
				</div>
				<p>Individual</p>
			</div>
			<div class="f-t-cor" ng-click="type='fp_corporate';" onClick="resetOther(4)">
				<div class="outer">
					<div id="FPL_ORG" class="innerInactive"></div>
				</div>
				<p>Corporate</p>
			</div>
		</div>
		  
		</div>
		<table  border="0" id="tab3">
			<tr>
				<td><input type="text" value="" placeholder="First Name" ng-model="user.firstname"
					 class="f-input f-fname" required></td>
				<td><input type="text" value="" placeholder="Last Name" ng-model="user.lastname"
					 class="f-input f-lname" required></td>
			</tr>
			<tr>
				<td><input type="email" name="regUsername" value="" ng-model="user.username" placeholder="Email"
					class="f-input f-mail"  ng-minlength=3 required></td>
				<td><input type="text" value=""
					placeholder="Your Mother's Maiden name?" 
					class="f-mmname f-input" name="regSecretQuestionAnswer" ng-model="user.regSecretQuestionAnswer" required></td>
			</tr>
			</table>
			<table style="top:270px; width:100%" border="0">
			
			<tr>
			 <td colspan="2">
			 <div class="agree" style="top:2px;"><input type="checkbox" name="agree" required/>&nbsp; I have read and accepted the Terms & Conditions</div>
			 </td>
			</tr>
			<tr>
			<td colspan="2" align="center">
			  <div class="g-recaptcha" data-sitekey="6LeFJAoTAAAAAK9EOWdlbe3-E6PcobyfOSNAH4oR" data-callback="alert('Captcha Verified')"></div>
			</td>
			</tr>
			
			<tr>
			 <td colspan="2" align="center">
	 			 	<span><input  type="submit" value="REGISTER" class="f-submit" style="top:150px; left: 280px;"></span>
		 	</td>
			</tr>
		</table>
		 
		
	</div>
	</td>
	</tr>
	</table>
	</form>
	<div id="successMSG" class="f-confirm" style="display:none;">
		<p id="Content" style="font-family: SansRegular; font-size: 15px;"></p>
		<input type="button" onClick="document.getElementById('successMSG').style.display='none';" value="Ok" class="f-submit" style="top:150px;"/>
	</div>
</div>
</div>
<script>
function displayCustomer()
{
  resetOther(1);
	
  document.getElementById("customer").style.display="block";
  document.getElementById("FPL").style.display="none";
  document.getElementById("regDiv").className="regform custform form-hide";
 
}
function displayFPL()
{
	resetOther(3);
	
	

  document.getElementById("customer").style.display="none";
  document.getElementById("FPL").style.display="block";
  document.getElementById("regDiv").className="regform orgform orgform-hide";
}

function resetOther(val)
{
	
	var scope = angular.element(
		    document.
		    getElementById("REG_CONTROLLER")).
		    scope();
	
	


  if(val==1)
  {
		scope.type='cust_individual';
	document.getElementById("CUST_IND").className="innerActive";
    document.getElementById("CUST_ORG").className="inner";
    document.getElementById("FPL_IND").className="inner";
    document.getElementById("FPL_ORG").className="inner";
  }	
  else if(val==2)
  {
	  document.getElementById("CUST_IND").className="innerInactive";
	  document.getElementById("CUST_ORG").className="innerActive";
	  document.getElementById("FPL_IND").className="innerInactive";
	    document.getElementById("FPL_ORG").className="innerInactive";
  }else if(val==3)
  {
	  scope.type='fp_individual';
		document.getElementById("CUST_IND").className="inner";
	    document.getElementById("CUST_ORG").className="inner";
	    document.getElementById("FPL_IND").className="innerOrgActive";
	    document.getElementById("FPL_ORG").className="inner";
  }	else if(val==4)
  {
		document.getElementById("CUST_IND").className="inner";
	    document.getElementById("CUST_ORG").className="inner";
	    document.getElementById("FPL_IND").className="inner";
	    document.getElementById("FPL_ORG").className="innerOrgActive";
	}
}

</script>