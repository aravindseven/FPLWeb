<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pull-left alert_msg" ng-bind-html="alert_message"></div>
<div ng-init="uCommunication.compose();uCommunication.getsession()" class="tab-pane active" class="tab-pane active maildiv" id="maildiv">
	<div class="col-lg-12 col-sm-12 title cust_color" ng-if="logged_in_user=='cust'">
		<div class="row">
			<div class="conter">
				<div class="col-lg-6" >
					
					<div class="set"><button type="" value="Submit" id="save" data-loading-text="Saving...." style="background:none;border:none;color:white;" ng-click="uCommunication.sendMail(message)">SEND MAIL</button></div>
					<div class="set"><button type="" value="Submit"  id="savedraft" data-loading-text="Saving...." style="background:none;border:none;color:white;" ng-click="uCommunication.savetodraft(message)">SAVE</button></div>
					<div class="set"><button type="" value="Submit" style="background:none;border:none;color:white;"ng-click="uCommunication.cancel()" >CANCEL</button></div>
				</div>
				<div class="col-lg-6">
					
				</div>
			</div>
		</div>					
	</div>
	<div class="col-lg-12 col-sm-12 title fp_color" ng-if="logged_in_user=='fp'">
		<div class="row">
			<div class="conter">
				<div class="col-lg-6">
					
					<div class="set"><button type="" value="Submit" id="save" data-loading-text="Saving...." style="background:none;border:none;color:white;" ng-click="uCommunication.sendMail(message)">SEND MAIL</button></div>
					<div class="set"><button type="" value="Submit"  id="savedraft" data-loading-text="Saving...." style="background:none;border:none;color:white;" ng-click="uCommunication.savetodraft(message)">SAVE</button></div>
					<div class="set"><button type="" value="Submit" style="background:none;border:none;color:white;"ng-click="uCommunication.cancel()" >CANCEL</button></div>
				</div>
				<div class="col-lg-6">
					
				</div>
			</div>
		</div>					
	</div>
	
    <div class="col-lg-12 compose">
		<div class="row">
			<div class="comp comp_back">
				<form name="newMessageForm"
								autocomplete="off" enctype="multipart/form-data">
					<div class="row tosubj">To:  
						<select class="demo-default subj select2me text_h" id="selectbox" ng-model="message.recipient">
							<option value="-1">Please specify recipient.</option>
							<option ng-repeat="element in usersList" value="{{element.loginId}}">{{element.user}}</option>
						</select>
					</div>
					<div class="row tosubj">Subject:  <input type="text" class="demo-default subj text_h" name="subject" 
													id="subject" ng-model="message.subject" /></div>
					<div class="row text_editorr">
						<input type="text" value="" class="jqte-test "  name="content" 
													id="content" ng-model="message.content" >
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
