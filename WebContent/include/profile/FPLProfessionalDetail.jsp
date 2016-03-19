<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-6">

<!-- SECOND - FIRST SECTION START -->
	<div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Professional Reg #</label>
            <input type="text" class="form-control" placeholder="Professional Registration" name="fplregistration" required="required" 
            	   id="fplregistration" ng-model="fplProfile.fplregistration" disabled />
        </div>
        <div class="form-group">
        	<label class="control-label">Registration Entity</label>
            <input type="date" class="form-control" name="fplregEntity" required="required" 
            	   id="fplregEntity" ng-model="fplProfile.fplregEntity" disabled />
        </div>
        <div class="form-group">
        	<label class="control-label">Expiry Date</label>
            <input type="date" class="form-control" name="fplexpiryDate" required="required" 
            	   id="fplexpiryDate" ng-model="fplProfile.fplexpiryDate" disabled />
        </div>
       	<div class="form-group">
        	<label class="control-label">Coverage</label>
            <input type="text" class="form-control" placeholder="Coverage" name="fplcoverage" required="required" 
            	   id="fplcoverage" ng-model="fplProfile.fplcoverage" disabled />
        </div>
        <div class="form-group">
        	<label class="control-label">Principal</label>
            <select class="form-control" name="fpldomainList" id="fpldomainList"  disabled>
            <!--	<select class="form-control" name="fpldomainList" id="fpldomainList" ng-model='fplProfile.fpldomain' ng-options="item.name as item.label for item in fplProfile.fpldomainList" disabled></select>-->
            	<!--  <c:forEach var="domain" items="${financialPlannerParam.domains}">
            		<option value="${domain.name}">${domain.name}</option>
            	</c:forEach> -->
            </select>
        </div>
        <div class="form-group">
        	<label class="control-label">Specialization 1</label>
            <input type="text" class="form-control" placeholder="Specialization" name="fplspecialization1" required="required" 
            	  id="fplspecialization1" ng-model="fplProfile.fplspecialization1" disabled>
        </div>
        <div class="form-group">
        	<label class="control-label">Specialization 2</label>
            <input type="text" class="form-control" placeholder="Specialization" name="fplspecialization2" required="required" 
            	   id="fplspecialization2" ng-model="fplProfile.fplspecialization2" disabled>
        </div>
        <div class="form-group">
        	<label class="control-label">Agency Name</label>
            <input type="text" class="form-control" placeholder="Agency Name" name="fplagencyName" required="required" 
            	  id="fplagencyName" ng-model="fplProfile.fplagencyName" disabled>
        </div>
        <div class="form-group">
        	<label class="control-label">Website</label>
            <input type="text" class="form-control" placeholder="Website" name="fplwebsite" required="required" 
            	  id="fplwebsite" ng-model="fplProfile.fplwebsite" disabled>
        </div>
	</div>  <!-- SECOND - FIRST SECTION END -->
                   
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Block / Unit Number</label>
            <input type="text" class="form-control" name="fplsecondaryblock" id="fplsecondaryblock" ng-model="fplProfile.fplsecondaryblock" disabled>
        </div>
 		<div class="form-group">
        	<label class="control-label">Building Name</label>
            <input type="text"  class="form-control" name="fplsecondarybuildingname" id="fplsecondarybuildingname" ng-model="fplProfile.fplsecondarybuildingname" disabled>
        </div>
        <div class="form-group">
        	<label class="control-label">Street</label>
            <input type="text" class="form-control" name="fplsecondarystreet" id="fplsecondarystreet" ng-model="fplProfile.fplsecondarystreet" disabled>
        </div>
        <div class="form-group">
        	<label class="control-label">City</label>
            <input type="text"  class="form-control" name="fplsecondarycity" id="fplsecondarycity" ng-model="fplProfile.fplsecondarycity" disabled>
        </div>
        <div class="form-group">
        	<label class="control-label">Postal Code</label>
            <input type="text"  class="form-control" name="fplsecondarypostalcode" id="fplsecondarypostalcode" ng-model="fplProfile.fplsecondarypostalcode" disabled>
        </div>
        <div class="form-group">
        	<label class="control-label">State</label>
            <input type="text" class="form-control" name="fplsecondarystate" id="fplsecondarystate" ng-model="fplProfile.fplsecondarystate" disabled>
        </div>
        <div class="form-group">
        	<label class="control-label">Country</label>
            <input type="text" class="form-control" name="fplsecondarycountry" id="fplsecondarycountry" ng-model="fplProfile.fplsecondarycountry" disabled>
        </div>
    </div> <!-- SECOND PERSON - SECOND SECTION END -->
    
    <!-- SECOND - THIRD SECTION START -->
    <div class="innerBorderDiv">
    	 <div class="form-group">
			<button id="ProfileEditButton"  class="btn btn-primary col-lg-2 marginRfifteen" ng-show="fplProfile.showFPLProfileEdit">Edit</button>
        	<button id="ProfileSaveButton" class="btn btn-primary col-lg-2 marginRfifteen" ng-show="fplProfile.showFPLProfileSave">Save</button>
            <button class="btn btn-danger col-lg-2 marginRfifteen">Cancel</button>
            <button class="btn btn-Secondary col-lg-2 marginRfifteen">Reset</button>
        </div>
    </div> <!-- SECOND - THIRD SECTION END -->
</div>