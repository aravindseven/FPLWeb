<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="col-md-6">

    <div class="innerBorderDiv">
        <div class="form-group">
        	<label class="control-label">Contact Name 1</label>
            <input type="text" class="form-control" name="contactPerson1" value="${companyParam.contactPerson1}" required="required" />
        </div>
        <div class="form-group">
        	<label class="control-label">Contact 1 Mobile</label>
            <input type="number" class="form-control" name="persoanNumber1" value="${companyParam.persoanNumber1}" required="required" />
        </div>
        <div class="form-group">
        	<label class="control-label">Contact Name 2</label>
            <input type="text" class="form-control" name="contactPerson2" value="${companyParam.contactPerson2}" required="required" />
        </div>
        <div class="form-group">
        	<label class="control-label">Contact 2 Mobile</label>
            <input type="number" class="form-control" name="persoanNumber2" value="${companyParam.persoanNumber2}" required="required" />
        </div>
    </div>
                      
    <div class="innerBorderDiv">
        <div class="form-group">
        	<label> Comments / Imp Notes </label>
            <textarea class="col-lg-12 col-md-12 col-sm-12 clear form-control" name="note"> ${companyParam.note} </textarea>
         </div>
    	<div class="checkbox clear">
        	<label>
            	<input type="checkbox" name="terms" required="required"/> I have read and accepted the<span class="link">Terms & Conditions</span>
            </label>
        </div>
        <div class="form-group">
        	<button class="btn btn-primary col-lg-2 marginRfifteen">Save</button>
            <button class="btn btn-danger col-lg-2 marginRfifteen">Cancel</button>
            <button class="btn btn-Secondary col-lg-2 marginRfifteen">Reset</button>
        </div>
    </div>
                      
    <div class="innerBorderDiv">
    	<div class="form-group">
        	<div class="form-group">
            	<label class="control-label">Validation Status:</label>
                <label class="control-label">${companyParam.status}</label>
            </div>
            <div class="form-group">
            	<label class="control-label">Validation Date:</label>
                <label class="control-label">${companyParam.updationDate}</label>
            </div>
        </div> 
    </div>
</div>
