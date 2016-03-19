<!-- Header section
    	 Contains the menu items, location selection, and search button
-->
<!-- <div>
<header>
	<div class="logo">
		<a href="index.html"><img src="support/icons/logo.png" style="border-style: none"/></a>
	</div>
	<nav>
		<ul class="n-ico">
			<li><a href="#"><img src="support/icons/works.png" style="border-style: none"></a></li>
			<li><a href="#"><img src="support/icons/resource.png" style="border-style: none"></a></li>
			<li class="toreg"><a href="#"><img src="support/icons/sign_up.png" style="border-style: none"></a></li>
			<li class="b-opener" style="margin-left: -15px; margin-top: 5px;" style="border-style: none"><a
				href="#"><img src="support/icons/login.png" style="border-style: none"></a></li>
		</ul>
		<ul class="n-name">
			<li><a href="#">How it works</a></li>
			<li><a href="#">Resource Center</a></li>
			<li class="toreg"><a href="#">Sign Up</a></li>
			<li class="b-opener" style="margin-left: -15px;"><a href="#">Login</a></li>
		</ul>
	</nav>
	<div class="snc">
		<div class="snc-ser">
			<a href="#"><input type="button" name="search" value="Search"
				class="s-s-input"></a>
		</div>
	</div>
	<div class="h-country">
		<img src="support/icons/sg.png">
		<p>Singapore</p>
	</div>
	<div class="h-counlist h-cl-hide">
		<ul>
			<li><a href="#"><img src="support/icons/at.png" style="border-style: none">
				Austria</a></li>
			<li><a href="#"><img src="support/icons/au.png" style="border-style: none">
				Australia</a></li>
			<li><a href="#"><img src="support/icons/ch.png" style="border-style: none">
				Switzerland</a></li>
			<li><a href="#"><img src="support/icons/cn.png" style="border-style: none">
				China</a></li>
			<li><a href="#"><img src="support/icons/de.png" style="border-style: none">
				Germany</a></li>
			<li><a href="#"><img src="support/icons/id.png" style="border-style: none">
				Indonesia</a></li>
			<li><a href="#"><img src="support/icons/in.png" style="border-style: none">
				India</a></li>
			<li><a href="#"><img src="support/icons/th.png" style="border-style: none">
				Thailand</a></li>
			<li><a href="#"><img src="support/icons/vn.png" style="border-style: none">
				Vietnam</a></li>
		</ul>
	</div>
</header>
</div> -->


<div>
<header>
	<div class="logo">
		<a href="index.html"><img src="support/icons/logo.png" style="border-style: none"/></a>
	</div>
	<nav ng-controller="UserLoginController as uLogin">
		<ul class="n-ico">
			<li><a href="#"><img src="support/icons/works.png" style="border-style: none"></a></li>
			<li><a href="#"><img src="support/icons/resource.png" style="border-style: none"></a></li>
			<li class="toreg"><a href="#"><img src="support/icons/sign_up.png" style="border-style: none"></a></li>
			<li class="b-opener" style="margin-left: -15px; margin-top: 5px;" style="border-style: none"><a
				href="javascript:void(0)"><img src="support/icons/login.png" ng-click="uLogin.loginpage()" style="border-style: none"></a></li>
		</ul>
		<ul class="n-name">
			<li><a href="#">How it works</a></li>
			<li><a href="#">Resource Center</a></li>
			<li class="toreg"><a href="#">Sign Up</a></li>
			<li class="b-opener" style="margin-left: -15px;"><a href="javascript:void(0)" ng-click="uLogin.loginpage()">Login</a></li>
		</ul>
	</nav>
	<div class="snc">
		<div class="snc-ser">
			<a href="#"><input type="button" name="search" value="Search"
				class="s-s-input"></a>
		</div>
	</div>
	<div class="h-country">
		<img src="support/icons/sg.png">
		<p>Singapore</p>
	</div>
	<div class="h-counlist h-cl-hide">
		<ul>
			<li><a href="#"><img src="support/icons/at.png" style="border-style: none">
				Austria</a></li>
			<li><a href="#"><img src="support/icons/au.png" style="border-style: none">
				Australia</a></li>
			<li><a href="#"><img src="support/icons/ch.png" style="border-style: none">
				Switzerland</a></li>
			<li><a href="#"><img src="support/icons/cn.png" style="border-style: none">
				China</a></li>
			<li><a href="#"><img src="support/icons/de.png" style="border-style: none">
				Germany</a></li>
			<li><a href="#"><img src="support/icons/id.png" style="border-style: none">
				Indonesia</a></li>
			<li><a href="#"><img src="support/icons/in.png" style="border-style: none">
				India</a></li>
			<li><a href="#"><img src="support/icons/th.png" style="border-style: none">
				Thailand</a></li>
			<li><a href="#"><img src="support/icons/vn.png" style="border-style: none">
				Vietnam</a></li>
		</ul>
	</div>
</header>
</div>