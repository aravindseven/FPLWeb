<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>FPL | Organization Registration</title>

    <!-- Bootstrap -->
    <link href="support/css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="support/css/style.css"/>
	<link rel="stylesheet" href="support/css/style.css"/>
	<link rel="stylesheet" href="support/css/ad_subscription.css"/>
	<link rel="stylesheet" href="support/css/e.css"/>
	<link rel="stylesheet" href="support/css/browse.css"/> 
	<link rel="stylesheet" href="support/css/fileinput.css"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	<link href="support/css/sidebar.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="support/js/jquery-2.1.4.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	
  </head>
  <body>
<section class="content">
<div id="exTab1" class="container" ng-init="erecords.initnewpolicy();">	
 <!-- dash menu -->
       <ul id="demo_menu3">
		   <li><a href="#" ><button class="round_but" type="button"><img src="images/record-list.png"></button></a></li>
		   <li><a href="#"><button  class="round_but"  type="button"><img src="images/add-record.png"></button></a></li>
     
        </ul>
        <!-- dash menu end -->
	<ul  class="nav nav-pills">
		<div class="tab-content clearfix">
			<div class="tab-pane active" id="a">
				<div class="row" style="background:white;">
						<div class="tab-cont">
							<div class="bac-gally"><img src="images/erc1.png" alt=""/></div>
						</div>
									<div class="row"  id="form1" style="padding-bottom:20px;">
										
										
												<form name="form1" class="form-contes" autocomplete="off">
													<div class="form-first">
													  <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" >
															<label class="overlap_label">Record ID</label>
																<input type="name"   class="form-control" id="recordid" ng-model="erecordsform.policyREId" disabled >
														 
														  </div>
														   <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														   <label class="overlap_label">Record Date</label>
																<input type="name"   class="form-control" id="recorddate" ng-model="erecordsform.policyREDate" disabled>
														  </div>
													</div>
													<div class="form-first">
														  <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" >
														  <label class="overlap_label">Name</label>
																<input type="name"  disabled  class="form-control" id="cname" placeholder=""  style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;" ng-model="erecordsform.policyFname">
														       
														  
														  </div>
														   <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														   <label class="overlap_label">Request Number</label>
																<input type="name" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policyREQNUMBER">
														  </div>
														  <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : form1.policynumber.$invalid && !form1.policynumber.$pristine }">
														  <label class="overlap_label">Policy Number</label>
																<input type="name" class="form-control" name="policynumber" id="policynumber" placeholder=""  ng-model="erecordsform.policyNUMBER" style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;" required>
														  <p ng-show="form1.policynumber.$invalid && !form1.policynumber.$pristine" class="help-block"> Policy Number is required.</p> 
														  </div>
														   <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : form1.policydesc.$invalid && !form1.policydesc.$pristine }">
														   <label class="overlap_label">Policy Description</label>
																<input type="name" class="form-control" name="policydesc" id="policydesc"  placeholder="" ng-model="erecordsform.policyDESC" style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;" required>
														     <p ng-show="form1.policydesc.$invalid && !form1.policydesc.$pristine" class="help-block"> Policy Description is required.</p>
														  
														  </div>
														  <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12"ng-class="{ 'has-error' : form1.insurancecompany.$invalid && !form1.insurancecompany.$pristine }">
														  <label class="overlap_label"> Insurance company.</label>
														  <select class="form-control demo-default subj select2me text_h" name="insurancecompany"id="insurancecompany" ng-model="erecordsform.policyINSURANCECOMPANY" required>
							                                    
							                                    <option ng-repeat=" company in erecordsform.policyCUList" value="{{company}}">{{company}}</option>
						                                   </select>
						                                   </div>
														   <!-- <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														   <label class="overlap_label">Insurance company</label>
																<input type="name" class="form-control" id="exampleInputEmail1" placeholder=""  style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;">
														  </div> -->
														  <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : form1.policytype.$invalid && !form1.policytype.$pristine }">
														  <label class="overlap_label"> Policy Type.</label>
														  
														  <select class="form-control demo-default subj select2me text_h" name="policytype" id="policytype" ng-model="erecordsform.policyTYPE" required>
							                                    
							                                    <option ng-repeat="element in erecordsform.policyTYPEList" value="{{element.id}}">{{element.name}}</option>
						                                   </select>
						                                   </div>
														  
														   <!-- <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														   <label class="overlap_label">Policy Type</label>
																<input type="name" class="form-control" id="exampleInputEmail1" placeholder=""  style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;">
														  </div> -->
														  
													</div>
													<div class="form-first">
														  <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														  <label class="overlap_label">Policy Date</label>
																<input type="date" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policyDATE">
														  </div>
														  
														   <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : form1.policyduration.$invalid && !form1.policyduration.$pristine }">
														   <label class="overlap_label">Policy Duration</label>
																<input type="number" class="form-control" name="policyduration" id="policyduration" placeholder="" ng-model="erecordsform.policyDURATION" style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;" required>
												            <p ng-show="form1.policyduration.$invalid && !form1.policyduration.$pristine" class="help-block"> Policy Duration is required.</p>
														  
														  </div>
														  
														  <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : form1.policyamount.$invalid && !form1.policyamount.$pristine }">
														  <label class="overlap_label">Policy amount</label>
																<input type="number" class="form-control" name="policyamount"  id="policyamount" placeholder=""  ng-model="erecordsform.policyAMOUNT" style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;" required>
													      <p ng-show="form1.policyamount.$invalid && !form1.policyamount.$pristine" class="help-block"> Policy amount is required.</p>
														 
														  </div>
														   <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														   <label class="overlap_label">Premium amount</label>
																<input type="number" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policyPREMAMOUNT">
														  </div>
														   <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														   <label class="overlap_label">Next Renewal date</label>
																<input type="date" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policyRENEWAL_DATE">
														  </div>
														   <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														   <label class="overlap_label">Financial planner name</label>
																<input type="name" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policyFPname">
														  </div>
														  
													</div>
													<!-- <div class="form-first">
														  
														  <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														  <label class="overlap_label">Insurance Company Name</label>
																<input type="name" class="form-control" id="exampleInputEmail1" placeholder="">
														  </div>
														   <div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
														   <label class="overlap_label">Policy Type</label>
																<input type="name" class="form-control" id="exampleInputEmail1" placeholder="">
														  </div>
														  												  
													</div> -->
																																																							
													<div class="form-third text-center">
														<a href="#2a" data-toggle="tab"><button type="button" class="btn btn-default continue" ng-disabled="!form1.$valid">Continue</button></a>
														<!-- ng-click="erecordsform.formId=2;erecords.loadInd(erecordsform)" -->
														<a href="" data-toggle="tab"><button type="button" class="btn btn-default">Cancel</button></a>
													</div>
													
												</form>
											
											
											
											
											
										</div>	
									
										

																
									 
				</div>
			</div>
		<div class="tab-pane" id="2a">
			<div class="register" ng-app>
			
						<div class="tab-cont">
							<div class="bac-gally"><img src="images/erc2.png" alt=""/></div>
						</div>
						<form name="form2" class="form-contes" autocomplete="off">
								<div class="form-first">
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
									  <label class="overlap_label">Nominee First Name</label>
										<input type="name" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policy_nomineefname">
									</div>
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
									  <label class="overlap_label">Nominee Last Name</label>
										<input type="name" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policy_nomineelname">
									</div>
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
									  <label class="overlap_label">Identity proff</label>
										<input type="name" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policy_nomineeIDprof">
									</div>
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
									  <label class="overlap_label">ID#</label>
										<input type="name" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policy_nomineeID">
									</div>
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{ 'has-error' : form2.nomineemobile.$error.number }">
									  <label class="overlap_label">Nominee Mobile Number</label>
										<input type="number" class="form-control" name="nomineemobile" id="exampleInputEmail1" ng-model="erecordsform.policy_nomineemobile" placeholder="" ng-minlength="10" ng-maxlength="10">
									           <p ng-show="((form2.nomineemobile.$error.minlength || form2.nomineemobile.$error.maxlength) && form2.nomineemobile.$dirty) ">phone number should be 10 digits</p>
									</div>
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
									  <label class="overlap_label">Relationship</label>
										<input type="name" class="form-control" id="exampleInputEmail1" placeholder="" ng-model="erecordsform.policy_nomineerelation">
									</div>
														
								</div>
																							
							<div class="only-but text-center">
								<div class="row">
									<a href="#3a" data-toggle="tab"><button type="button" class="btn btn-default continue" >Continue</button></a>
									<a href="#a" data-toggle="tab"><button type="button" class="btn btn-default">Back</button></a>
									<a href="" data-toggle="tab"><button type="button" class="btn btn-default">Cancel</button></a>
								</div>
							</div>
							
						
						
						</form>
															
			</div>
		</div>

	<div class="tab-pane" id="3a" id="form3">
			<div class="register" style="float:left;">
			
						<div class="tab-cont">
							<div class="bac-gally"><img src="images/erc3.png" alt=""/></div>
						</div>
						<form name=form3 class="form-contes" autocomplete="off">
								<div class="form-first">
									<div class="form-group col-lg-12 col-md-12 col-sm-12 col-xs-12" ng-class="{ 'has-error' : form3.conf.$invalid && !form3.conf.$pristine}">
									  <label class="overlap_label">CONFIDENTIALITY PREFERENCE</label>
										<select class="i-input"  name="conf" style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;" ng-model="erecordsform.policyCONFIDENTIALITY_PRE" required>
											
											<option value="ft1">PREF 1</option>
											<option value="ft2">PREF 2</option>
											<option value="ft3">PREF 3</option>
											<option value="ft1">PREF 4</option>
										</select>
									</div>
								</div>
								<div class="form-first">
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<label class="overlap_label">Advance Alert</label>
										<input type="number" class="form-control" id="exampleInputEmail1" ng-model="erecordsform.policyAdvancealert" placeholder="" style="background:#E3E3E3">
									</div>
									<ul class="email col-lg-6 col-md-6 col-sm-6 col-xs-12" style="margin-top: 42px;">
										<li><input type="checkbox" name="vehicle" value="Bike"><span class="mail">Email Alert</span></input></li>
										<li><input type="checkbox" name="vehicle" value="Bike"><span class="mail">Sms Alert</span></input></li>
										<li><input type="checkbox" name="vehicle" value="Bike"><span class="mail">Mobile App Notification</span></input></li>
									</ul>
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<label class="overlap_label">Alert Schedule</label>
										<input type="name" class="form-control" id="exampleInputEmail1" ng-model="erecordsform.policyAlertsch" placeholder=""  style="background:#E3E3E3">
									</div>
									<div class="form-group col-lg-6 col-md-6 col-sm-6 col-xs-12" ng-class="{'has-error' :form3.Schedule_Days.$invalid && !form3.Schedule_Days.$pristine}">
									<label class="overlap_label">Schedule Days</label>
										<select class="i-input" name="Schedule_Days"  style="background: #fff url('images/inputtop.png') no-repeat scroll right top / 20px auto;"ng-model="erecordsform.policySchedule_Days" required>
											
											<option value="1">Day 1</option>
											<option value="2">Day 2</option>
											<option value="3">Day 3</option>
											<option value="4">Day 4</option>
											<option value="5">Day 5</option>
											<option value="6">Day 6</option>
											<option value="7">Day 7</option>
										</select>
									</div>
								</div>
							<div class="area">
								<textarea class="boxing" placeholder="Comments and important notes" ng-model="erecordsform.policyComment"></textarea>
							</div>
																					
							<div class="only-but text-center">
								<div class="row">
									<a href="" data-toggle="tab"><button type="button" class="btn btn-default continue" ng-click="erecords.CreatePolicy(erecordsform)" ng-disabled="form3.$invalid">Save</button></a>
									<a href="#2a" data-toggle="tab"><button type="button" class="btn btn-default">Back</button></a>
									<a href="" data-toggle="tab"><button type="button" class="btn btn-default">Cancel</button></a>
								</div>

							
							</div>
							
								
						</form>
															
			</div>
		</div>
		
		
		</div>
	</ul>
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
 <script src="support/js/fileinput.js" type="text/javascript"></script>
    <script src="support/js/fileinput_locale_fr.js" type="text/javascript"></script>
    <script src="support/js/fileinput_locale_es.js" type="text/javascript"></script> 
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
<!-- file input script end -->
<!-- sidebar start -->

   <script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
 <script src="js/jquery.sidebar.js" type="text/javascript"></script>
       <script type="text/javascript" src="js/jquery.sidebar2.js"></script>
    <script type="text/javascript">
        $("ul#demo_menu3").sidebar({
            position:"right",
          open:"click"
        });
        
        </script>
		<!-- sidebar end -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="support/js/bootstrap.min.js"></script>
  </body>
</html>