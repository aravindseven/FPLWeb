          <div class="col-lg-10 col-md-10 col-sm-10 profileTabForm" ng-controller="AdminProfileManagementController as uProfile">
              <form ng-submit="uProfile.saveThirdParty(uProfile);" role="form" id="ThirdPartyform" class="col-sm-12">
                <div class="row">
                  <div class="col-md-12 paddingNone">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                      <h3>Third Party Registration</h3>
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
                        <label class="control-label">Name</label>
                        <input type="text" class="form-control" id="thirdPartyName" name="thirdPartyName" ng-model="uProfile.thirdPartyName">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Registration Number</label>
                        <input type="text" class="form-control" id="thidPartyRegNumber" name="thidPartyRegNumber" ng-model="uProfile.thidPartyRegNumber">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Incorporation Location</label>
                        <input type="text"  class="form-control" id="thirdPartyLocation" name="thirdPartyLocation" ng-model="uProfile.thirdPartyLocation">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Website</label>
                        <input type="text"  class="form-control" name="thirdPartyWebsite" id="thirdPartyWebsite" ng-model="uProfile.thirdPartyWebsite" >
                      </div>
                      <div class="form-group">
                        <label class="control-label">Domains</label>
                        <select class="form-control" name="thirdPartyDomain" id="thirdPartyDomain" ng-model="uProfile.thirdPartyDomain" >
                          <option value="">Select Domain</option>
                          <option value="hospital">Hospital</option>
                        </select>
                      </div>
                    </div>
                    <!-- FIRST SECTION END -->
                    <!-- SECOND SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Block / Unit Number</label>
                        <input type="text"  class="form-control" id="thirdPartyBlock" name="thirdPartyBlock" ng-model="uProfile.thirdPartyBlock">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Building Name</label>
                        <input type="text"  class="form-control" id="thirdPartyBuilding" name="thirdPartyBuilding" ng-model="uProfile.thirdPartyBuilding">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Street</label>
                        <input type="text" class="form-control" id="thirdPartyStreet" name="thirdPartyStreet" ng-model="uProfile.thirdPartyStreet">
                      </div>
                      <div class="form-group">
						<label class="control-label">Postal Code</label>
						 <input	type="text" class="form-control"  ng-model="uProfile.thirdPartyPostalCode" id="thirdPartyPostalCode" ng-blur="uProfile.getGeoLocation(uProfile)">
					 </div>
                      <div class="form-group">
                        <label class="control-label">City</label>
                        <input type="text"  class="form-control" id="thirdPartyCity" name="thirdPartyCity" ng-model="uProfile.thirdPartyCity">
                      </div>
                      <div class="form-group">
                        <label class="control-label">State</label>
                        <input type="text"  class="form-control" name="thirdPartyState" id="thirdPartyState" ng-model="uProfile.thirdPartyState">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Country</label>
                        <input type="text"  class="form-control" id="thirdPartyCountry" name="thirdPartyCountry" ng-model="uProfile.thirdPartyCountry">
                      </div>
                    </div>
                    <!-- SECOND SECTION END -->
                    <!-- THIRD SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Phone: Mobile</label>
                        <input type="number"  class="form-control" id="thirdPartyMobile" name="thirdPartyMobile" ng-model="uProfile.thirdPartyMobile">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Phone: Landline</label>
                        <input type="tel"  class="form-control" id="thirdPartyLandline" name="thirdPartyLandline" ng-model="uProfile.thirdPartyLandline">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Email</label>
                        <input type="email" class="form-control" id="thirdPartyEmail" name="thirdPartyEmail" ng-model="uProfile.thirdPartyEmail">
                      </div>
                    </div>
                    <!-- THIRD SECTION END -->
                  </div>
                  <div class="col-md-6">
                    <!-- SECOND - FIRST SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group primaryName">
                        <label class="control-label">Contact Name</label>
                        <input type="text"  class="form-control" placeholder="Firstname" id="primaryFirstname" name="primaryFirstname" ng-model="uProfile.thirdPartyprimaryFirstname">
                        <input type="text"  class="form-control" placeholder="Lastname" id="primaryLastname" name="primaryLastname" ng-model="uProfile.thirdPartyprimaryLastname">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Mobile Number</label>
                        <input type="text"  class="form-control" id="thirdPartyContactMobile" name="thirdPartyContactMobile" ng-model="uProfile.thirdPartyContactMobile">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Position</label>
                        <input type="text"  class="form-control" id="thirdPartyPosition" name="thirdPartyPosition" ng-model="uProfile.thirdPartyPosition">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Identity Proof</label>
                        <input type="text"  class="form-control" name="thirdPartyIdProof" id="thirdPartyIdProof" ng-model="uProfile.thirdPartyIdProof">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Id #</label>
                        <input type="text"  class="form-control" name="thirdPartyIdNumber" id="thirdPartyIdNumber" ng-model="uProfile.thirdPartyIdNumber">
                      </div>
                    </div>
                    <!-- SECOND - FIRST SECTION END -->
                    <!-- SECOND - SECOND SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label>Comments / Imp Notes</label>
                        <textarea class="col-lg-12 col-md-12 col-sm-12 clear form-control" name="thirdPartyComments" id="thirdPartyComments" ng-model="uProfile.thirdPartyComments"></textarea>
                      </div>
                    </div>
                    <div class="form-group">
                      <button class="btn btn-Secondary col-lg-2 pull-right" type="reset">Reset</button>
                      <button class="btn btn-danger col-lg-2 marginRfifteen pull-right">Cancel</button>
                      <button class="btn btn-primary col-lg-2 marginRfifteen pull-right" type="submit">Save</button>
                    </div>
                    <!-- SECOND - SECOND SECTION END -->
                    <!-- SECOND - THIRD SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Validation Status</label>
                        <input type="text" class="form-control" id="thirdPartyValidateStatus" name="thirdPartyValidateStatus" ng-model="uProfile.thirdPartyValidateStatus">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Validation Date</label>
                        <input type="date" class="form-control" id="thirdPartyValidateDate" name="thirdPartyValidateDate" ng-model="uProfile.thirdPartyValidateDate">
                      </div>
                    </div>
                    <!-- SECOND - THIRD SECTION END -->
                  </div>
                </div>
              </form>
            </div>
       