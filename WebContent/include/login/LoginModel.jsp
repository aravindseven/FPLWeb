<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content loginWrapper">
			<div class="modal-header">
				<div>Login</div>
			</div>
			<div class="modal-body">
				<form name="loginForm" ng-submit="uLogin.login(credentials)" novalidate autocomplete="off">
					<div class="username">
						<input type="email" class="form-control" name="username" id="username" ng-model="credentials.username" placeholder="Email id" ng-keyup="uLogin.loginValid()" required/>
					</div><br>
					<div class="password">
						<input type="password" class="form-control" name="password" id="password" ng-model="credentials.password" placeholder="Password" ng-keyup="uLogin.loginValid()" required/>
					</div>
					<div>
						<button type="submit" class="btn btn-primary" ng-disabled="!loginForm.$valid" ng-enabled="loginForm.$valid">Login</button>
						<button data-dismiss="modal" class="btn btn-primary" type="button" ng-click="uLogin.reset()">Cancel</button>
					</div>
				</form>
				<div class="clear">
					<ul class="forgotWrapper clear">
						<li>
							<a href="#home" role="tab" id="forgotPassword" data-toggle="tab">Forgot Password 
								<span class="glyphicon glyphicon-chevron-down"></span>
							</a>
						</li>
						<li>
							<a href="#profile" role="tab" id="forgotUsername" data-toggle="tab">Recover Username 
								<span class="glyphicon glyphicon-chevron-down"></span>
							</a>
						</li>
					</ul>
					<div class="forgotPassword pull-left">
						<!-- PASSWORD RECOVERY START -->
						<div ng-controller="PasswordRecoveryController as pRecovery" id="passwordRecovery" style="display: none;">
							<h3>Password Recovery:</h3>
							<hr />
							<form name="PasswordRecoveryForm" ng-submit="pRecovery.Recovery(credentials)" autocomplete="off">
								<div class="col-lg-6 col-md-6 marginTten">
									<input type="text" name="Username" class="form-control" placeholder="Email" ng-model="credentials.username" autofocus required />
								</div>
								<div class="col-lg-6 col-md-6 marginTten">
									<input pattern="[0-9]{3}[0-9]{3}[0-9]{4}" class="form-control" placeholder="Mobile phone" type="tel" name="mobilephone"
										   ng-model="credentials.mobilephone" title="Mobile number should be 10 digits." required>
								</div>
								<div class="col-lg-6 col-md-6 marginTten">
									<input type="email" name="AlternateEmail" class="form-control" placeholder="Alternate Email"
										   ng-model="credentials.AlternateEmail" required>
								</div>
								<div class="col-lg-12 col-md-12 marginTten secretQuestion">
									<label for="SecretQuestion"> What is your Mother's Maiden Name? </label> 
									<input type="text" name="SecretQuestion" class="form-control" placeholder="Your Answer" ng-model="credentials.SecretQuestion" required>
								</div>
								<button class="btn btn-primary btn-medium" type="submit" ng-disabled="!PasswordRecoveryForm.$valid" ng-enabled="PasswordRecoveryForm.$valid">Recover</button>
							</form>
						</div> <!-- PASSWORD RECOVERY END -->

						<!-- USERNAME RECOVERY START -->
						<div ng-controller="UserNameRecoveryController as uRecovery" id="usernameRecovery" style="display: none;">
							<h3> Email Recovery: </h3>
							<hr />
							<form name="UserNameRecoveryForm" ng-submit="uRecovery.Recovery(credentials)" autocomplete="off">
								<div class="col-lg-6 col-md-6 marginTten">
									<input pattern="[0-9]{3}[0-9]{3}[0-9]{4}" type="tel" class="form-control" placeholder="Mobile phone" name="mobilephone" 
									       ng-model="credentials.mobilephone" autofocus title="Mobile number should be 10 digits." required />
								</div>
								<div class="col-lg-6 col-md-6 marginTten">
										<input type="email" name="AlternateEmail" class="form-control" placeholder="Alternate Email" ng-model="credentials.AlternateEmail" required />
								</div>
								<div class="col-lg-12 col-md-12 marginTten secretQuestion">
									<label for="SecretQuestion"> What is your Mother's Maiden Name?</label> 
									<input type="text" name="SecretQuestion" class="form-control" ng-model="credentials.SecretQuestion" placeholder="Your Answer" required />
								</div>
								<button class="btn btn-primary btn-medium" type="submit"  ng-disabled="!UserNameRecoveryForm.$valid" ng-enabled="UserNameRecoveryForm.$valid">Recover</button>
							</form>
						</div> <!-- USERNAME RECOVERY END -->
					</div>
				</div> <!-- Forgot password and user name end -->
			</div>
			<div class="modal-footer">
				<span class="logo"></span>
				<span class="pull-right">Copyright fplonline 2014</span>
			</div>
		</div>
	</div>
</div>
