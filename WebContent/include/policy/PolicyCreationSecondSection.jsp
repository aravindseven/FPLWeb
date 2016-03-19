<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script language="JavaScript">
     	function submitMe(form,actionURL){
           	form.action = actionURL;
            form.submit();
     	}
</script>

<div class="innerBorderDiv">
	<div class="form-group secondaryName">
    	<label class="control-label">Contact Name</label>
        <input type="text"  class="form-control" placeholder="First name" name="firstName" required="required" value="${newPolicyParam.firstName}" />
        <input type="text"  class="form-control" placeholder="Last name" name="lastName" required="required" value="${newPolicyParam.lastName}" />
    </div>
    <div class="form-group">
    	<label class="control-label">Mobile Number</label>
        <input type="number" class="form-control" name="contactNumber" required="required" value="${newPolicyParam.contactNumber}" />
    </div>
    <div class="form-group">
    	<label class="control-label">Relation</label>
        <input type="text" class="form-control" name="relation" required="required" value="${newPolicyParam.relation}" />
    </div>
    <div class="form-group">
    	<label class="control-label">Identity Proof</label>
        <input type="text" class="form-control" name="secondaryidproof" required="required" value="${newPolicyParam.idProof}" />
    </div>
</div>
    
<div class="innerBorderDiv">
	<div class="col-lg-8 col-md-8 col-sm-8">
 		<textarea rows="5" cols="20" class="form-control" name="note" wrap="soft" >${newPolicyParam.note}</textarea>
 	</div>
</div>
      
                   
<div class="innerBorderDiv">
	<input type="hidden" name="id" value="${newPolicyParam.id}" />
    <div class="form-group">
    	<button class="btn btn-primary col-lg-2 marginRfifteen" onclick="submitMe(document.PolicyCreationForm,'PolicyCreation.do')">Save</button>
        <button class="btn btn-danger col-lg-2 marginRfifteen" onclick="submitMe(document.PolicyCreationForm,'initPolicy.do')">Cancel</button>
        <button class="btn btn-Secondary col-lg-2 marginRfifteen" onclick="submitMe(document.PolicyCreationForm,'initNewPolicy.do')">Reset</button>
    </div>
</div>
