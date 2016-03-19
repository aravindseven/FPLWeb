 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-lg-10 col-md-10 col-sm-10 profileTabForm" id="AdvertisementType" ng-controller="AdminSubscriptionController as aSubscription">
       <form ng-submit="aSubscription.saveAdvType(aSubscription);" role="form" id="advTypeForm" class="col-sm-12">
                <div class="row">
                  <div class="col-md-12 paddingNone">
                    <div class="col-lg-6 col-mdy-6 col-sm-6">
                      <h3>Advertisement</h3>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-6">
                      <h3>Type</h3>
                    </div>
                  </div>
                  <hr class="clear">
                  
                  <div class="col-md-6">
                    <!-- FIRST SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Advertisement Type</label>
                        <input type="text" class="form-control" id="AdvType" name="AdvType" ng-model="aSubscription.AdvType">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Country Id</label>
                        <select class="form-control" name="CountryType" id="CountryType" ng-model="aSubscription.Country">
                             <option value="">Select Country</option>
                              <option value="SG" selected="selected">Singapore</option>
                              <option value="IN">India</option>
                          </select>
                      </div>
                      <div class="form-group">
                      <label class="control-label">Media</label>
				          <label class="checkbox">
				            	<input type="checkbox" id="MediaWeb" value="Web" ng-model="aSubscription.mediaWeb" />Web
				          </label>
				          <label class="checkbox">
				            	<input type="checkbox" id="MediaMobile" value="Mobile" ng-model="aSubscription.mediaMobile" />Mobile
				          </label>
                      </div>
                    </div>
                    <!-- FIRST SECTION END -->
                    
                    <!-- SECOND SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">Subscription rate</label>
                        <input type="text"  class="form-control" id="SubRate" name="SubRate" ng-model="aSubscription.SubRate">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Promotion Start date</label>
                        <input type="date"  class="form-control" id="PromotionStartDate" name="PromotionStartDate" ng-model="aSubscription.pStartDate">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Promotion End date</label>
                        <input type="date"  class="form-control" id="PromotionEndDate" name="PromotionEndDate" ng-model="aSubscription.pEndDate">
                      </div>
                    </div>
                    <!-- SECOND SECTION END -->
                    
                    <!-- THIRD SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label class="control-label">File types</label>
                        <input type="text"  class="form-control" id="FileTypes" name="FileTypes" ng-model="aSubscription.FileTypes">
                      </div>
                      <div class="form-group">
                        <label class="control-label">File size</label>
                        <input type="text"  class="form-control" id="FileSize" name="FileSize" ng-model="aSubscription.FileSize">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Dimension</label>
                        <input type="text"  class="form-control" id="Dimension" name="Dimension" ng-model="aSubscription.Dimension">
                      </div>
                      <div class="form-group">
                        <label class="control-label">Submission deadline</label>
                        <input type="date" class="form-control" id="SubDeadline" name="SubDeadline" ng-model="aSubscription.SubDeadline">
                      </div>
                      <div class="form-group">
                        <label>Items to submit</label>
                        <textarea class="col-lg-12 col-md-12 col-sm-12 clear form-control" id="ItemSubmit" name="ItemSubmit" ng-model="aSubscription.ItemSubmit"> </textarea>
                      </div>
                    </div>
                    <!-- THIRD SECTION END -->
                  </div>
                  
                  <div class="col-md-6">
                    <!-- SECOND - FIRST SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                      		<label class="control-label">Active flag</label>
				            <label class="radio-inline">
				            	<input type="radio" id="ActiveFlag" value="Active" ng-model="aSubscription.ActiveFlag" /> Active
				            </label>
				            <label class="radio-inline">
				            	<input type="radio" id="ActiveFlag" value="Inactive" ng-model="aSubscription.ActiveFlag" /> Inactive
				            </label>
                      </div>
                      <div class="form-group">
                        <label class="control-label">Discount policy</label>
                        <label class="radio-inline">
                        <input type="radio" id="DiscountPolicy" value="ActiveDiscount" ng-model="aSubscription.DiscountPolicy" />
                        Active</label>
                        <label class="radio-inline">
                        <input type="radio" id="DiscountPolicy" value="InactiveDiscount" ng-model="aSubscription.DiscountPolicy" />
                        Inactive</label>
                      </div>
                      
                       <div class="form-group">
                        <label class="control-label">Discount percentage</label>
                        <input type="text" class="form-control" id="discountPercentage" name="discountPercentage" ng-model="aSubscription.discountPercentage">
                      </div>
                    </div>
                    <!-- SECOND - FIRST SECTION END -->
                    <!-- SECOND - SECOND SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <label>Comments / Imp Notes</label>
                        <textarea class="col-lg-12 col-md-12 col-sm-12 clear form-control" id="comments" name="comments" ng-model="aSubscription.comments"></textarea>
                      </div>
                    </div>
                    <!-- SECOND - SECOND SECTION END -->
                    <!-- SECOND - THIRD SECTION START -->
                    <div class="innerBorderDiv">
                      <div class="form-group">
                        <button class="btn btn-primary col-lg-2 marginRfifteen" type="submit">Save</button>
                        <button class="btn btn-danger col-lg-2 marginRfifteen">Cancel</button>
                        <button class="btn btn-Secondary col-lg-2 marginRfifteen" type="button" ng-click="aSubscription.reset()">Reset</button>
                      </div>
                      <span style="float: right">Last Updated Date: {{aSubscription.lastUpdatedDate}}</span>
										<!-- <span style="float: right">Last Updated Date: {{date |
											date:'yyyy-MM-dd'}}</span> -->
                    </div>
                    <!-- SECOND - THIRD SECTION END -->
                  </div>
                </div>
                </form>
                </div>
              
