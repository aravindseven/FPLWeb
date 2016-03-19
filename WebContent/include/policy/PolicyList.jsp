<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script language="JavaScript">
     	function submitPolicyList(form,actionURL,policyId){
     		document.getElementById("policyId").value = policyId;
           	form.action = actionURL;
            form.submit();
     	}
</script>

<div class="col-lg-10 col-md-10 col-sm-10 requestGrid">
	<form name="PolicyListForm" method="post">
	<table class="table table-hover">
    	<thead>
        	<tr>
            	<th>Policy Number</th>
            	<th>Request Number</th>
                <th>Date</th>
                <th>Type</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="policyView" items="${PolicyList}">
	        	<tr>
		            <td>${policyView.policyNumber}</td>
		            <td>${policyView.requestId}</td>
		            <td>${policyView.policyDate}</td>
		            <td>${policyView.type}</td>
		            <td>${policyView.status}</td>
		           
		            <td>
		            	<a href="#" ng-click="uPolicy.viewPolicy(${policyView.policyNumber})"><img src="support/images/view.png" title="View" class="cursorP"/></a>
		            	<a href="#" onclick="submitPolicyList(document.PolicyListForm,'initNewPolicy.do','${policyView.id}')"><img src="support/images/edit.png" class="marginLten cursorP" title="Edit" /></a>
		            	<c:if test="${UserLoginInfo.userType.user eq 'cust_individual' or UserLoginInfo.userType.user eq 'cust_corporate'}">
			            	<a href="#" ng-click="uPolicy.acceptPolicy(${policyView.policyNumber})"><img src="support/images/verify.GIF" title="Accept" class="cursorP"/></a>
			            	<a href="#" ng-click="uPolicy.rejectPolicy(${policyView.policyNumber})"><img src="support/images/not_verify.GIF" title="Reject" class="cursorP"/></a>
			            	<a href="#" ng-click="uPolicy.expressRenewalPolicy(${policyView.policyNumber})"><img src="support/images/autoRenewal.png" title="ExpressRenewal" class="cursorP"/></a>
			            	<a href="#" ng-click="uPolicy.renewalPolicy(${policyView.requestId})"><img src="support/images/renewal.png" title="Renewal" class="cursorP"/></a>
		           		</c:if>
		            </td>	
	            </tr>
        	</c:forEach>
    	</tbody>
	</table>
	<input type="hidden" name="policyId" id="policyId">
	</form>
</div>
