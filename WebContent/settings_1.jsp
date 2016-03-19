<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="fpl">
	<head>
		<title>FPL</title>
		
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0" />
		<link type="text/css" rel="stylesheet" href="support/css/register.css" />
		<link type="text/css" rel="stylesheet" href="support/css/index.css" />
		<link type="text/css" rel="stylesheet" href="support/css/communication-style.css" />
		<link type="text/css" rel="stylesheet" href="support/css/register_media.css">
		<link type="text/css" rel="stylesheet" href="support/css/form1_cus.css">
		<link type="text/css" rel="stylesheet" href="support/css/setti.css">
		<link type="text/css" rel="stylesheet" href="support/css/form2_cus.css">
		<link type="text/css" rel="stylesheet" href="support/css/form3_cus.css">
		<link type="text/css" rel="stylesheet" href="support/css/form1_org.css">
		<link type="text/css" rel="stylesheet" href="support/css/form2_org.css">
		<link type="text/css" rel="stylesheet" href="support/css/form3_org.css">
		<link type="text/css" rel="stylesheet" href="support/css/bootstrap.css">
		<link type="text/css" rel="stylesheet" href="support/css/base.css">
		<link type="text/css" rel="stylesheet" href="support/css/styless.css">
		<link type="text/css" rel="stylesheet" href="support/css/form1_fpreg_individual.css">
		<link type="text/css" rel="stylesheet" href="support/css/form2_fpreg_individual.css">
		<link type="text/css" rel="stylesheet" href="support/css/form3_fpreg_individual.css">
		<link type="text/css" rel="stylesheet" href="support/css/register_fp_individual.css">
		<link type="text/css" rel="stylesheet" href="support/css/register_fp_individual_media.css">
<!-- 		<link type="text/css" rel="stylesheet" href="support/css/custom/image-crop-styles.css"> -->
		
		<script src="support/js/jquery-2.1.4.js"></script>
<!-- 			<link rel="stylesheet" href="support/script/calendar/css/jquery.mobile-1.3.0.min.css" /> -->
<!-- 	<link href="support/script/calendar/css/mobipick.css" rel="stylesheet" type="text/css" /> -->
<!-- 	<script type="text/javascript" src="support/script/calendar/xdate.js"></script> -->
<!-- 	<script type="text/javascript" src="support/script/calendar/xdate.i18n.js"></script> -->
<!-- 	<script type="text/javascript" src="external/jquery-1.9.1.min.js"></script> -->
<!-- 	<script type="text/javascript" src="support/script/calendar/jquery.mobile-1.3.0.min.js"></script> -->
<!-- 	<script type="text/javascript" src="support/script/calendar/mobipick.js"></script> -->
		
<!-- 		<script type="text/javascript" src="support/js/registration.js"></script> -->
<!-- 		<script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script> -->
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.center.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
		 <script type="text/javascript" src="headfoot.js"></script> 
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
		
<!-- 		      <script type='text/javascript' src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script> -->
<!-- 		<script type="text/javascript" src="support/script/custom/image-crop.js"></script> -->
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
		
		
		<script type="text/javascript" src="support/script/custom/app.js"></script>
		<script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
		<script type="text/javascript" src="support/script/custom/GAddress.js"></script>
		
		<style type="text/css">

.img{
	background: url(support/images/inputtop.png) no-repeat #FFF;
	background-position: right top;
	background-size: 20px;
}
</style>
		
		
		<script>

function load(value)
{
  if(value==1)
   {
	  document.getElementById('form1').style.display="block";
	  document.getElementById('form2').style.display="none";
	  document.getElementById('form3').style.display="none";
   }
  else if(value==2)
   {
	  document.getElementById('form1').style.display="none";
	  document.getElementById('form2').style.display="block";
	  document.getElementById('form3').style.display="none";
   }else if(value==3)
   {
		  document.getElementById('form1').style.display="none";
		  document.getElementById('form2').style.display="none";
		  document.getElementById('form3').style.display="block";
	   }
}
</script>

	</head>
<!-- <header></header> -->
<body class="scrollbody">
<!-- Main Wrapper Start -->
<div class="mainWrapper" ng-controller="UserLoginController as uLogin">

<!-- 

-->
	<!-- Header bar Start -->
	 <jsp:include page="/header.html" flush="true" /> 
	<%--  <jsp:include page="/include/common/FPLHeader_Profile.jsp" flush="true" />  --%>
	
	<!-- Login Modal -->
<%-- 	<jsp:include page="/include/login/LoginModel.jsp" flush="true" /> --%>
		
	<!-- User Registration Modal -->
<%-- 	<jsp:include page="/include/login/UserRegistrationModel.jsp" flush="true" /> --%>
	
	<!--Slide Show and content -->
<%-- 	<jsp:include page="/include/common/InfoPage.jsp" flush="true" /> --%>
	
	<!-- Settings Content -->
<%-- 	<jsp:include page="/include/login/SettingsModel_1.jsp" flush="true" /> --%>
	<jsp:include page="/redirectProfileCreation.do" flush="true" />
	
	<!-- Login Modal -->
<%-- 	<jsp:include page="/include/common/InsideFooter.jsp" flush="true" /> --%>
	
	    <!-- <div class="footer" style="position:absolute;top:0px;">
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
    </div> -->
   
	
</div>
 <footer></footer>
</body>

</html>