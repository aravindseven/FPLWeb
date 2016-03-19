<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="tips" style="overflow:hidden;">
	<head>
		<title>FBL Tips</title>
		<link href="support/communication/css/bootstrap.css" rel="stylesheet">
		<link rel="stylesheet" href="support/communication/style.css"/>
		<link rel="stylesheet" href="support/communication/css/normalize.css">
		<link rel="stylesheet" href="support/communication/css/stylesheet.css">
		<link type="text/css" rel="stylesheet" href="support/communication/css/text_edit.css">
		<link type="text/css" rel="stylesheet" href="support/communication/css/jquery-te-1.4.0.css">
		<link type="text/css" rel="stylesheet" href="support/css/select2/select2.min.css">
		<link type="text/css" rel="stylesheet" href="support/css/select2/select2-bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="support/css/font-awesome/css/font-awesome.min.css">
		<link type="text/css" rel="stylesheet" href="support/css/toaster/toastr.min.css">
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    <script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.center.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
		 <script type="text/javascript" src="support/script/ng-table.js">
        </script>
		<script type="text/javascript" src="support/script/select2/select2.full.min.js"></script>
	    <script type="text/javascript" src="support/script/custom/app.js"></script>
	    <script type="text/javascript" src="support/script/toastr/toastr.min.js"></script>
		<!-- <script type="text/javascript" src="support/controller/UserLoginController.js"></script> -->
		<script type="text/javascript" src="support/controller/TipsController.js"></script>
		<!-- <script type="text/javascript" src="support/controller/UserRegisterController.js"></script>
		<script type="text/javascript" src="support/controller/UserSettingsController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordRecoveryController.js"></script>
		<script type="text/javascript" src="support/controller/UserNameRecoveryController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordResetController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordChangeController.js"></script>
		 --><script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
		<!-- <script src="//tinymce.cachefly.net/4.1/tinymce.min.js"></script> -->
		<script type="text/javascript" src="support/script/ui-bootstrap.js"></script>
		<!-- <script src="https://cdn.pubnub.com/pubnub.min.js"></script>
  		<script src="https://cdn.pubnub.com/pubnub-crypto.min.js"></script>
  		<script src="http://pubnub.github.io/angular-js/scripts/pubnub-angular.js"></script> -->
  		<script src="https://cdn.socket.io/socket.io-1.2.0.js"></script>
  		<!-- <script src="http://requirejs.org/docs/release/2.1.15/minified/require.js"></script> -->
	</head>
<body>
	<div class="mainWrapper">
	</div>
	<div class="container-fluid" style="background:white;">
		<!-- Static navbar -->
		<nav class="navbar navbar-default" style="background:none;border:none;">
			<div class="container-fluid">
				<div class="navbar-header">
				  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				    <span class="sr-only">Toggle navigation</span>
				    <span class="icon-bar"></span>
				    <span class="icon-bar"></span>
				    <span class="icon-bar"></span>
				  </button>
				  <a class="navbar-brand" href="#"><img src="support/communication/images/logo.png" alt=""/></a>
				</div><br/>
				<div id="navbar" class="navbar-collapse collapse">
		            <ul class="nav navbar-nav" style="margin-top:25px;margin-left:40px;">
		              <li class="hi"><a href="#"><img src="support/communication/images/fo4.png"/><br/>TIPS</a></li>
		            </ul>
					<ul class="nav navbar-nav navbar-right" style="margin-top:5px;position:relative;right:10px;">
		              	<li class="ac" style="margin-top:20px;"><img src="support/communication/images/man.png" alt=""><span style="position:relative;bottom:5px;cursor:pointer;">&#9660;</span></li>
						<li>
							<div class="menu-right">
								<div class="menus">
									<div class="singa"><img src="support/communication/images/dee.png"></div>
									<div class="singa">Singapore</div>
								</div></br>
							</div>
						</li>
            		</ul>
				</div><!--/.nav-collapse -->
			</div><!--/.container-fluid -->
		</nav>
		<!-- Main component for a primary marketing message or call to action -->
	</div> <!-- /container -->
	<jsp:include page="/include/tips/tipsmodel.jsp" flush="true" />


<!-- tab content -->  
<!-- tap menu close -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="support/communication/js/jquery-te-1.4.0.js" charset="utf-8"></script>
	<script src="support/communication/js/selectize.js"></script>
	<script src="support/communication/js/index.js"></script>
	
	<script>
				$('#input-tags').selectize({
					persist: false,
					createOnBlur: true,
					create: true
				});
	</script>
	<script>
					$('.select2me').select2({
				        placeholder: "Select",
				        width: 'auto', 
				        allowClear: true
				    });
				
	</script>

				<script>
				
				</script>
  </body>
</html>
