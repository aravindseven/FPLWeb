<div id="customerProfile" ng-controller="UserProfileController as uProfile">

<form  method="post" class="col-sm-12" ng-init="uProfile.fetchCustomerDetails();" ng-submit="uProfile.userType=2;uProfile.SaveChanges(uProfile)">


<div class="formsection" id="form1" style="height:500px;  display:block;">


    	<div class="f-indicator">
        	<img src="support/images/form1_1.png" style="cursor:pointer;">
            <img src="support/images/form1_2.png">
            <img src="support/images/form1_3.jpg">
        </div>
        <div class="identify id1" name="identify" id="form1">
        	<table id="tab1" style="top:180px;">
            <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" value="" class="i-input" ng-model="uProfile.customerIdText"  disabled></td>
                    <td><input type="text" name="regate" placeholder="Registration Date" class="i-input" value="{{uProfile.formatDate(uProfile.customerCreationDate) | date:'dd-MMM-yyyy'}}" disabled ></td>
                </tr>
            	<tr>
                	<td><input type="text" name="name" maxlength="30" id="primaryfirstName1" placeholder="Name" ng-model="uProfile.primaryfirstName1"  class="i-input fname img" ></td>
                    <td><input type="email" name="email" maxlength="30" placeholder="E-Mail/Identifier User Name"  ng-model="uProfile.email" value="" class="i-input mail img" disabled></td>
                </tr>
                <tr>
                	<td><input type="text" name="regnumber" maxlength="30" id="proofNum" placeholder="Registration Number"  ng-model="uProfile.proofNum" value="" class="i-input regnum img" ></td>
                	<td><input type="text" name="inclocation" maxlength="30" placeholder="Incorporation Location" ng-model="uProfile.primarylastName1" class="i-input i-proo imgf" value=""></td>
                </tr>
                <td><input type="text" id="alternativeemail" maxlength="30" name="Altemail" placeholder="Alternative emailid" ng-model="uProfile.alternativeemail" class="i-input"  ></td>
                
                <tr>
        		<td colspan='2'>
			    	<span id="fplType_error1" style="padding-left:10px; color:red; display:none;"> 
              				* The highlighted information is required. Kindly complete/fill the relevant details to proceed
      				</span> 			
        		</td>
        		</tr>
                
            </table>
            
            <input type="button" name="bt-continue" value="Continue" style="top:100px; left:80px;" class="bt-end bt-cont bt-form1" ng-click="uProfile.formId=2;uProfile.loadIndOrg(uProfile)">
            <input type="button" name="bt-cancel" value="Cancel" style="top:100px; left:130px;" class="bt-end">
        </div>

</div>

<div class="formsection" id="form2" style="display:none;">

		<div class="f-indicator">
        	<img src="support/images/form1_1.png" onClick="load1();" style="cursor: pointer;">
            <img src="support/images/form2_2.png" style="cursor: pointer;">
            <img src="support/images/form1_3.jpg">
        </div>
        <div class="identify" id="identify">
        	<table id="tab2">
                <tr>
	                <td><input type="text" name="postal" maxlength="6" id="postalCode" placeholder="Postal Code" ng-model="uProfile.postalCode"  ng-change="uProfile.fetchAddress(uProfile)" value="" class="i-input post img" ></td>
                    <td><input type="text" name="unit" maxlength="30" id="state" placeholder="Unit Number" ng-model="uProfile.state" value="" class="i-input unum img"></td>                	
                </tr>
                <tr>
                    <td><input type="text" name="block" maxlength="30" placeholder="Block Number" ng-model="uProfile.block" value="" class="i-input blkn"></td>            	             
                    <td><input type="text" name="building" maxlength="30" placeholder="Building Name" ng-model="uProfile.buildingName" value="" class="i-input bldn"></td>
                </tr>
                <tr>
                    <td><input type="text" name="street" maxlength="30" placeholder="Street" ng-model="uProfile.street" class="i-input" value=""></td>
                     <td><input type="text" name="country" maxlength="30" placeholder="Country" ng-model="uProfile.country" value="Singapore" class="i-input "> </td>
                    
                </tr>
               
                <tr>
                   <td><input type="text" placeholder="+65" class="i-input" disabled style="width:20%;padding-left:10px;"><input style="width:78%;margin-left:2px;" type="text" name="mobnum" maxlength="10" placeholder="Mobile Number" ng-model="uProfile.primarymobile" id="primarymobile" value="" class="i-input mobnum img" ></td>
                    <td><input type="text" placeholder="+65" class="i-input" disabled style="padding-left: 10px;width:20%;"><input type="text" name="landline" placeholder="Landline Number" maxlength="10" value="" class="i-input llnum" ng-model="uProfile.landLine"  id="primarylandline" style="width:78%;margin-left:2px;"></td>
                </tr>
           <tr>
        	<td colspan='2'>
        	 	 <span id="fplType_error_Postal" style="padding-left:10px; color:red; display:none;"> 
              				* Postal Code should be numeric and of 6 digits..! 
      			</span> 		
      			 <span id="fplType_error_Mobile" style="padding-left:10px; color:red; display:none;"> 
              				* Mobile should be numeric and of 10 digits..! 
      			</span> 
      			 <span id="fplType_error_Landline" style="padding-left:10px; color:red; display:none;"> 
              				* Land line should be numeric and of 10 digits..! 
      			</span>
			    <span id="fplType_error2" style="padding-left:10px; color:red; display:none;"> 
              				* The highlighted information is required. Kindly complete/fill the relevant details to proceed 
      			</span> 
      			 
      				
        	</td>
        	</tr>

                     
            </table>
              <input type="button" name="bt-continue" style="top:120px;" value="Continue" class="bt-end bt-cont bt-form2" ng-click="uProfile.formId=3;uProfile.loadIndOrg(uProfile)">
               <input type="button" name="bt-cancel" style="top:120px;" value="Back" class="bt-end bt-cancel" onclick="load(1);">
                <input type="button" name="bt-cancel" style="top:120px;" value="Cancel" class="bt-end bt-cancel">
        </div>

