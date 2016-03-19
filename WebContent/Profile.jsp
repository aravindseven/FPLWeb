<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="personalSettings" class="col-lg-10 col-md-10 col-sm-10 profileTabForm alternativeForm">
	<div class="row">
		<div class="col-md-12 paddingNone">
			<div class="col-lg-6 col-md-6 col-sm-6">
				<h3>User Information</h3>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<h3></h3>
			</div>
		</div>
		<hr class="clear">
		<c:choose>
			<c:when test="${UserLoginInfo.userType.user eq 'cust_individual'}">
				<jsp:include page="/include/profile/profileCustomer.jsp" flush="true" />
			</c:when>
			<c:when test="${UserLoginInfo.userType.user eq 'cust_corporate'}">
				<jsp:include page="/include/profile/profileCustomerOrganization.jsp" flush="true" />
			</c:when>
			
			<c:when test="${ UserLoginInfo.userType.user eq 'fp_individual'}">
				<jsp:include page="/include/profile/profileFPL.jsp" flush="true" />
			</c:when>
			<c:when test="${UserLoginInfo.userType.user eq 'fp_corporate'}">
				<jsp:include page="/include/profile/profileFPLOrganization.jsp" flush="true" />
			</c:when>
			<c:when test="${UserLoginInfo.userType.user eq 'fp_admin'}">
				<jsp:include page="/include/profile/AdminProfileCreation.jsp" flush="true" />
			</c:when>
		</c:choose>
	</div>
</div>
