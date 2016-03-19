<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="policyCreation" class="col-lg-10 col-md-10 col-sm-10 profileTabForm">
	<div class="row">
		<div class="col-md-12 paddingNone">
			<div class="col-lg-6 col-md-6 col-sm-6">
				<h3>New Policy</h3>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<h3></h3>
			</div>
		</div>
		<hr class="clear">
		<div class="col-md-6">
			<jsp:include page="/include/policy/PolicyCreationFirstSection.jsp" flush="true" />
		</div>
		<div class="col-md-6">
			<jsp:include page="/include/policy/PolicyCreationSecondSection.jsp" flush="true" />
		</div>
	</div>
</div>
