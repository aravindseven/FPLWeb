<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html ng-app="communication" ng-controller="CommunicationController as uCommunication" style="overflow:hidden;">
	<head>
		<title>FBL COMMUNICATION</title>
		<link href="support/communication/css/bootstrap.css" rel="stylesheet">
		<!-- <link rel="stylesheet" href="support/css/style.css"/> -->
		<link rel="stylesheet" href="support/communication/css/normalize.css">
		<link rel="stylesheet" href="support/communication/css/stylesheet.css">
		<link type="text/css" rel="stylesheet" href="support/communication/css/text_edit.css">
		<link type="text/css" rel="stylesheet" href="support/communication/css/jquery-te-1.4.0.css">
		<link type="text/css" rel="stylesheet" href="support/css/select2/select2.min.css">
		<link type="text/css" rel="stylesheet" href="support/css/select2/select2-bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="support/css/font-awesome/css/font-awesome.min.css">
		<link type="text/css" rel="stylesheet" href="support/css/toaster/toastr.min.css">
		<link rel="stylesheet" type="text/css" href="support/css/custom/tip_slide.css">
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
		<script type="text/javascript" src="support/script/angularJS/angular-idle.min.js"></script>
		<script type="text/javascript" src="support/script/select2/select2.full.min.js"></script>
	    <script type="text/javascript" src="support/script/custom/app.js"></script>
	    <script type="text/javascript" src="support/script/toastr/toastr.min.js"></script>
		<!-- <script type="text/javascript" src="support/controller/UserLoginController.js"></script> -->
		<script type="text/javascript" src="support/controller/CommunicationController.js"></script>
		<script type="text/javascript" src="support/controller/HeaderController.js"></script>
		
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
  		<!-- <script type="text/javascript" src="support/script/custom/jssor.slider.min.js"></script> -->
    	
    	
    	
    	<link rel="stylesheet" href="support/communication/css/communication-style.css"/>
	<link rel="stylesheet" href="support/communication/css/side.css"/>
	<link rel="stylesheet" href="support/communication/css/fileinput.css"/>
	<link rel="stylesheet" href="support/communication/css/tooltip.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,800italic,700italic,600italic,400italic,300italic,800,700,600" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="support/css/chat-box.css">	
	
    <!-- Application -->
     <script type="text/javascript" src="javascript/application.js"></script> 
     <script src="support/communication/js/fileinput.js" type="text/javascript"></script>
     <script src="support/communication/js/tooltip.js" type="text/javascript"></script>
    <script src="support/communication/js/fileinput_locale_fr.js" type="text/javascript"></script>
    <script src="support/communication/js/fileinput_locale_es.js" type="text/javascript"></script>
     <script src="support/communication/js/chat-box.js"></script>
     <script type="text/javascript" src="support/communication/js/tooltipsy.source.js"> </script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.touchswipe/1.6.4/jquery.touchSwipe.min.js"></script>
     <script type="text/javascript" src="support/script/sockjs.js"></script>
     <script type="text/javascript" src="support/script/stomp.js"></script>
     <script type="text/javascript" src="support/script/custom/slider_infor.js"></script>
    </head>
	
	<body style="background:white;color:#696969;">
<head>

 <jsp:include page="/header.html" flush="true" /> 
 <!-- <nav class="navbar navbar-default">
  <div class="container">
    Brand and toggle get grouped for better mobile display
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#"><img src="support/communication/images/logo2.png"/></a>
    </div>
			
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <div class="navcenter">
      <ul class="nav navbar-nav welco">
		 <li class="hi"><a href="#"><img src="support/communication/images/fo1.png"/><br/><span class="prof">PROFILE</span></a></li>
              <li class="hi"><a href="#"><img src="support/communication/images/foo2.png"/><br/><span class="prof">REQUEST</span></a></li>
              <li class="hi"><a href="#"><img src="support/communication/images/fm3.png"/><br/><span class="prof">E RECORDS</span></a></li>
			  <li class="hi" style=" background:#ebebeb;"><a href="#"><img src="support/communication/images/foo3.png" style="padding-left:28px;"/><br/><span class="prof">COMMUNICATION</span></a></li>
	</ul>
      </div>
			<div class="nav navbar-nav navbar-right visible-lg visible-md hidden-sm hidden-xs">
		<table>
			<tr>
				<td style="border-bottom:none;"><a href="#" onmouseover="tooltip.pop(this, '#sub3', {offsetY:-10, smartPosition:false})"><img src="support/communication/images/profile.png"></a></td>
				<td style="border:none;">
					<img src="support/communication/images/singa.png"><span class="firsta">Singapore</span><br/>
					<span class="firstb"><b style="color:#757171; font-size:11pt;">{{ first_name }} {{ last_name }}</b></span><br/>
					<div class="helo"><i class="fa fa-power-off" style="color:#ccc;font-size:20px;"><span class="firstc">Logout</span></i></div>
				</td>
			</tr>
		</table>
	<div class="request">Request > Menu Name > Menu Name 1</div>
    </div>/.navbar-collapse
</div>/.container-fluid
</nav> -->
		<!-- Main component for a primary marketing message or call to action -->
	</div> <!-- /container -->
	<!-- communication ends -->
	<div class="container-fluid">
		
	</div>
	<!-- tap menu start -->
	<jsp:include page="/include/communication/CommunicationsModel.jsp" flush="true" />


<!-- tab content -->  
<!-- tap menu close -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="support/communication/js/jquery-te-1.4.0.js" charset="utf-8"></script>
	<script src="support/communication/js/selectize.js"></script>
	<script src="support/communication/js/index.js"></script>
	
	<script>
				jssor_1_slider_init();
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
				
				
				$('.btn.file-btn').click(function(){

					$('.previewmodal_cont').fadeIn();



				});
				$('#preview_closer').click(

					function(){
						$('.previewmodal_cont').fadeOut();
					}

					);

				</script>
  </body>
</html>
