<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- MY PROFILE START -->
<div class="innerWrapper">
	<div class="row innerContentWrapper mySettingsWrapper">
		<div class="tabbable">
			<div class="titleHeaderBlue">Profile</div>
			<div class="tab-content clear paddingLnone">
				<div id="profile" class="tab-pane fade active in paddingLnone profileTabContent">
					<div class="col-lg-12 col-md-12 col-sm-12 paddingLnone">
						
						<jsp:include page="/include/login/MyProfileSideMenu.jsp" flush="true" />
						
						<jsp:include page="/include/login/LoginSetting.jsp" flush="true" />
				
						<jsp:include page="/redirectProfileCreation.do" flush="true" />
					
						<jsp:include page="/include/login/ChangePasswordSetting.jsp" flush="true" />
						
						<c:if test="${UserLoginInfo.userType.user eq 'cust_individual' or UserLoginInfo.userType.user eq 'cust_corporate'}">
								<jsp:include page="/include/profile/MyFP.jsp" flush="true" />
						</c:if>
						<c:if test="${UserLoginInfo.userType.user eq 'fp_individual' or UserLoginInfo.userType.user eq 'fp_admin' or UserLoginInfo.userType.user eq 'fp_corporate'}">
								<jsp:include page="/include/profile/MyCustomer.jsp" flush="true" />
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- MY PROFILE END -->