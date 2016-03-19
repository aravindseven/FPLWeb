<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-6">

	<!-- SECOND PERSON - FIRST SECTION START -->
    <div class="innerBorderDiv">
    	<div class="form-group secondaryName">
        	<label class="control-label">Secondary Name</label>
            <input type="text"  class="form-control" placeholder="First name" id="secondaryfirstname" required="required" disabled
            		ng-model="uProfile.secondaryfirstName"/>
            <input type="text"  class="form-control" placeholder="Last name" id="secondarylastname" required="required" disabled
            		ng-model="uProfile.secondarylastName" />
        </div>
        <div class="form-group">
        	<label class="control-label">Secondary: Mobile Number</label>
            <input type="number" class="form-control" id="secondarymobile" required="required" disabled
            ng-model="uProfile.secondarymobile" />
        </div>
        <div class="form-group">
        	<c:choose>
        		<c:when test="${UserLoginInfo.userType.user eq 'cust_individual'}">
        			<label class="control-label"  disabled>Relationship</label>
        		</c:when>
        		<c:otherwise>
        			<label class="control-label" disabled>Position</label>
        		</c:otherwise>
        	</c:choose>
            <input type="text" class="form-control" id="secondaryrelation" required="required" disabled
            		ng-model="uProfile.secondaryrelation" />
        </div>
        <div class="form-group">
        	<label class="control-label">Identity Proof</label>
            <input type="text" class="form-control" id="secondaryidproof" required="required" disabled
            		ng-model="uProfile.secondaryidProof" />
        </div>
        <div class="form-group">
        	<label class="control-label">ID #</label>
            <input type="text" class="form-control" id="secondaryproofnum" required="required" disabled
            		ng-model="uProfile.secondaryproofNum" />
        </div>
    </div> <!-- SECOND PERSON - FIRST SECTION END -->
    
     <!-- SECOND PERSON - SECOND SECTION START -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Block / Unit Number</label>
            <input type="text" class="form-control" id="secondaryblock" ng-model="uProfile.secondaryblock" disabled/>
        </div>
 		<div class="form-group">
        	<label class="control-label">Building Name</label>
            <input type="text"  class="form-control" id="secondarybuildingname" ng-model="uProfile.secondarybuildingName" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Street</label>
            <input type="text" class="form-control" id="secondarystreet" ng-model="uProfile.secondarystreet" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">City</label>
            <input type="text"  class="form-control" id="secondarycity" ng-model="uProfile.secondarycity" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Postal Code</label>
            <input type="text"  class="form-control" id="secondarypostalcode" ng-model="uProfile.secondarypostalCode" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">State</label>
            <input type="text" class="form-control" id="secondarystate" ng-model="uProfile.secondarystate" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Country</label>
            <input type="text" class="form-control" id="secondarycountry" ng-model="uProfile.secondarycountry" disabled/>
        </div>
    </div> <!-- SECOND PERSON - SECOND SECTION END -->
                      
    <!-- SECOND - THIRD SECTION START -->
    <div class="innerBorderDiv">
        <div class="form-group">
        	<button id="ProfileEditButton"  class="btn btn-primary col-lg-2 marginRfifteen" ng-show="uProfile.showProfileEdit">Edit</button>
        	<button id="ProfileSaveButton" class="btn btn-primary col-lg-2 marginRfifteen" ng-show="uProfile.showProfileSave">Save</button>
            <button class="btn btn-danger col-lg-2 marginRfifteen">Cancel</button>
            <button class="btn btn-Secondary col-lg-2 marginRfifteen" ng-click="uProfile.reset(uProfile)">Reset</button>
        </div>
    </div> <!-- SECOND PERSON - THIRD SECTION END -->
    
</div>
