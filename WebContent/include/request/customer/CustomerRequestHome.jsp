<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="innerWrapper">
	<div class="row innerContentWrapper myProfileWrapper">
    	<div class="tabbable">
			<div class="titleHeaderBlue">Request</div>
        	<div class="tab-content clear paddingLnone">
          		<div id="request" class="tab-pane fade active in paddingLnone profileTabContent">
            		<div class="col-lg-12 col-md-12 col-sm-12 paddingLnone" ng-controller="RequestController as uRequest" style="min-height:150px">
            			<jsp:include page="/include/request/customer/ViewRequest.jsp" flush="true" />
            			<jsp:include page="/include/request/customer/ActivityHistory.jsp" flush="true" />
            			<jsp:include page="/include/request/customer/CustRequestSideMenu.jsp" flush="true" />
            			<c:choose>
            				<c:when test="${RequestDisplayCriteria eq 'Search'}">
            					<jsp:include page="/include/request/customer/CustRequestList.jsp" flush="true" />
            				</c:when>
            				<c:when test="${RequestDisplayCriteria eq 'Create'}">
            					<jsp:include page="/include/request/customer/RequestCreation.jsp" flush="true" />
            				</c:when>
            				<c:when test="${RequestDisplayCriteria eq 'FPLSelect'}">
            					<jsp:include page="/include/request/customer/FPLSelection.jsp" flush="true" />
            				</c:when>
            			</c:choose>	
            		</div>
          		</div>
        	</div>
      	</div>
	</div>
</div>
