<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Change Password</title>

    <!-- Bootstrap -->
    <link href="support/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="support/css/style3.css"/>
	<link rel="stylesheet" href="support/css/custom/style.css"/>
	<link rel="stylesheet" href="support/css/ad_substatus.css"/>
	<link rel="stylesheet" href="support/css/ad_subscription.css"/>
	<link rel="stylesheet" href="support/css/index.css"/>
	<link rel="stylesheet" href="support/css/tooltip.css"/>
	<link rel="stylesheet" href="support/css/fileinput.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<script type="text/javascript" src="support/js/jquery-2.1.4.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script type="text/javascript" src="headfoot2.js"></script>
		<link rel="stylesheet" href="support/css/bootStrap/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/style.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/mySettings.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/notify.css" type="text/css">
		<link rel="stylesheet" href="support/css/jquery/jquery.msg.css" type="text/css">
		<script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.center.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
		<script type="text/javascript" src="support/script/custom/app.js"></script>
		<script type="text/javascript" src="support/controller/PasswordChangeController.js"></script>
		<script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
  
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
	
  </head>
  <body>
<!-- new nav -->
<header>
 
</header>
<!-- new nav end -->
<!-- communication start -->

<!-- communication ends -->
<section>
	<div class="container-fluid">
		 <!-- dash menu -->
             <!-- dash menu end -->
			<div class="change-pwd">
			<div class="chae">
			<form name="passwordResetForm" method="post"  class="foem" action="fplPasswordReset.do">
				<div class="form-group marginTfifteen">
       			Email id: ${passwordRestParam.email}
     		</div> <br />
					<div class="form-group">
						<label class="overlap_label">Enter New Password</label>
						<input type="password" name="newPassword" class="form-control" placeholder="New Password">
					</div>
					<div class="form-group" style="margin-bottom: 15px;">
					<label class="overlap_label">Re-Enter New Password</label>
						<input type="password" name="conformPassword" class="form-control" placeholder="Confirm Password">
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
					    <input type="hidden" name="otp" value="${passwordRestParam.otp}">
     		              <input type="hidden" name="email" value="${passwordRestParam.email}">
						<a href="#"><button type="submit" class="btn btn-default eneny" style="background:#CE2B2C;">Save</button></a>
						<a href="#"><button type="button" class="btn btn-default eneny">Cancel</button></a>
					</div>
								
							
				</form>
			</div>
			
			
			
			
			<%
	String errorMsg = (String) request.getAttribute("ErrorMessage");
	String successMsg = (String) request.getAttribute("SuccessMessage");
	if(successMsg != null && !successMsg.isEmpty()) {
		
%>
	<div>
		<hr color="green" align="center"/> 
			 <b> <%= successMsg %> </b> <br />
		<hr color="green" />
	</div>
	<br />
<% 
	} else {
		if(errorMsg == null || errorMsg.isEmpty()) {
			errorMsg = (String) request.getParameter("ErrorMessage");
			
			if(errorMsg == null || errorMsg.isEmpty()) {
				errorMsg = "";
			}
		}
%>
	<div align="center">
		<hr color="red" align="center" /> 
			 <b> <%= errorMsg %> </b> <br />
		<hr color="red" />
	</div>
	<script>
		//$('.form-control').attr('disabled','');
		//$('.eneny').attr('disabled','');
		//$('.foem').hide();
	</script>
	<br />
<%
	}
%>
			
			
			
			
			
			</div>					
											
	</div>
</section>
<section class="footer">
	<div class="container-fluid">
		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
			<p>All rights reserved to FPL 2015</p>
		</div>
	<div class="col-lg-9 col-md-9 col-sm-9 col-xs-12">
		<p><a href="#">About Us |&nbsp;</a><a href="#"> Terms of Use |&nbsp;</a><a href="#"> Privacy Policy |&nbsp;</a><a href="#"> Acceptable Use Policy |&nbsp;</a><a href="#"> Terms of Purchase |&nbsp;</a><a href="#"> Site Map |&nbsp;</a><a href="#"> Feedback |&nbsp;</a><a href="#"> RSS Feed |&nbsp;</a><a href="#"> Contact Us</a></p>
	</div>
	   
	</div>
</section>

<!-- file input scrtipt start -->
<script src="js/fileinput.js" type="text/javascript"></script>
    <script src="js/fileinput_locale_fr.js" type="text/javascript"></script>
    <script src="js/fileinput_locale_es.js" type="text/javascript"></script>


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
  </body>
</html>