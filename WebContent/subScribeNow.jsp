<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="fpl">
	<head>
		<title>FPL</title>
		
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0" />
		<link type="text/css" rel="stylesheet" href="support/css/register.css" />
		<link type="text/css" rel="stylesheet" href="support/css/index.css" />

	<link type="text/css" rel="stylesheet" href="support/css/fp_subscription.css" />
	<link type="text/css" rel="stylesheet" href="support/css/fp_subscription_media.css">
	<link type="text/css" rel="stylesheet" href="support/css/form1_fp_subscription.css"> 
	<link type="text/css" rel="stylesheet" href="support/css/form2_fp_subscription.css">
		
		<script src="support/js/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.center.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
		
		<script type='text/javascript' src="support/script/angularJS/ng-file-upload-shim.js"></script>
        <script type='text/javascript' src="support/script/angularJS/ng-file-upload.js"></script>
		
		
		
		<script type="text/javascript" src="support/controller/UserLoginController.js"></script>
		<script type="text/javascript" src="support/controller/UserRegisterController.js"></script>
		<script type="text/javascript" src="support/controller/UserSettingsController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordRecoveryController.js"></script>
		<script type="text/javascript" src="support/controller/UserNameRecoveryController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordResetController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordChangeController.js"></script>
		<script type="text/javascript" src="support/controller/UserProfileController.js"></script>
		<script type="text/javascript" src="support/controller/FPLProfileController.js"></script>
		<script type="text/javascript" src="support/controller/FPLSubscribeController.js"></script>
		
		
		<script type="text/javascript" src="support/script/custom/app.js"></script>
		<script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
		<script type="text/javascript" src="support/script/custom/GAddress.js"></script>

		

		<script>

function load(value)
{
	
  if(value==1)
   {
	  document.getElementById('form1').style.display="block";
	  document.getElementById('form2').style.display="none";
	  
   }
  else if(value==2)
   {
	  document.getElementById('form1').style.display="none";
	  document.getElementById('form2').style.display="block";
	  
   }
}
</script>
		

<style type="text/css">

.img{
	background: url(support/images/inputtop.png) no-repeat #FFF;
	background-position: right top;
	background-size: 20px;
}
</style>
		
		
	</head>

<body>
<!-- Main Wrapper Start -->
<div class="mainWrapper" ng-controller="UserLoginController as uLogin">


	<!-- Header bar Start -->
	<jsp:include page="/include/common/FPLHeader_Subscription.jsp" flush="true" />
	
	<jsp:include page="/include/profile/fplSubsription.jsp" flush="true" />
	

	    <div id="footer" class="footer">
        	<p>All rights reserved to FPL 2015</p>
             <ul style="width:750px;">
             	<li><a href="">About Us |&nbsp;</a></li>
                <li><a href=""> Terms of Use |&nbsp;</a></li>
                <li><a href=""> Privacy Policy |&nbsp;</a></li>
                <li><a href=""> Acceptable Use Policy |&nbsp;</a></li>
                <li><a href=""> Terms of Purchase |&nbsp;</a></li>
                <li><a href=""> Site Map |&nbsp;</a></li>
                <li><a href=""> Feedback |&nbsp;</a></li>
                <li><a href=""> RSS Feed |&nbsp;</a></li>
                <li><a href=""> Contact Us</a></li>
           </ul>
    </div>
	
</div>
</body>
</html>