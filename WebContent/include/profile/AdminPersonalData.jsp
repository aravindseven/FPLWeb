<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-6">

	<!-- Personal information start -->
    <div class="innerBorderDiv">
    	<div class="form-group primaryName">
			<label class="control-label">Primary Name</label>
            <input type="text" class="form-control" placeholder="First Name" name="firstName" required="required" 
            	   value="${adminParam.personalDataPV.firstName}" />
            <input type="text" class="form-control" placeholder="Last Name" name="lastName" required="required" 
            	   value="${adminParam.personalDataPV.lastName}" />
        </div>
        <div class="form-group">
        	<label class="control-label">Gender</label>
        	<c:choose>
				<c:when test="${adminParam.personalDataPV.gender eq 'Male'}">
		            <label class="radio-inline">
		            	<input type="radio" name="gender" value="Male" checked="checked"/> Male
		            </label>
		            <label class="radio-inline">
		            	<input type="radio" name="gender" value="Female" /> Female
		            </label>
				</c:when>
 				<c:when test="${adminParam.personalDataPV.gender eq 'Female'}">
		            <label class="radio-inline">
		            	<input type="radio" name="gender" value="Male" /> Male
		            </label>
		            <label class="radio-inline">
		            	<input type="radio" name="gender" value="Female" checked="checked" /> Female
		            </label>
				</c:when>
        		<c:otherwise>
		            <label class="radio-inline">
		            	<input type="radio" name="gender" value="Male" /> Male
		            </label>
		            <label class="radio-inline">
		            	<input type="radio" name="gender" value="Female" /> Female
		            </label>
        		</c:otherwise>
        	</c:choose>
		</div>
        <div class="form-group">
        	<label class="control-label">Date of Birth</label>
            <input type="date" class="form-control" name="dob" required="required" 
            		value="${adminParam.personalDataPV.dob}"/>
		</div>
        <div class="form-group">
        	<label class="control-label">Identity Proof</label>
            <input type="text" class="form-control" name="idProof" required="required"
					value="${adminParam.personalDataPV.idProof}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">ID #</label>
            <input type="text"  class="form-control" name="proofNum" required="required"
            		value="${adminParam.personalDataPV.proofNum}"/>
        </div>
	</div> <!-- Personal information end  -->
	
    <!-- Address information start -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Block / Unit Number</label>
            <input type="text" class="form-control" name="block" value="${adminParam.addressPV.block}"/>
        </div>
 		<div class="form-group">
        	<label class="control-label">Building Name</label>
            <input type="text"  class="form-control" name="buildingName" value="${adminParam.addressPV.buildingName}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">Street</label>
            <input type="text" class="form-control" name="street" value="${adminParam.addressPV.street}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">City</label>
            <input type="text"  class="form-control" name="city" value="${adminParam.addressPV.city}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">Postal Code</label>
            <input type="text"  class="form-control" name="postalCode" value="${adminParam.addressPV.postalCode}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">State</label>
            <input type="text" class="form-control" name="state" value="${adminParam.addressPV.state}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">Country</label>
            <input type="text" class="form-control" name="country" value="${adminParam.addressPV.country}" readonly="readonly"/>
        </div>
    </div>  <!-- Address information end -->
</div>
