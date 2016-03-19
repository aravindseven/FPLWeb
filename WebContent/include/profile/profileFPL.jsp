<div id="fplProfile"  ng-controller="FPLProfileController as fplProfile" >
	<form  method="post" name="formFPL" class="col-sm-12" ng-init="fplProfile.fetchFPLDetails();" ng-submit="fplProfile.userType=1;fplProfile.SaveFPLChanges(fplProfile);" novalidate>
	
	<div class="formsection" id="form1" style="display:block; ">
    	<div class="f-indicator">
        	<img src="support/images/fpregorg_1.png">
        </div>
   				
        <div class="identify id1" name="identify" >
        	<table id="tab1">
            <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" value="" class="i-input" ng-model="fplProfile.fplIdText" disabled></td>
                    <td><input type="text" name="regate" placeholder="Registration Date"  class="i-input" value="{{fplProfile.formatDate(fplProfile.fplCreationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>
            	<tr>
                	<td><input type="text"  maxlength="30" name="firstname" id="firstName" placeholder="First Name" value="" class="i-input fname img" ng-model="fplProfile.fplfirstname1" >
                	</td>
					<td><input type="text" maxlength="30" name="lastname" id="lastName" placeholder="Last Name" value="" class="i-input lname img"  ng-model="fplProfile.fpllastname1"></td>                    
                </tr>
                <tr>
                	<td><label>Gender</label><input type="radio" value="Male" name="gender" class="i-radio" ng-model="fplProfile.fplgender">&nbsp;Male <input type="radio" value="Female" name="gender" class="i-radio" ng-model="fplProfile.fplgender">&nbsp;Female</td>
                	<td>
               			<input type="date" class="i-input" id="primarydob" ng-model="fplProfile.fpldob"/>
                     </td>
                </tr>
                <tr>
                	<td>
                    	<select id="proofType" class="i-input i-id img" ng-model="fplProfile.fplidProof">
                    		<option id="PT" value="-1">---Select Proof Type---</option>
                        	<option id="nric" value="NRIC">NRIC</option>
                        	<option id="none" value="None">None</option>
                        </select>
                        <img src="icons/qmark.png" class="qmark" />
                    </td>
                	<td><input type="text" name="proofnumber"  maxlength="30" placeholder="Proof Number" class="i-input i-proof proonum" id="i-proof" value="" ng-model="fplProfile.fplproofNum"></td>
                </tr>
                 <tr>
                    <td>
                    	<input type="text" name="upload" class="i-input" value="" placeholder="Profile Picture">
                        <input type="button" name="uploadbtn" class="i-upload"  style="top:280px; left:420px;" value="Upload" onClick="window.open('test/image-preview/index.html', '',' width=500, height=500');">			
                    </td>
                    <td><input type="text" id="alternativeemail" maxlength="30" name="Altemail" placeholder="Alternative emailid" ng-model="fplProfile.alternativeemail" class="i-input"  ></td>

                </tr>
                        	<tr>
        	<td>
			    <span id="fplType_error" style="padding-left:10px; color:red; display:none;"> 
              				* Please fill required Information. 
      			</span> 			
        	</td>
        	</tr>
                
                
            </table>
            	
            
            <input type="button" name="bt-save" style="top:50px; left:80px;" value="Continue" class="bt-end bt-cont" ng-click="fplProfile.formId=2;fplProfile.loadFPL(fplProfile)">
            <input type="button" name="bt-cancel" style="top:50px; left:130px;" value="Cancel" class="bt-end">
      </div>
</div>

<div class="formsection" id="form2" style="display:none;">

	<div class="f-indicator">
        	<img src="support/images/fpregorg_2.png">
        </div>
    
        <div class="identify id1" name="identify" id="form1">
            <table id="tab2">
            <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" value="" class="i-input" ng-model="fplProfile.fplIdText" disabled ></td>
                    <td><input type="text" name="regate" placeholder="Registration Date"  class="i-input" value="{{fplProfile.formatDate(fplProfile.fplCreationDate) | date:'dd-MMM-yyyy'}}" disabled ></td>
                </tr>
             <tr>
	                <td><input type="text" id="postal" maxlength="6" name="postal" placeholder="Postal Code" value="" class="i-input post img" ng-model="fplProfile.postalCode" ng-change="fplProfile.fetchAddress(fplProfile)"></td>
                    <td><input type="text" id="unit" maxlength="30" name="unit" placeholder="Unit Number" value="" class="i-input unum img" ng-model="fplProfile.fplstate"></td>                	
                </tr>
                <tr>
                    <td><input type="text" name="block" maxlength="30" placeholder="Block Number" value="" class="i-input blkn" ng-model="fplProfile.block"></td>            	             
                    <td><input type="text" name="building" maxlength="30" placeholder="Building Name" value="" class="i-input bldn" ng-model="fplProfile.fplbuildingName"></td>
                </tr>
                <tr>
                    <td><input type="text" name="street" maxlength="30" placeholder="Street" class="i-input" value="" ng-model="fplProfile.street"></td>
     
                  <td><input type="text" name="country" placeholder="Country" maxlength="30" value="Singapore" class="i-input" ng-model="fplProfile.country"> </td>               
                </tr>
                     <tr>
                   <td>
                   		<nobr><input type="text" placeholder="+65" class="i-input" disabled style="width:20%;padding-left:10px;">
                   		<input style="width:78%;margin-left:2px;" type="text" maxlength="10" id="mobnum" name="mobnum" placeholder="Mobile Number" ng-model="fplProfile.fplmobile"  class="i-input mobnum img" >
                   		</nobr>
                   	</td>
                    <td>
                    <nobr>
                    	<input type="text" placeholder="+65" class="i-input" disabled style="padding-left: 10px;width:20%;">
                    	<input type="text" name="landline" maxlength="10" placeholder="Landline Number" id="landline" value="" class="i-input llnum" ng-model="fplProfile.fpllandLine" style="width:78%;margin-left:2px;">
                    </nobr>	
                    	</td>
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
           
            <input type="button" name="bt-save" style="top:80px;" value="Continue" class="bt-end bt-cont" ng-click="fplProfile.formId=3;fplProfile.loadFPL(fplProfile)">
             <input type="button" name="bt-cancel" style="top:80px;" value="Back" class="bt-end" onclick="load(1);">
             <input type="button" name="bt-cancel"  style="top:80px;" value="Cancel" class="bt-end bt-cancel">
       </div>

