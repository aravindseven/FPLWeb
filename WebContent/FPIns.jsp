<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html ng-app="fpl">
<head>
<title>FPL</title>
<link rel="stylesheet" href="support/css/bootStrap/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="support/css/custom/style.css"
	type="text/css">
<link rel="stylesheet" href="support/css/custom/mySettings.css"
	type="text/css">
<link rel="stylesheet" href="support/css/custom/notify.css"
	type="text/css">
<link rel="stylesheet" href="support/css/jquery/jquery.msg.css"
	type="text/css">
<script type="text/javascript"
	src="support/script/jQuery/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="support/script/jQuery/jquery.cookie.js"></script>
<script type="text/javascript"
	src="support/script/jQuery/jquery.center.min.js"></script>
<script type="text/javascript" src="support/script/jQuery/jquery.msg.js"></script>
<script type="text/javascript"
	src="support/script/bootStrapJS/bootstrap.min.js"></script>
<script type="text/javascript"
	src="support/script/angularJS/angular.min.js"></script>
<script type="text/javascript" src="support/script/custom/captcha.js"></script>
<script type="text/javascript" src="support/script/mespeak/mespeak.js"></script>
<script type="text/javascript"
	src="support/script/mespeak/mespeak_init.js"></script>
<script type="text/javascript" src="support/script/custom/app.js"></script>
<script type="text/javascript"
	src="support/controller/UserLoginController.js"></script>
<script type="text/javascript"
	src="support/controller/UserRegisterController.js"></script>
<script type="text/javascript"
	src="support/controller/UserSettingsController.js"></script>
<script type="text/javascript"
	src="support/controller/PasswordRecoveryController.js"></script>
<script type="text/javascript"
	src="support/controller/UserNameRecoveryController.js"></script>
<script type="text/javascript"
	src="support/controller/PasswordResetController.js"></script>
<script type="text/javascript"
	src="support/controller/PasswordChangeController.js"></script>
<script type="text/javascript"
	src="support/controller/FPInsController.js"></script>
<script type="text/javascript" src="support/script/custom/AesUtil.js"></script>
<script type="text/javascript" src="support/script/custom/util.js"></script>
</head>

