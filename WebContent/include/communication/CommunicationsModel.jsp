<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pull-left alert_msg" ng-bind-html="alert_message"></div>
<!-- MY PROFILE START -->

<div class="container-fluid" style="background:#e9eef1; padding:px;" ng-init="uCommunication.gettips();uCommunication.getuserlist()">
		
		<div class="row" >	
			<div class="col-lg-12 middle col-lg-offset-2">
			
			<div class="container newcontainer">
			<div class="fist-content hidden-sm hidden-xs">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<div class="short" ng-if="windowType!='compose'&&windowType!='Settings'">
						<a href="" data-toggle="tab"><button type="button" class="btn btn-default" style="padding-left:23px;padding-right:25px;" ng-click="uCommunication.pagerefresh()" ng-if="!mailread" ng-cloak>Refresh</button></a>
						
			</div>
					
			<div class="short_pul" ng-if="!mailread" ng-cloak>
			<!-- <ul class="dropdown check">
							  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort
							  <span class="caret"></span></button>
							  <ul class="dropdown-menu">
								<li><a href="#" ng-click="uCommunication.tipscheckfunc()">Date</a></li>
								
								<li><a href="#">Ascending</a></li>
								<li><a href="#">Desending</a></li>
								<li><a href="#">From</a></li>
								<li><a href="#">To</a></li>
							  </ul>
						</ul> -->
						<ul class="dropdown check" ng-if="!mailread && windowType!='draft'&&windowType!='compose'&&windowType!='Settings'&&windowType!='notification'" ng-cloak>
							  <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">More
							  <span class="caret"></span></button>
							  <ul class="dropdown-menu">
							  
							  <li><a href="#" ng-click="uCommunication.allmail()">All</a></li>
									  <li><a href="#" ng-click="uCommunication.unreadmail()">Unread</a></li>
									  <li><a href="#" ng-click="uCommunication.starredmail()">Starred</a></li>
									  <li><a href="#" id="savemvtotrash"   ng-click="uCommunication.deleteMail()"  ng-if="windowType!='trash'">Move to Trash</a></li>
									  <li><a href="#" id="savemvtoarchieve"  ng-click="uCommunication.moveToArchive()" ng-if="windowType!='archive'&&windowType!='trash'">Move to Archive</a></li>
							  </ul>
						</ul>
						<ul class="short"  ng-if="windowType=='trash'" ng-cloak>
								<div class="dropdown">
									<button class="btn  dropdown-toggle" type="button" ng-click="uCommunication.discard()" ng-if="windowType=='trash'" ng-cloak>Discard</button>
								</div>
						</ul>
						
				       <div class="short" ng-if="!mailread&&windowType=='draft'" ng-cloak>
								<div class="dropdown">
									<button class="btn  dropdown-toggle" type="button" ng-click="uCommunication.deleteMail()" ng-if="windowType=='draft'" ng-cloak>Discard</button>
								</div>
						</div>
							<div  class="short" ng-if="mailread" ng-cloak>
								<h4>{{ readMail.MI_SUBJECT }}</h4>
							</div>
						</div>		
				<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6 pagin">
							<div class="short_pul " ng-if="!mailread" ng-cloak ng-show="total=='true'">
							{{begin}} - {{end}} of {{totallength}} <a href="javascript:void(0)"><i class="fa fa-step-backward" ng-click="uCommunication.previouspage()" ng-show="previousdisable=='true'"></i></a>  <a href="javascript:void(0)"><i class="fa fa-step-forward" ng-click="uCommunication.nextpage()" ng-show="nextdisable=='true'"></i></a>
							</div>
			</div>
		</div>
		</div>
	</div>		
			
	</div>			
	<div class="container hidden-xs hidden-sm newcontainer_big">
					<jsp:include page="/include/communication/MyInboxSideMenu.jsp" flush="true" />
			        <% if(request.getParameter("type").equals("inbox")){%>
						 <jsp:include page="/include/communication/Inbox.jsp" flush="true"/> 
						
					<%} else if(request.getParameter("type").equals("compose")){%>
						<jsp:include page="/include/communication/MailCompose.jsp" flush="true"/>
					<%} else if(request.getParameter("type").equals("sent")){%>
						<jsp:include page="/include/communication/Sent.jsp" flush="true"/>
					<%} else if(request.getParameter("type").equals("trash")){%>
						<jsp:include page="/include/communication/Trash.jsp" flush="true"/>
					<%} else if(request.getParameter("type").equals("archive")){%>
						<jsp:include page="/include/communication/Archive.jsp" flush="true"/>
					<%} else if(request.getParameter("type").equals("Settings")){%>
						<jsp:include page="/include/communication/Settings.jsp" flush="true"/>
					<%} else if(request.getParameter("type").equals("chat")){%>
						<jsp:include page="/include/communication/ChatUser.jsp" flush="true"/>
					<%} else if(request.getParameter("type").equals("draft")){%>
						<jsp:include page="/include/communication/Draft.jsp" flush="true"/>
					<%}else if(request.getParameter("type").equals("notification")){%>
					<jsp:include page="/include/communication/Notification.jsp" flush="true"/>
				<%}else {%>
						<jsp:include page="/include/communication/Inbox.jsp" flush="true"/>
					<%} %>
					
					   <jsp:include page="/include/communication/ChatUser.jsp" flush="true" />  
					  </div>
					  
				<!-- chatbox -->
	<div class="row chatter">
		<!-- <div class="overflow_counter">
			<div class="col-md-12" id="minicounter">
				<div class="minicounterval " id="minicounterval">
				  
				 </div>
			<div id="minichat" class="col-md-12 minichat" >
				
				<ul id="mini_populate">
					
				</ul>
		
			</div>
		    </div>

		</div> -->

		<div class="" ng-repeat="chat in connectedRooms" ng-cloak>
			<div class="chat_contain mobileblue " id="{{chat.chat_room}}" idle-countdown="countdown">
				<div class="chat_title" id="{{chat.chat_room}}" ng-click="uCommunication.minmax(chat.chat_room)">
					<div class="title_text">
						<div class="online_stat_title c_online"></div>
						Chat with <span class="uname">{{chat.benificiary_name}}</span>
					</div>
					<i class="fa fa-times closerr" id="closerr1" ng-click="uCommunication.popupclose(chat.chat_room)"></i> <i
						class="fa fa-cog cogger closerr hastip" id="settings_chat"
						title=""></i>
				</div>

				<div class="chat_screen" id={{chat.chat_room}}>
					<div class="row text-center">
						<span class="main_time_stamp">Mar 5</span>
					</div>
					<ul>
						<li>
						</li>
					</ul>
					<!-- <ul id="sessionshowid{{chat.chat_room}}">
						<li>Your Chat Session  Getting Close In Another One minute</li>
					</ul> -->
				</div>

				<div class="chat_action">
					<form ng-submit="uCommunication.sendmessage(chat.chat_room)">
						<div class="col-md-10 padd_typer">
							<input type="text" id="{{chat.chat_room}}" ng-blur="uCommunication.idletimecalculator(chat.chat_room)" placeholder=" Write a message"
								class="typer_box" />
						</div>
						<div class="col-md-2 padd_fly">
							<div class="plane_click">
								<!-- <i class="fa fa-paper-plane"></i> -->
								<button type="submit" class="send">Send</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
</div>
			
       			</div>
				<!--  Code for Chat -->
          
           
		</div>
	</div>
</div>
<!-- MY PROFILE END -->