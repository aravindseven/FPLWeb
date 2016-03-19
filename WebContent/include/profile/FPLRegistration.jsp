<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- MY PROFILE START -->
<div id="fplProfile" ng-controller="FPLProfileController as fplProfile">
	<form  method="post" class="col-sm-12" ng-init="fplProfile.fetchFPLDetails();" ng-submit="fplProfile.SaveFPLChanges(fplProfile);">
		<jsp:include page="/include/profile/FPLPersonalInfo.jsp" flush="true" />
		<jsp:include page="/include/profile/FPLProfessionalDetail.jsp" flush="true" />
	</form>
</div>
	
<!-- MY PROFILE END -->

