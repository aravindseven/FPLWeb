<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script language="JavaScript">
     	function submitPolicyCreation(form,actionURL,reqId){
     		document.getElementById("EreocrdRequestNumber").value = reqId;
           	form.action = actionURL;
            form.submit();
     	}
</script>

<div class="col-lg-10 col-md-10 col-sm-10 requestGrid">

	<jsp:include page="/include/request/RequestSearchFilter.jsp" flush="true" />
	
	<table class="table table-hover">
    	<thead>
        	<tr>
            	<th>Request ID</th>
                <th>Date</th>
                <th>Type</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="searchRequestResult">
        	<c:forEach var="requestListPv" items="${RequestList}">
	        	<tr>
		            <td>${requestListPv.id}</td>
		            <td>${requestListPv.createdDate}</td>
		            <td>${requestListPv.domainName}</td>
		            <td>${requestListPv.currentStatus}</td>
		            <td>
		            	<a href="#" ng-click="uRequest.viewRequest(${requestListPv.id})"><img src="support/images/view.png" title="View" class="cursorP"/></a>
		            	 <c:if test="${requestListPv.statusDisDesc eq 'ST_04'}">
		            		<a href="#" ng-click="uRequest.editRequest(${requestListPv.id})"><img src="support/images/edit.png" class="marginLten cursorP" title="Edit" /></a>
		            		<a href="#" ng-click="uRequest.deleteRequest(${requestListPv.id})" ng-confirm="Are you sure you want to delete this request?"><img src="support/images/delete.jpg" class="marginLten cursorP" title="Delete"/></a>
		            	 </c:if>	
		            	 <c:if test="${requestListPv.statusDisDesc ne 'ST_04'}">
		            	 	<a href="#" ng-click="uRequest.respondedList(${requestListPv.id})"><img src="support/images/respond.png" class="marginLten cursorP" title="Responded" /></a>
		            	 </c:if>	            	
		            	<a href="#" ng-click="uRequest.activityHistory(${requestListPv.id})"><img src="support/images/history.png" class="marginLten cursorP" title="History" /></a>
		            	<c:if test="${UserLoginInfo.userType.user eq 'fp_admin'}">
		            	 	<a href="#" ng-click="uRequest.respondedList(${requestListPv.id})"><img src="support/images/forward.png" class="marginLten cursorP" title="Assign" /></a>
		            	 </c:if>
		            </td>
	            </tr>
	            <tr><td colspan='5'>
	            	<div id="respondReqDiv${requestListPv.id}">
	            	</div>
	            </td></tr>
        	</c:forEach>
    	</tbody>
	</table>
	<form action="" name="PolicyCreationForward" method="post">
		<input type="hidden" name="EreocrdRequestNumber" id="EreocrdRequestNumber" />
	</form>
</div>
