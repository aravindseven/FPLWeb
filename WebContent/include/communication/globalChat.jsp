<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div ng-init="uchat.chatUsersList('${UserLoginInfo.email}')"
	class="col-lg-10 col-md-10 col-sm-10 chatGrid">

	<div class="panel panel-primary">
		<div class="panel-heading" ng-show="false">
			<b>Users</b>
		</div>
		<div class="panel-body" ng-show="false">
			<ul class="media-list">
				<li ng-repeat="userDetail in chatList" class="media">
					<div class="media-body">
						<div ng-show="userDetail.email != '${UserLoginInfo.email}'" class="media">
							<div ng-show="userDetail.status == 'online'">
								<a class="pull-left" href="#"> <img
									class="media-object img-circle "
									src="support/images/online.png">
								</a>
							</div>
							<div ng-show="userDetail.status == 'offline'">
								<a class="pull-left" href="#"> <img
									class="media-object img-circle "
									src="support/images/offline.png">
								</a>
							</div>
							<div style="margin-left: 25px;" class="media-body">
								<a href="#" ng-click="uchat.startConversation(userDetail.email, '${UserLoginInfo.email}')">
									{{userDetail.user}} </a>
							</div>
						</div>

					</div>
				</li>
			</ul>
		</div>
		<div ng-click="uchat.bringItUp(userDetail.user)"
			ng-repeat="userDetail in chatList" style="resize: both;">
			<div id="userDetail.selectedUser" ng-show="userDetail.selectedUser"
				class="chatWindow" style="right: {{userDetail.chatMargin}}px;z-index:{{userDetail.zIndex}}">
				<div class="chatUserHeader">
					<div style="float: right;margin:10px">
						<a class="pull-left" href="#"
							ng-click='uchat.closeChat(userDetail.user);uchat.removeChatSession(userDetail, "${UserLoginInfo.email}")'> <img
							class="media-object img-circle "
							src="support/images/not_verify.GIF">
						</a>
					</div>
					<br>
					<div class="panel-heading">
						<b>{{userDetail.user}}</b>
					</div>
				</div>
				<div style="height: 90%;">
					<div id="{{userDetail.id}}" class="well chatMessageDiv">
						<div ng-repeat="message in userDetail.messages" style="height: 25px;"><b>{{message.user}} :</b> {{message.msg}}<br></div>
					</div>
					<form class="chatSendDiv" ng-submit='uchat.publish(userDetail,newMessage, "${UserLoginInfo.email}")'>
						<div class="form-group">
							<textarea id="{{userDetail.id}}" rows="2" cols="30" ng-model='newMessage'/></textarea>
							<button type="submit" style="margin-bottom:30px;margin-left:5px" class="btn btn-primary">Send</button>
        				</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>