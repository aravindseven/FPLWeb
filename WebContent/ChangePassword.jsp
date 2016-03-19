<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html ng-app="fpl">
	<head>
		<title>FPL</title>
		<link rel="stylesheet" href="support/css/bootStrap/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/style.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/notify.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/errorDisplay.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/forgotPassword.css" type="text/css">
		<script type="text/javascript" src="support/script/jQuery/jquery.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
		<script type="text/javascript" src="support/script/custom/captcha.js"></script>
		<script type="text/javascript" src="support/script/mespeak/mespeak.js"></script>
		<script type="text/javascript" src="support/script/mespeak/mespeak_init.js"></script>
		<script type="text/javascript" src="support/script/custom/app.js"></script>
		<script type="text/javascript" src="support/controller/UserLoginController.js"></script>
		<script type="text/javascript" src="support/controller/UserRegisterController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordRecoveryController.js"></script>
		<script type="text/javascript" src="support/controller/UserNameRecoveryController.js"></script>
		<script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
	</head>
</html>

<!-- Main Wrapper Start -->
<div class="mainWrapper" ng-controller="UserLoginController as uLogin">

	<!-- Header bar Start -->
	<jsp:include page="/include/common/FPLHeader.jsp" flush="true" />
	
	<!-- Login Modal -->
	<jsp:include page="/include/login/LoginModel.jsp" flush="true" />
		
	<!-- User Registration Modal -->
	<jsp:include page="/include/login/UserRegistrationModel.jsp" flush="true" />
	
	<!-- Forgot Password -->
	<jsp:include page="/include/login/ForgotPassword.jsp" flush="true" />
	
</div>
