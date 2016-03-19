<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" ng-app="fpl">
<head>
<title>FPL</title>
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
		<script type="text/javascript" src="support/script/custom/captcha.js"></script>
		<script type="text/javascript" src="support/script/mespeak/mespeak.js"></script>
		<script type="text/javascript" src="support/script/mespeak/mespeak_init.js"></script>
		<script type="text/javascript" src="support/script/custom/app.js"></script>
		<script type="text/javascript" src="support/controller/UserLoginController.js"></script>
		<script type="text/javascript" src="support/controller/UserRegisterController.js"></script>
		<script type="text/javascript" src="support/controller/UserSettingsController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordRecoveryController.js"></script>
		<script type="text/javascript" src="support/controller/UserNameRecoveryController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordResetController.js"></script>
		<script type="text/javascript" src="support/controller/PasswordChangeController.js"></script>
		<script type="text/javascript" src="support/controller/AdminSubscriptionController.js"></script>
		<script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
</head>
<body>
</body>
</html>
<!-- Main Wrapper Start -->
<div class="mainWrapper" ng-controller="UserLoginController as uLogin">
  <!-- Header bar Start -->
 <jsp:include page="/include/common/FPLHeader.jsp" flush="true" />
  <!-- Header Bar End -->
  
  <!-- REQUEST START -->
  <div class="innerWrapper">
    <div class="row innerContentWrapper myProfileWrapper">
      <div class="tabbable">
<div class="titleHeaderBlue">Resource center</div>
        <div class="tab-content clear paddingLnone" style="height:auto;min-height:400px;">
          <div id="profile" class="tab-pane fade active in paddingLnone profileTabContent">
            <div class="col-lg-12 col-md-12 col-sm-12 paddingLnone">
              <div class="col-lg-2 col-md-2 col-sm-2">
                <div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix" role="complementary">
                  <ul class="nav bs-docs-sidenav">
                    <li><a href="#dropdowns-example">Help</a></li>
                    <li><a href="#dropdowns-alignment">Insurance Domain</a></li>
                  </ul>
                </div>
              </div>
              <div class="col-lg-10 col-md-10 col-sm-10 requestGrid">
              <h3 class="blue marginNone">Help</h3>
                <hr />
                <div>Content comes here..</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- MY PROFILE END -->
  
  <!-- Footer Start -->
  <jsp:include page="include/common/InsideFooter.jsp" flush="true" />
  <!-- Footer End -->
  
  <!--Notification Start-->
  <div id="notify-success" class="success"></div>
  <div id="notify-failure" class="error"></div>
  <div id="notify-info" class="info"></div>
  <!--<div class="warning"></div>
  <div class="print"></div>
  <div class="purchase"></div>
  <div class="secure"></div>
  <div class="message"></div>
  <div class="download"></div>
  <div class="tip"></div>-->
  <!--Notification End-->
</div>
