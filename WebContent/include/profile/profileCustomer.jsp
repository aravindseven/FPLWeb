<div id="customerProfile" ng-controller="UserProfileController as uProfile">

<form  method="post" class="col-sm-12" ng-init="uProfile.fetchCustomerDetails();" ng-submit="uProfile.userType=1;uProfile.SaveChanges(uProfile)">


<div class="formsection" id="form1" style=" height:500px;  display:block;">


    	<div class="f-indicator">
        	<img src="support/images/form1_1.png" style="cursor:pointer;">
            <img src="support/images/form1_2.png" onclick="load(2)">
            <img src="support/images/form1_3.jpg" onclick="load(3)">
        </div>
        <div class="identify id1" id="form11">
        	<table id="tab1" border="0" style="top:220px;">
       	
                <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" value="" class="i-input" ng-model="uProfile.customerIdText"></td>
                    <td><input type="text" name="regate" placeholder="Registration Date"  class="i-input" value="{{uProfile.formatDate(uProfile.customerCreationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>
               
                <tr>
                	<td><input type="text" name="firstname" maxlength="30" id="primaryfirstname" ng-model="uProfile.primaryfirstName1" placeholder="First Name" class="i-input fname img" ></td>
                    <td><input type="text" name="lastname" maxlength="30" id="primarylastname" placeholder="Last Name" ng-model="uProfile.primarylastName1"  class="i-input lname img"></td>
                </tr>
                <tr>
                	<td><label>Gender</label>
                	<input type="radio" value="Male" name="gender" ng-model="uProfile.gender" class="i-radio" >&nbsp;Male 
                	<input type="radio" value="Female" name="gender" ng-model="uProfile.gender" class="i-radio">&nbsp;Female</td>
                	<td>
                    	 <input type="date" class="i-input" id="primarydob"  ng-model="uProfile.dob"/>

                    </td>


                </tr>
                <tr>
                	<td>
                    	<select class="i-input i-id" id="idProof" ng-model="uProfile.idProof">
                        	<option value="-1">--Select Proof Type--</option>
                        	<option id="nric" value="NRIC">NRIC</option>
                        	<option id="none" value="none">None</option>
                        </select>
                        <img src="icons/qmark.png" class="qmark" />
                    </td>
                	<td><input type="text" id="proofnumber" maxlength="30" name="proofnumber" placeholder="Proof Number" ng-model="uProfile.proofNum" class="i-input i-proof" id="i-proof" value=""></td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="upload" class="i-input" value="" placeholder="Profile Picture">
                  </td>
                  <td><input type="text" id="alternativeemail" maxlength="30" name="Altemail" placeholder="Alternative emailid" ng-model="uProfile.alternativeemail" class="i-input"  ></td>
                  
                    
                </tr>
                <tr>
                	<td >      
                  
                    <input type="button" name="uploadbtn" class="i-upload" style="top:0px; left:0px;"  value="Upload" onClick="window.open('test/image-preview/index.html', '',' width=500, height=500');">
                    </td>
                </tr>
        	<tr>
        		<td colspan='2'>
			    	<span id="fplType_error1" style="padding-left:10px; color:red; display:none;"> 
              				* The highlighted information is required. Kindly complete/fill the relevant details to proceed 
      				</span> 			
        		</td>
        	</tr>
                
            </table>
            
            <input type="button" name="bt-continue" value="Continue" style="top:60px; left:80px;" class="bt-end bt-cont bt-form1" ng-click="uProfile.formId=2;uProfile.loadInd(uProfile)">
            <input type="button" name="bt-cancel" value="Cancel"  style="top:60px; left:130px;" class="bt-end">
            
        </div>
		<div class="ans"><p>National Registration Identity Card</p></div>

</div>

