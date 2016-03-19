<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar navbar-default" role="navigation">
	<div class="topBar"></div>
	<!-- Header start -->
	<div class="headerWrapper">		
		<div class="logo">
			<a class="navbar-brand" href="/FPLWeb/"></a>
		</div>
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
		</div>
		
		<!-- Top menu bar start -->
		<div class="navbar-collapse collapse pull-right">
			<ul class="nav navbar-nav">
				<li class="active"> <a href="/FPLWeb/">Home </a> </li> 
				<li><a href="howItWorks.jsp">How it works</a> </li>
				<li><a href="ResourceCenter.jsp">Resource center</a></li>
				<li><a href="ResourceCenter.jsp">Help</a></li>
				<li><a href="ContactUs.jsp">Contact Us</a></li>
				<li class="dropdown">
					<a data-toggle="dropdown" href="#">
						FPL Online SG <span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
						<li><a href="#">Option 1</a></li>
						<li><a href="#">Option 2</a></li>
						<li><a href="#">Option 3</a></li>
						<li class="divider"></li>
						<li class="dropdown-header">Option A</li>
						<li><a href="#">Option B</a></li>
						<li><a href="#">Option C</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- Top menu bar end -->
		
		<!-- Tab Nav Start -->
		<div class="pull-left col-lg-9 col-md-8 col-sm-9">
			<div ng-hide="${UserLoginInfo ne null}">
				<ul class="nav nav-tabs" role="tablist">
					<li class="active">
						<a href="#" role="tab" data-toggle="tab" ng-click="uLogin.loginModal()">Customer</a>
					</li>
					<li><a href="#profile" role="tab" data-toggle="tab" ng-click="uLogin.loginModal()">Corporates</a>
					</li>
					<li><a href="#messages" role="tab" data-toggle="tab" ng-click="uLogin.loginModal()">Financial Planners</a>
					</li>
				</ul>
			</div>
			<div class="tab-content">  <!-- Tab panes -->
				<div class="tab-pane active" id="home">
					<div ng-show="${UserLoginInfo ne null}">
						<a href="settings.jsp#personalSettings"> Profile </a>
					</div>
					<c:if test="${UserLoginInfo eq null and UserLoginInfo.userType.user ne 'fp_admin'}">
						<a href="#" ng-click="uLogin.loginModal('initRequest.do')">Request</a>
					 </c:if>
					<c:if test="${UserLoginInfo ne null and UserLoginInfo.userType.user ne 'fp_admin'}">
						<form name="RequestForm" action="initRequest.do" id="RequestFormId" method="post">
							<a href="#" onclick="document.getElementById('RequestFormId').submit();" > Request </a>
						</form>
					</c:if>
					<%-- <div ng-show="${UserLoginInfo ne null and UserLoginInfo.userType.user ne 'fp_admin'}">
						<form name="SearchProfileForm" action="initSearchProfile.do" id="SearchProfileId" method="post">
							<a href="#" onclick="document.getElementById('SearchProfileId').submit();" > Manage Profile </a>
						</form>
					</div> --%>
					<c:if test="${UserLoginInfo eq null and UserLoginInfo.userType.user ne 'fp_admin'}">
						<a href="#" ng-click="uLogin.loginModal('initPolicy.do')">ERecord</a>
					 </c:if>
					<c:if test="${UserLoginInfo ne null and UserLoginInfo.userType.user ne 'fp_admin'}">
						<form name="PolicyForm" action="initPolicy.do" id="PolicyFormId" method="post">
							<a href="#" onclick="document.getElementById('PolicyFormId').submit();" > ERecord </a>
						</form>
					</c:if>	
					
					<c:if test="${UserLoginInfo ne null and UserLoginInfo.userType.user ne 'fp_admin'}">
						<a href="Communication.jsp?type=inbox"> Communications </a>
					</c:if>
					
					<div ng-show="${UserLoginInfo.userType.user eq 'fp_admin'}">
						<a href="FplDB.jsp"> FPL DB </a>
					</div>	
					
					<div ng-show="${UserLoginInfo.userType.user eq 'fp_admin'}">
						<a href="FPIns.jsp"> FP Registration </a>
					</div>
					
					<div ng-show="${UserLoginInfo.userType.user eq 'fp_admin'}">
						<a href="usermgmt.jsp"> Users<br></a>
					</div>
					
					<div ng-show="${UserLoginInfo.userType.user eq 'fp_admin'}">
						<a href="subscriptionmgmt.jsp"> Subscription<br></a>
					</div>
					
					<div ng-show="${UserLoginInfo.userType.user eq 'fp_admin'}">
						<form name="RequestForm" action="initRequest.do" id="RequestFormId" method="post">
							<a href="#" onclick="document.getElementById('RequestFormId').submit();" > Request </a>
						</form>
					</div>
					<div ng-show="${UserLoginInfo.userType.user eq 'fp_admin'}">
						<a href="settings.jsp#personalSettings"> Connect </a>
					</div>		
				</div>
				
			</div> <!-- Tab panes end -->
			
			<div class="loginBtnWrapper" ng-hide="${UserLoginInfo ne null}">
				<div class="register" ng-click="uLogin.register('FP')">Register FP</div>
				<div class="register" ng-click="uLogin.register('Customer')">Register<br> Customer</div>
				<div class="login" ng-click="uLogin.loginModal()">Login</div>
			</div>
			
			<div ng-show="${UserLoginInfo ne null}">
				<ul class="userDetails">
					<li class="dropdown">
						<a data-toggle="dropdown" href="#">
							<span style="color: #fff;">Welcome ${UserLoginInfo.email}</span>
							<span style="color: #fff;" class="glyphicon glyphicon-chevron-down"></span></a>
							<c:if test="${UserLoginInfo.userType.user eq 'cust_individual'}">
								<br><span style="color: #fff;">Customer</span>
							</c:if>
							<c:if test="${UserLoginInfo.userType.user eq 'fp_individual'}">
								<br><span style="color: #fff;">Financial Planners</span>
							</c:if>
							<c:if test="${UserLoginInfo.userType.user eq 'fp_corporate' or UserLoginInfo.userType.user eq 'cust_corporate'}">
								<br><span style="color: #fff;">Corporate</span>
							</c:if>
							<c:if test="${UserLoginInfo.userType.user eq 'fp_admin'}">
								<br><span style="color: #fff;">Admin</span>
							</c:if>
						<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
							<li>
								<a href="settings.jsp">Profile</a>
							</li>
							<li>
								<a href="#">Notifications</a>
							</li>
							<li>
								<a href="#">Reminders</a>
							</li>
							<li class="divider"></li>
							<li>
								<a ng-click="uLogin.logout()" class="logout">Logout</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div> <!-- Tab Nav End -->
		
	</div> <!-- Header End -->
</div> <!-- Header Bar End -->
