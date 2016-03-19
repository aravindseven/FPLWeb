
<!DOCTYPE html>
  <html ng-app="fpl">
	<head>
		<title>FPL</title>
		
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0" />
		<link type="text/css" rel="stylesheet" href="support/css/register.css" />
		<link type="text/css" rel="stylesheet" href="support/css/index.css" />
		<link type="text/css" rel="stylesheet" href="support/css/register_media.css">
		<link type="text/css" rel="stylesheet" href="support/css/form1_cus.css">
		<link type="text/css" rel="stylesheet" href="support/css/form2_cus.css">
		<link type="text/css" rel="stylesheet" href="support/css/form3_cus.css">
		<link type="text/css" rel="stylesheet" href="support/css/form1_org.css">
		<link type="text/css" rel="stylesheet" href="support/css/form2_org.css">
		<link type="text/css" rel="stylesheet" href="support/css/form3_org.css">
		
		<script src="support/js/jquery-2.1.4.js"></script>
<!-- 		<script type="text/javascript" src="support/js/registration.js"></script> -->
<!-- 		<script type="text/javascript" src="support/script/jQuery/jquery-1.11.1.min.js"></script> -->
		<script type="text/javascript" src="support/script/jQuery/jquery.cookie.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.center.min.js"></script>
		<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
		<script type="text/javascript" src="support/script/bootStrapJS/bootstrap.min.js"></script>
		<script type="text/javascript" src="support/script/custom/captcha.js"></script>
		<script type="text/javascript" src="support/script/mespeak/mespeak.js"></script>
		
				
		
  
<!--       <script type='text/javascript' src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script> -->
    <script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
  
    
      <script type='text/javascript' src="support/script/angularJS/ng-file-upload-shim.js"></script>
    
  
    
      <script type='text/javascript' src="support/script/angularJS/ng-file-upload.js"></script>
		<script type="text/javascript" src="support/controller/UserProfileController.js"></script>      
      <script type="text/javascript" src="support/script/custom/app.js"></script>
    
  
  <style type='text/css'>
    .thumb {
    width: 24px;
    height: 24px;
    float: none;
    position: relative;
    top: 7px;
}

form .progress {
    line-height: 15px;
}
}

.progress {
    display: inline-block;
    width: 100px;
    border: 3px groove #CCC;
}

.progress div {
    font-size: smaller;
    background: orange;
    width: 0;
}
  </style>
  



<script type='text/javascript'>//<![CDATA[

//inject angular file upload directives and services.
//var app1 = angular.module('fileUpload', ['ngFileUpload']);

app.controller('MyCtrl', ['$scope', 'Upload', '$timeout', function ($scope, Upload, $timeout) {
    $scope.uploadFiles = function(file) {
        $scope.f = file;
        if (file && !file.$error) {
            file.upload = Upload.upload({
                url: 'upload.html',
                file: file
            });

            file.upload.then(function (response) {
                $timeout(function () {
                    file.result = response.data;
                });
            }, function (response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            });

            file.upload.progress(function (evt) {
                file.progress = Math.min(100, parseInt(100.0 * 
                                                       evt.loaded / evt.total));
            });
        }   
    }
}]);
//]]> 

</script>

</head>
<body>
  <body ng-app="fileUpload" ng-controller="MyCtrl">
  <h4>Upload on file select</h4>
  <button type="file" ngf-select="uploadFiles($file)"
          accept="image/*" ngf-max-height="1000" ngf-max-size="1MB">
      Select File</button>
  <br><br>
  File:
  <div style="font:smaller">{{f.name}} {{f.$error}} {{f.$errorParam}}
      <span class="progress" ng-show="f.progress >= 0">
          <div style="width:{{f.progress}}%"  
               ng-bind="f.progress + '%'"></div>
      </span>
  </div>     
  {{errorMsg}}
</body>
  
</body>

</html>

