<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="pull-left alert_msg" ng-bind-html="alert_message"></div>
<div ng-init="uCommunication.compose();uCommunication.getsession()" class="tab-pane active maildiv" id="maildiv">
	<div class="tab-pane active" id="tab_b" ng-hide="mailread">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 title cust_color" ng-if="logged_in_user=='cust'" ng-cloak>
			<div class="row">
				<div class="conter">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
						<div class="op"><input type="checkbox"  ng-model="checkAll" ng-change="uCommunication.selectAll(checkAll)"></div>
						<div class="op_from">To</div>
					</div>
					<div class="col-lg-5 col-md-5 col-sm-3 col-xs-3">
						<div class="op">Subject</div>
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
						<div class="op_from">To</div>
					</div>
					<div class="col-lg-5 col-md-5 col-sm-3 col-xs-3">
						<div class="op">Subject</div>
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
			<div class="row" ng-repeat="draft in draftList" ng-cloak>
				<div class="col-lg-12 fontsizes">
					<div class="row">
						<div class="col-lg-3 subclass">
							<div class="name"><input type="checkbox" ng-model="draft.selection" ng-change="uCommunication.selectedmail(draft)"></div>
							<!-- <div class="name_left" ng-cloak>{{draft.MI_TO_ID}} {{ usersListbyID[draft.MI_TO_ID].user }}</div> -->
							<!-- <div class="name_left" ng-cloak>{{ usersListbyID[draft.MI_TO_ID].user }}</div> -->
							<div ng-if="draft.threadmsg.length!=1">
								<div class="name_left ng-cloak" ng-if="draft.MI_TO_ID!=currentUser.UserId">{{ usersListbyID[draft.MI_TO_ID].user+" ("+draft.threadmsg.length+")" }} <span style="color:#FF0000">Draft</span></div>
								<div class="name_left ng-cloak" ng-if="draft.MI_TO_ID==currentUser.UserId">{{ usersListbyID[draft.MI_FROM_ID].user+" ("+draft.threadmsg.length+")" }} <span style="color:#FF0000">Draft</span></div>
							</div>
							<div ng-if="draft.threadmsg.length==1">
								<div class="name_left ng-cloak" ng-if="draft.MI_TO_ID!=currentUser.UserId">{{ usersListbyID[draft.MI_TO_ID].user }} <span style="color:#FF0000">Draft</span></div>
								<div class="name_left ng-cloak" ng-if="draft.MI_TO_ID==currentUser.UserId">{{ usersListbyID[draft.MI_FROM_ID].user }} <span style="color:#FF0000">Draft</span></div>
							</div>
						</div>
						<div class="col-lg-5 subclass">
							<div class="subject"><a href="#" ng-click="uCommunication.mailRead(draft)" ng-cloak ng-if="draft.MI_SUBJECT!='null'">{{draft.MI_SUBJECT}}</a></div>
							<div class="subject"><a href="#" ng-click="uCommunication.mailRead(draft)" ng-cloak ng-if="draft.MI_SUBJECT=='null'">(NO SUBJECT)</a></div>
						</div>
						<div class="col-lg-1 subclass">
							<div class="date" ng-cloak>{{  uCommunication.initDate(draft) }}</div>
						</div>
						<div class="col-lg-3">
							<div class="mailbx">
								<div class="res"><img src="support/communication/images/mo1.png" alt=""/></div>
								<div class="res"><img src="support/communication/images/ar.png" ng-click="uCommunication.moveToArchiveicon(draft)" alt=""/></div>
								<div class="res"><img src="support/communication/images/tr.png" ng-click="uCommunication.deleteMailicon(draft)" alt=""/></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div ng-show="searchvalue=='true'" ng-cloak>Some messages might match for your search in :
			<ul class="unorderlist">
			<li style="cursor:pointer"  ng-click="uCommunication.suggestfolder('Inbox')"><a href="#">Inbox ({{inboxsearchlength}})</a></li>
			<li style="cursor:pointer"   ng-click="uCommunication.suggestfolder('Sent')"><a href="#">Sent ({{sentsearchlength}})</a></li>
			<li style="cursor:pointer"  ng-click="uCommunication.suggestfolder('Archive')"><a href="#">Archive ({{archivesearchlength}})</a></li>
			<li style="cursor:pointer"  ng-click="uCommunication.suggestfolder('Trash')"><a href="#">Trash ({{trashsearchlength}})</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="tab-pane active" ng-show="mailread">
		<div class="col-lg-12 col-sm-12 title">
			<div class="row">
				<div class="conter">
				<div class="col-lg-6 cust_color" ng-if="logged_in_user=='cust'" ng-cloak>
					
					<div class="set"><button type="" value="Submit" id="save" data-loading-text="Saving...." style="background:none;border:none;color:white;" ng-click="uCommunication.sendMail(message)">SEND MAIL</button></div>
					<div class="set"><button type="" value="Submit"  id="savedraft" data-loading-text="Saving...." style="background:none;border:none;color:white;" ng-click="uCommunication.savetodraft(message)">SAVE</button></div>
					<div class="set"><button type="" value="Submit" style="background:none;border:none;color:white;"ng-click="uCommunication.cancel()" >CANCEL</button></div>
				</div>
				<div class="col-lg-6 fp_color" ng-if="logged_in_user=='fp'" ng-cloak>
					
					<div class="set"><button type="" value="Submit" id="save" data-loading-text="Saving...." style="background:none;border:none;color:white;" ng-click="uCommunication.sendMail(message)">SEND MAIL</button></div>
					<div class="set"><button type="" value="Submit"  id="savedraft" data-loading-text="Saving...." style="background:none;border:none;color:white;" ng-click="uCommunication.savetodraft(message)">SAVE</button></div>
					<div class="set"><button type="" value="Submit" style="background:none;border:none;color:white;"ng-click="uCommunication.cancel()" >CANCEL</button></div>
				</div>
				<div class="col-lg-6">
					
				</div>
			</div>
			</div>					
		</div>
	     <div class="col-lg-12 compose" ng-cloak>
		<div class="row">
			<div class="comp comp_back">
				<form name="newMessageForm"
								autocomplete="off" enctype="multipart/form-data">
					<div class="row tosubj">To:  
						<select class="demo-default subj select2me" id="selectbox" ng-model="message.recipient">
							<option value="-1">Please specify recipient.</option>
							<option ng-repeat="element in usersList" value="{{element.loginId}}">{{element.user}}</option>
						</select>
					</div>
					<div class="row tosubj">Subject:  <input type="text" class="demo-default subj" name="subject" 
													id="subject" ng-model="message.subject" required="required"/></div>
					<!-- <div class="row text_editorr">
						<input type="text" value="" class="jqte-test "  name="content" 
													id="content" ng-model="message.content">
					</div> -->
					<div class="row text_editorr">
						<div class="jqte ng-valid ng-dirty"><div class="jqte_toolbar unselectable" role="toolbar" unselectable="on" style="-webkit-user-select: none;"><div class="jqte_tool jqte_tool_1 unselectable" role="button" data-tool="0" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_label unselectable" unselectable="on" style="-webkit-user-select: none;"><span class="jqte_tool_text unselectable" unselectable="on" style="-webkit-user-select: none;">Normal</span><span class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></span></a><div class="jqte_formats unselectable" unselectable="on" style="-webkit-user-select: none;"><a jqte-formatval="p" class="jqte_format jqte_format_0 unselectable" role="menuitem" unselectable="on" style="-webkit-user-select: none;">Normal</a><a jqte-formatval="h1" class="jqte_format jqte_format_1 unselectable" role="menuitem" unselectable="on" style="-webkit-user-select: none;">Header 1</a><a jqte-formatval="h2" class="jqte_format jqte_format_2 unselectable" role="menuitem" unselectable="on" style="-webkit-user-select: none;">Header 2</a><a jqte-formatval="h3" class="jqte_format jqte_format_3 unselectable" role="menuitem" unselectable="on" style="-webkit-user-select: none;">Header 3</a><a jqte-formatval="h4" class="jqte_format jqte_format_4 unselectable" role="menuitem" unselectable="on" style="-webkit-user-select: none;">Header 4</a><a jqte-formatval="h5" class="jqte_format jqte_format_5 unselectable" role="menuitem" unselectable="on" style="-webkit-user-select: none;">Header 5</a><a jqte-formatval="h6" class="jqte_format jqte_format_6 unselectable" role="menuitem" unselectable="on" style="-webkit-user-select: none;">Header 6</a><a jqte-formatval="pre" class="jqte_format jqte_format_7 unselectable" role="menuitem" unselectable="on" style="-webkit-user-select: none;">Preformatted</a></div></div><div class="jqte_tool jqte_tool_2 unselectable" role="button" data-tool="1" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a><div class="jqte_fontsizes unselectable" unselectable="on" style="-webkit-user-select: none;"><a jqte-styleval="10" class="jqte_fontsize unselectable" style="font-size: 10px; -webkit-user-select: none;" role="menuitem" unselectable="on">Abcdefgh...</a><a jqte-styleval="12" class="jqte_fontsize unselectable" style="font-size: 12px; -webkit-user-select: none;" role="menuitem" unselectable="on">Abcdefgh...</a><a jqte-styleval="16" class="jqte_fontsize unselectable" style="font-size: 16px; -webkit-user-select: none;" role="menuitem" unselectable="on">Abcdefgh...</a><a jqte-styleval="18" class="jqte_fontsize unselectable" style="font-size: 18px; -webkit-user-select: none;" role="menuitem" unselectable="on">Abcdefgh...</a><a jqte-styleval="20" class="jqte_fontsize unselectable" style="font-size: 20px; -webkit-user-select: none;" role="menuitem" unselectable="on">Abcdefgh...</a><a jqte-styleval="24" class="jqte_fontsize unselectable" style="font-size: 24px; -webkit-user-select: none;" role="menuitem" unselectable="on">Abcdefgh...</a><a jqte-styleval="28" class="jqte_fontsize unselectable" style="font-size: 28px; -webkit-user-select: none;" role="menuitem" unselectable="on">Abcdefgh...</a></div></div><div class="jqte_tool jqte_tool_3 unselectable" role="button" data-tool="2" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a><div class="jqte_cpalette unselectable" unselectable="on" style="-webkit-user-select: none;"><a jqte-styleval="0,0,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(0, 0, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="68,68,68" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(68, 68, 68);" role="gridcell" unselectable="on"></a><a jqte-styleval="102,102,102" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(102, 102, 102);" role="gridcell" unselectable="on"></a><a jqte-styleval="153,153,153" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(153, 153, 153);" role="gridcell" unselectable="on"></a><a jqte-styleval="204,204,204" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(204, 204, 204);" role="gridcell" unselectable="on"></a><a jqte-styleval="238,238,238" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(238, 238, 238);" role="gridcell" unselectable="on"></a><a jqte-styleval="243,243,243" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(243, 243, 243);" role="gridcell" unselectable="on"></a><a jqte-styleval="255,255,255" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(255, 255, 255);" role="gridcell" unselectable="on"></a><div class="jqte_colorSeperator"></div><a jqte-styleval="255,0,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(255, 0, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="255,153,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(255, 153, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="255,255,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(255, 255, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="0,255,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(0, 255, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="0,255,255" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(0, 255, 255);" role="gridcell" unselectable="on"></a><a jqte-styleval="0,0,255" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(0, 0, 255);" role="gridcell" unselectable="on"></a><a jqte-styleval="153,0,255" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(153, 0, 255);" role="gridcell" unselectable="on"></a><a jqte-styleval="255,0,255" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(255, 0, 255);" role="gridcell" unselectable="on"></a><div class="jqte_colorSeperator"></div><a jqte-styleval="244,204,204" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(244, 204, 204);" role="gridcell" unselectable="on"></a><a jqte-styleval="252,229,205" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(252, 229, 205);" role="gridcell" unselectable="on"></a><a jqte-styleval="255,242,204" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(255, 242, 204);" role="gridcell" unselectable="on"></a><a jqte-styleval="217,234,211" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(217, 234, 211);" role="gridcell" unselectable="on"></a><a jqte-styleval="208,224,227" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(208, 224, 227);" role="gridcell" unselectable="on"></a><a jqte-styleval="207,226,243" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(207, 226, 243);" role="gridcell" unselectable="on"></a><a jqte-styleval="217,210,233" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(217, 210, 233);" role="gridcell" unselectable="on"></a><a jqte-styleval="234,209,220" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(234, 209, 220);" role="gridcell" unselectable="on"></a><a jqte-styleval="234,153,153" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(234, 153, 153);" role="gridcell" unselectable="on"></a><a jqte-styleval="249,203,156" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(249, 203, 156);" role="gridcell" unselectable="on"></a><a jqte-styleval="255,229,153" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(255, 229, 153);" role="gridcell" unselectable="on"></a><a jqte-styleval="182,215,168" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(182, 215, 168);" role="gridcell" unselectable="on"></a><a jqte-styleval="162,196,201" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(162, 196, 201);" role="gridcell" unselectable="on"></a><a jqte-styleval="159,197,232" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(159, 197, 232);" role="gridcell" unselectable="on"></a><a jqte-styleval="180,167,214" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(180, 167, 214);" role="gridcell" unselectable="on"></a><a jqte-styleval="213,166,189" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(213, 166, 189);" role="gridcell" unselectable="on"></a><a jqte-styleval="224,102,102" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(224, 102, 102);" role="gridcell" unselectable="on"></a><a jqte-styleval="246,178,107" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(246, 178, 107);" role="gridcell" unselectable="on"></a><a jqte-styleval="255,217,102" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(255, 217, 102);" role="gridcell" unselectable="on"></a><a jqte-styleval="147,196,125" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(147, 196, 125);" role="gridcell" unselectable="on"></a><a jqte-styleval="118,165,175" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(118, 165, 175);" role="gridcell" unselectable="on"></a><a jqte-styleval="111,168,220" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(111, 168, 220);" role="gridcell" unselectable="on"></a><a jqte-styleval="142,124,195" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(142, 124, 195);" role="gridcell" unselectable="on"></a><a jqte-styleval="194,123,160" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(194, 123, 160);" role="gridcell" unselectable="on"></a><a jqte-styleval="204,0,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(204, 0, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="230,145,56" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(230, 145, 56);" role="gridcell" unselectable="on"></a><a jqte-styleval="241,194,50" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(241, 194, 50);" role="gridcell" unselectable="on"></a><a jqte-styleval="106,168,79" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(106, 168, 79);" role="gridcell" unselectable="on"></a><a jqte-styleval="69,129,142" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(69, 129, 142);" role="gridcell" unselectable="on"></a><a jqte-styleval="61,133,198" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(61, 133, 198);" role="gridcell" unselectable="on"></a><a jqte-styleval="103,78,167" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(103, 78, 167);" role="gridcell" unselectable="on"></a><a jqte-styleval="166,77,121" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(166, 77, 121);" role="gridcell" unselectable="on"></a><a jqte-styleval="153,0,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(153, 0, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="180,95,6" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(180, 95, 6);" role="gridcell" unselectable="on"></a><a jqte-styleval="191,144,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(191, 144, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="56,118,29" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(56, 118, 29);" role="gridcell" unselectable="on"></a><a jqte-styleval="19,79,92" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(19, 79, 92);" role="gridcell" unselectable="on"></a><a jqte-styleval="11,83,148" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(11, 83, 148);" role="gridcell" unselectable="on"></a><a jqte-styleval="53,28,117" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(53, 28, 117);" role="gridcell" unselectable="on"></a><a jqte-styleval="116,27,71" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(116, 27, 71);" role="gridcell" unselectable="on"></a><a jqte-styleval="102,0,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(102, 0, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="120,63,4" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(120, 63, 4);" role="gridcell" unselectable="on"></a><a jqte-styleval="127,96,0" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(127, 96, 0);" role="gridcell" unselectable="on"></a><a jqte-styleval="39,78,19" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(39, 78, 19);" role="gridcell" unselectable="on"></a><a jqte-styleval="12,52,61" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(12, 52, 61);" role="gridcell" unselectable="on"></a><a jqte-styleval="7,55,99" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(7, 55, 99);" role="gridcell" unselectable="on"></a><a jqte-styleval="32,18,77" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(32, 18, 77);" role="gridcell" unselectable="on"></a><a jqte-styleval="76,17,48" class="jqte_color unselectable" style="-webkit-user-select: none; background-color: rgb(76, 17, 48);" role="gridcell" unselectable="on"></a></div></div><div class="jqte_tool jqte_tool_4 unselectable" role="button" data-tool="3" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_5 unselectable" role="button" data-tool="4" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_6 unselectable" role="button" data-tool="5" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_7 unselectable" role="button" data-tool="6" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_8 unselectable" role="button" data-tool="7" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_9 unselectable" role="button" data-tool="8" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_10 unselectable" role="button" data-tool="9" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_11 unselectable" role="button" data-tool="10" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_12 unselectable" role="button" data-tool="11" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_13 unselectable" role="button" data-tool="12" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_14 unselectable" role="button" data-tool="13" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_15 unselectable" role="button" data-tool="14" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_16 unselectable" role="button" data-tool="15" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_17 unselectable" role="button" data-tool="16" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_18 unselectable" role="button" data-tool="17" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_19 unselectable" role="button" data-tool="18" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_20 unselectable" role="button" data-tool="19" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div><div class="jqte_tool jqte_tool_21 unselectable" role="button" data-tool="20" unselectable="on" style="-webkit-user-select: none;"><a class="jqte_tool_icon unselectable" unselectable="on" style="-webkit-user-select: none;"></a></div></div><div class="jqte_linkform" style="display:none" role="dialog"><div class="jqte_linktypeselect unselectable" unselectable="on" style="-webkit-user-select: none;"><div class="jqte_linktypeview unselectable" unselectable="on" style="-webkit-user-select: none;"><div class="jqte_linktypearrow unselectable" unselectable="on" style="-webkit-user-select: none;"></div><div class="jqte_linktypetext">Web Address</div></div><div class="jqte_linktypes unselectable" role="menu" unselectable="on" style="-webkit-user-select: none; display: none;"><a jqte-linktype="0" unselectable="on" class="unselectable" style="-webkit-user-select: none;">Web Address</a><a jqte-linktype="1" unselectable="on" class="unselectable" style="-webkit-user-select: none;">E-mail Address</a><a jqte-linktype="2" unselectable="on" class="unselectable" style="-webkit-user-select: none;">Picture URL</a></div></div><input class="jqte_linkinput" type="text/css" value=""><div class="jqte_linkbutton unselectable" unselectable="on" style="-webkit-user-select: none;">OK</div> <div style="height:1px;float:none;clear:both"></div></div><div class="jqte_editor" contenteditable="true">hxcjbvxhvjvhfvdhvfsdsdhdshdshdshsd sdghskd iusd &nbsp;iusdhdshgdssddsdsdssdsdysdsdfsdsdfsdf iudshfuisdf<div>sdfysd</div><div>osdfosid</div><div>usd]sd</div><div>fsdfsd</div><div>fsdfsdfisdui</div></div><div class="jqte_source jqte_hiddenField"><textarea class="jqte-test " name="content" id="content" ng-model="message.content" data-origin="input"></textarea></div></div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>
</div>