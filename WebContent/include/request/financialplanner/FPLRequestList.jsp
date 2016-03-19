<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		            <td>${requestListPv.description}</td>
		            <td>
		           		<a href="#" ng-click="uRequest.viewRequest(${requestListPv.id})"><img src="support/images/view.png" title="View" class="cursorP"/></a>
		           		<c:if test="${requestListPv.statusDisDesc eq 'ST_00'}">
		           			<a href="#" ng-click="uRequest.acceptRequest(${requestListPv.id})"><img src="support/images/verify.GIF" title="Accept" class="cursorP"/></a>
		           			<a href="#" ng-click="uRequest.rejectRequest(${requestListPv.id})"><img src="support/images/not_verify.GIF" title="Reject" class="cursorP"/></a>
		           		</c:if>	
		           		<c:if test="${requestListPv.statusDisDesc eq 'ST_07'}">
		           			<a href='#'><img src='support/images/erecord.jpg' title="ERecord" class="cursorP"/></a>
		           		</c:if>	            	
		            </td>
	            </tr>
        	</c:forEach>
    	</tbody>
	</table>
</div>
