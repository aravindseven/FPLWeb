<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Primary person detail -->

<div id="customerProfile" ng-controller="UserProfileController as uProfile">
	<form  method="post" class="col-sm-12" ng-init="uProfile.fetchCustomerDetails();" ng-submit="uProfile.SaveChanges(uProfile)">
		<jsp:include page="/include/profile/PrimaryCustomerInsert.jsp" flush="true" />
		<!-- Secondary person detail -->
		<jsp:include page="/include/profile/SecondaryCustomerInsert.jsp" flush="true" />
	</form>
</div>