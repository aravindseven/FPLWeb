
<div class="innerWrapper" ng-controller="EntityDBController as uEntity">
	<div class="row innerContentWrapper myProfileWrapper">
		<div class="tabbable">
			<div class="titleHeaderBlue">
				FPL DB <span style="float: right">Current TimeStamp: {{date |
					date:'yyyy-MM-dd HH:mm:ss'}}</span>
			</div>
			<div class="tab-content clear paddingLnone">
				<div id="profile"
					class="tab-pane fade active in paddingLnone profileTabContent">
					<div class="col-lg-12 col-md-12 col-sm-12 paddingLnone">
						<div class="col-lg-12 col-md-12 col-sm-12 profileTabForm">
							<form name="fplDBForm" ng-init="uEntity.fetchFPLDB(entity)" ng-submit="uEntity.saveEntity(entity)" autocomplete="off" class="col-sm-12">
								<div class="row">
									<div class="col-md-12 paddingNone">
										<div class="col-lg-6 col-md-6 col-sm-6">
											<h3>Entity Details</h3>
										</div>
										<div class="col-lg-6 col-md-6 col-sm-6">
											<h3>Registration Details</h3>
										</div>
									</div>
									<hr class="clear">
									<div class="col-md-6">
										<!-- FIRST SECTION START -->
										<div class="innerBorderDiv">
											<div class="form-group">
												<label class="control-label">Name</label> <input type="text"
													class="form-control" ng-model="entity.name" required id="name">
											</div>
											<div class="form-group">
												<label class="control-label">Registration Number</label> <input
													type="text" class="form-control" ng-model="entity.registration" required id="registration">
											</div>
											<div class="form-group">
												<label class="control-label">Incorporation Location</label>
												<input type="text" class="form-control" ng-model="entity.incLocation" required id="incLocation">
											</div>
											<div class="form-group">
												<label class="control-label">Description</label> <input
													type="text" class="form-control" ng-model="entity.incDesc" required id="incDesc">
											</div>
											<div class="form-group">
												<label class="control-label">VAT #</label> <input
													type="password" class="form-control" ng-model="entity.vat" required id="vat">
											</div>
										</div>
										<!-- FIRST SECTION END -->
										<!-- SECOND SECTION START -->
										<div class="innerBorderDiv">
											<div class="form-group">
												<label class="control-label">Block / Unit Number</label> <input
													type="text" class="form-control"  ng-model="entity.block" required id="block">
											</div>
											<div class="form-group">
												<label class="control-label">Building Name</label> <input
													type="text" class="form-control"  ng-model="entity.buildingName" required id="buildingName">
											</div>
											<div class="form-group">
												<label class="control-label">Street</label> <input
													type="text" class="form-control"  ng-model="entity.street" required id="street">
											</div>
											<div class="form-group">
												<label class="control-label">Postal Code</label> <input
													type="text" class="form-control"  ng-model="entity.postalCode" required id="postalCode" ng-blur="uEntity.getGeoLocation(entity)">
											</div>
											<div class="form-group">
												<label class="control-label">City</label> <input
													type="text" class="form-control"  ng-model="entity.city" required id="city">
											</div>
											<div class="form-group">
												<label class="control-label">State</label> <input
													type="text" class="form-control"  ng-model="entity.state" required id="state">
											</div>
											<div class="form-group">
												<label class="control-label">Country</label> <input
													type="text" class="form-control"  ng-model="entity.country" required id="country">
											</div>
										</div>
										<!-- SECOND SECTION END -->
										<!-- THIRD SECTION START -->
										<div class="innerBorderDiv">
											<div class="form-group">
												<label class="control-label">Fax</label> <input type="tel"
													class="form-control" ng-model="entity.fax" id="fax">
											</div>
											<div class="form-group">
												<label class="control-label">Landline</label> <input
													type="te1" class="form-control"  ng-model="entity.landLine" required id="landLine">
											</div>
											<div class="form-group">
												<label class="control-label">Email</label> <input
													type="email" class="form-control"  ng-model="entity.email" required id="email">
											</div>
										</div>
										<!-- THIRD SECTION END -->
									</div>
									<div class="col-md-6">
										<!-- SECOND - FIRST SECTION START -->
										<div class="innerBorderDiv">
											<div class="form-group primaryName">
												<label class="control-label">Contact Name</label> <input
													type="text" class="form-control" placeholder="Firstname"
													 ng-model="entity.contactFirstName1" id="contactFirstName1"> <input
													type="text" class="form-control" placeholder="Lastname"
													 ng-model="entity.contactLastName1" id="contactLastName1">
											</div>
											<div class="form-group">
												<label class="control-label">Mobile number</label> <input
													type="text" class="form-control"  ng-model="entity.persoanNumber1" id="persoanNumber1">
											</div>
											<div class="form-group">
												<label class="control-label">Position</label> <input
													type="text" class="form-control"  ng-model="entity.personPosition1" id="personPosition1">
											</div>
											<div class="form-group">
												<label class="control-label">Description</label> <input
													type="text" class="form-control" ng-model="entity.description1" id="description1">
											</div>
											<div class="form-group">
												<label class="control-label">ID #</label> <input
													type="text" class="form-control" ng-model="entity.personId1" id="personId1">
											</div>
										</div>
										<!-- SECOND - FIRST SECTION END -->
										<!-- SECOND - SECOND SECTION START -->
										<div class="innerBorderDiv">
											<div class="form-group primaryName">
												<label class="control-label">Contact Name</label> <input
													type="text" class="form-control" placeholder="Firstname"
													 ng-model="entity.contactFirstName2" id="contactFirstName2"> <input
													type="text" class="form-control" placeholder="Lastname"
													 ng-model="entity.contactLastName2" id="contactLastName2">
											</div>
											<div class="form-group">
												<label class="control-label">Mobile number</label> <input
													type="tel" class="form-control" ng-model="entity.persoanNumber2" id="persoanNumber2">
											</div>
											<div class="form-group">
												<label class="control-label">Position</label> <input
													type="text" class="form-control" ng-model="entity.personPosition2" id="personPosition2">
											</div>
											<div class="form-group">
												<label class="control-label">Identity proof</label> <input
													type="text" class="form-control" ng-model="entity.description2" id="description2">
											</div>
											<div class="form-group">
												<label class="control-label">ID #</label> <input
													type="text" class="form-control" ng-model="entity.personId2"id="personId2">
											</div>
										</div>
										<!-- SECOND - SECOND SECTION END -->
										<!-- SECOND - THIRD SECTION START -->
										<div class="innerBorderDiv">
											<div class="form-group">
												<label class="control-label">Website</label> <input
													type="url" class="form-control" ng-model="entity.website" required id="website">
											</div>
										</div>
										<!-- SECOND - THIRD SECTION END -->
										<!-- SECOND - FOURTH SECTION START -->
										<div class="form-group">
											<button type="button" ng-click="uEntity.reset()" class="btn btn-Secondary col-lg-2 pull-right">Reset</button>
											<button ng-click=""
												class="btn btn-danger col-lg-2 marginRfifteen pull-right">Cancel</button>
											<button type="submit"
												class="btn btn-primary col-lg-2 marginRfifteen pull-right">Save</button>
										</div>
										<span style="float: right">Last Updated Date: {{entity.updationDate}}</span>
										<!-- <span style="float: right">Last Updated Date: {{date |
											date:'yyyy-MM-dd'}}</span> -->
									</div>
									<!-- SECOND - FOURTH SECTION END -->
								</div>
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