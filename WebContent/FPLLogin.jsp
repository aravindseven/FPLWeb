<!-- <div ng-controller="UserLoginController as uLogin">
	<form name="loginForm" ng-submit="uLogin.login(credentials)" novalidate autocomplete="off">
		Login Form and forgot Password
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
</div> -->

<!DOCTYPE html>
<html lang="en">

<head>

<!-- title and meta -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<meta name="description" content="In this lab, we make a responsive content slider jQuery plugin" />
<title>FPL Login</title>
    
<!-- css -->
<link href='http://fonts.googleapis.com/css?family=Ubuntu:300,400,700,400italic' rel='stylesheet' type='text/css' >
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="support/css/base.css" />
<link rel="stylesheet" href="support/css/style.css" />
<link href="support/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="support/css/styless.css" />
<link rel="stylesheet" href="support/css/font-awesome/css/font-awesome.min.css" />
    
<!-- js -->
<script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
<script src="support/js/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="support/js/index.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		
		<script type="text/javascript" src="support/script/custom/app.js"></script>
         <script src="https://www.google.com/recaptcha/api.js?onload=vcRecaptchaApiLoaded&render=explicit" async defer></script>
		<!-- <script src='https://www.google.com/recaptcha/api.js'></script> -->
		<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.0/angular-messages.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script>
		
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.center.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/controller/UserLoginController.js"></script>
		<script type="text/javascript" src="support/controller/UserNameRecoveryController.js"></script>
		<script type="text/javascript" src="support/script/custom/app.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
		<script type="text/javascript" src="support/controller/PasswordRecoveryController.js"></script>
		
		<!-- UserLogin Controller -->
	
		<!-- User Register Controller -->
		<script type="text/javascript" src="support/controller/UserRegisterController.js"></script>
		<script type='text/javascript' src="support/script/angularJS/ng-file-upload-shim.js"></script>
        <script type='text/javascript' src="support/script/angularJS/ng-file-upload.js"></script>
			

<script>
$(document).ready(function(){
    $('#myCarousal').carousel({
		pause: true,
		interval: false
	});
	$('.showSecondSlide').click(function(){
		$('#secondSlide').trigger('click');
	});
	$('.showThirdSlide').click(function(){
		$('#thirdSlide').trigger('click');
	});
	$('.showFourthSlide').click(function(){
		$('#fourthSlide').trigger('click');
	});
	$('.showFirstSlide').click(function(){
		$('#firstSlide').trigger('click');
	});
});
</script>


</head>



<body ng-app="fpl" ng-controller="UserLoginController as uLogin" ng-init="uLogin.initfunc()" class="scrollbody">

<div id="wrapper" >
<!-- headder start -->
<!-- <script type="text/javascript" src="headfoot.js"></script>
<header></header> 
<footer></footer>-->
<header class="indextrns hidden-xs hidden-sm">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-4 co-xs-12">
				<div class="logo-head">
					<a href="index.html"><img src="support/images/logo2.png" alt=""/></a>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 co-xs-12">
			</div>
			<div class="col-lg-2 col-md-2 col-sm-2 co-xs-12 alert">
				<a href="alert.html" class="alert">Alert</a>
			</div>
		</div>
	</div>
