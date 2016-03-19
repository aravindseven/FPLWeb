<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- <html ng-app="fpl">
	<head>
		<title>FPL</title>
	<link href="support/css/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="style.css"/>
	<link rel="stylesheet" href="css/style.css"/>
	<link rel="stylesheet" href="form_css/ad_substatus.css"/>
	<link rel="stylesheet" href="form_css/ad_subscription.css"/>
	<link rel="stylesheet" href="form_css/e.css"/>
	<link rel="stylesheet" href="form_css/tooltip.css"/>
	<link rel="stylesheet" href="form_css/fileinput.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link href="form_css/sidebar.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    Include all compiled plugins (below), or include individual files as needed
    <script src="js/bootstrap.min.js"></script>
		
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
	</head>
<div id="changePassword" class="col-lg-10 col-md-10 col-sm-10 profileTabForm alternativeForm" ng-controller="PasswordChangeController as pChange">
	<form ng-submit="pChange.change(pChange)" autocomplete="off" class="col-sm-12" name="PasswordResetForm" method="post">
		<div class="row">
			<div class="col-md-12 paddingNone">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<h3>Change Password</h3>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<h3></h3>
				</div>
			</div>
			<hr class="clear">
			<div class="col-md-6">
				FIRST SECTION START
				<div class="innerBorderDiv">
					<div class="form-group">
						<label class="control-label">New Password</label> <input
							type="password" name="newPassword" class="form-control" required id="newPassword"
							pattern="^(?=^.{8,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
							ng-model="pChange.newPassword" onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');if(this.checkValidity()) PasswordResetForm.confirmPassword.pattern = this.value;"
							title="Password should contain minimum 8 characters, Uppercase, Lowercase and Number" />
					</div>
					<div class="form-group">
						<label class="control-label">Confirm Password</label> <input
							type="password" name="confirmPassword" class="form-control"
							ng-model="pChange.confirmPassword" required
							ng-keyup="pChange.confirmPasswordError = false" /> 
					</div>
					<div class="form-group">
						<button type="submit"
							class="btn btn-primary col-lg-2 marginRfifteen saveChangesBtn">Change</button>
					</div>
					<label
							ng-show="pChange.confirmPasswordError"
							class="ng-hide validationMsg"> Passwords do not
							match...</label>
				</div>
				FIRST SECTION END
			</div>
	</form>
	Try setting this Password:Test@1234
	Password Rules:
	
</div>
</html>
 -->

<html lang="en" ng-app="fpl">
  <head>
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
		
		<link rel="stylesheet" href="support/css/bootStrap/bootstrap.min.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/style.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/mySettings.css" type="text/css">
		<link rel="stylesheet" href="support/css/custom/notify.css" type="text/css">
		<link rel="stylesheet" href="support/css/jquery/jquery.msg.css" type="text/css">
		<script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.center.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
		<script type="text/javascript" src="headfoot2.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
		<script type="text/javascript" src="support/script/custom/app.js"></script>
		<script type="text/javascript" src="support/controller/PasswordChangeController.js"></script>
		<script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Change Password</title>

  </head>
  <body>
<!-- new nav -->
<header>
 
