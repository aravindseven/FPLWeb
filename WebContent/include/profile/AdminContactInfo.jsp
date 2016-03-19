<div class="col-md-6">

    <!-- Contact information start -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<label class="control-label">Phone: Mobile</label>
            <input type="number"  class="form-control" name="mobile" required="required" 
            		value="${adminParam.personalDataPV.mobile}" readonly="readonly"/>
        </div>
        <div class="form-group">
        	<label class="control-label">Phone: Landline</label>
            <input type="number"  class="form-control" name="landLine" value="${adminParam.personalDataPV.landLine}"/>
        </div>
        <div class="form-group">
        	<label class="control-label">Email</label>
            <input type="email" class="form-control" name="email" value="${adminParam.personalDataPV.email}" readonly="readonly"/>
        </div>
    </div> <!-- Contact information end -->
                   
   	<!-- SECOND - THIRD SECTION START -->
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<div class="form-group">
            	<label class="control-label">Status:</label>
                <label class="control-label">${adminParam.status}</label>
            </div>
            <div class="form-group">
            	<label class="control-label">Date:</label>
                <label class="control-label">${adminParam.validationDate}</label>
            </div>
        </div> 
    </div> <!-- SECOND PERSON - THIRD SECTION END -->
    
    <!-- SECOND - SECOND SECTION START -->
    <div class="innerBorderDiv">
    	<div class="checkbox">
        	<label>
            	<input type="checkbox" name="terms" required="required"/> I have read and accepted the<span class="link">Terms & Conditions</span>
            </label>
        </div>
        <div class="form-group">
        	<button class="btn btn-primary col-lg-2 marginRfifteen">Save</button>
            <button class="btn btn-danger col-lg-2 marginRfifteen">Cancel</button>
            <button class="btn btn-Secondary col-lg-2 marginRfifteen">Reset</button>
        </div>
    </div> <!-- SECOND - SECOND SECTION END -->
                    
 </div>