<div ng-controller="UserLoginController as uLogin">
	<form name="loginForm" ng-submit="uLogin.login(credentials)" novalidate autocomplete="off">
		<!-- Login Form and forgot Password-->
	  		<div class="b-form b-f-hide">
	          	<div id="logimg"></div>
	          	<div id='loginFailed' style='display:none'>
	          			<p id="prompt" style="color:red;position:absolute;left:50%;transform:translateX(-50%);-webkit-transform:translateX(-50%);font-size:14px;top:75%;z-index:999999;font-family:SansBold;">
	          				Invalid login details
	          			</p>
	          		</div>
	              <div id="b-f-cright"><p>All rights reserved to FPL 2015</p></div>
	              <p class="b-closer">X</p>
	              <input type="email" class="b-f-usn" name="username" id="username" ng-model="credentials.username" placeholder="Email id" ng-keyup="uLogin.loginValid()" required/>
	              <input type="password" class="b-f-pwd" name="password" id="password" ng-model="credentials.password" placeholder="Password" ng-keyup="uLogin.loginValid()" required/>
	              <button type="submit" class="b-f-log" ng-disabled="!loginForm.$valid" ng-enabled="loginForm.$valid">Login</button>
	              <footer>
	          		<ul>
	          	    	<li class="getpwd"><a href="#">Forgot Password</a></li>
	      	            <li class="toreg"><a href="#">New User?</a></li>
	  	            </ul>
	           </footer>
	        </div>
	</form>
</div>
<div class="fgtpwd fp-hide" ng-controller="PasswordRecoveryController as pRecovery" id="passwordRecovery">
 	<p>X</p>
 	<h3>Enter your Email to recieve the link to retrieve your password</h3>
     <form method="post" name="PasswordRecoveryForm" ng-submit="pRecovery.Recovery(credentials)" autocomplete="off">
     	<input type="email" class="fp-input" name="Username" class="form-control" placeholder="Email" ng-model="credentials.username" autofocus required />
      	<input type="submit" class="fp-submit" value="SUBMIT">
     </form>
</div>