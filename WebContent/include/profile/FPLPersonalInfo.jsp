<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-6">
    <div  style="position: fixed; z-index: 9999; bottom: 25px; right: 25px; background: none repeat scroll 0% 0% rgb(36, 138, 175); border-radius: 6px 6px 0px 0px; color: rgb(255, 255, 255); font-size: 15px; width: 20%; line-height: 10px; height: 10%;" class="innerBorderDiv">
     <div class="form-group" id="userprogressbar"><b>Profile Completion:</b></div>
     <label class="control-label ng-binding"><progress max="100" id="FPLProgress" value="50"></progress>  {{fplProfile.percentageCompleted.toFixed()}}% </label>
   </div>
	<!-- Personal information start -->
    <div class="innerBorderDiv">
    	<div class="form-group primaryName">
			<label class="control-label">Primary Name</label>
            <input type="text" class="form-control" placeholder="First Name" name="fplfirstName" required="required" id="fplfirstname" ng-model="fplProfile.fplfirstname" disabled />
            <input type="text" class="form-control" placeholder="Last Name" name="fpllastName" required="required" id="fpllastname" ng-model="fplProfile.fpllastname" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Gender</label>
	            <label class="radio-inline">
	            	<input type="radio" id="fplgendermale" value="Male" ng-model="fplProfile.fplgender" disabled/> Male
	            </label>
	            <label class="radio-inline">
	            	<input type="radio" id="fplgenderfemale" value="Female" ng-model="fplProfile.fplgender" disabled/> Female
	            </label>
		</div>
        <div class="form-group">
        	<label class="control-label">Date of Birth</label>
            <input type="date" class="form-control" name="fpldob" required="required" id="fpldob" ng-model="fplProfile.fpldob" disabled /> 
		</div>
        <div class="form-group">
        	<label class="control-label">Identity Proof</label>
            <input type="text" class="form-control" name="fplidProof" required="required" id="fplidProof" ng-model="fplProfile.fplidProof" disabled />
        </div>
        <div class="form-group">
        	<label class="control-label">ID #</label>
            <input type="text"  class="form-control" name="fplproofNum" required="required" id="fplproofNum" ng-model="fplProfile.fplproofNum" disabled />
        </div>
	</div> <!-- Personal information end  -->
	
    <!-- Address information start -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Block / Unit Number</label>
            <input type="text" class="form-control" name="fplblock" id="fplblock" ng-model="fplProfile.fplblock" disabled/>
        </div>
 		<div class="form-group">
        	<label class="control-label">Building Name</label>
            <input type="text"  class="form-control" name="fplbuildingName" id="fplbuildingName" ng-model="fplProfile.fplbuildingName" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Street</label>
            <input type="text" class="form-control" name="fplstreet" id="fplstreet" ng-model="fplProfile.fplstreet" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">City</label>
            <input type="text"  class="form-control" name="fplcity" id="fplcity" ng-model="fplProfile.fplcity" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Postal Code</label>
            <input type="text"  class="form-control" name="fplpostalCode" id="fplpostalCode" ng-model="fplProfile.fplpostalCode" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">State</label>
            <input type="text" class="form-control" name="fplstate" id="fplstate" ng-model="fplProfile.fplstate" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Country</label>
            <input type="text" class="form-control" name="fplcountry" id="fplcountry" ng-model="fplProfile.fplcountry" disabled />
        </div>
    </div>  <!-- Address information end -->
                    
    <!-- Contact information start -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Phone: Mobile</label>
            <input type="number"  class="form-control" name="fplmobile" required="required" id="fplmobile" ng-model="fplProfile.fplmobile" disabled />
        </div>
        <div class="form-group">
        	<label class="control-label">Phone: Landline</label>
            <input type="number"  class="form-control" name="fpllandLine" id="fpllandLine" ng-model="fplProfile.fpllandLine" disabled />
        </div>
        <div class="form-group">
        	<label class="control-label">Email</label>
            <input type="email" class="form-control" name="fplemail" id="fplemail" ng-model="fplProfile.fplemail" disabled />
        </div>
    </div> <!-- Contact information end -->
</div>