</header> 
<!-- /header end -->
<div id="main">
    <div class="container login_container">
	<div id="myCarousal" class="carousel slide" data-ride="carousel" data-interval="false">
	<!-- <indicators -->
	<ol class="carousel-indicators" style="visibility: hidden;">
		<li data-target="#myCarousal" id="firstSlide" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousal" id="secondSlide" data-slide-to="1"></li>
		<li data-target="#myCarousal" id="thirdSlide" data-slide-to="2"></li>
		<li data-target="#myCarousal" id="fourthSlide" data-slide-to="3"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<!-- home -->
			<div class="middle-contentss">
				<a href="index1.jsp" class="home"><img src="support/images/back1.jpg" alt=""/></a>
				<div class="row">
					<div class="grandelogo col-lg-2 col-lg-offset-5">
                    	<div class="mob-logo">
						<img src="support/images/popup-logo.png" alt=""/>
                        </div>
					</div>
				</div>
				<div class="row">
									
						<div class="style-cot">
						
							<div class="row">
						      
								<form class="first-form" ng-submit="uLogin.login(credentials)" novalidate autocomplete="off"  >
									<div class="form-group">
										<label class="overlap_label">User Name</label>
										<input type="name" class="form-control" ng-model="credentials.username" placeholder="Email id" ng-keyup="uLogin.loginValid()"  id="exampleInputEmail1"  style="background: #fff url('support/images/name.png') no-repeat scroll right 10px top 7px / 20px auto;" required>
									</div>
									<div class="form-group">
										<label class="overlap_label">Password</label>
										<input type="password" class="form-control" id="exampleInputEmail1" ng-model="credentials.password" placeholder="Password" ng-keyup="uLogin.loginValid()"  style="background: #fff url('support/images/name.png') no-repeat scroll right 10px top 7px / 20px auto;" required>
									</div>
								  	
								
							
								<div class="reg-buton">
									
										<div class="form-group text-center">
											<button type="submit"   ng-disabled="first-form.$valid" ng-enabled="!first-form.$valid" class="btn btn-default"  style="background:#4D5B69"><span class="reg-buton">Login</span></button></a>
										</div>
								
								</div>
						<div id='loginFailed' style='display:none'>
	          			<p id="Content1" style="color:red;position:absolute;left:50%;transform:translateX(-50%);-webkit-transform:translateX(-50%);font-size:14px;top:75%;z-index:999999;font-family:SansBold;">	
	          			</p>
	          		</div>
	          		
	          		
	          		<div style='display:none' id='loginreset' class="tabs" >
                       
                    <a type="submit" id="resetcontent" class="btn btn-default"  style="background:#4D5B69"><span class="reg-buton">Click Here To Reset</span></a>
                       
                     </div>
	          		<!-- <div id='loginreset' style='display:none'>
	          		
	                   <button type="button" id="resetcontent" class="btn btn-default"  style="background:#4D5B69"><span class="reg-buton">Click Here To Reset</span></button></a>
	          	       <a href="" class="home"></a>
	          		
	          		</div> -->
								</form>
							</div>
							
					
							<div class="row">
								<div class="reguser">
										<ul class="forget" style="width:100%"> 
                                        <li><p style="color:rgb(102, 102, 102); margin-bottom:0px;">Forget:</p></li>
											<li><a class="active" href="javascript: void(0)"><span class="showSecondSlide"><div class="for-user"> Username /</div></span></a> </li>
											<li><a class="active" href="javascript: void(0)"><span class="showSecondSlide"><div class="for-user">Password</div></span></a> </li>
											<li style="float:right"><a href="signup.html"><span class="newuser">New User</span></a></li>
										</ul>
																
								</div>
								
							</div>
							<div class="gettting text-center"> 
								
							</div>		
										
						</div>
								
				
						
					<div class="foot-allright">
						<div class="row">
							<div class="allright">All rights reserved to FPL 2015</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="item">
			<!-- center content -->
			<div class="middle-contentss">
			   <span class="showFirstSlide pull-right just"><img src="support/images/back1.jpg" alt=""/></span>
				<div class="row">
						<div class="row">
							<div class="grandelogo col-lg-2 col-lg-offset-5">
                            	<div class="mob-logo">
									<img src="support/images/popup-logo.png" alt=""/>
                                </div>
							</div>
						</div>
					<div class="signups">
						<div class="row">
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<div class="customer"><a class="active" href="javascript: void(0)"><span class="showThirdSlide"><img src="support/images/pass1.png" alt=""></span></a>
									<h4 class="pwd">Password</h4>
								</div>
								
							</div>
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
								<div class="finacial"><a class="active" href="javascript: void(0)"><span class="showFourthSlide"><img src="support/images/user_forgot.png" alt=""></a>
									<h4 class="pwd">Username</h4>
								</div>
							</div>
						</div>
					</div>
					<div class="foot-allright">
						<div class="row">
							<div class="allright">All rights reserved to FPL 2015</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="item">
			<div class="middle-contents">
						<span class="showSecondSlide pull-right justss"><img src="support/images/back1.jpg" alt=""/></span>
						<div class="row">
							<div class="grandelogo col-lg-2 col-lg-offset-5 logo">
                          	  <div class="mob-logo">
								<img src="support/images/popup-logo.png" alt=""/>
                               </div>
							</div>
						</div>
				<div class="row" ng-app="app" ng-controller="PasswordRecoveryController as upassrecovery">
					 <form name="userForm" autocomplete="off">
					<div class="modal-contentss">
						<div class="cus-reg text-center">
							<div class="row">
										<img src="support/images/pwd.png" alt=""/>				
								<span class="pwd-sign">Password Recovery</span>
							</div>
							
						</div>
						
						<div class="form-signs">
						 <div class="row">
						 
							<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : userForm.name.$invalid && !userForm.name.$pristine }">
                                   <label class="overlap_label">First Name</label>
                                     <input type="text" name="name" class="form-control" ng-model="credentials.firstname" required>
                                     <p ng-show="userForm.name.$invalid && !userForm.name.$pristine" class="help-block"> firstname is required.</p>
                                   </div>
							
								
								<div class="form-group col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : userForm.lname.$invalid && !userForm.lname.$pristine }">
									<label class="overlap_label">Last Name</label>
								    <input type="name" name="lname" class="form-control" id="exampleInputEmail1"  ng-model=credentials.lastname required>
								<p ng-show="userForm.lname.$invalid && !userForm.lname.$pristine" class="help-block">lastname is required.</p>
								</div>
								
								
								
								
								<!-- <div class="form-group col-md-6 col-sm-6 col-xs-12">
									<label class="overlap_label">Date & Time</label>
									<input type="name" class="form-control" id="exampleInputEmail1" placeholder="28 Nov 2015 18:15">
								</div> -->
								<div class="form-group col-md-6 col-sm-6 col-xs-12">
									<label class="overlap_label">Security questions?</label>
									<select class="selectpicker form_selectp">
													<option value="default">What is Your mother name?</option>
													<option value="ft1">What is Your Father name?</option>
													<option value="ft2">Motor</option>
													<option value="ft3">Travel</option>
													<option value="ft1">Home Protection</option>
													<option value="ft2">Medical</option>
													<option value="ft3">Workmen Compensation</option>
													<option value="ft1">Fire</option>
												</select>
								</div>
								<div class="form-group col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : userForm.answer.$invalid && !userForm.answer.$pristine }">
								<label class="overlap_label">Answer</label>
									<input type="name" name="answer" class="form-control" id="exampleInputEmail1" placeholder="" ng-model=credentials.answer required>
								<p ng-show="userForm.answer.$invalid && !userForm.answer.$pristine" class="help-block">Answer is required</p>
								</div>
								
								 <div class="form-group col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : userForm.email.$invalid && !userForm.email.$pristine }">
                                  <label class="overlap_label">E-Mail</label>
                                  <input type="email" name="email" class="form-control" ng-model=credentials.email required>
                                  <p ng-show="userForm.email.$invalid && !userForm.email.$pristine" class="help-block">Enter a valid email.</p>
                                    </div>
                                    
                                    	<div class="g-recaptcha form-group col-md-6 col-sm-6 col-xs-12" data-sitekey="6LeFJAoTAAAAAK9EOWdlbe3-E6PcobyfOSNAH4oR" data-callback="alert('Captcha Verified')"></div>
                                    
