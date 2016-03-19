<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pull-left alert_msg" ng-bind-html="alert_message"></div>
<div  class="tab-pane active" ng-init="uCommunication.getsession();uCommunication.getSettings()" class="tab-pane active maildiv" id="maildiv">
	
						<div class="col-lg-12 col-sm-12 title cust_color" ng-if="logged_in_user=='cust'" ng-cloak>
							<div class="row">
								<div class="conter">
									<div class="col-lg-3">
										<div class="set"><img src="support/communication/images/sett_1.png" alt=""/></div>
										<div class="set">SETTINGS</div>
									</div>
									<div class="col-lg-6">
									</div>
								</div>
							</div>					
						</div>
						<div class="col-lg-12 col-sm-12 title fp_color" ng-if="logged_in_user=='fp'" ng-cloak>
							<div class="row">
								<div class="conter">
									<div class="col-lg-3">
										<div class="set"><img src="support/communication/images/sett_1.png" alt=""/></div>
										<div class="set">SETTINGS</div>
									</div>
									<div class="col-lg-6">
									</div>
								</div>
							</div>					
						</div>
	    				<div class="col-lg-12 setting">
							<div class="col-lg-12 mask hide">
								<div class="row">
									<div class="col-lg-3"><div class="stand">Status</div>
									</div>
									<div class="col-lg-9 col-md-8 col-sm-6 col-xs-10">
										<div class="row">
											<div class="col-lg-12 col-sm-6 col-xs-10 col-md-8">
												<div class="status">
													<div class="status-first"><input type="radio" name="sex" value="show" checked style="position:right:2px;"></div>
													<div class="status-first">Online</div>
													<div class="status-first"><img src="support/communication/images/ro1.png"></div>
												</div>
												<div class="status">
													<div class="status-first"><input type="radio" name="sex" value="show" checked></div>
													<div class="status-first">Offline</div>
													<div class="status-first"><img src="support/communication/images/ro3.png"></div>
												</div>
												<div class="status">
													<div class="status-first"><input type="radio" name="sex" value="show" checked></div>
													<div class="status-first">Away</div>
													<div class="status-first"><img src="support/communication/images/ro2.png"></div>
												</div>
												<div class="status">
													<div class="status-first"><input type="radio" name="sex" value="show" checked></div>
													<div class="status-first">Do not disturb</div>
													<div class="status-first"><img src="support/communication/images/ro4.png"></div>
												</div>
											</div>			
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-12 mask">
								<div class="row">
									<div class="col-lg-3"><div class="stand">Profile Picture</div>
									</div>
									<div class="col-lg-9">
										<div class="row">
											<div class="col-lg-12">
												<form>
													<div class="gan"><input type="radio" name="profile" ng-model="settings.profilepic" value="Show" checked> <b>Show</b></div>
													<div class="gan"><input type="radio" name="profile" ng-model="settings.profilepic" value="Not Show"> <b>Don't Show</b></div>
												</form> 
											</div>	
											
										</div>
									</div>
								</div>
							</div>
						<div class="col-lg-12 mask">
							<div class="row">
								<div class="col-lg-3 notifi"><div class="stand">Archive Duration</div></div>
									<div class="col-lg-8">
										<div class="row">
											<div class="col-lg-12">
												<form>
													 <div class="gan"><input type="radio" name="archieve" ng-model="settings.archieve" value="30" checked> <span style="font-family:open sans; font-weight:bold;font-size:14px;">Default 30 days</span>
													 <input type="radio" name="archieve" ng-model="settings.archieve" value="0"> <span style="font-family:open sans; font-weight:bold;font-size:14px;">More than 30 days</span> </div>
												</form>
											</div>
										</div>
									</div>
							</div>
						</div>
						<div class="col-lg-12 mask">
							<div class="row">
								<div class="col-lg-3 notifi"><div class="stand">Auto response</div></div>
								<div class="col-lg-8">
									<form>
										<div class="gan">  <input type="radio" name="auto_response" ng-model="settings.auto_response" value="Enabled" checked><b> Enable</b></div>
										<div class="gan">  <input type="radio" name="auto_response" ng-model="settings.auto_response" value="Disabled"><b> Disable</b></div>
									</form><BR/>
								</div>
							</div>
						</div>
						<div class="col-lg-12 mask" ng-show="settings.auto_response=='Enabled'" >
							<div class="row">
								<div class="col-lg-3 notifi"><div class="stand">Message</div></div>
									<div class="col-lg-8">
										<div class="te"style="margin-top:5px;"><textarea rows="3" cols="40" style="max-width:100%;" ng-model="settings.auto_response_message" >
										</textarea></div>
									</div>
							</div>
						</div>
						<div class="col-lg-12 mask">
							<div class="row">
								<div class="col-lg-3 notifi"><div class="stand">Notification</div></div>
									<div class="col-lg-8">
										<form>
											<div class="gan">  <input type="radio" name="notification" ng-model="settings.notification" value="Enabled" checked><b> Enable</b></div>
											<div class="gan">  <input type="radio" name="notification" ng-model="settings.notification" value="Disabled"><b> Disable</b></div>
												  
	
										</form> 
									</div>
							</div>
						</div>
						<div class="col-lg-12 mask">
							<div class="row">
								<div class="col-lg-3 notifi"><div class="stand">Stars</div></div>
									<div class="col-lg-9">
										<div class="staw">
											<span style="font-family:open sans;font-weight:regular;font-size:10pt;"><b>Drag the stars between the lists.</b> The stars will rotate in the order shown
											below when you click successively. To learn the name of a star for search.</span>
											hover your mouse over the image.
										</div>
											
										<div class="col-lg-12">
											<div class="row" style="margin-top:5px;">
												<div class="pre"><b>Presets:</b></div>
												<div class="pre"><div class="star">1star </div></div>
												<div class="pre"><div class="star">4star </div></div>
												<div class="pre"><div class="star">all star </div></div>
											</div>
										</div>
								
										<div class="col-lg-12">
											<div class="row">
												<div class="bond"><b>In use</b></div>	
												<div class="col-lg-8">
													<div class="bond"><img src="support/communication/images/req1.png" alt=""/></div>
													<div class="bond"><img src="support/communication/images/req.png" alt=""/></div>
													<div class="bond"><img src="support/communication/images/req4.png" alt=""/></div>
													<div class="bond"><img src="support/communication/images/req5.png" alt=""/></div>
													<div class="bond"><img src="support/communication/images/qqq.png" alt=""/></div>
													<div class="bond"><img src="support/communication/images/qu.png" alt=""/></div>
												</div>
														
											</div>
										</div>
												
									</div>
							</div>
						</div>
						<div class="col-lg-12 mask">
							<div class="row">
								<div class="col-lg-3"></div>
								<div class="col-lg-8">
									<div class="col-lg-12 foot" ng-if="logged_in_user=='cust'" ng-cloak>
										<div class="col-lg-3" >
											<button type="button" class="but" id="savesettings" data-loading-text="Saving...."  style="background:#ce2b2c;border:none;color:white;" ng-click="uCommunication.putSettings()">SAVE CHANGES</button>
										</div>
										<div class="col-lg-1" >
										</div>
										<div class="col-lg-3">
											 <button type="button" class="but" style="background:#ce2b2c ;border:none;color:white;margin-left:10px;" ng-click="uCommunication.cancelSettings()">CANCEL</button><br/>
										</div>
									</div><br/>
									
									<div class="col-lg-12 foot" ng-if="logged_in_user=='fp'" ng-cloak>
										<div class="col-lg-3" >
											<button type="button" class="but" id="savesettings" data-loading-text="Saving...."  style="background:#2A468B ;border:none;color:white;" ng-click="uCommunication.putSettings()">SAVE CHANGES</button>
										</div>
										<div class="col-lg-1" >
										</div>
										<div class="col-lg-3">
											 <button type="button" class="but" style="background:#2A468B ;border:none;color:white;margin-left:10px;" ng-click="uCommunication.cancelSettings()">CANCEL</button><br/>
										</div>
									</div><br/>
								</div>
							</div>
						</div>
						
				
			</div>
        
</div>