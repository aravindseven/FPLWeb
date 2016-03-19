<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- FIRST SECTION START -->
<div class="innerBorderDiv">
    <div class="form-group">
      	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Request Number</label>
      	<div class="col-lg-6 col-md-6 col-sm-6">
          	<input type="number" class="form-control" name="requestId" required="required" value="${newPolicyParam.requestId}"/>
    	</div>
	</div>
	<div class="form-group">
		<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Policy number</label>
    	<div class="col-lg-6 col-md-6 col-sm-6">
    		<input type="text"  class="form-control" name="policyNumber" required="required" value="${newPolicyParam.policyNumber}"/>
         </div>	
    </div>
    <div class="form-group">
     	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Policy Type</label>
     	<div class="col-lg-6 col-md-6 col-sm-6">
			<select class="form-control" name="domainId">
				<option value="">--Select--</option>
				<c:forEach var="domain" items="${DomainList}">
					<fmt:parseNumber var="selectedDomain" type="number" value="${newPolicyParam.domainId}" />
					<c:choose>
						<c:when test="${domain.id eq selectedDomain}">
							<option value="${domain.id}" selected="selected">${domain.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${domain.id}">${domain.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
	</div>
    <div class="form-group">
     	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Policy Date</label>
     	<div class="col-lg-6 col-md-6 col-sm-6">
         	<input type="date" class="form-control" name="policyDate" required="required" value="${newPolicyParam.policyDate}"/>
    	</div>	
	</div>
	<div class="form-group">
		<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Policy duration </label>
    	<div class="col-lg-6 col-md-6 col-sm-6">
    		<input type="number"  class="form-control" name="duriation" required="required" value="${newPolicyParam.duriation}"/>
    	</div>	
	</div>
</div> <!-- FIRST SECTION END -->  
                 
		    <!-- SECOND SECTION START -->
<div class="innerBorderDiv">
	<div class="form-group">
		<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Premium Frequency</label>
			<div class="col-lg-6 col-md-6 col-sm-6">
		    	<input type="number" class="form-control" name="frequency" value="${newPolicyParam.frequency}"/>
		    </div>	
	</div>
	<div class="form-group">
      	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Premium amount</label>
      	<div class="col-lg-6 col-md-6 col-sm-6">
          	<input type="number" class="form-control" name="amount" value="${newPolicyParam.amount}"/>
        </div>	
	</div>
    <div class="form-group">
    	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Renewal date</label>
    	<div class="col-lg-6 col-md-6 col-sm-6">
        	<input type="date" class="form-control" name="renewalDate" value="${newPolicyParam.renewalDate}"/>
        </div>	
    </div>
    <div class="form-group">
    	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Advance Alert</label>
    	<div class="col-lg-6 col-md-6 col-sm-6">
    	<c:choose>
    		<c:when test="${newPolicyParam.advanceAlert eq '0'}">
    			<input type="checkbox" class="form-control" name="advanceAlert" value="0" checked="checked" />
    		</c:when>
    		<c:otherwise>
    			<input type="checkbox" class="form-control" name="advanceAlert" value="0" />
    		</c:otherwise>
    	</c:choose>
    	</div>
    </div>
    <div class="form-group">
    	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Alert type</label>
        <div class="col-lg-6 col-md-6 col-sm-6">
        	<input type="text"  class="form-control" name="alertType" value="${newPolicyParam.alertType}"/>
        </div>
    </div>
</div> <!-- SECOND SECTION END -->
