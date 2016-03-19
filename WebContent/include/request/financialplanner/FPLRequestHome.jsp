<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="innerWrapper">
	<div class="row innerContentWrapper myProfileWrapper">
    	<div class="tabbable">
			<div class="titleHeaderBlue">Request</div>
        	<div class="tab-content clear paddingLnone">
          		<div id="request" class="tab-pane fade active in paddingLnone profileTabContent">
            		<div class="col-lg-12 col-md-12 col-sm-12 paddingLnone" ng-controller="RequestController as uRequest">
            			<jsp:include page="/include/request/customer/ViewRequest.jsp" flush="true" />
            			<jsp:include page="/include/request/financialplanner/FPLRespond.jsp" flush="true" />
            			
            			<jsp:include page="/include/request/financialplanner/FPLRequestSideMenu.jsp" flush="true" />
            			
            			<jsp:include page="/include/request/financialplanner/FPLRequestList.jsp" flush="true" />
            			
            		</div>
          		</div>
        	</div>
      	</div>
	</div>
</div>