<!--                                    <div class="g-recaptcha figcapion col-md-6 col-xs-12" data-sitekey="6Lc_0f4SAAAAAF9ZA_d7Dxi9qRbPMMNW-tLSvhe6">	</div>	 
 -->                                    
							</div>
						<!-- 	<div class="invalid">
							    <div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<div class="invalid-text">
										ERROR: Invalid domain for site key
								</div>
								</div>

								
							</div>
						</div> -->
						</div>
						<div id = "successMSG1" class="busd text-center" style='display:none'>
						
						<p id="Content0" class="psucc" style="font-family: SansRegular; font-size: 15px;display:none;">
						<i class="fa fa-check"></i>
						</p>
						
						<p id="Content2" class="pfail" style="font-family: SansRegular; font-size: 15px;display:none;">
						<i class="fa fa-times"></i>
						</p>
								
						</div>
						
					</div>
					
						<button type="submit"  ng-click="upassrecovery.Recovery(credentials)" class="back-function" ng-disabled="!userForm.$valid" >Submit</button>
					<div class="foot-allrights">
						<div class="row">
							<div class="allrights">All rights reserved to FPL 2015</div>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="item">
			<div class="middle-contentsuu">
			 <span class="showSecondSlide pull-right justss"><img src="support/images/back1.jpg" alt=""/></span>
				<div class="row">
					<div class="grandelogo col-lg-2 col-lg-offset-5 logo">
                   		 <div class="mob-logo">
							<img src="support/images/popup-logo.png" alt=""/>
                        </div>
					</div>
				</div>
				<div class="row" ng-app="app" ng-controller="UserNameRecoveryController as urecovery">
					 <form name="userForm" autocomplete="off">
					<div class="modal-contentss">
						<div class="cus-reg text-center">
							<div class="row">
										<img src="support/images/pwd.png" alt=""/>				
								<span class="pwd-sign">Username Recovery</span>
							</div>
							
						</div>
						
						<div class="form-signs" >
						
						 <div class="row">
							
							<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : userForm.name.$invalid && !userForm.name.$pristine }">
                                   <label class="overlap_label">First Name</label>
                                     <input type="text" name="name" class="form-control" ng-model="credentials.firstname" required>
                                     <p ng-show="userForm.name.$invalid && !userForm.name.$pristine" class="help-block"> firstname is required.</p>
                                   </div>
							
								
								<div class="form-group col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : userForm.lname.$invalid && !userForm.lname.$pristine }">
									<label class="overlap_label">Last Name</label>
								    <input type="name" name="lname" class="form-control" id="exampleInputEmail1"  ng-model=credentials.lastname required>
								<p ng-show="userForm.lname.$invalid && !userForm.lname.$pristine" class="help-block">lastname is required.</p>
								</div>
								
								 
								
								
								
								<div class="form-group col-md-6 col-sm-6 col-xs-12">
									<label class="overlap_label">Security questions?</label>
									<select class="selectpicker form_selectp">
													<option value="default">What is Your mother name?</option>
													<option value="ft1">What is Your Father name?</option>
													<option value="ft2">Motor</option>
													<option value="ft3">Travel</option>
													<option value="ft1">Home Protection</option>
													<option value="ft2">Medical</option>
													<option value="ft3">Workmen Compensation</option>
													<option value="ft1">Fire</option>
												</select>
								</div>
								<div class="form-group col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : userForm.answer.$invalid && !userForm.answer.$pristine }">
								<label class="overlap_label">Answer</label>
									<input type="name" name="answer" class="form-control" id="exampleInputEmail1" placeholder="" ng-model=credentials.answer required>
								<p ng-show="userForm.email.$invalid && !userForm.email.$pristine" class="help-block">Answer is required</p>
								</div>
								
								<div class="form-group col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : userForm.email.$invalid && !userForm.email.$pristine }">
                                  <label class="overlap_label">E-Mail</label>
                                  <input type="email" name="email" class="form-control" ng-model=credentials.email required>
                                  <p ng-show="userForm.email.$invalid && !userForm.email.$pristine" class="help-block">Enter a valid email.</p>
                                    </div>
								
								<!-- <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<div class="refresh"><img src="support/images/refresh.png" alt=""/></div>
						            <div class="g-recaptcha" data-sitekey="6LeFJAoTAAAAAK9EOWdlbe3-E6PcobyfOSNAH4oR" data-callback="alert('Captcha Verified')"></div>
								</div> -->
			                     <div class="g-recaptcha form-group col-md-6 col-sm-6 col-xs-12" data-sitekey="6LeFJAoTAAAAAK9EOWdlbe3-E6PcobyfOSNAH4oR" data-callback="alert('Captcha Verified')"></div>
									
										</div>
								</div>
								<!-- <div class="invalid">
							    <div class="row">
								<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<div class="invalid-text">
										ERROR: Invalid domain for site key
								</div>
								</div>

								
							</div>
						</div> -->
						
						<div id = "successMSG2"  class="busd text-center" style='display:none'>
						
						<p id="Content3" class="psucc" style="font-family: SansRegular; font-size: 15px;display:none;">
						<i class="fa fa-check"></i>
						</p>
						
						<p id="Content4" class="pfail" style="font-family: SansRegular; font-size: 15px;display:none;">
						<i class="fa fa-times"></i>
						</p>
								
						</div>
						</div>
						
					<div class="text-center">
						<button type="submit"  ng-click="urecovery.Recovery(credentials)" class="back-function"  ng-disabled="!userForm.$valid"  >Submit</button>
					</div>
					<div class="foot-allrights">
						<div class="row">
							<div class="allrights">All rights reserved to FPL 2015</div>
						</div>
					</div>
					</form> 
				</div>	
			
		</div>
		</div>
	</div>
	<a href="#myCarousal" id="nextSlide" style="visibility: hidden;" class="left-carousel-control" role="button" data-slide="prev">
		Previous
	</a>
	<a href="#myCarousal" id="prevSlide" style="visibility: hidden;" class="left-carousel-control" role="button" data-slide="next">
		Next
	</a>
</div>
</div><!-- #main -->
</div>



<footer>
<div class="container-fluid">
<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
<p class="foot">All rights reserved to FPL 2015</p>
</div>
<div class="col-lg-9 col-md-9 col-sm-6 col-xs-12">
             <p class="fore">About Us |&nbsp; Terms of Use |&nbsp; Privacy Policy |&nbsp;
              Acceptable Use Policy |&nbsp;
              Terms of Purchase |&nbsp;
              Site Map |&nbsp;
               Feedback |&nbsp;
             RSS Feed |&nbsp;
              Contact Us
           </ul>
           </div>
    
</div>
</footer>



</div><!-- /#wrapper -->
<script>
function myFunction() {
    alert("Success! Please check your Email.");
	
}

</script>

</body>
</html>