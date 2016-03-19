<!DOCTYPE html>
<html  lang="en" ng-app="erecord" ng-controller="ErecordsController as erecords">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>FPL COMMUNICATION || Search Request`</title>
    
    <link href="support/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="support/css/style.css"/>
    <link rel="stylesheet" href="support/css/datepicker.css"/>
	<link rel="stylesheet" href="support/css/browse.css"/>
	<link rel="stylesheet" href="support/css/fileinput.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	
<!-- hetch over efect -->
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet" type="text/css" href="support/css/sidebar.css" />
<link rel="stylesheet" type="text/css" href="support/css/component.css" />
		<script src="support/js/modernizr.custom.js"></script>
        
         <!-- This is what you need -->
    <!-- <script src="lib/sweet-alert.js"></script>
    <link rel="stylesheet" href="lib/sweet-alert.css"> --> 
    <script type="text/javascript" src="support/script/angularJS/angular.min.js"></script>
     <script type="text/javascript" src="support/script/custom/app.js"></script>
    <script type="text/javascript" src="support/controller/HeaderController.js"></script>
    <script type="text/javascript" src="support/controller/ErecordsController.js"></script>
    <script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
	<script type="text/javascript" src="support/script/custom/util.js"></script>
    <script type="text/javascript" src="support/script/ui-bootstrap.js"></script>
    
   
    </head>
  <body >
  <jsp:include page="/header.html" flush="true"></jsp:include>
         <ul id="demo_menu3" style="padding-top: 30px;" >
       <li class="activedash"><a href="#"><button class="round_but" type="button"><img src="images/dashboard2.png" alt="DASHBOARD"></button></a></li>
       <!--<li><a href="#" ><button class="round_but" type="button"><img src="images/newreqst2.png"></button></a></li>-->
       <li><a href="#"><button class="round_but" type="button"><img src="images/searchreq2.png"></button></a></li>
        </ul>
        <div class="mainWrapper">
        		
               <% if(request.getParameter("type").equals("form")){%>
						 <jsp:include page="/erecordform.jsp" flush="true"/> 
						
					<%} else if(request.getParameter("type").equals("list")){%>
						<jsp:include page="/erecord-list.jsp" flush="true"/>
					<%}%>
 </div>
 
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
   	<script type="text/javascript" src="support/js/jquery-2.1.4.js"></script>
  
    <script src="support/communication/js/bootstrap.min.js"></script>
    <script src="support/communication/js/datepicker.js"></script>
   	<script type="text/javascript" src="support/js/tooltip.js"></script>
    <script type="text/javascript" src="support/communication/js/jquery.sidebar.js"></script>
	 <script src="support/js/fileinput.js" type="text/javascript"></script>
    <script src="support/js/fileinput_locale_fr.js" type="text/javascript"></script>
    <script src="support/js/fileinput_locale_es.js" type="text/javascript"></script> 
    <script src="support/communication/js/classie.js"></script>
		<script src="support/communication/js/uisearch.js"></script>
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
	 });
		$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>

    
    <script>
        $("ul#demo_menu3").sidebar({
            position:"right",
          open:"click"
        });
		
        
        </script>
        
		<!-- <script>
			new UISearch( document.getElementById( 'sb-search' ) );
		</script> -->
  </body>
</html>