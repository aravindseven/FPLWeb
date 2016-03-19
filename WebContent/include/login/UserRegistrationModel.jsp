<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal fade" id="RegisterModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog registerUser" ng-controller="UserRegisterController as uRegister">
		<form name="registerForm" ng-submit="uRegister.register(user)" autocomplete="off" ng-init="uRegister.checkCountry(user)">
			<div class="modal-content loginWrapper">
				<div class="modal-header">
					<div id="RegisterTitle">Register</div>
				</div>
				<div class="modal-body">
					<div class="col-lg-6 col-md-6">
						<select ng-model="user.type" class="form-control" required id="userType" name="userType" required>
						</select>
					</div>
					<div class="col-lg-6 col-md-6">
						<input type="email" class="form-control" name="regUsername" placeholder="Email" ng-model="user.username" required 
						 ng-minlength=3>
					</div>
					<div class="col-lg-6 col-md-6">
						<input type="text" class="form-control" placeholder="First Name"  required
						 ng-minlength=3>
					</div>
					<div class="col-lg-6 col-md-6">
						<input type="text" class="form-control" placeholder="Last Name"  required
						 ng-minlength=3>
					</div>
					<!-- 
					<div class="col-lg-6 col-md-6">
						<input type="email" class="form-control" name="regEmail" placeholder="Alternate Email" ng-model="user.email">
						<label ng-show="uRegister.emailAltEmailError" class="ng-hide validationMsg">Alternate Email should not be same as username</label>
					</div>
					<div class="col-lg-6 col-md-6">
						<input  class="form-control" type="tel" name="mobilephone" placeholder="Mobile Phone" id="mobilephone"
						   ng-model="user.mobilephone" title="Mobile number should be a number.">
					</div>
					 -->
					
					<!-- 
					<div class="col-lg-6 col-md-6">
						<input pattern="^(?=^.{8,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" type="password" class="form-control" name="regPassword" ng-model="user.password" id="regPassword"
							   placeholder="Password" title="Password should contain minimum 8 characters, Uppercase, Lowercase, Special characters and Number" required ng-keyup="uRegister.userPwdError = false;uRegister.passwordStrength(user);"
							   onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');if(this.checkValidity()) registerForm.regConfirmPassword.pattern = this.value;"> 
						<label ng-show="uRegister.userPwdError" class="ng-hide validationMsg">Password should not be same as username</label>
						<div id="password-strength-text" style="display:none"> </div>
					</div>				
					
					<div class="col-lg-6 col-md-6">
						<input type="password" class="form-control" name="regConfirmPassword" title="Confirm Password should be same as Password." id="regConfirmPassword" placeholder="Confirm Password" ng-model="user.confirmPassword" required ng-keyup="uRegister.confirmPwdError = false"  ng-change="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');" disabled> 
					</div>
					 -->
					<div class="pull-left col-lg-6 col-md-6 clear">
						<label for="regSecretQuestion">What is your Mother's Maiden Name?</label> 
						<input type="text" class="form-control" placeholder="Your answer" name="regSecretQuestionAnswer" ng-model="user.regSecretQuestionAnswer" required>
					</div>
					<div class="pull-left col-lg-6 col-md-6 pull-right captchaSection">
						<label for="captcha">Captcha:</label>
						<div id="captcha" class="marginNone"></div>
						<a href="#" tabindex="-1"><img src="support/images/refersh.gif" alt="Re-generate captcha" height="16" width="16" ng-click="uRegister.regenerateCaptcha()" tabindex="0"></a>
						<a href="#" tabindex="-1"><img src="support/images/audio.gif" alt="Speak captcha" height="16" width="16" ng-click="uRegister.speakCaptcha()" tabindex="0"> </a>
						<input type="text" class="form-control" name="regCaptcha" ng-model="user.captcha" required id="regCaptcha"> 
						<label ng-show="uRegister.confirmCaptcha" class="ng-hide validationMsg">Captcha text do not match. Please re-enter.<br></label>
					</div>
					<div class="pull-left col-lg-7 col-md-12">
							<input type="checkbox" name="regTermsConditions" id="regTermsConditions" value="0" ng-model="user.terms" required/> 
							<label for="regTermsConditions">I accept the <a target="_blank" href="terms.html">Terms and Conditions.</a></label> 
					</div>
					<div class="pull-left col-lg-7 col-md-15">		
						    <div class="legend">* Please ensure to fill out all fields bordered Red.</div>
					</div>
					<div class="clear registerBtnGroup">
						<button class="btn btn-primary btn-medium" type="submit" ng-disabled="!registerForm.$valid" ng-enabled="registerForm.$valid">Register</button>
						<button data-dismiss="modal" class="btn btn-primary btn-medium" type="button" ng-click="uRegister.reset()">Cancel</button>
					</div>
				</div>
				<div class="modal-footer">
					<span class="logo"></span>
					<span class="pull-right">Copyright fplonline 2014</span>
				</div>
			</div>
		</form>
	</div>
</div>