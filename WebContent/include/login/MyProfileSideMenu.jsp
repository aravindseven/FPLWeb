<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-2 col-md-2 col-sm-2">
	<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm settingsSidebar" role="complementary">
		<ul class="nav bs-docs-sidenav">
			<li>
				<a href="#" onclick="$('.innerWrapper').scrollTop(0);"><b>General</b></a>
					<ul class="nav">
						<li class=""><a href="#accountSettings">Account</a></li>
						<li class=""><a href="#personalSettings">User Information</a></li>
						<li class=""><a href="#changePassword">Change Password</a></li>
						<c:if test="${UserLoginInfo.userType.user eq 'cust_individual' or UserLoginInfo.userType.user eq 'cust_corporate'}">
								<li class=""><a href="#myFp">My FP</a></li>
						</c:if>
						<%-- <c:if test="${UserLoginInfo.userType.user eq 'fp_individual' or UserLoginInfo.userType.user eq 'fp_admin' or UserLoginInfo.userType.user eq 'fp_corporate'}">
								<li class=""><a href="#myCustomer">My Customers</a></li>
						</c:if> --%>
					</ul>
			</li>
		</ul>
	</div>
</div>