</div>
<div class="formsection" id="form3" style="display:none;">
	<div class="f-indicator">
        	<img src="support/images/fpregorg_3.png">
        </div>
    
        <div class="identify id1" name="identify" id="form3">
           <table id="tab3" style="top:250px;">
           <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" value="" class="i-input" ng-model="fplProfile.fplIdText" disabled ></td>
                    <td><input type="text" name="regate" placeholder="Registration Date" value="{{fplProfile.formatDate(fplProfile.fplCreationDate) | date:'dd-MMM-yyyy'}}" disabled class="i-input" ></td>
                </tr>
      			<tr>
                	<td><input type="email" name="altmail" placeholder="Alternate Email" value="Email" ng-model="fplProfile.fplemail" class="i-input altmail img" disabled></td>
                    <td><input type="text" name="proregno" maxlength="50" placeholder="Professional Registration Number" id="fplregistration" ng-model="fplProfile.fplregistration" value="" class="i-input regno img" ></td>
                </tr>          
      			<tr>
                	<td><input type="date" name="regentity" placeholder="Registration Entity" ng-model="fplProfile.fplregEntity" value="" class="i-input regent" ></td>
                    <td><input type="date" name="expdate" placeholder="License Expiry Date" id="fplexpiryDate" ng-model="fplProfile.fplexpiryDate" value="" class="i-input expdate img"></td>
                </tr>          
      			<tr>
                	<td>
                    	<select class="i-input cvrg" name="coverage" ng-model="fplProfile.fplcoverage">
                        	<option value="default">Coverage</option>
                            <option value="val1">opt1</option>
                            <option value="val2">opt2</option>
                            <option value="val3">opt3</option>
                        </select>
                    </td>
                    <td><input type="text" maxlength="50" name="inscomp" placeholder="Insurance Company" id="insuranceCompany" ng-model="fplProfile.insuranceCompany" value="" class="i-input inscom img" ></td>
                </tr>          
      			<tr>
                	<td><input type="text" name="spec1" maxlength="50" placeholder="Specialization 1" ng-model="fplProfile.fplspecialization1" value="" class="i-input spec1"></td>
                    <td><input type="text" name="spec2" maxlength="50"  placeholder="Specialization 2" ng-model="fplProfile.fplspecialization2" value="" class="i-input spec2"></td>
                </tr>          
      			<tr>
                	<td><input type="text" name="agenname" maxlength="50" placeholder="Agency Name" ng-model="fplProfile.fplagencyName" value="" class="i-input agname"></td>
	                
                    
                </tr>          
               
            <tr>
        	<td>
			    <span id="fplType_error3" style="padding-left:10px; color:red; display:none;"> 
              				* Please fill required Information. 
      			</span> 			
        	</td>
        	</tr>

            </table>
             <input type="submit" name="bt-save" value="Save" class="bt-end bt-cont" style="top:50px;">
            <input type="button" name="bt-cancel" value="Back" class="bt-end" onclick="load(2);" style="top:50px;">
           
             <input type="button" name="bt-cancel" value="Cancel" class="bt-end bt-cancel" style="top:50px;">
       </Div>
       <div name="substatus" class="substatus" style="top:580px;">
       		<table id="tab4" >
            	<tr>
                	<td class="i-input" style="border: 0;">Validation Status</td>
                    <td><input type="text" name="subdisp" disabled class="i-input subdisp" ng-model="fplProfile.validationStatusDesc"></td>
                </tr>
                <tr>
                	<td class="i-input" style="border: 0;">Validation Date</td>
                    <td><input type="text" name="subdate" disabled class="i-input subdate" value="{{fplProfile.formatDate(fplProfile.validationDate) | date:'dd-MMM-yyyy'}}" disabled></td>
                </tr>
                 <tr>
                	<td><input type="button" id="SUBSCRIBE" name="subbtn" style="top:10px;" value="Subscribe Now" class="bt-end bt-cont bt-sub" ng-click="fplProfile.userType=1;fplProfile.subscribe(fplProfile)"></td>
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
