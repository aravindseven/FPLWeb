<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form action="adminProfileCreation.do" method="post" class="col-sm-12">
	<jsp:include page="/include/profile/AdminPersonalData.jsp"
		flush="true" />
	<!-- Secondary person detail -->
	<jsp:include page="/include/profile/AdminContactInfo.jsp"
		flush="true" />
</form>