<!-- Main Wrapper Start -->
<div class="mainWrapper" ng-controller="UserLoginController as uLogin">

	<!-- Header bar Start -->
	<jsp:include page="/include/common/FPLHeader.jsp" flush="true" />

	<!-- Login Modal -->
	<jsp:include page="/include/login/LoginModel.jsp" flush="true" />

	<!-- User Registration Modal -->
	<jsp:include page="/include/login/UserRegistrationModel.jsp"
		flush="true" />

	<!--Slide Show and content -->
	<jsp:include page="/include/common/InfoPage.jsp" flush="true" />

	<div class="innerWrapper" ng-controller="FPInsController as uEntity">
		<div class="row innerContentWrapper myProfileWrapper">
			<div class="tabbable">
				<div class="titleHeaderBlue">FP Registration <span style="float: right">Current TimeStamp: {{date
						| date:'yyyy-MM-dd HH:mm:ss'}}</span></div>
				<div class="tab-content clear paddingLnone">
					<div id="profile"
						class="tab-pane fade active in paddingLnone profileTabContent">
						<div class="col-lg-12 col-md-12 col-sm-12 paddingLnone">
							<div class="col-lg-12 col-md-12 col-sm-12 profileTabForm">
								<form name="fplInsForm" ng-submit="uEntity.saveEntity(entity)"
									autocomplete="off" class="col-sm-12">
									<div class="row">
										<div class="col-md-12 paddingNone">
											<div class="col-lg-6 col-md-6 col-sm-6">
												<h3>FP ID</h3>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-6">
												<h3>Registration Details</h3>
											</div>
										</div>
										<hr class="clear">
										<div class="col-md-6">
											<!-- FIRST SECTION START -->
											<div class="innerBorderDiv">
												<div class="form-group primaryName">
													<label class="control-label">Primary Name</label> <input
														type="text" class="form-control" placeholder="Firstname"
														ng-model="entity.firstName" required id="firstName">
													<input type="text" class="form-control"
														placeholder="Lastname" ng-model="entity.lastName" required
														id="lastName">
												</div>
												<div class="form-group">
													<label class="control-label">Gender</label> <label
														class="radio-inline"> <input type="radio"
														ng-model="entity.gender" id="entityName"
														value="male"> Male
													</label> <label class="radio-inline"> <input type="radio"
														ng-model="entity.gender" id="entityName"
														value="female"> Female
													</label>
												</div>
												<div class="form-group">
													<label class="control-label">Date of Birth</label> <input
														type="date" class="form-control" ng-model="entity.dob"
														placeholder="mm-dd-yyyy" required id="dob">
												</div>
												<div class="form-group">
													<label class="control-label">Identity Proof</label> <input
														type="text" class="form-control" ng-model="entity.idProof"
														required id="idProof">
												</div>
												<div class="form-group">
													<label class="control-label">ID #</label> <input
														type="text" class="form-control" ng-model="entity.id"
														required id="id">
												</div>
											</div>
											<!-- FIRST SECTION END -->
											<!-- SECOND SECTION START -->

											<div class="innerBorderDiv">
												<div class="form-group">
													<label class="control-label">Block / Unit Number</label> <input
														type="text" class="form-control" ng-model="entity.block"
														required id="block">
												</div>
												<div class="form-group">
													<label class="control-label">Building Name</label> <input
														type="text" class="form-control"
														ng-model="entity.buildingName" required id="buildingName">
												</div>
												<div class="form-group">
													<label class="control-label">Street</label> <input
														type="text" class="form-control" ng-model="entity.street"
														required id="street">
												</div>
												<div class="form-group">
													<label class="control-label">Postal Code</label> <input
														type="text" class="form-control"
														ng-model="entity.postalCode" required id="postalCode"
														ng-blur="uEntity.getGeoLocation(entity)">
												</div>
												<div class="form-group">
													<label class="control-label">City</label> <input
														type="text" class="form-control" ng-model="entity.city"
														required id="city">
												</div>
												<div class="form-group">
													<label class="control-label">State</label> <input
														type="text" class="form-control" ng-model="entity.state"
														required id="state">
												</div>
												<div class="form-group">
													<label class="control-label">Country</label> <input
														type="text" class="form-control" ng-model="entity.country"
														required id="country">
												</div>
											</div>

											<!-- SECOND SECTION END -->

											<!-- THIRD SECTION START -->
											<div class="innerBorderDiv">
												<div class="form-group">
													<label class="control-label">Phone: Mobile</label> <input
														type="tel" class="form-control" ng-model="entity.mobile"
														required id="mobile">
												</div>
												<div class="form-group">
													<label class="control-label">Phone: Landline</label> <input
														type="tel" class="form-control" ng-model="entity.landline"
														id="landline">
												</div>
												<div class="form-group">
													<label class="control-label">Email</label> <input
														type="email" class="form-control" ng-model="entity.email"
														required id="email">
												</div>
											</div>
											<!-- THIRD SECTION END -->
										</div>
										<div class="col-md-6">
											<!-- SECOND - FIRST SECTION START -->
											<div class="innerBorderDiv">
												<div class="form-group">
													<label class="control-label">Professional Reg #</label> <input
														type="text" class="form-control" ng-model="entity.regNo"
														required id="regNo">
												</div>
												<div class="form-group">
													<label class="control-label">Registration Entity</label> <input
														type="text" class="form-control"
														ng-model="entity.regEntity" required id="regEntity" />
												</div>
												<div class="form-group">
													<label class="control-label">Expiry Date</label> <input
														type="date" class="form-control"
														placeholder="mm-dd-yyyy" ng-model="entity.expiryDate" required id="expiryDate">
												</div>
												<div class="form-group">
													<label class="control-label">Coverage</label>  <select
														type="text" class="form-control"
														ng-model="entity.coverage" required id="coverage" multiple>
														<option value="auto">Auto Insurance</option>
														<option value="home">Home Insurance</option>
														<option value="life">Life Insurance</option>
														<option value="marine">Marine Insurance</option>
														</select>
												</div>
												<div class="form-group">
													<label class="control-label">Principal</label> <select
														class="form-control" ng-model="entity.principal" required
														id="principal">
														<option value="">Select Principal</option>
														<option value="principal1">Principal1</option>
														<option value="principal2">Principal2</option>
														<option value="principal3">principal3</option>
														<option value="others">Others</option>
													</select>
												</div>
												<div class="form-group" ng-show="entity.principal == 'others'">
													<label class="control-label">Specify</label> <input
														type="text" class="form-control"
														ng-model="entity.principalDesc"
														id="principalDesc">
												</div>
												<div class="form-group">
													<label class="control-label">Specialization 1</label> <input
														type="text" class="form-control"
														ng-model="entity.specialization1" required
														id="specialization1">
												</div>
												<div class="form-group">
													<label class="control-label">Specialization 2</label> <input
														type="text" class="form-control"
														ng-model="entity.specialization2" required
														id="specialization2">
												</div>
												<div class="form-group">
													<label class="control-label">Agency Name</label> <input
														type="text" class="form-control"
														ng-model="entity.agencyName" required id="agencyName">
												</div>
												<div class="form-group">
													<label class="control-label">Website</label> <input
														type="text" class="form-control" ng-model="entity.website"
														 id="website">
												</div>
											</div>
											<!-- SECOND - FIRST SECTION END -->
											<!-- SECOND - SECOND SECTION START -->
											<div class="innerBorderDiv">
												<div class="checkbox">
													<label>
														<input type="checkbox" required>
														I have read and accepted the<span class="link">Terms
															& Conditions</span>
													</label>
												</div>
												<div class="form-group">
													<button type="button" ng-click="uEntity.reset()"
														class="btn btn-Secondary col-lg-2 pull-right">Reset</button>
													<button type="button"
														class="btn btn-danger col-lg-2 marginRfifteen pull-right">Cancel</button>
													<button type="submit"
														class="btn btn-primary col-lg-2 marginRfifteen pull-right">Save</button>
												</div>
											</div>
											<!-- SECOND - SECOND SECTION END -->
											<!-- SECOND - THIRD SECTION START -->
											<div class="innerBorderDiv">
												<div class="form-group">
													<label class="control-label">Subscription Status</label> <input
														type="text" class="form-control" disabled
														ng-model="entity.subscriptionStatus"
														id="subscriptionStatus">
												</div>
												<div class="form-group">
													<label class="control-label"></label>
													<button class="btn btnPrimaryBlue" onclick="location.href='subscriptionmgmt.jsp?type=createFPSub'">Subscribe Now</button>
												</div>
											</div>
											<!-- SECOND - THIRD SECTION END -->
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Login Modal -->
	<jsp:include page="/include/common/InsideFooter.jsp" flush="true" />

</div>
</html>
