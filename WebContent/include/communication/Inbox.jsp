<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="pull-left alert_msg" ng-bind-html="alert_message"></div>
<div ng-init="uCommunication.compose();uCommunication.getsession()" class="tab-pane active maildiv" id="maildiv">
	<div class="tab-pane active" id="tab_b" ng-hide="mailread">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 title cust_color" ng-if="logged_in_user=='cust'" ng-cloak>
		<div class="row">
				<div class="conter">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="op"><input type="checkbox"  ng-model="checkAll" ng-change="uCommunication.selectAll(checkAll)"></div>
						<div class="op_from" >From</div>
					</div>
					<div class="col-lg-5 col-md-5 col-sm-3 col-xs-3">
						<div class="op" >Subject</div>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-3">
						<div class="op" ng-click="uCommunication.datefunc()">Date
						<i class="fa fa-sort-desc arD"></i>
						</div>
						
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="op_action">Action</div>
					</div>
				</div>
			</div>
		</div>	
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 title fp_color" ng-if="logged_in_user=='fp'" ng-cloak>
				<div class="row">
				<div class="conter">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="op"><input type="checkbox"  ng-model="checkAll" ng-change="uCommunication.selectAll(checkAll)"></div>
						<div class="op_from" >From</div>
					</div>
					<div class="col-lg-5 col-md-5 col-sm-3 col-xs-3">
						<div class="op" >Subject</div>
					</div>
					<div class="col-lg-1 col-md-1 col-sm-3 col-xs-3">
						<div class="op" ng-click="uCommunication.datefunc()">Date
						<i class="fa fa-sort-desc arD"></i>
						</div>
						
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="op_action">Action</div>
					</div>
				</div>
			</div>				
		</div>
		<div class="col-lg-12 content">
			<div class="row" ng-repeat="inbox in inboxList" ng-cloak>
				<div class="col-lg-12 fontsizes" ng-class="inbox.MI_READ_DATE=='UNREAD'? 'unread' : '' " >
					<div class="row">
						<div class="col-lg-3 subclass">
							<div class="name"><input type="checkbox" ng-model="inbox.selection" ng-change="uCommunication.selectedmail(inbox)"></div>
							<div class="name"><img src="{{ uCommunication.initStar(inbox.MI_STAR_TYPE); }}" alt="" class="popper" data-id="popper-{{ inbox.MI_ID }}"data-toggle="popover" />
								<div class="popper-content hide">
									<img class="rating-stars" src="support/communication/images/gree.png" data-id="{{ inbox.MI_ID }}" data-star-id="1" alt=""/>
									<img class="rating-stars" src="support/communication/images/req.png" data-id="{{ inbox.MI_ID }}" data-star-id="2" alt=""/>
									<img class="rating-stars" src="support/communication/images/req1.png" data-id="{{ inbox.MI_ID }}" data-star-id="3" alt=""/>
									<img class="rating-stars" src="support/communication/images/req4.png" data-id="{{ inbox.MI_ID }}" data-star-id="4" alt=""/>
									<img class="rating-stars" src="support/communication/images/req5.png" data-id="{{ inbox.MI_ID }}" data-star-id="5" alt=""/>
								</div>
							</div>
							<div ng-if="inbox.threadmsg.length!=1">
								<div class="name_left ng-cloak" ng-if="inbox.MI_FROM_ID!=currentUser.UserId">{{ usersListbyID[inbox.MI_FROM_ID].user+" ("+inbox.threadmsg.length+")" }}</div>
								  <div class="name_left ng-cloak" ng-if="inbox.MI_FROM_ID==currentUser.UserId">{{ usersListbyID[inbox.MI_TO_ID].user+" ("+inbox.threadmsg.length+")" }}</div> 
							</div>
							<div ng-if="inbox.threadmsg.length==1">
								<div class="name_left ng-cloak" ng-if="inbox.MI_FROM_ID!=currentUser.UserId">{{ usersListbyID[inbox.MI_FROM_ID].user }}</div>
								<div class="name_left ng-cloak" ng-if="inbox.MI_FROM_ID==currentUser.UserId">{{ usersListbyID[inbox.MI_TO_ID].user }}</div>
							</div>
						</div>
						<div class="col-lg-5 subclass">
							<div class="subject"><a href="#" ng-click="uCommunication.mailRead(inbox)" ng-cloak>{{inbox.MI_SUBJECT}}</a></div>
						</div>
						<div class="col-lg-1 subclass">
							<div class="date" ng-cloak>{{ uCommunication.initDate(inbox) }}</div>
						</div>
						<div class="col-lg-3 ">
							<div class="mailbx">
								<div class="res"><img src="support/communication/images/mo1.png" alt=""/></div>
								<div class="res"><img src="support/communication/images/ar.png" ng-click="uCommunication.moveToArchiveicon(inbox)" alt=""/></div>
								<div class="res"><img src="support/communication/images/tr.png" ng-click="uCommunication.deleteMailicon(inbox)" alt=""/></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div ng-show="searchvalue=='true'" ng-cloak>Some messages might match for your search in :
			<ul class="unorderlist">
			<li style="cursor:pointer" ng-cloak ng-click="uCommunication.suggestfolder('Sent')"><a href="#">Sent ({{sentsearchlength}})</a></li>
			<li style="cursor:pointer" ng-cloak ng-click="uCommunication.suggestfolder('Draft')"><a href="#">Draft ({{draftsearchlength}})</a></li>
			<li style="cursor:pointer" ng-cloak ng-click="uCommunication.suggestfolder('Archive')"><a href="#">Archive ({{archivesearchlength}})</a></li>
			<li style="cursor:pointer" ng-cloak ng-click="uCommunication.suggestfolder('Trash')"><a href="#">Trash ({{trashsearchlength}})</a></li>
			</ul>
			</div>
		</div>
	</div>
	<div class="col-lg-12 compose" id="replydiv" ng-show="replymail" ng-cloak>
			<div class="row">
				<div class="comp comp_back">
					<form name="newMessageForm"
									autocomplete="off" enctype="multipart/form-data">
						<div class="row tosubj" style="text-align:left"><span style="line-height: 21px;vertical-align: top;">To:</span> 
							<span class="demo-default subj">{{ usersListbyID[message.MI_TO_ID].user }}</span>
							<div class="set">
							<button type="" value="Submit" style="background:#ce2b2c;border:none;color:white;padding: 8px;margin-top: 10px;margin-left: 0px;" ng-click="uCommunication.replyEmail()">SEND MAIL</button>
							</div>
						</div>
						<div class="row tosubj" style="text-align:left"><span style="line-height: 22px;vertical-align: top;">Subject:</span>  
							<span class="demo-default subj">{{ message.MI_SUBJECT }}</span><br/>
						</div>
							<div class="row text_editorr">
								<input type="text" value="" class="jqte-test"  name="content" id="content" ng-model="message.content">
							</div>
					</form>
				</div>
			</div>
	</div>
	<div class="tab-pane active" ng-show="mailread" ng-cloak>
		<div class="panel-group">
			<div class="panel panel-default noborder" ng-repeat="thread in readMail.threadmsg">
					<div class="panel-heading quick" ng-class="$index+1==1?'opened':''">
						<div class="row">
							<div class="col-sm-9 col-xs-9">
								<a data-toggle="collapse" href="#collapse-{{$index+1}}" class="toggle-a">
									<div class="baharv"><img src="support/communication/images/lets1.png"/></div>
									 <div style="display: inline-block">
									<div class="baharv" style="padding-left:10px;" ng-if="currentUser.UserId==thread.MI_FROM_ID"><span class="name_left">{{ currentUser.UserName }}</span></div>
									<div class="baharv" style="padding-left:10px;" ng-if="currentUser.UserId!=thread.MI_FROM_ID"><span class="name_left">{{ usersListbyID[thread.MI_FROM_ID].user }}</span></div><br/>
									<div class="baharv" style="padding-left:10px;" ng-if="thread.MI_SUBJECT!='null'">{{ thread.MI_SUBJECT }}</div><br/>
									<div class="baharv" style="padding-left:10px;" ng-if="thread.MI_SUBJECT=='null'">(NO SUBJECT)</div>
									<span style="padding-left:10px;">{{ uCommunication.dateforviewmail(thread) }}</span>
									</div>
								</a>
							</div>
							<div class="col-sm-3 col-xs-3" style="padding-right:0px"> 
								<i class="fa fa-arrow-left inbox_back btn dropdown-toggle" ng-click="uCommunication.backbutton()"></i>
								<div class="dropdown right-drop-btn  " style="display: inline;">
									<button class="btn dropdown-toggle inbox_back" type="button" data-toggle="dropdown">
									<span class="caret"></span></button>
									<ul class="dropdown-menu">
									  <li><a href="#" ng-click="uCommunication.reply(readMail)">Reply</a></li>
									  <li><a href="#" ng-click="readMail.selection='true';uCommunication.selectedmail(readMail);uCommunication.moveToArchive()">Archive</a></li>
									  <li><a href="#" ng-click="readMail.selection='true';uCommunication.selectedmail(readMail);uCommunication.deleteMail()">Trash</a></li>
									</ul>
								</div>
							</div>
							
						</div>
					</div>
					<div id="collapse-{{$index+1}}" class="panel-collapse collapse" ng-class="$index+1==1 && !replymail ?'in':''"   ng-cloak>
						<div class="col-lg-12 read" id="mailbox">
							<div class="col-lg-12">
								<div class="row">
									<input type="text" value=""  name="mail-read-{{$index+1}}" id="mail-read-{{$index+1}}">
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		
		
	</div>
</div>