<div id="customerProfile" ng-controller="UserProfileController as uProfile">

<form  method="post" class="col-sm-12" ng-init="uProfile.fetchCustomerDetails();" ng-submit="uProfile.SaveChanges(uProfile)">

    	<div class="f-indicator">
        	<img src="support/images/form1_1.png" style="cursor:pointer;">
            <img src="support/images/form1_2.png">
            <img src="support/images/form1_3.jpg">
        </div>
        <div class="identify id1" id="form1">
        	<table id="tab1">
                <tr>
                	<td><input type="text" name="custid" placeholder="Customer ID" value="" class="i-input"  required></td>
                    <td><input type="text" name="regate" placeholder="Registration Date" value="" class="i-input" required></td>
                </tr>
               
                <tr>
                	<td><input type="text" name="firstname" id="primaryfirstname" ng-model="uProfile.primaryfirstName1" placeholder="First Name" value="" class="i-input fname img" required></td>
                    <td><input type="text" name="lastname" id="primarylastname" placeholder="Last Name" ng-model="uProfile.primarylastName" value="" class="i-input lname img" required></td>
                </tr>
                <tr>
                	<td><label>Gender</label><input type="radio" value="Male" name="gender" class="i-radio" required>&nbsp;Male <input type="radio" value="Female" name="gender" class="i-radio">&nbsp;Female</td>
                	<td>
                    	<select class="date d-num">
                        	<option>DD</option>                        	
                        </select>
                        <select class="date d-mon">
	                        <option>MM</option>
                            <option value="jan">January</option>
                            <option value="feb">February</option>
                            <option value="mar">March</option>
                            <option value="apr">April</option>
                            <option value="may">May</option>
                            <option value="june">June</option>
                            <option value="jul">July</option>
                            <option value="aug">August</option>
                            <option value="sep">September</option>
                            <option value="oct">October</option>
                            <option value="nov">November</option>
                            <option value="dec">December</option> 
                        </select>
                        <select class="date d-year">
                        	<option>YYYY</option>
                        </select>
                    </td>


                </tr>
                <tr>
                	<td>
                    	<select class="i-input i-id">
                        	<option value="default"><style="background:#F3F3F3">Proof</style></option>
                        	<option id="nric" value="nric">NRIC</option>
                        	<option id="none" value="none">None</option>
                        </select>
                        <img src="icons/qmark.png" class="qmark" />
                    </td>
                	<td><input type="text" name="proofnumber" placeholder="Proof Number" class="i-input i-proof" id="i-proof" value=""></td>
                </tr>
                <tr>
                    <td>
                    	<input type="text" name="upload" class="i-input" value="" placeholder="Profile Picture">
                        <input type="button" name="uploadbtn" class="i-upload" value="Upload">
                    </td>

                </tr>
                
            </table>
            <input type="submit" name="bt-continue" value="Continue" class="bt-end bt-cont bt-form1">
            <input type="button" name="bt-cancel" value="Cancel" class="bt-end">
        </div>
		<div class="ans"><p>National Registration Identity Card</p></div>
        
</form>        
</div>