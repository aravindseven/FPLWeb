<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-2 col-md-2 col-sm-2">
	<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm settingsSidebar" role="complementary" style="line-height:0.2;">
		<ul class="nav bs-docs-sidenav">
			<li>
				<a href="#"><b>FP Type</b></a>
					<ul class="nav">	
						<li class=""><a href="?type=listFPType">List Types</a></li>
						<li class=""><a href="?type=createFPType">Create New</a></li>
					</ul>
				<a href="#"><b>FP Subscription</b></a>
					<ul class="nav">	
						<li class=""><a href="?type=listFPSub">List Subscription</a></li>
						<li class=""><a href="?type=createFPSub">New Subscription</a></li>
					</ul>
				<a href="#" onclick="$('.innerWrapper').scrollTop(0);"><b>Advertisement Type</b></a>
					<ul class="nav">
						<li class=""><a href="?type=listAdv">List Types</a></li>
						<li class=""><a href="?type=createAdv">Create New</a></li>
					</ul>
				<a href="#" onclick="$('.innerWrapper').scrollTop(0);"><b>Advertisement Subscription</b></a>
					<ul class="nav">	
						<li class=""><a href="?type=listSub">List Subscription</a></li>
						<li class=""><a href="?type=createSub">New Subscription</a></li>
					</ul>
				<a href="#"><b>Advertisement Registration</b></a>
				<ul class="nav">	
						<li class=""><a href="?type=advRegistration">Create Registration</a></li>
				</ul>
				
			</li>
		</ul>
	</div>
</div>
