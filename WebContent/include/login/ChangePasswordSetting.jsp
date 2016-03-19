<div id="changePassword" class="col-lg-10 col-md-10 col-sm-10 profileTabForm alternativeForm" ng-controller="PasswordChangeController as pChange">
	<form ng-submit="pChange.change(pChange)" autocomplete="off" class="col-sm-12" name="PasswordResetForm">
		<div class="row">
			<div class="col-md-12 paddingNone">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<h3>Change Password</h3>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<h3></h3>
				</div>
			</div>
			<hr class="clear">
			<div class="col-md-6">
				<!-- FIRST SECTION START -->
				<div class="innerBorderDiv">
					<div class="form-group">
						<label class="control-label">New Password</label> <input
							type="password" name="newPassword" class="form-control" required id="newPassword"
							pattern="^(?=^.{8,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$"
							ng-model="pChange.newPassword" onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');if(this.checkValidity()) PasswordResetForm.confirmPassword.pattern = this.value;"
							title="Password should contain minimum 8 characters, Uppercase, Lowercase and Number" />
					</div>
					<div class="form-group">
						<label class="control-label">Confirm Password</label> <input
							type="password" name="confirmPassword" class="form-control"
							ng-model="pChange.confirmPassword" required
							ng-keyup="pChange.confirmPasswordError = false" /> 
					</div>
					<label
							ng-show="pChange.confirmPasswordError"
							class="ng-hide validationMsg"> Passwords do not
							match...</label>
				</div>
				<!-- FIRST SECTION END -->
			</div>
			<div class="col-md-6">
				<!-- SECOND - SECOND SECTION START -->
				<div class="innerBorderDiv">
					<div class="form-group">
						<label class="control-label">Old Password</label> <input
							type="password" name="oldPassword" class="form-control"
							required ng-model="pChange.oldPassword" />
					</div>
					<div class="form-group">
						<button type="submit"
							class="btn btn-primary col-lg-2 marginRfifteen saveChangesBtn">Change</button>
					</div>
				</div>
				<!-- SECOND - SECOND SECTION END -->
			</div>
		</div>
	</form>
</div>