<div class="formsection" id="form2" style="display:none;">
		<div class="f-indicator">
        	<img src="support/images/form1_1.png" onClick="load(1);" style="cursor: pointer;">
            <img src="support/images/form2_2.png" style="cursor: pointer;">
            <img src="support/images/form1_3.jpg">
        </div>
        <div class="identify" id="identify">
        	<table id="tab2" style="top:180px;">
      	
               
                <tr>
	                <td><input type="text" name="postal" maxlength="6" id="postalCode" placeholder="Postal Code" ng-model="uProfile.postalCode" ng-change="uProfile.fetchAddress(uProfile)" value="" class="i-input post img" ></td>
                    <td><input type="text" name="unit" id="state" maxlength="30" placeholder="Unit Number" ng-model="uProfile.state" value="" class="i-input unum img"></td>                	
                </tr>
                <tr>
                    <td><input type="text" name="block" maxlength="30" placeholder="Block Number" ng-model="uProfile.block" value="" class="i-input blkn"></td>            	             
                    <td><input type="text" name="building" maxlength="30" placeholder="Building Name" ng-model="uProfile.buildingName" value="" class="i-input bldn"></td>
                </tr>
                <tr>
                    <td><input type="text" name="street" maxlength="30" placeholder="Street" ng-model="uProfile.street"  class="i-input" value=""></td>
                     <td><input type="text" name="country"  maxlength="30"placeholder="Country" ng-model="uProfile.country" class="i-input "> </td>
                    
                </tr>
               
                <tr>
                   <td><input type="text" placeholder="+65" class="i-input" disabled style="width:20%;padding-left:10px;"><input style="width:78%;margin-left:2px;" type="text" name="mobnum" maxlength="10" placeholder="Mobile Number" ng-model="uProfile.primarymobile" id="primarymobile" value="" class="i-input mobnum img" ></td>
                    <td><input type="text" placeholder="+65" class="i-input" disabled style="padding-left: 10px;width:20%;"><input type="text" name="landline" placeholder="Landline Number" maxlength="10" value="" class="i-input llnum" ng-model="uProfile.landLine"  id="primaryLandline" style="width:78%;margin-left:2px;"></td>
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
              <input type="button" name="bt-continue" value="Continue" style="top:80px;" class="bt-end bt-cont bt-form2" ng-click="uProfile.formId=3;uProfile.loadInd(uProfile)">
               <input type="button" name="bt-cancel" value="Back" style="top:80px;"class="bt-end bt-cancel" onclick="load(1);">
                <input type="button" name="bt-cancel" value="Cancel" style="top:80px;" class="bt-end bt-cancel">
        </div>

</div>

<div class="formsection" id="form3" style="display:none;">
  <div class="f-indicator">
        	<img src="support/images/form1_1.png" onClick="load1();" style="cursor: pointer;">
            <img src="support/images/form2_2.png" onClick="load2();" style="cursor: pointer;">
            <img src="support/images/form3_3.png" style="cursor: pointer;">
        </div>
        <div class="identify" id="identify">
        	<table id="tab3">
        	
            	<tr>
                	<td><input type="text" name="nomfirstname" maxlength="30" placeholder="Nominee First Name" ng-model="uProfile.secondaryfirstName" value="" class="i-input nfname"></td>
                	<td><input type="text" name="nomlastname"  maxlength="30"placeholder="Nominee Last Name" ng-model="uProfile.secondarylastName" value="" class="i-input nlname"></td>
                </tr>
                <tr>
                	<td><input type="text" name="nommobnum" maxlength="10" ng-model="uProfile.secondarymobile" placeholder="Nominee Mobile Number" value="" id="secMobile" class="i-input nmnum" ></td>

                    <td><input type="text" name="relnshp" maxlength="30" ng-model="uProfile.secondaryrelation"  placeholder='Relationship/Position' value="" class="i-input reln"></td>
                </tr>
                <tr>
                    <td>
                    	<select class="i-input i-id" ng-model="uProfile.secondaryidProof">
                    	<option value="-1" class="">--Select Proof Type--</option>
                        	<option value="NRIC" class="">NRIC</option>
                            <option id="none" value="NONE">NONE</option>
                        </select>
                    </td>

                    <td><input type="text" name="proofnum" maxlength="30" placeholder="Proof Number" ng-model="uProfile.secondaryproofNum" value="" class="i-input i-proof"></td>
                </tr>
            </table>
            	<div class="conrefimp" style="position:relative; width: 720px; left:0px; top:-50px;">
	          	 	<select class="i-input conref img" id="Pref" style="width: 720px;" ng-model="uProfile.confPreference">
    	       			<option value="-1">--Select CONFIDENTIALITY PREFERENCE-- </option>
                  	 	<option value="ref1">PREF1</option>
                  		<option value="ref2">PREF2</option>
                        <option value="ref3">PREF3</option>
                   </select>
                </div>
                
                 <span id="fplType_error_SecMobile" style="position:relative; left:50px; top:-80px; color:red; display:none;"> 
              				* Mobile should be numeric and of 10 digits..! 
      			</span> 
			    <span id="fplType_error3" style="position:relative; left:50px; top:-100px; color:red; display:none;"> 
              				* The highlighted information is required. Kindly complete/fill the relevant details to proceed 
      			</span> 
		    				
               
           <input type="submit" style="background-color:#CE2B2C; top:-80px;" name="bt-save" class="bt-end bt-cancel" value="Save">
         	  <input type="button" name="bt-cancel" value="Back" style="top:-80px;" class="bt-end bt-cancel" onclick="load(2);">
            <input type="button" name="bt-cancel" value="Cancel" style="top:-80px;" class="bt-end bt-cancel">
	</div>

</div>

<div id="successMSG" class="f-confirm" style="display:none;">
		<p id="Content" style="font-family: SansRegular; font-size: 15px;"></p>
		<input type="button" onClick="document.getElementById('successMSG').style.display='none';" value="Ok" class="f-submit" style="top:150px;"/>
	</div>

</form>
</div>

