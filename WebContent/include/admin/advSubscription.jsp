<div class="col-lg-10 col-md-10 col-sm-10 profileTabForm" ng-controller="AdminSubscriptionController as aSubscription">
              <form ng-submit="aSubscription.saveadvSubscription(aSubscription);" role="form" id="FPSubscriptionform" class="col-sm-12">
                <div class="row">
                  <div class="col-md-12 paddingNone">
                    <div class="col-lg-6 col-md-6 col-sm-6">
                      <h3>Advertisement subscription ID</h3>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                      <h3>Subscription Date</h3>
                    </div>
                  </div>
                  <hr class="clear">
                  <div class="col-md-6">
                    <!-- FIRST SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Adv Target</label>
                        <input type="text" class="form-control" id="advSubTarget" name="advSubTarget" ng-model="aSubscription.advSubTarget">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Subscription Start date</label>
                        <input type="date" class="form-control" id="advSubStartDate" name="advSubStartDate" ng-model="aSubscription.advSubStartDate">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Subscription End date</label>
                        <input type="date" class="form-control" id="advSubEndDate" name="advSubEndDate" ng-model="aSubscription.advSubEndDate">
                      </div>
                    </div>
                    <!-- FIRST SECTION END -->
                    <!-- SECOND SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Advertisement type</label>
                        <select class="form-control" id="advSubType" name="advSubType" ng-model="aSubscription.advSubType" multiple="multiple">
                          <option value="">Select type</option>
                          <option value="type1">Type1</option>
                        </select>
                      </div>
                       <div class="form-group">
                      <label class="control-label">Media</label>
				          <label class="checkbox">
				            	<input type="checkbox" id="advSubMediaWeb" value="Web" ng-model="fpSubscription.advSubmediaWeb" />Web
				          </label>
				          <label class="checkbox">
				            	<input type="checkbox" id="advSubMediaMobile" value="Mobile" ng-model="fpSubscription.advSubmediaMobile" />Mobile
				          </label>
                      </div>
                      <div class="form-group">
                        <label class="control-label">Country Id</label>
                          <select class="form-control" name="advSubCountry" id="advSubCountry" ng-model="aSubscription.advSubCountry">
                              <option value="SG">Singapore</option>
                              <option value="IN">India</option>
                          </select>
                      </div>
                    </div>
                    <!-- SECOND SECTION END -->
                    <!-- THIRD SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Payment mode</label>
                         <select class="form-control" name="advSubPaymentMode" id="advSubPaymentMode" ng-model="aSubscription.advSubPaymentMode">
                              <option value="">Select Payment Mode</option>
                              <option value="cheque">Cheque</option>
                              <option value="TT">TT</option>
                              <option value="Paypal">Paypal</option>
                           </select>   
                      </div>
                      <div class="form-group">
                        <label class="control-label">Payment terms</label>
                        <input type="text"  class="form-control" id="advSubPaymentTerms" name="advSubPaymentTerms" ng-model="aSubscription.advSubPaymentTerms">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Payment receipt date</label>
                        <input type="date"  class="form-control" id="advSubPayReceipt" name="advSubPayReceipt" ng-model="aSubscription.advSubPayReceipt">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Currency</label>
                        <input type="text"  class="form-control" id="advSubCurrency" name="advSubCurrency" ng-model="aSubscription.advSubCurrency">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Amount</label>
                        <input type="text"  class="form-control" id="advSubAmountReceived" name="advSubAmountReceived" ng-model="aSubscription.advSubAmountReceived" ng-blur="aSubscription.discountCalculation(aSubscription.advSubType);">
                      </div>
                      <div class="pull-left col-lg-7 col-md-15 col-sm-6">
                     	 <label ng-show="aSubscription.shownDiscount" class="ng-hide">The discount is calculated as per the Advertisement type.</label>
                     </div>	 
                    </div>
                    <!-- THIRD SECTION END -->
                  </div>
                  <div class="col-md-6">
                    <!-- SECOND - FIRST SECTION START -->
                    <div class="innerBorderDiv">
                        <div class="form-group">
                          <label class="control-label">Renewal</label>
	                        <label class="radio-inline" nowrap>
	                        <input type="radio" id="advSubRenewalFlag" value="RenewalSame" ng-model="aSubscription.advSubRenewalFlag">
	                        With Same Content</label>
	                        <label class="radio-inline">
	                        <input type="radio" id="advSubRenewalFlag" value="RenewalNew" ng-model="aSubscription.advSubRenewalFlag">
	                        With New Content</label>
	                      </div>
                          <button class="btn btnPrimaryBlue" type="button">Renew your subscription Now</button>
                      </div>
                    
                    <!-- SECOND - FIRST SECTION END -->
                    <!-- SECOND - SECOND SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Active flag</label>
                        <label class="radio-inline">
                        <input type="radio" id="advSubActiveFlag" value="Active" ng-model="aSubscription.advSubActiveFlag">
                        Active</label>
                        <label class="radio-inline">
                        <input type="radio" id="advSubActiveFlag" value="Inactive" ng-model="aSubscription.advSubActiveFlag">
                        Inactive</label>
                      </div>
                    </div>
                    <!-- SECOND - SECOND SECTION END -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Art Work Status</label>
                        <label class="radio-inline">
                        <input type="radio" id="advSubArtWorkStatus" value="Red" ng-model="aSubscription.advSubArtWorkStatus">
                        Red</label>
                        <label class="radio-inline">
                        <input type="radio" id="advSubArtWorkStatus" value="Amber" ng-model="aSubscription.advSubArtWorkStatus">
                        Amber</label>
                        <label class="radio-inline">
                        <input type="radio" id="advSubArtWorkStatus" value="Green" ng-model="aSubscription.advSubArtWorkStatus">
                        Green</label>
                      </div>
                    </div>
                    <!-- SECOND - THIRD SECTION START -->
                    <div class="innerBorderDiv">
                    	 <div class="form-group">
                        <label>Agreed Submission date</label>
                        <input type="date" class="form-control" id="advSubAgreedDate" name="advSubAgreedDate" ng-model="aSubscription.advSubAgreedDate">
                      <div class="form-group">
                        <label>Comments / Imp Notes</label>
                        <textarea class="col-lg-12 col-md-12 col-sm-12 clear form-control" id="advSubComments" name="advSubComments" ng-model="aSubscription.advSubComments"></textarea>
                      </div>
                    </div>
                    
                    <!-- SECOND - THIRD SECTION END -->
                    <!-- SECOND - FOURTH SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <button class="btn btn-primary col-lg-2 marginRfifteen" type="submit">Save</button>
                        <button class="btn btn-danger col-lg-2 marginRfifteen" type="button">Cancel</button>
                        <button class="btn btn-Secondary col-lg-2 marginRfifteen"  type="button" ng-click="aSubscription.reset()">Reset</button>
                      </div>
                      <div class="form-group">
                        <button class="btn btnPrimaryBlue" type="button">Generate Invoice</button>
                       </div>
                    </div>
                    </div>
                    <!-- SECOND - FOURTH SECTION END -->
                  </div>
                </div>
                </div>
              </form>
            </div>
          