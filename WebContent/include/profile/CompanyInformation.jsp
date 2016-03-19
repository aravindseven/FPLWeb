<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-6">

	<!-- Company information start -->
    <div class="innerBorderDiv">
	   	<div class="form-group">
	   		<label class="control-label">Name</label>
	       	<input type="text" class="form-control" placeholder="Name" name="name" required="required" value="${companyParam.name}" />
	   </div>
	   	<div class="form-group">
	   		<label class="control-label">Registration number</label>
	       	<input type="text" class="form-control" placeholder="Registration number" name="registration" required="required" 
	       			value="${companyParam.registration}" />
	   	</div>
	   	<div class="form-group">
	   		<label class="control-label">Incorporation Location</label>
	       	<input type="text" class="form-control" placeholder="Incorporation Location" name="location" required="required" 
	       	   		value="${companyParam.location}" />
	   	</div>
        <div class="form-group">
        	<label class="control-label">Website</label>
            <input type="text" class="form-control" name="Website" required="required" value="${companyParam.website}"/>
        </div>
	</div> <!-- Company information end  -->
	
    <!-- Address information start -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Block / Unit Number</label>
            <input type="text" class="form-control" name="block" value="${companyParam.addressPV.block}"/>
        </div>
 		<div class="form-group">
        	<label class="control-label">Building Name</label>
            <input type="text"  class="form-control" name="buildingName" value="${companyParam.addressPV.buildingName}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">Street</label>
            <input type="text" class="form-control" name="street" value="${companyParam.addressPV.street}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">City</label>
            <input type="text"  class="form-control" name="city" value="${companyParam.addressPV.city}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">State</label>
            <input type="text" class="form-control" name="state" value="${companyParam.addressPV.state}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">Country</label>
            <input type="text" class="form-control" name="country" value="${companyParam.addressPV.country}" />
        </div>
    </div>  <!-- Address information end -->
                    
    <!-- Contact information start -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Phone: Mobile</label>
            <input type="number"  class="form-control" name="phNumber1" required="required" value="${companyParam.phNumber1}" />
        </div>
        <div class="form-group">
        	<label class="control-label">Phone: Landline</label>
            <input type="number"  class="form-control" name="phNumber2" value="${companyParam.phNumber1}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">Email</label>
            <input type="email" class="form-control" name="email" value="${companyParam.email}" />
        </div>
        <div class="form-group">
        	<label class="control-label">Fax</label>
            <input type="text" class="form-control" name="fax" value="${companyParam.fax}" />
        </div>
    </div> <!-- Contact information end -->
</div>
