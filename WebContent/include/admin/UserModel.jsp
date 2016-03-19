<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MY PROFILE START -->
<div class="innerWrapper"   style="min-height:150px">
	<div class="row innerContentWrapper mySettingsWrapper">
		<div class="tabbable">
			<div class="titleHeaderBlue">User Management</div>
			<div class="tab-content clear paddingLnone">
				<div id="User Management" class="tab-pane fade active in paddingLnone profileTabContent">
					<div class="col-lg-12 col-md-12 col-sm-12 paddingLnone">
						<jsp:include page="/include/admin/UserSideMenu.jsp" flush="true" />
						<% String varType = request.getParameter("type");
						if(varType != null && varType.equals("createUser")){%>
							<jsp:include page="/include/admin/createUser.jsp" flush="true" />
						<%}
						 else {%>
							<jsp:include page="/include/admin/searchUser.jsp" flush="true" />
						<%} %>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>