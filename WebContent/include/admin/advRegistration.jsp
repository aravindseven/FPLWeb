<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       
<div class="col-lg-10 col-md-10 col-sm-10 profileTabForm" id="AdvRegistration" ng-controller="AdminSubscriptionController as aSubscription">
     <form ng-submit="aSubscription.saveAdvRegistration(aSubscription);" role="form" id="advRegistrationForm" class="col-sm-12">
                <div class="row">
                  <div class="col-md-12 paddingNone">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                      <h3>Advertisement</h3>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                      <h3>Registration</h3>
                    </div>
                  </div>
                  <hr class="clear">
                  <div class="col-md-6">
                    <!-- FIRST SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Name</label>
                        <input type="text" class="form-control" id="advRegName" name="advRegName" ng-model="aSubscription.advRegName">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Registration Number</label>
                        <input type="text" class="form-control" id="advRegNumber" name="advRegNumber" ng-model="aSubscription.advRegNumber">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Service Coverage</label>
                        <input type="text"  class="form-control" id="advRegServiceCoverage" name="advRegServiceCoverage" ng-model="aSubscription.advRegServiceCoverage">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Service Category</label>
                        <input type="text"  class="form-control" name="advRegServiceCategory" id="advRegServiceCategory" ng-model="aSubscription.advRegServiceCategory">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Domains</label>
                        <select class="form-control" multiple="multiple" name="advRegDomains" id="advRegDomains" ng-model="aSubscription.advRegDomains">
                          <option>Select Domain</option>
                          <option value="Domain1">Domain1</option>
                          <option value="Domain2">Domain2</option>
                        </select>
                      </div>
                    </div>
                    <!-- FIRST SECTION END -->
                    <!-- SECOND SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Block / Unit Number</label>
                        <input type="text"  class="form-control" id="advRegBlock" name="advRegBlock" ng-model="aSubscription.advRegBlock">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Building Name</label>
                        <input type="text"  class="form-control" id="advRegBuilding" name="advRegBuilding" ng-model="aSubscription.advRegBuilding">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Street</label>
                        <input type="text" class="form-control" id="advRegStreet" name="advRegStreet" ng-model="aSubscription.advRegStreet">
                      </div>
                      <div class="form-group">
						<label class="control-label">Postal Code</label>
						 <input	type="text" class="form-control"  ng-model="aSubscription.advRegpostalCode" id="advRegpostalCode" ng-blur="aSubscription.getGeoLocation(aSubscription)">
					 </div>
                      <div class="form-group">
                        <label class="control-label">City</label>
                        <input type="text"  class="form-control" id="advRegCity" name="advRegCity" ng-model="aSubscription.advRegCity" >
                      </div>
                      <div class="form-group">
                        <label class="control-label">State</label>
                        <input type="text"  class="form-control" name="advRegState" id="advRegState" ng-model="aSubscription.advRegState">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Country</label>
                        <input type="text"  class="form-control" id="advRegCountry" name="advRegCountry" ng-model="aSubscription.advRegCountry">
                      </div>
                    </div>
                    <!-- SECOND SECTION END -->
                    <!-- THIRD SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Phone</label>
                        <input type="tel"  class="form-control" id="advRegMobile" name="advRegMobile" ng-model="aSubscription.advRegMobile">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Website</label>
                        <input type="text"  class="form-control" id="advRegLandline" name="advRegLandline" ng-model="aSubscription.advRegLandline">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Email</label>
                        <input type="email" class="form-control" id="advRegEmail" name="advRegEmail" ng-model="aSubscription.advRegEmail">
                      </div>
                    </div>
                    <!-- THIRD SECTION END -->
                  </div>
                  <div class="col-md-6">
                    <!-- SECOND - FIRST SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group primaryName">
                        <label class="control-label">Contact Name</label>
                        <input type="text"  class="form-control" placeholder="Firstname" id="advRegprimaryFirstname" name="advRegprimaryFirstname" ng-model="aSubscription.advRegprimaryFirstname">
                        <input type="text"  class="form-control" placeholder="Lastname" id="advRegprimaryLastname" name="advRegprimaryLastname" ng-model="aSubscription.advRegprimaryLastname">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Mobile Number</label>
                        <input type="tel"  class="form-control" id="advRegContactMobile" name="advRegContactMobile" ng-model="aSubscription.advRegContactMobile">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Position</label>
                        <input type="text"  class="form-control" id="advRegidPosition" name="advRegidPosition" ng-model="aSubscription.advRegidPosition">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Identity Proof</label>
                        <input type="text"  class="form-control" name="advRegIDproof" id="advRegIDproof" ng-model="aSubscription.advRegIDproof">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Id#</label>
                        <input type="text"  class="form-control" name="advRegID" id="advRegID" ng-model="aSubscription.advRegID">
                      </div>
                    </div>
                    <!-- SECOND - FIRST SECTION END -->
                    <!-- SECOND - SECOND SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label>Comments / Imp Notes</label>
                        <textarea class="col-lg-12 col-md-12 col-sm-12 clear form-control" id="advRegComments" name="advRegComments" ng-model="aSubscription.advRegComments"></textarea>
                      </div>
                    </div>
                    <div class="form-group">
                      <button class="btn btn-Secondary col-lg-2 pull-right" type="button" ng-click="aSubscription.reset()">Reset</button>
                      <button class="btn btn-danger col-lg-2 marginRfifteen pull-right" type="button">Cancel</button>
                      <button class="btn btn-primary col-lg-2 marginRfifteen pull-right" type="submit">Save</button>
                    </div>
                    <!-- SECOND - SECOND SECTION END -->
                    <!-- SECOND - THIRD SECTION START -->
                    <!-- SECOND - THIRD SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Subscription Status</label>
                        <input type="text" class="form-control" id="advRegSubStatus" name="advRegSubStatus" ng-model="aSubscription.advRegSubStatus">
                      </div>
                      <div class="form-group">
                        <label class="control-label"></label>
                        <button class="btn btnPrimaryBlue" type="button" onclick="location.href='subscriptionmgmt.jsp?type=createSub'">Subscribe Now</button>
                      </div>
                    </div>
                    <!-- SECOND - THIRD SECTION END -->
                    <!-- SECOND - THIRD SECTION END -->
                  </div>
                </div>
                </div>
              </form>
            </div>
          