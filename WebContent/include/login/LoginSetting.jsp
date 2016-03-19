<div id="accountSettings" class="col-lg-10 col-md-10 col-sm-10 profileTabForm" ng-controller="UserSettingsController as uSettings">
	<form ng-init="uSettings.fetch()" ng-submit="uSettings.change(uSettings)" autocomplete="off" role="form" id="form" class="col-sm-12">
		<div class="row">
			<div class="col-md-12 paddingNone">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<h3>Account Details</h3>
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
					<label class="control-label">Primary Email</label> <input
						type="email" class="form-control" id="primaryEmail"
						ng-model="uSettings.primaryEmailId" disabled>
				</div>
				<div class="form-group">
					<label class="control-label">Alternate Email</label> <input
						type="email" class="form-control" id="alternateEmail"
						ng-model="uSettings.alternateMailId" required disabled>
				</div>
				<div class="form-group">
					<button type="button" ng-click="uSettings.verify()" class="btn btn-primary col-lg-2 marginRfifteen verifyChangesBtn">Verify</button>
				</div>
				<div class="form-group">
					<label class="control-label">Mobile Number</label><input
						pattern="[0-9]{3}[0-9]{3}[0-9]{4}" type="password"
						class="form-control" name="mobilephone" id="mobilePhone"
						ng-model="uSettings.mobilephone"required disabled ng-click="uSettings.typeChange('mobile','view');" ng-blur="uSettings.typeChange('mobile','mask');">
				</div>
			</div>
			<!-- FIRST SECTION END -->
		</div>
		<div class="col-md-6">
			<!-- SECOND - SECOND SECTION START -->
			<div class="innerBorderDiv">
				<div class="form-group">
					<label class="control-label">Type</label> <select id="userType"
						class="form-control" ng-model="uSettings.userType" required title="Please contact admin to change your user type." disabled>
						<option value="">-- choose user type --</option>
						<option value="fp_admin">Functional Admin</option>
						<optgroup label="Customers">
							<option value="cust_corporate">Corporate</option>
							<option value="cust_individual">Individual</option>
						</optgroup>
						<optgroup label="Financial Planner">
							<option value="fp_corporate">Corporate</option>
							<option value="fp_individual">Individual</option>
						</optgroup>
					</select>
				</div>
				<div class="form-group">
					<label class="control-label">What is your Mother's
						Maiden Name?</label> <input type="password" class="form-control" id="SecretQuestionAnswer" ng-click="uSettings.typeChange('SecQuestion','view');" ng-blur="uSettings.typeChange('SecQuestion','mask');"
						ng-model="uSettings.SecretQuestionAnswer" required disabled>
				</div>
				<div class="form-group">
					<button type="submit" id="EditButton" ng-show="uSettings.showEdit"
						class="btn btn-primary col-lg-2 marginRfifteen saveChangesBtn">Edit</button>
						<button type="submit" id="SaveButton" ng-show="uSettings.showSave"
						class="btn btn-primary col-lg-2 marginRfifteen saveChangesBtn">Save Changes</button>
					<button type="button" ng-click="uSettings.reset()"
						class="btn btn-primary col-lg-2 marginRfifteen saveChangesBtn">Reset Changes</button>
				</div>
			</div>
			<!-- SECOND - SECOND SECTION END -->
			</div>
		</div>
	</form>
</div>