</div>

<div class="formsection" id="form3" style="display:none;">
  
  
          <div class="f-indicator">
        	<img src="support/images/form1_1.png" onClick="load1();" style="cursor: pointer;">
            <img src="support/images/form2_2.png" onClick="load2();" style="cursor: pointer;">
            <img src="support/images/form3_3.png" style="cursor: pointer;">
        </div>
        <div class="identify" id="identify">
        	<table id="tab3" style="top:180px;">
            	<tr>
                	<td><input type="text" name="Name" placeholder="First Name" maxlength="30" id="secondaryfirstName" ng-model="uProfile.secondaryfirstName" value="" class="i-input fname img" ></td>
                    <td><input type="text" name="name" placeholder="Last Name" maxlength="30" id="secondarylastName" value="" ng-model="uProfile.secondarylastName" class="i-input lname img" ></td>
                </tr>
                <tr>
                	<td><input type="text" placeholder="+65" class="i-input" disabled style="width:20%;padding-left:10px;"><input style="width:78%;margin-left:2px;" type="text" maxlength="10" name="mobnum" placeholder="Mobile Number" ng-model="uProfile.secondarymobile" id="secondarymobile"  value="" class="i-input mobnum img" ></td>
                    <td><input type="text" placeholder="+65" class="i-input" disabled style="padding-left: 10px;width:20%;"><input type="text" name="landline" placeholder="Landline Number" maxlength="10" value="" class="i-input llnum" ng-model="uProfile.secondarylandLine"  id="secondarylandline" style="width:78%;margin-left:2px;"></td>  
                </tr>
                <tr>
                    <td>
                        <select class="i-input img" ng-model="uProfile.secondaryidProof" id="secondaryidProof">
                            <option value="-1" selected>--Select Proof Type--</option>
                            <option value="NRIC">NRIC</option>
                            <option value="None">None</option>
                        </select>
                    </td>
                    <td>
                        <input type="password" name="proofnum" placeholder="Identity Number" maxlength="30"  ng-model="uProfile.secondaryproofNum" class="i-input">
                    </td>
                </tr>
                <tr>
                	<td><input type="text" name="position" placeholder="Position" maxlength="30" ng-model="uProfile.secondaryrelation" value="" class="i-input pos" ></td>
                    <td><input type="text" name="altmail" placeholder="Alternate Email" maxlength="30" id="secondaryemail" ng-model="uProfile.secondaryemail" value="" class="i-input altmail img" ></td>
                </tr>
           <tr>
        	<td colspan='2'>
      			 <span id="fplType_error_SecMobile" style="padding-left:10px; color:red; display:none;"> 
              				* Mobile should be numeric and of 10 digits..! 
      			</span> 
      			 <span id="fplType_error_SecLandline" style="padding-left:10px; color:red; display:none;"> 
              				* Land line should be numeric and of 10 digits..! 
      			</span>
			    <span id="fplType_error3" style="padding-left:10px; color:red; display:none;"> 
              				* The highlighted information is required. Kindly complete/fill the relevant details to proceed 
      			</span> 
      			 
      				
        	</td>
        	</tr>
                
            </table>
            <div class="conrefimp" style="position:relative; width: 720px; left:0px; top:-70px;">
	          	 	<select class="i-input conref" id="Pref" style="width: 720px;" ng-model="uProfile.confPreference">
    	       			<option value="-1">--Select CONFIDENTIALITY PREFERENCE-- </option>
                  	 	<option value="ref1">PREF1</option>
                  		<option value="ref2">PREF2</option>
                        <option value="ref3">PREF3</option>
                   </select>
                </div>
          <input type="submit" style="background-color:#CE2B2C; top:-120px;" name="bt-save" class="bt-end bt-cancel " value="Save">
         <input type="button" name="bt-cancel" value="Back" style="top:-120px;" class="bt-end bt-cancel" onclick="load(2);">
            <input type="button" name="bt-cancel" value="Cancel" style="top:-120px;" class="bt-end bt-cancel">
        </div>
  
</div>
<div id="successMSG" class="f-confirm" style="display:none;">
		<p id="Content" style="font-family: SansRegular; font-size: 15px;"></p>
		<input type="button" onClick="document.getElementById('successMSG').style.display='none';" value="Ok" class="f-submit" style="top:150px;"/>
	</div>

</form>
</div>
