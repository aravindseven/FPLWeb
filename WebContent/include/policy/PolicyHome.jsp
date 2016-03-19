<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="innerWrapper">
	<div class="row innerContentWrapper myProfileWrapper">
    	<div class="tabbable">
			<div class="titleHeaderBlue">E-Records</div>
        	<div class="tab-content clear paddingLnone">
          		<div id="request" class="tab-pane fade active in paddingLnone profileTabContent">
            		<div class="col-lg-12 col-md-12 col-sm-12 paddingLnone" ng-controller="PolicyController as uPolicy" style="min-height:150px">
            			<jsp:include page="/include/policy/ViewPolicy.jsp" flush="true" />
            			<jsp:include page="/include/policy/PolicySideMenu.jsp" flush="true" />
            			<c:choose>
            				<c:when test="${PolicyDisplayCriteria eq 'Search'}">
            					<jsp:include page="/include/policy/PolicyList.jsp" flush="true" />
            				</c:when>
            				<c:when test="${PolicyDisplayCriteria eq 'Create'}">
            					<form name="PolicyCreationForm" id="PolicyCreationFormId" method="post">
            						<jsp:include page="/include/policy/PolicyCreation.jsp" flush="true" />
            					</form>
            				</c:when>
            			</c:choose>	
            		</div>
          		</div>
        	</div>
      	</div>
	</div>
</div>
