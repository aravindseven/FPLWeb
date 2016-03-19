<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-6">
   <div  style="position: fixed; z-index: 9999; bottom: 25px; right: 25px; background: none repeat scroll 0% 0% rgb(36, 138, 175); border-radius: 6px 6px 0px 0px; color: rgb(255, 255, 255); font-size: 15px; width: 20%; line-height: 10px; height: 10%;" class="innerBorderDiv">
     <div class="form-group" id="userprogressbar"><b>Profile Completion:</b></div>
     <label class="control-label ng-binding"><progress max="100" id="customerProgress" value="50"></progress>  {{uProfile.percentageCompleted.toFixed()}}% </label>
   </div>
	<!-- FIRST PERSON - FIRST SECTION START -->
	<div class="innerBorderDiv">
       	<c:choose>
       		<c:when test="${UserLoginInfo.userType.user eq 'cust_individual'}">
		    	<div class="form-group primaryName">
		        	<label class="control-label">Primary Name</label>
		            <input type="text" class="form-control" placeholder="First Name" id="primaryfirstname" required="required" 
		            	   ng-model="uProfile.primaryfirstName" disabled/>
		            <input type="text" class="form-control" placeholder="Last Name" id="primarylastname" required="required" 
		            	   ng-model="uProfile.primarylastName" disabled/>
		        </div>
		        <div class="form-group">
		        	<label class="control-label">Gender</label>
		        	
				            <label class="radio-inline">
				            	<input type="radio" id="primarygendermale" value="Male" ng-model="uProfile.gender" disabled/> Male
				            </label>
				            <label class="radio-inline">
				            	<input type="radio" id="primarygenderfemale" value="Female" ng-model="uProfile.gender" disabled/> Female
				            </label>
		        	
		      	</div>
		        <div class="form-group">
		        	<label class="control-label">Date of Birth</label>
		            <input type="date" class="form-control" id="primarydob" required="required" disabled ng-model="uProfile.dob"/>
		        </div>
       		</c:when>
       		<c:otherwise>
		        <div class="form-group">
		        	<label class="control-label">Name</label>
		            <input type="text" class="form-control" placeholder="Name" id="primaryfirstname" required="required" 
		            	   ng-model="uProfile.primaryfirstname" disabled/>
		        </div>
		        <div class="form-group">
		        	<label class="control-label">Registration number</label>
		            <input type="text" class="form-control" placeholder="Registration number" id="primarylastname" required="required" 
		            	   ng-model="uProfile.primarylastname" disabled/>
		        </div>
		        <div class="form-group">
		        	<label class="control-label">Incorporation Location</label>
		            <input type="text" class="form-control" placeholder="Incorporation Location" id="primarygender" required="required" 
		            	   ng-model="uProfile.gender" disabled/>
		        </div>
       		</c:otherwise>
       	</c:choose>
        <div class="form-group">
        	<label class="control-label">Identity Proof</label>
            <input type="text" class="form-control" id="primaryidproof" required="required" disabled
					ng-model="uProfile.idProof"/>
        </div>
        <div class="form-group">
        	<label class="control-label">ID #</label>
            <input type="text"  class="form-control" id="primaryproofnum" required="required" disabled
            		ng-model="uProfile.proofNum"/>
        </div>
   	</div> <!-- FIRST PERSON - FIRST SECTION END -->
                      
    <!-- FIRST PERSON - SECOND SECTION START -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Block / Unit Number</label>
            <input type="text" class="form-control" id="primaryblock" ng-model="uProfile.block" disabled/>
        </div>
 		<div class="form-group">
        	<label class="control-label">Building Name</label>
            <input type="text"  class="form-control" id="primarybuildingname" ng-model="uProfile.buildingName" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Street</label>
            <input type="text" class="form-control" id="primarystreet" ng-model="uProfile.street" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">City</label>
            <input type="text"  class="form-control" id="primarycity" ng-model="uProfile.city" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Postal Code</label>
            <input type="text"  class="form-control" id="primarypostalcode" ng-model="uProfile.postalCode" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">State</label>
            <input type="text" class="form-control" id="primarystate" ng-model="uProfile.state" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Country</label>
            <input type="text" class="form-control" id="primarycountry" ng-model="uProfile.country" disabled/>
        </div>
    </div> <!-- FIRST PERSON - SECOND SECTION END -->
                      
    <!-- FIRST PERSON - THIRD SECTION START -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Phone: Mobile</label>
            <input type="number"  class="form-control" id="primarymobile" ng-model="uProfile.primarymobile" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Phone: Landline</label>
            <input type="number"  class="form-control" id="primarylandline" ng-model="uProfile.landLine" disabled/>
        </div>
        <div class="form-group">
        	<label class="control-label">Email</label>
            <input type="email" class="form-control" id="primaryemail" ng-model="uProfile.email"  disabled/>
        </div>
    </div> <!-- FIRST PERSON - THIRD SECTION END -->
    
</div> <!-- FIRST PERSON SECTION END -->