</header>
<section>
	<div id="changePassword" class="container" ng-controller="PasswordChangeController as pChange">
		 <!-- dash menu -->
       <!-- <ul id="demo_menu3">
		   <li><a href="#" ><button class="round_but" type="button"><img src="images/change.png"></button></a></li>
		   <li><a href="#"><button  class="round_but"  type="button"><img src="images/security.png"></button></a></li>
		   <li><a href="#"><button  class="round_but"  type="button"><img src="images/edit-profle.png"></button></a></li>
        </ul> -->
        <!-- dash menu end -->
			<div class="change-pwd">
			
			<div class="chae">
				<form ng-submit="pChange.change(pChange)" autocomplete="off" class="foem" name="PasswordResetForm" method="post">
					<div class="form-group">
						<label class="overlap_label">Enter New Password</label>
						<input
							type="password" name="newPassword" class="form-control" required id="newPassword"
							pattern="^(?=^.{8,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
							ng-model="pChange.newPassword" onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');if(this.checkValidity()) PasswordResetForm.confirmPassword.pattern = this.value;"
							title="Password should contain minimum 8 characters, Uppercase, Lowercase and Number" />
					</div>
					<div class="form-group" style="margin-bottom: 15px;">
					<label class="overlap_label">Re-Enter New Password</label>
						<input
							type="password" name="confirmPassword" class="form-control"
							ng-model="pChange.confirmPassword" required
							ng-keyup="pChange.confirmPasswordError = false" /> 
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<a href="#"><button type="submit" class="btn btn-default eneny" style="background:#CE2B2C;">Save</button></a>
						<a href="#"><button type="button" class="btn btn-default eneny">Cancel</button></a>
					</div>
								
					<label
							ng-show="pChange.confirmPasswordError"
							class="ng-hide validationMsg"> Passwords do not
							match...</label>		
				</form>
			</div>
			
			<%-- <%
	String errorMsg = (String) request.getAttribute("failure");
	String successMsg = (String) request.getAttribute("success");
	if(successMsg != null && !successMsg.isEmpty()) {
		
%>
	<div>
		<hr color="green" align="center"/> 
			 <b> <%= successMsg %> </b> <br />
		<hr color="red" />
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
	<br />
<%
	}
%> --%>
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
<!-- tooltip code start -->
<!--Menu Data-->
   <!--  <div style="display:none;background:red;">
        <div id="sub2">
          
                    
        </div>
        <div id="sub3">
            <div class="column" style="float:left;">
               <img src="images/profile.png"><br />
				 <a href="#popup1" style="padding-left:15px;position:relative;bottom:20px;">Edit Profile</a>
               
            </div>  
			<div class="column-fok">
				<span class="fpl-pop">FPL</span></br>
				fpl2123@gmail.com</br>
				<a href="#">Google-plus </a>- <a href="#">Profile</a>
				
			</div>
            <div class="column">
                <img src="src/tooltips-cd4.jpg" style="width:75px;height:15px;" alt="" />
            </div>  

            <div style="clear:both;"></div>
            <div style="width:250px;margin-top:16px;padding-top:12px;border-top:2px solid #666;">
               <a href="#">Add account</a>
			   <div class="pop"style="float:right;"><i class="fa fa-power-off" style="color:#ccc;font-size:20px;"><span class="firstc">Logout</span></i></div>
            </div>
        </div>
    </div >-->
<!-- tooltip code end -->
<!-- fil popup start -->
<!-- <div id="popup1" class="overlay">
						<div class="popup">
							<a class="close" href="#">&times;</a>
							<form enctype="multipart/form-data">
							<div class="form-group">
								<input id="file-1" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="1">
							</div>
							</form>
						</div>
</div> -->
<!-- tooltip start -->
<!-- <script src="js/tooltip.js" type="text/javascript"></script>
tooltp end
file input scrtipt start
<script src="js/fileinput.js" type="text/javascript"></script>
    <script src="js/fileinput_locale_fr.js" type="text/javascript"></script>
    <script src="js/fileinput_locale_es.js" type="text/javascript"></script>
<script>
    $("#file-1").fileinput({
        uploadUrl: '#', // you must set a valid URL here else you will get an error
        allowedFileExtensions : ['jpg', 'png','gif'],
        overwriteInitial: false,
        maxFileSize: 1000,
        maxFilesNum: 10,
        //allowedFileTypes: ['image', 'video', 'flash'],
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        }
	});
	
	 $(document).ready(function() {
        $("#test-upload").fileinput({
            'showPreview' : false,
            'allowedFileExtensions' : ['jpg', 'png','gif'],
            'elErrorContainer': '#errorBlock'
        });
		$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script> -->
<!-- file input script end -->
<!-- <script>
    function showhide()
     {
           var div = document.getElementById("middle-content");
    if (div.style.display !== "none") {
        div.style.display = "none";
    }
    else {
        div.style.display = "block";
    }
     }
  </script> -->
  <!-- sidebar start -->
  <!-- <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
 <script src="js/jquery.sidebar.js" type="text/javascript"></script>
       <script type="text/javascript" src="js/jquery.sidebar2.js"></script>
    <script type="text/javascript">
        $("ul#demo_menu3").sidebar({
            position:"right",
          open:"click"
        });
        
        </script> -->
		<!-- sidebar end -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    
  </body>
</html>
















