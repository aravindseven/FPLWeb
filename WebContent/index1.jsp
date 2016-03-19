<!-- 
	Note: 
    		1. Custom buttons( which are actually divisions and not input tags ) whose values have to be retrieved 
            using data attribute in those particular divs
    		2. Animations are purely built from scratch, and no plugins are used other than jQuery. Please contact
            us in case of change in animations or clarification
			3. No frameworks are used and class names are purely content based. 
            4. Most features are mobile ready. The site was built with desktop and tablet layout in mind.
            5. Certain sections are commented out and not deleted until the site is live to save time
            6. All images/icons in the site can be changed by replacing the images/icons in their respective folders
    
-->
<!DOCTYPE html>
<html ng-app="fpl">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0" />
		<link type="text/css" rel="stylesheet" href="support/css/index.css" />
		<link type="text/css" rel="stylesheet" href="support/css/index_media.css" />
		<script src="support/js/jquery-2.1.4.js"></script>
		<script type="text/javascript" src="support/js/index.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
		<script type="text/javascript" src="support/script/custom/app.js"></script>

		<script src='https://www.google.com/recaptcha/api.js'></script>
		<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

		<script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.center.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/custom/app.js"></script>
		<script type="text/javascript" src="support/script/custom/util.js"></script>
		<script type="text/javascript" src="support/controller/PasswordRecoveryController.js"></script>
		
		<!-- UserLogin Controller -->
		<script type="text/javascript" src="support/controller/UserLoginController.js"></script>
		<!-- User Register Controller -->
		<script type="text/javascript" src="support/controller/UserRegisterController.js"></script>
		<script type='text/javascript' src="support/script/angularJS/ng-file-upload-shim.js"></script>
        <script type='text/javascript' src="support/script/angularJS/ng-file-upload.js"></script>
				
		<title>FPL | Home</title>
	</head>
<body>
	<!-- Background Banner -->
	<jsp:include page="/include/common/FPLBanner.jsp" flush="true" />
	<!-- Header bar Start -->
	<jsp:include page="/include/common/FPLHeader1.jsp" flush="true" />	
	<!-- Bottom Ads Section -->
	<jsp:include page="/include/common/FPLBottomAds.jsp" flush="true" />	
	<!-- Footer Section -->
	<jsp:include page="/include/common/FPLFooter.jsp" flush="true" />
    <!-- Login PopUp -->
   <%--  <jsp:include page="/include/common/FPLLogin.jsp" flush="true" />
    <!-- Registration PageUp -->
    <jsp:include page="/include/common/FPLRegistration.jsp" flush="true" /> --%>
</body>
</html>