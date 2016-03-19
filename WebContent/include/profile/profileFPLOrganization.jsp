<div id="fplProfile" ng-controller="FPLProfileController as fplProfile">
	<form name="formFPL" method="post" class="col-sm-12" ng-init="fplProfile.fetchFPLDetails();" ng-submit="fplProfile.userType=2;fplProfile.SaveFPLChanges(fplProfile);">

	<div class="formsection" id="form1" style="display:block;">
		<div class="f-indicator">
        	<img src="support/images/fpregorg_1.png">
        </div>
    
        <div class="identify id1" name="identify" id="form1">
        	<table id="tab1">
            <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" value="" class="i-input"  ng-model="fplProfile.fplIdText" disabled></td>
                    <td><input type="text" name="regate" placeholder="Registration Date"  class="i-input" value="{{fplProfile.formatDate(fplProfile.fplCreationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>
            	<tr>
                	<td><input type="text" id="agentname" maxlength="50" name="agenname" ng-model="fplProfile.fplagencyName" placeholder="Agency Name" value="" class="i-input agname img"  style="width:240% !important;">
                
                	</td>

                </tr>
                <tr>
					<td><input type="text" id="regnum" maxlength="30" name="regnum" ng-model="fplProfile.fplregistration" placeholder="Registration Number" value="" class="i-input regnum img" ></td>
                    <td><input type="text" name="incloc" maxlength="30" ng-model="fplProfile.insuranceCompany" placeholder="Incorporation Location" value="" class="i-input incloc" ></td>
                </tr>
                <tr>
                	<td>
                    	<select class="i-input i-id img"  id="idProof" ng-model="fplProfile.fplidProof">
                    		<option id="nric1" value="-1" class="img">--Select Proof Type-- </option>
                        	<option id="nric" value="GST" class="img">GST</option>
                        	<option id="none" value="None">None</option>
                        </select>
                    </td>

                    <td><input type="text" id="proofnumber" maxlength="30" name="proofnumber" ng-model="fplProfile.fplproofNum" placeholder="Proof Number" class="i-input i-proof proonum img" id="i-proof" value=""></td>
                </tr>
                <tr>
                   <td><input type="text" id="alternativeemail" maxlength="30" name="Altemail" placeholder="Alternative emailid" ng-model="fplProfile.alternativeemail" class="i-input"  ></td>
                </tr>
                <tr>
        		<td colspan='2'>
			    	<span id="fplType_error1" style="padding-left:10px; color:red; display:none;"> 
              				* The highlighted information is required. Kindly complete/fill the relevant details to proceed 
      				</span> 			
        		</td>
        		</tr>
                
             
            </table>
            
            <input type="button" name="bt-save" style="top:70px; left:80px;" value="Continue" class="bt-end bt-cont" ng-click="fplProfile.formId=2;fplProfile.loadFPLOrg(fplProfile)">
           <input type="button" name="bt-cancel" style="top:70px; left:130px;" value="Cancel" class="bt-end">
       </div>
		
	</div>
	
	<div class="formsection" id="form2" style="display:none;">
	
	   
    	<div class="f-indicator">
        	<img src="support/images/fpregorg_2.png">
        </div>
    
        <div class="identify id1" name="identify" id="form1">
            <table id="tab2">
            <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" value="" class="i-input"   ng-model="fplProfile.fplIdText" disabled></td>
                    <td><input type="text" name="regate" placeholder="Registration Date"  class="i-input" value="{{fplProfile.formatDate(fplProfile.fplCreationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>
             <tr>
	                <td><input type="text" id="postal" maxlength="6" name="postal" placeholder="Postal Code" value="" class="i-input post img" ng-model="fplProfile.postalCode" ng-change="fplProfile.fetchAddress(fplProfile)" ></td>
                    <td><input type="text" id="unit" maxlength="30" name="unit" placeholder="Unit Number" value="" class="i-input unum img" ng-model="fplProfile.fplstate"></td>                	
                </tr>
                <tr>
                    <td><input type="text" name="block" maxlength="30" placeholder="Block Number" value="" class="i-input blkn " ng-model="fplProfile.block"></td>            	             
                    <td><input type="text" name="building" maxlength="30" placeholder="Building Name" value="" class="i-input bldn" ng-model="fplProfile.fplbuildingName"></td>
                </tr>
                <tr>
                    <td><input type="text" name="street" maxlength="30" placeholder="Street" class="i-input" value="" ng-model="fplProfile.street"></td>
                     <td><input type="text" name="country"  maxlength="30" placeholder="Country" value="" class="i-input img" ng-model="fplProfile.country"> </td>
                </tr>
               
              <tr> <td><input type="text" placeholder="+65" class="i-input" disabled style="width:20%;padding-left:10px;"><input style="width:78%;margin-left:2px;" type="text" name="mobnum" maxlength="10" placeholder="Mobile Number" value="" class="i-input mobnum img" ng-model="fplProfile.fplmobile" id="mobnum" ></td>
                    <td><input type="text" placeholder="+65" class="i-input" disabled style="padding-left: 10px;width:20%;"><input type="text" name="landline" placeholder="Landline Number" maxlength="10" value="" class="i-input llnum"  style="width:78%;margin-left:2px;" ng-model="fplProfile.fpllandLine" id="landline"></td>
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
           
            <input type="button" name="bt-save" value="Continue"  style="top:80px;" class="bt-end bt-cont" ng-click="fplProfile.formId=3;fplProfile.loadFPLOrg(fplProfile)">
             <input type="button" name="bt-cancel" value="Back"  style="top:80px;" class="bt-end" onClick="load(1);">
            <input type="button" name="bt-cancel" value="Cancel"  style="top:80px;" class="bt-end bt-cancel">
       </div>
	   
	</div>
	
	<div class="formsection" id="form3" style="display:none;">
	
	      	<div class="f-indicator">
        	<img src="support/images/fpregorg_3.png">
        </div>
    
        <div class="identify id1" name="identify" id="form1">
           <table id="tab3" style="top:250px;">
           <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" class="i-input" ng-model="fplProfile.fplIdText" disabled ></td>
                    <td><input type="text" name="regate" placeholder="Registration Date"  class="i-input" value="{{fplProfile.formatDate(fplProfile.fplCreationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>
      			<tr>
                	<td><input type="text" id="fname" name="fname" maxlength="30" placeholder="First Name" ng-model="fplProfile.secondaryfirstName" value="" class="i-input fname img"></td>
                    <td><input type="text" id="lname" name="lname"  maxlength="30" placeholder="Last Name" ng-model="fplProfile.secondarylastName" value="" class="i-input lname img"></td>
                </tr>          

               <tr><td>
               <noBr><input type="text" placeholder="+65" class="i-input" disabled style="width:20%;padding-left:10px;">
               <input style="width:78%;margin-left:2px;" maxlength="10" type="text" id="secmobnum" name="mobnum" placeholder="Mobile Number" value="" class="i-input mobnum img" ng-model="fplProfile.secondarymobile">
               </noBr>
               </td>
                    <td>
                    <nobr>
                    <input type="text" placeholder="+65" class="i-input" disabled style="padding-left: 10px;width:20%;">
                    <input type="text" name="landline" maxlength="10" id="seclandline" placeholder="Landline Number" value="" class="i-input llnum"  style="width:78%;margin-left:2px;" ng-model="fplProfile.secondarylandLine">
                    </nobr>
                    </td>
                </tr>          
      			<tr>
                	<td>
                    	<select class="i-input i-id img" id="secidProof" ng-model="fplProfile.secondaryidProof">
                    	<option id="" value="-1">--Select Proof Type--</option>
                         	<option id="nric" value="NRIC">NRIC</option>
                        	<option id="none" value="None">None</option>
                        </select>
                    </td>
                	<td><input type="text"  id="secproofnumber" maxlength="30" name="proofnumber" placeholder="Proof Number"  ng-model="fplProfile.secondaryproofNum" class="i-input i-proof proonum img" id="i-proof" value=""></td>
                </tr>          

                <tr>
                    <td><input type="text" name="position" placeholder="Position" value="" class="i-input pos" ng-model="fplProfile.fplwebsite" maxlength="30"></td>
                    <td><input type="email" id="altmail" maxlength="30" name="altmail" placeholder="Alternate Email" value="Email" class="i-input altmail img" ng-model="fplProfile.secondaryemail"></td>
                </tr>          
      			<tr>
                	<td>
                    	<select class="i-input i-id" ng-model="fplProfile.secondaryPref">
                        	<option value="default">Confidentiality Preference</option>
                        	<option value="pref1">pref1</option>
                        	<option value="pref2">pref2</option>
                        </select>
                    </td>
	                <td>
                    	<input type="text" name="upload" style="width:140px;" class="i-input" value="" placeholder="Profile Picture">
                        <input type="button" name="uploadbtn" style="top:345px;" class="i-upload" value="Upload" onClick="window.open('test/image-preview/index.html', '',' width=500, height=500');">
                    </td>
                    
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
           
            <input type="submit" name="bt-save" style="top:50px;" value="Save" class="bt-end bt-cont">
             <input type="button" name="bt-cancel" style="top:50px;" value="Back" class="bt-end" onClick="load(2);">
             <input type="button" name="bt-cancel" style="top:50px;" value="Cancel" class="bt-end bt-cancel">
          
       </div>
       <div name="substatus" class="substatus" style="top:580px;">
       		<table id="tab4">
            	<tr>
                	<td class="i-input" style="border: 0;">Validation Status</td>
                    <td><input type="text" name="subdisp" disabled class="i-input subdisp" ng-model="fplProfile.validationStatusDesc"></td>
                </tr>
                <tr>
                	<td class="i-input" style="border: 0;">Validation Date</td>
                    <td><input type="text" name="subdate" disabled class="i-input subdate" value="{{fplProfile.formatDate(fplProfile.validationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>
                 <tr>
                	<td>
                		<input type="button" id="SUBSCRIBE" name="subbtn" style="top:10px;" value="Subscribe Now" class="bt-end bt-cont bt-sub" ng-click="fplProfile.userType=2;fplProfile.subscribe(fplProfile)">
                	
                	</td>
                </tr>
            </table>
       </div>
	  
	</div>
	<div id="successMSG" class="f-confirm" style="display:none;">
		<p id="Content" style="font-family: SansRegular; font-size: 15px;"></p>
		<input type="button" onClick="document.getElementById('successMSG').style.display='none';" value="Ok" class="f-submit" style="top:150px;"/>
	</div>
	</form>
</div>	