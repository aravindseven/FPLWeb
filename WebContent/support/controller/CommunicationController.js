function CommunicationController($scope, $http, $sce) {
	this.scope = $scope;
	this.http = $http;
	this.sce = $sce; 
	this.scope.master = {};
	this.util = new Util();
	this.chatBaseMargin = 400;
	this.chatAdjustmentMargin = 150;
	this.chatAddtionalMargin = 250;
	this.type = this.util.gup("type");
	$scope.windowType = this.util.gup("type");
	$scope.message = {};
	$scope.mailread= false;
	$scope.replymail=false;
	$scope.FROM;
	$scope.lastMailDetail = [];
	$scope.readMail = {};
	$scope.mailList = [];
	$scope.inboxList = [];
	$scope.notificationlist = [];
	$scope.sentList = [];
	$scope.tipslist = [];
	$scope.trashList = [];
	$scope.selectedList = [];
	$scope.draftList = [];
	$scope.draftEdit = {};
	$scope.archiveList = [];
	$scope.chatList = [];
	$scope.filteredTodos = [];
	$scope.usersList= [];
	$scope.usersListbyID = [];
	$scope.currentPage = 1;
	$scope.numPerPage = 10;
	$scope.begin = 0;
	$scope.end = 0;
	$scope.datecheck = 1;
	$scope.maxSize = 5;
	$scope.noOfUsersSelected = 1;
	$scope.messages = [];
	$scope.settings = {};
	$scope.currentUser = null;
	$scope.serverDate = "";
	$scope.currentUser = {};
	$scope.searchvalue = "false;"
	$scope.dataobj = {};
	$scope.dataobj.search;
	$scope.dataobj.filter="all";
	$scope.previous = 0;
	$scope.next = 0;
	$scope.connectedRooms = [];
	$scope.onlineusers =[];
	$scope.chatmessage_flag = 0;
	$scope.authorsendername;
	$scope.events = [];
	$scope.first_name;
	$scope.last_name;
	$scope.benificiary_name;
	$scope.active_connectedrooms ="false";
	$scope.activeconnections = [];
	$scope.chatboxcountflag;
	$scope.eventsForMinimize = [];
	$scope.lastelementarraya = [];
	$scope.finalminarray = [];
	$scope.movetoarchive_icon = 0;
	$scope.movetodelete_icon = 0;
	$scope.logged_in_user;
	$('#mail-read').jqte();
	if(this.type=="")
		window.location.href="Communication.jsp?type=inbox";
	this.monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
	                  "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
	var self = this;
	var d = new Date();
	self.scope.currentchatdate = this.monthNames[d.getMonth()]+" "+d.getDate();
	$('body').on('click','.popper',function (e) {
	    $('.popper').not(this).popover('hide');
	});
	$('body').on('click','.rating-stars',function (e) {
		if(self.scope.windowType=="inbox")
		   {
			var box = "inbox";
			self.mailStar($(this).attr('data-id'),$(this).attr('data-star-id'),box);
		   }
		else if(self.scope.windowType=="sent")
			{
			var box = "sent";
			self.mailStar($(this).attr('data-id'),$(this).attr('data-star-id'),box);
			}
		else if(self.scope.windowType=="draft")
			{
			var box = "draft";
			self.mailStar($(this).attr('data-id'),$(this).attr('data-star-id'),box);
			}
		else if(self.scope.windowType=="archive")
			{
			var box = "archive";
			self.mailStar($(this).attr('data-id'),$(this).attr('data-star-id'),box);
			}
		else
			{
			var box = "trash";
			self.mailStar($(this).attr('data-id'),$(this).attr('data-star-id'),box);
			}
		
	});
	$('body').on('click','.panel-heading a.toggle-a',function() {
	    $('.panel-heading').removeClass('opened');
	    $(this).parents('.panel-heading').addClass('opened');
	});
	
	sizeChange();
	window.onresize = function(){
		sizeChange();
    };
    function sizeChange()
    {
    	var div = document.getElementById('maildiv');
    	if(window.innerWidth>1199)
		{
    		if(div!==null)
			{
    			space = window.innerHeight - (div.offsetTop + 20);
                var rh=space+'px'.toString();
                $('#maildiv').css('height',rh);
            }
    		$('html').css('overflow','hidden');
		}
    	else
		{
    		$('html').css('overflow','auto');
		}
    }
    
   if($scope.windowType=="compose")
   {
	   $('#content').jqte();
		
		// settings of status
		var jqteStatus = true;
		$(".status").click(function()
		{
			jqteStatus = jqteStatus ? false : true;
			$('#content').jqte({"status" : jqteStatus})
		});
   }

 }
//checking for pagination with window type
CommunicationController.prototype.nextpage = function()
{

	//calculating the page number
	var self = this;
	self.scope.previous = 0;
	self.scope.next = 1;
	self.scope.currentPage = self.scope.currentPage + 1;
	if(self.scope.windowType=="inbox")
	   {
		self.inbox(self.scope.currentPage);
	   }
	else if(self.scope.windowType=="sent")
		{
		self.sent(self.scope.currentPage);
		}
	else if(self.scope.windowType=="draft")
		{
		self.draft(self.scope.currentPage);
		}
	else if(self.scope.windowType=="archive")
		{
		self.archive(self.scope.currentPage);
		}
	else
		{
		self.trash(self.scope.currentPage);
		}
	
	   
}
//previous page pagination
CommunicationController.prototype.previouspage = function()
{
	var self = this;
	//calculating the page number
	self.scope.currentPage = self.scope.currentPage - 1;
	self.scope.previous = 1;
	self.scope.next = 0;
	if(self.scope.windowType=="inbox")
	   {
		self.inbox(self.scope.currentPage);
	   }
	else if(self.scope.windowType=="sent")
		{
		self.sent(self.scope.currentPage);
		}
	else if(self.scope.windowType=="draft")
		{
		self.draft(self.scope.currentPage);
		}
	else if(self.scope.windowType=="archive")
		{
		self.archive(self.scope.currentPage);
		}
	else
		{
		self.trash(self.scope.currentPage);
		}
	
	   
}

//back button

CommunicationController.prototype.backbutton = function()
{
	var self = this;
	var pageno =1;
	
	if(self.scope.windowType=="inbox")
	   {
		window.location.href="Communication.jsp?type=inbox";
	   }
	else if(self.scope.windowType=="sent")
		{
		window.location.href="Communication.jsp?type=sent";
		}
	else if(self.scope.windowType=="draft")
		{
		window.location.href="Communication.jsp?type=draft";
		}
	else if(self.scope.windowType=="archive")
		{
		window.location.href="Communication.jsp?type=archive";
		}
	else
		{
		window.location.href="Communication.jsp?type=trash";
		}
	
	   
}

CommunicationController.prototype.initStar = function(MI_STAR_TYPE) {
	
	var starpath = "support/communication/images/qq.png";
	if(MI_STAR_TYPE=="0")
		starpath = starpath;
	if(MI_STAR_TYPE=="1")
		starpath = "support/communication/images/gree.png";
	if(MI_STAR_TYPE=="2")
		starpath = "support/communication/images/req.png";
	if(MI_STAR_TYPE=="3")
		starpath = "support/communication/images/req1.png";
	if(MI_STAR_TYPE=="4")
		starpath = "support/communication/images/req4.png";
	if(MI_STAR_TYPE=="5")
		starpath = "support/communication/images/req5.png";
	return starpath;
};

CommunicationController.prototype.initDate = function(mail) {
	var self = this;
	var a = new Date(self.scope.serverDate);
	a.setHours(0,0);
	var b = new Date(mail.MI_SENT_DATE);
	b.setHours(0,0,0);
	if(a.getTime() === b.getTime())
		{
			var a = mail.MI_SENT_TIME.split(':')[0];
			var b = mail.MI_SENT_TIME.split(':')[1];
			var c = mail.MI_SENT_TIME.split(':')[2];
			mail.MI_SENT_TIME =  a+":"+b+":"+c.split('.')[0];
			return mail.MI_SENT_TIME;
		}
	else if(a.getFullYear() === b.getFullYear())
		{
		return b.getDate()+" "+self.monthNames[b.getMonth()]+" "+(b.getFullYear() + '').substring(2, 4);
		}
	else
		{
		return b.getDate()+" "+self.monthNames[b.getMonth()]+" "+(b.getFullYear() + '').substring(2, 4);
		}
		
	
};

CommunicationController.prototype.dateforviewmail = function(mail) {
	var self = this;
	var a = new Date(self.scope.serverDate);
	a.setHours(0,0);
	var b = new Date(mail.MI_SENT_DATE);
	b.setHours(0,0,0);
	if(a.getFullYear() === b.getFullYear())
		{
			var a1 = mail.MI_SENT_TIME.split(':')[0];
			var b1 = mail.MI_SENT_TIME.split(':')[1];
			var c1 = mail.MI_SENT_TIME.split(':')[2];
			var d= c1.split('.')[0];
			return b.getDate()+" "+self.monthNames[b.getMonth()]+" "+b.getFullYear()+" "+a1+":"+b1+":"+d;
		}
	else
		{
			var a = mail.MI_SENT_TIME.split(':')[0];
			var b = mail.MI_SENT_TIME.split(':')[1];
			var c = mail.MI_SENT_TIME.split(':')[2];
			var d1 = c.split('.')[0];
			return b.getDate()+" "+self.monthNames[b.getMonth()]+" "+b.getFullYear()+" "+a+":"+b+":"+d1;
		}
		
	
};


/*CommunicationController.prototype.chatdate = function()
{
	var a = new Date();
	console.log(a)
	a.setHours(0,0);
	return self.monthNames[a.getMonth()]+" "+a.getDate();
}*/



CommunicationController.prototype.inbox = function(pageno) {
	
	//$('.arD').addClass('descend');
	var self = this;
	if(self.scope.logged_in_user == "cust")
	{
	
	$("li").removeClass('custa_color');
	$("#inbox").addClass('custa_color');
	}
else
	{
	
	$("li").removeClass('fpa_color');
	$("#inbox").addClass('fpa_color');
	}
	self.scope.replymail=false;
	self.scope.currentPage = pageno;
	self.scope.inboxList = [];
	/*alert()*/
	if(self.scope.dataobj.filter == "asc")
		{
		$('.arD').removeClass('descend');
		}
	else
		{
		$('.arD').addClass('descend');
		}
	this.util.block("Loading Inbox..");
	this.http({
		method : 'POST',
		//url:'support/contents/inbox.json',
		url:'getInboxMessages.do',
		params : {
			"pageno" : pageno,
			"filter" : self.scope.dataobj.filter,
			"search" : self.scope.dataobj.search,
			"box"    : self.scope.windowType
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		
		if (data) {
			if(data.Status == "Success"){
				self.scope.inboxList = eval(data.Output.al);
				self.scope.serverDate = data.Current_Time;
				self.scope.currentUser.UserId = data.UserId;
				self.scope.currentUser.UserEmail = data.UserEmail;
				self.scope.currentUser.UserName = data.UserName;
				//paginationa
				self.scope.totallength = data.Output.NumberofMails;
				self.scope.numPages = Math.ceil(self.scope.totallength / self.scope.numPerPage);
				if(self.scope.numPages == 0)
					{
						self.scope.begin = "";
						self.scope.end = "";
						self.scope.totallength = "";
						self.scope.total = "false";
					}
				else
					{
						self.scope.total = "true";
							if(self.scope.numPages == self.scope.currentPage)
							{
								//next button disable
								if(self.scope.currentPage == 1)
								{
									self.scope.begin = 1;
									self.scope.end = self.scope.totallength;
									self.scope.previousdisable = "false";
									self.scope.nextdisable = "false";
									//next disablea
								}
								else
								{
									self.scope.begin = self.scope.end + 1;
									self.scope.end = self.scope.totallength;
									self.scope.nextdisable = "false";
									self.scope.previousdisable = "true";
								}
							}
						 
						else
							{
								if(self.scope.currentPage == 1)
								{
									self.scope.begin = 1;
									self.scope.end = 10;
									self.scope.nextdisable = "true";
									self.scope.previousdisable = "false";
								}
								else
									{
										self.scope.lastbeforecheck  = self.scope.numPages - 1;
										if(self.scope.currentPage == self.scope.lastbeforecheck)
											{
											self.scope.roundtotal = self.scope.numPages * 10;
											self.scope.a = self.scope.roundtotal - self.scope.totallength;
											self.scope.remainder = 10 - self.scope.a; 
												if(self.scope.previous == 1)
												{
													
												self.scope.begin = self.scope.begin - 10;
												self.scope.end = self.scope.end - self.scope.remainder;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
											}
										else
											{
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
												if(self.scope.previous == 1)
												{
												
												self.scope.begin = self.scope.begin - 10;
												self.scope.end = self.scope.end - 10;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
											}
										
									}
								
								
							}
					}
				
			}	
					
			 else {
				self.scope.inboxList = [];
				self.util.notify("Mail", data.Output, "failure");
			}
			if(self.scope.dataobj.search != undefined && self.scope.dataobj.search != null && self.scope.dataobj.search != " ")
				{
				if(self.scope.dataobj.search.length != 0)
					{
					self.scope.sentsearchlength = data.sent.NumberofMails;
					self.scope.draftsearchlength = data.draft.NumberofMails;
					self.scope.archivesearchlength = data.archive.NumberofMails;
					self.scope.trashsearchlength = data.trash.NumberofMails;
					self.scope.searchvalue = "true";
					}
				else
					{
					self.scope.searchvalue = "false";
					}
				
				}
			else
				{
				self.scope.searchvalue = "false";
				}
		} else {
			self.scope.inboxList = [];
		}
		self.scope.mailList = self.scope.inboxList;
		/*window.setTimeout(function(){ 
			$('.popper').popover({
			    placement: 'bottom',
			    container: 'body',
			    html: true,
			    content: function () {
			        return $(this).next('.popper-content').html();
			    }
			});
		},1000);*/
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.mailRead = function(mailObject) {
	var self = this;
	this.util.block("Loading Mail..");
	
	var from = this.type;
	self.scope.mailread = true;
	self.scope.readMail = mailObject;
	var cont = mailObject.MI_CONTENT;
	var msgid=mailObject.MI_ID;
	if(mailObject.MI_READ_DATE=="UNREAD")
	{
		this.http({
			method : 'POST',
			//url:'support/contents/sent.json',
			url:'readmailinbox.do',
			params : {
				'msgid' : msgid,
				'from'  : from
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			
		}).error(function(data, status, headers, config) {
		});
	}
	
	if(self.type==='draft')
	{
		self.scope.draftEdit = mailObject;
		self.scope.message.recipient = mailObject.MI_FROM_ID;
		if(self.scope.message.recipient != -1)
			{
			$("#selectbox").select2("val",self.scope.message.recipient)
			}

		self.scope.message.subject = mailObject.MI_SUBJECT;
		if(self.scope.message.subject == "null")
			{
				self.scope.message.subject = "NO SUBJECT";
			}
		self.scope.message.msgid = mailObject.MI_ID;
		$(".jqte-test").jqteVal(String(mailObject.MI_CONTENT));
	}
	else
	{
		self.scope.readMail.threadmsg.reverse();
		window.setTimeout(function(){
			angular.forEach(self.scope.readMail.threadmsg, function(value, key) {
				var id='#mail-read-'+(key+1);
				$(id).jqte();
				var cont = value.MI_CONTENT;
				$(id).jqteVal(String(cont));
				//console.log('id---'+id);
				//alert("ADSf");
			});
			
			$("body").find(".jqte_editor").attr("contenteditable","false");
			$("body").find(".jqte_toolbar").remove();
		},500);
		
	}
	this.util.unblock();
	//$('body .dropdown-toggle').dropdown();
};
CommunicationController.prototype.closeMail = function() {
	var self = this;
	self.scope.mailread = false;
};
CommunicationController.prototype.mailStar = function(msgid,star,box) {
	var self = this;
	this.http({
		method : 'POST',
		url:'changeStar.do',
		params : {
			'msgid' : msgid,
			'star'  : star,
			'box'   : box
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		var starpath = self.initStar(star);
		$('img[data-id="popper-' + msgid + '"]').attr('src', starpath);
		$('.popper').not(this).popover('hide');
	}).error(function(data, status, headers, config) {
	});
};
CommunicationController.prototype.todoController = function(list){
	var self = this;
	  self.scope.numPages = function () {
	    return Math.ceil(list.length / self.scope.numPerPage);
	  };
	  
	  self.scope.$watch('currentPage + numPerPage', function() {
		  //alert(self.scope.currentPage);
	    var begin = ((self.scope.currentPage - 1) * self.scope.numPerPage)
	    , end = begin + self.scope.numPerPage;
	    
	    self.scope.filteredTodos = list.slice(begin, end);
	  });
	  $("ul", $("#pagination")).first().addClass("pagination");
	  $("#pagination").css('margin', '0px');
}

CommunicationController.prototype.sent = function(pageno) {
	
	var self = this;
	if(self.scope.logged_in_user == "cust")
	{
	
	$("li").removeClass('custa_color');
	$("#sent").addClass('custa_color');
	}
else
	{
	
	$("li").removeClass('fpa_color');
	$("#sent").addClass('fpa_color');
	}
	self.scope.replymail=false;
	self.scope.currentPage = pageno;
	if(self.scope.dataobj.filter == "asc")
	{
	$('.arD').removeClass('descend');
	}
else
	{
	$('.arD').addClass('descend');
	}
	this.util.block("Loading Sent Emails..");
	
	this.http({
		method : 'POST',
		//url:'support/contents/sent.json',
		url:'getSentMail.do',
		params : {
			"pageno" : pageno,
			"filter" : self.scope.dataobj.filter,
			"search" : self.scope.dataobj.search,
			"box"    : self.scope.windowType
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		
		if (data) {
			if(data.Status == "Success"){
				
				self.scope.sentList = eval(data.Output.al);
				self.scope.serverDate = data.Current_Time;
				self.scope.currentUser.UserId = data.UserId;
				self.scope.currentUser.UserEmail = data.UserEmail;
				self.scope.currentUser.UserName = data.UserName;
				//paginationa
				self.scope.totallength = data.Output.NumberofMails;
				self.scope.numPages = Math.ceil(self.scope.totallength / self.scope.numPerPage);
				
				if(self.scope.numPages == 0)
					{
						self.scope.begin = "";
						self.scope.end = "";
						self.scope.totallength = "";
						self.scope.total = "false";
					}
				else
					{
						self.scope.total = "true";
							if(self.scope.numPages == self.scope.currentPage)
							{
								//next button disable
								if(self.scope.currentPage == 1)
								{
									self.scope.begin = 1;
									self.scope.end = self.scope.totallength;
									self.scope.previousdisable = "false";
									self.scope.nextdisable = "false";
									//next disablea
								}
								else
								{
									self.scope.begin = self.scope.end + 1;
									self.scope.end = self.scope.totallength;
									self.scope.nextdisable = "false";
									self.scope.previousdisable = "true";
								}
							}
						 
						else
							{
								if(self.scope.currentPage == 1)
								{
									
									self.scope.begin = 1;
									self.scope.end = 10;
									self.scope.nextdisable = "true";
									self.scope.previousdisable = "false";
								}
								else
									{
										self.scope.lastbeforecheck  = self.scope.numPages - 1;
										if(self.scope.currentPage == self.scope.lastbeforecheck)
											{
											
											self.scope.roundtotal = self.scope.numPages * 10;
											console.log(self.scope.roundtotal)
											self.scope.a = self.scope.roundtotal - self.scope.totallength;
											self.scope.remainder = 10 - self.scope.a; 
											
												if(self.scope.previous == 1)
												{
													
												self.scope.begin = self.scope.begin - 10;
												
												self.scope.end = self.scope.end - self.scope.remainder;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
											}
										else
											{
											
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
												if(self.scope.previous == 1)
												{
												
												self.scope.begin = self.scope.begin - 10;
												self.scope.end = self.scope.end - 10;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
											}
										
									}
								
								
							}
					}
				
			} else {
				self.scope.sentList = [];
				self.util.notify("Mail", data.Output, "failure");
			}
			if(self.scope.dataobj.search != undefined && self.scope.dataobj.search != null && self.scope.dataobj.search != " ")
			{
			if(self.scope.dataobj.search.length != 0)
				{
				self.scope.inboxsearchlength = data.inbox.NumberofMails;
				self.scope.draftsearchlength = data.draft.NumberofMails;
				self.scope.archivesearchlength = data.archive.NumberofMails;
				self.scope.trashsearchlength = data.trash.NumberofMails;
				self.scope.searchvalue = "true";
				}
			else
				{
				self.scope.searchvalue = "false";
				}
			
			}
		else
			{
			self.scope.searchvalue = "false";
			}
		} else {
			self.scope.sentList = [];
		}
		self.scope.mailList = self.scope.sentList;
		
		window.setTimeout(function(){ 
			$('.popper').popover({
			    placement: 'bottom',
			    container: 'body',
			    html: true,
			    content: function () {
			        return $(this).next('.popper-content').html();
			    }
			});
		},1000);
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.draft = function(pageno) {
	
	var self = this;
	if(self.scope.logged_in_user == "cust")
	{
	
	$("li").removeClass('custa_color');
	$("#draft").addClass('custa_color');
	}
else
	{
	
	$("li").removeClass('fpa_color');
	$("#draft").addClass('fpa_color');
	}
	self.scope.replymail=false;
	self.scope.currentpage = pageno;
	self.scope.draftList = [];
	if(self.scope.dataobj.filter == "asc")
	{
	$('.arD').removeClass('descend');
	}
else
	{
	$('.arD').addClass('descend');
	}
	this.util.block("Loading draft..");
	this.http({
		method : 'POST',
		//url:'support/contents/trash.json',
		url:'getDraftMessages.do',
		params : {
			"pageno" : pageno,
			"filter" : self.scope.dataobj.filter,
			"search" : self.scope.dataobj.search,
			"box"    : self.scope.windowType
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
			
			if(data.Status == "Success"){
				
				self.scope.draftList = eval(data.Output.al);
				self.scope.serverDate = data.Current_Time;
				
				self.scope.currentUser.UserId = data.UserId;
				self.scope.currentUser.UserEmail = data.UserEmail;
				self.scope.currentUser.UserName = data.UserName;
				//paginationa
				self.scope.totallength = data.Output.NumberofMails;
				
				self.scope.numPages = Math.ceil(self.scope.totallength / self.scope.numPerPage);
				
				if(self.scope.numPages == 0)
					{
						self.scope.begin = "";
						self.scope.end = "";
						self.scope.totallength = "";
						self.scope.total = "false";
					}
				else
					{
						self.scope.total = "true";
							if(self.scope.numPages == self.scope.currentPage)
							{
								//next button disable
								if(self.scope.currentPage == 1)
								{
									self.scope.begin = 1;
									self.scope.end = self.scope.totallength;
									self.scope.previousdisable = "false";
									self.scope.nextdisable = "false";
									//next disablea
								}
								else
								{
									self.scope.begin = self.scope.end + 1;
									self.scope.end = self.scope.totallength;
									self.scope.nextdisable = "false";
									self.scope.previousdisable = "true";
								}
							}
						 
						else
							{
								if(self.scope.currentPage == 1)
								{
									
									self.scope.begin = 1;
									self.scope.end = 10;
									self.scope.nextdisable = "true";
									self.scope.previousdisable = "false";
								}
								else
									{
										self.scope.lastbeforecheck  = self.scope.numPages - 1;
										if(self.scope.currentPage == self.scope.lastbeforecheck)
											{
											
											self.scope.roundtotal = self.scope.numPages * 10;
											
											self.scope.a = self.scope.roundtotal - self.scope.totallength;
											self.scope.remainder = 10 - self.scope.a; 
											
												if(self.scope.previous == 1)
												{
											
												self.scope.begin = self.scope.begin - 10;
											
												self.scope.end = self.scope.end - self.scope.remainder;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
											}
										else
											{
											
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
												if(self.scope.previous == 1)
												{
												
												self.scope.begin = self.scope.begin - 10;
												self.scope.end = self.scope.end - 10;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
											}
										
									}
								
								
							}
					}
			} else {
				self.scope.draftList = [];
				self.util.notify("Mail", data.Output, "failure");
			}
			if(self.scope.dataobj.search != undefined && self.scope.dataobj.search != null && self.scope.dataobj.search != " ")
			{
			if(self.scope.dataobj.search.length != 0)
				{
				self.scope.inboxsearchlength = data.inbox.NumberofMails;
				self.scope.sentsearchlength = data.sent.NumberofMails;
				self.scope.archivesearchlength = data.archive.NumberofMails;
				self.scope.trashsearchlength = data.trash.NumberofMails;
				self.scope.searchvalue = "true";
				}
			else
				{
				self.scope.searchvalue = "false";
				}
			
			}
		else
			{
			self.scope.searchvalue = "false";
			}
		} else {
			self.scope.draftList = [];
		}
		self.scope.mailList = self.scope.draftList;
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.trash = function(pageno) {
		var self = this;
		if(self.scope.logged_in_user == "cust")
		{
		
		$("li").removeClass('custa_color');
		$("#trash").addClass('custa_color');
		}
	else
		{
		
		$("li").removeClass('fpa_color');
		$("#trash").addClass('fpa_color');
		}
	self.scope.replymail=false;
	self.scope.currentPage = pageno;
	self.scope.trashList = [];
	if(self.scope.dataobj.filter == "asc")
	{
	$('.arD').removeClass('descend');
	}
else
	{
	$('.arD').addClass('descend');
	}
	this.util.block("Loading Trash..");
	this.http({
		method : 'POST',
		//url:'support/contents/trash.json',
		url:'getTrashMessages.do',
		params : {
			"pageno" : pageno,
			"filter" : self.scope.dataobj.filter,
			"search" : self.scope.dataobj.search,
			"box"    : self.scope.windowType
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
			if(data.Status == "Success"){
				
				self.scope.trashList = eval(data.Output.al);
				self.scope.serverDate = data.Current_Time;
				self.scope.currentUser.UserId = data.UserId;
				self.scope.currentUser.UserEmail = data.UserEmail;
				self.scope.currentUser.UserName = data.UserName;
				//paginationa
				self.scope.totallength = data.Output.NumberofMails;
				self.scope.numPages = Math.ceil(self.scope.totallength / self.scope.numPerPage);
				if(self.scope.numPages == 0)
					{
						self.scope.begin = "";
						self.scope.end = "";
						self.scope.totallength = "";
						self.scope.total = "false";
					}
				else
					{
						self.scope.total = "true";
							if(self.scope.numPages == self.scope.currentPage)
							{
								//next button disable
								if(self.scope.currentPage == 1)
								{
									self.scope.begin = 1;
									self.scope.end = self.scope.totallength;
									self.scope.previousdisable = "false";
									self.scope.nextdisable = "false";
									//next disablea
								}
								else
								{
									self.scope.begin = self.scope.end + 1;
									self.scope.end = self.scope.totallength;
									self.scope.nextdisable = "false";
									self.scope.previousdisable = "true";
								}
							}
						 
						else
							{
								if(self.scope.currentPage == 1)
								{
									self.scope.begin = 1;
									self.scope.end = 10;
									self.scope.nextdisable = "true";
									self.scope.previousdisable = "false";
								}
								else
									{
										self.scope.lastbeforecheck  = self.scope.numPages - 1;
										if(self.scope.currentPage == self.scope.lastbeforecheck)
											{
											self.scope.roundtotal = self.scope.numPages * 10;
											self.scope.a = self.scope.roundtotal - self.scope.totallength;
											self.scope.remainder = 10 - self.scope.a; 
												if(self.scope.previous == 1)
												{
			
												self.scope.begin = self.scope.begin - 10;
			
												self.scope.end = self.scope.end - self.scope.remainder;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
											}
										else
											{
			
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
												if(self.scope.previous == 1)
												{
												
												self.scope.begin = self.scope.begin - 10;
												self.scope.end = self.scope.end - 10;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
											}
										
									}
								
								
							}
					}
			} else {
				self.scope.trashList = [];
				
				self.util.notify("Mail", data.Output, "failure");
			}
			if(self.scope.dataobj.search != undefined && self.scope.dataobj.search != null && self.scope.dataobj.search != " ")
			{
			if(self.scope.dataobj.search.length != 0)
				{
				self.scope.inboxsearchlength = data.inbox.NumberofMails;
				self.scope.sentsearchlength = data.sent.NumberofMails;
				self.scope.archivesearchlength = data.archive.NumberofMails;
				self.scope.draftsearchlength = data.draft.NumberofMails;
				self.scope.searchvalue = "true";
				}
			else
				{
				self.scope.searchvalue = "false";
				}
			
			}
		else
			{
			self.scope.searchvalue = "false";
			}
		} else {
			self.scope.trashList = [];
		}
		self.scope.mailList = self.scope.trashList;
		window.setTimeout(function(){ 
			$('.popper').popover({
			    placement: 'bottom',
			    container: 'body',
			    html: true,
			    content: function () {
			        return $(this).next('.popper-content').html();
			    }
			});
		},1000);
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

CommunicationController.prototype.archivefrom = function(pageno) {
	var self = this;
	self.scope.dataobj.filter = "all";
	self.archive(pageno)
}

CommunicationController.prototype.trashfrom = function(pageno) {
	var self = this;
	self.scope.dataobj.filter = "all";
	self.trash(pageno)
}

CommunicationController.prototype.inboxfrom = function(pageno) {
	var self = this;
	self.scope.dataobj.filter = "all";
	self.inbox(pageno)
}

CommunicationController.prototype.sentfrom = function(pageno) {
	var self = this;
	self.scope.dataobj.filter = "all";
	self.sent(pageno)
}


CommunicationController.prototype.archive = function(pageno) {
	
	
	var self = this;
	if(self.scope.logged_in_user == "cust")
	{
	
	$("li").removeClass('custa_color');
	$("#archive").addClass('custa_color');
	}
	else
	{
	
	$("li").removeClass('fpa_color');
	$("#archive").addClass('fpa_color');
	}
	self.scope.replymail=false;
	self.scope.currentPage = pageno;
	self.scope.archiveList = [];
	if(self.scope.dataobj.filter == "asc")
	{
	$('.arD').removeClass('descend');
	}
	else
	{
	$('.arD').addClass('descend');
	}
	
	this.util.block("Loading Archives..");
	this.http({
		method : 'POST',
		url:'getArchiveMessages.do',
		params : {
			"pageno" : pageno,
			"filter" : self.scope.dataobj.filter,
			"search" : self.scope.dataobj.search,
			"box"    : self.scope.windowType
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
		
			self.scope.archiveList = eval(data.Output.al);
			self.scope.serverDate = data.Current_Time;
			self.scope.currentUser.UserId = data.UserId;
			self.scope.currentUser.UserEmail = data.UserEmail;
			self.scope.currentUser.UserName = data.UserName;
			//paginationa
			self.scope.totallength = data.Output.NumberofMails;
			self.scope.numPages = Math.ceil(self.scope.totallength / self.scope.numPerPage);
			if(self.scope.numPages == 0)
					{
						self.scope.begin = "";
						self.scope.end = "";
						self.scope.totallength = "";
						self.scope.total = "false";
					}
				else
					{
						self.scope.total = "true";
							if(self.scope.numPages == self.scope.currentPage)
							{
								//next button disable
								if(self.scope.currentPage == 1)
								{
									self.scope.begin = 1;
									self.scope.end = self.scope.totallength;
									self.scope.previousdisable = "false";
									self.scope.nextdisable = "false";
									//next disablea
								}
								else
								{
									self.scope.begin = self.scope.end + 1;
									self.scope.end = self.scope.totallength;
									self.scope.nextdisable = "false";
									self.scope.previousdisable = "true";
								}
							}
						 
						else
							{
								if(self.scope.currentPage == 1)
								{
									
									self.scope.begin = 1;
									self.scope.end = 10;
									self.scope.nextdisable = "true";
									self.scope.previousdisable = "false";
								}
								else
									{
										self.scope.lastbeforecheck  = self.scope.numPages - 1;
										if(self.scope.currentPage == self.scope.lastbeforecheck)
											{
										
											self.scope.roundtotal = self.scope.numPages * 10;
										
											self.scope.a = self.scope.roundtotal - self.scope.totallength;
											self.scope.remainder = 10 - self.scope.a; 
										
												if(self.scope.previous == 1)
												{
													
												self.scope.begin = self.scope.begin - 10;
												
												self.scope.end = self.scope.end - self.scope.remainder;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
											}
										else
											{
											
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
												if(self.scope.previous == 1)
												{
												
												self.scope.begin = self.scope.begin - 10;
												self.scope.end = self.scope.end - 10;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
											}
										
									}
								
								
							}
					}
		} else {
			self.scope.archiveList = [];
		}
		if(self.scope.dataobj.search != undefined && self.scope.dataobj.search != null && self.scope.dataobj.search != " ")
		{
		if(self.scope.dataobj.search.length != 0)
			{
			self.scope.inboxsearchlength = data.inbox.NumberofMails;
			self.scope.sentsearchlength = data.sent.NumberofMails;
			self.scope.trashsearchlength = data.trash.NumberofMails;
			self.scope.draftsearchlength = data.draft.NumberofMails;
			self.scope.searchvalue = "true";
			}
		else
			{
			self.scope.searchvalue = "false";
			}
		
		}
	else
		{
		self.scope.searchvalue = "false";
		}
		self.scope.mailList = self.scope.archiveList;
		window.setTimeout(function(){ 
			$('.popper').popover({
			    placement: 'bottom',
			    container: 'body',
			    html: true,
			    content: function () {
			        return $(this).next('.popper-content').html();
			    }
			});
		},1000);
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.moveToArchive = function() {
	
	var self = this;
	self.scope.archiveList = [];
	//this.util.block("Loading Archives..");
	$("#savemvtoarchieve").button("Loading Archives..");
	$('#savemvtoarchieve').prop('disabled', true);
	
	var ids=self.scope.selectedList.join(','); 
	
	var from=self.scope.FROM;
	this.http({
		method : 'POST',
		url:'movetoarchive.do',
		params : {
			'from':from ,
			'mailList' : ids
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
			var pgno = 1;
			if(data.Status == "Success"){
				$("#savemvtoarchieve").button('reset');
				self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Mail Archived Successfully</div>');
				
				 window.setTimeout(function() {self.scope.$apply(function(){
					 
					 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
				//self.util.notify("Info", data.reason, "success");
				self.scope.selectedList = [];
				self.scope.mailread = false;
				if(self.scope.FROM == "inbox")
					{
					self.inbox(pgno);
					}
				else if(self.scope.FROM == "sentbox")
					{
					self.sent(pgno);
					}
				else if(self.scope.FROM == "draft")
					{
					self.draft(pgno);
					}
				else if(self.scope.FROM == "archive")
					{
					self.archive(pgno);
					}
			} else {
				$("#savemvtoarchieve").button('reset');
				self.util.notify("Failure", data.Output, "failure");
			}
			//self.util.unblock();
			
		} 
		else {
			$("#savemvtoarchieve").button('reset');
			self.scope.archiveList = [];
		}
		//self.inbox(1);
		//self.todoController(self.scope.archiveList);
		//self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

CommunicationController.prototype.savetodraft = function(message) {
	var self = this;
	var newMessageJson = {
		 "msgid": message.msgid,
		 "recipient" : message.recipient,
		 "subject" : message.subject,
		 "content" : $('.jqte-test').jqteVal()
	};
	 if($(".jqte-test").jqteVal()=="")
	{
		 self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Please Enter Mail Content</div>');
			
		 window.setTimeout(function() {self.scope.$apply(function(){
			 
			 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
		return;
	}
	 $('#savedraft').button('loading');
     $('#savedraft').prop('disabled', true);
	this.http({
		method : 'POST',
		url : 'savetodraft.do',
		params : newMessageJson,
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.Result != null){
				self.scope.message.msgid = data.Result;
			} 
			if(data.Status == "Success"){
				$("#savedraft").button('reset');
				self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Mail Saved In Draft Successfully</div>');
				window.setTimeout(function() {self.scope.$apply(function(){
					 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
				self.util.notify("Info", data.reason, "success");
				window.setTimeout(function(){window.location.href="Communication.jsp?type=inbox";},2000);
			} else {
				$("#savedraft").button('reset');
				self.util.notify("Failure", data.reason, "failure");
			}
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.savetodraftfromdraft = function(message) {
	var self = this;
	var newMessageJson = {
		 "msgid": message.msgid,
		 "recipient" : message.recipient,
		 "subject" : message.subject,
		 "content" : $('.jqte-test').jqteVal()
	};
	 if($(".jqte-test").jqteVal()=="")
	{
		 self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Please Enter Mail Content</div>');
			
		 window.setTimeout(function() {self.scope.$apply(function(){
			 
			 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
		return;
	}
	 $('#savedraft').button('loading');
     $('#savedraft').prop('disabled', true);
	this.http({
		method : 'POST',
		url : 'savetodraftfromdraft.do',
		params : newMessageJson,
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.Result != null){
				self.scope.message.msgid = data.Result;
			} 
			if(data.Status == "Success"){
				$("#savedraft").button('reset');
				self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Mail Saved In Draft Successfully</div>');
				window.setTimeout(function() {self.scope.$apply(function(){
					 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
				self.util.notify("Info", data.reason, "success");
				window.setTimeout(function(){window.location.href="Communication.jsp?type=inbox";},2000);
			} else {
				$("#savedraft").button('reset');
				self.util.notify("Failure", data.reason, "failure");
			}
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.sendMail = function(message) {
	//alert("called send mail");
	var self = this;
	
	//console.log(message.MI_ID);
	var newMessageJson = {
		 "recipient" : message.recipient,
		 "subject" : message.subject,
		 "content" : $(".jqte-test").jqteVal(),
	};
	if(message.recipient==undefined||message.recipient=="-1")
	{
		self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Please Select Recipitent</div>');
		
		 window.setTimeout(function() {self.scope.$apply(function(){
			 
			 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
		return;
	}
	if(message.subject==undefined||message.subject=="")
	{
		self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Please Enter Subject</div>');
		
		 window.setTimeout(function() {self.scope.$apply(function(){
			 
			 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
		return;
	}
	else if($(".jqte-test").jqteVal()=="")
	{
		self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Please Enter Some Content</div>');
		
		 window.setTimeout(function() {self.scope.$apply(function(){
			 
			 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
		return;
	}
	//this.util.block("Sending Message");
	 $('#save').button('loading');
     $('#save').prop('disabled', true);
	this.http({
		method : 'POST',
		url : 'postMessages.do',
		//url : 'support/contents/mail_sent.json',
		params : newMessageJson,
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
		
			if(data.Status == "Success"){
				$('#save').button('reset');
				self.util.unblock();
				self.selectedmail(self.scope.draftEdit);
				self.discard();
				self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Mail Sent Successfully</div>');
				
				 window.setTimeout(function() {self.scope.$apply(function(){
					 
					 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
				window.setTimeout(function(){window.location.href="Communication.jsp?type=inbox";},2000);
				self.scope.message = [];
			} else {
				 $('#save').button('reset');
				self.util.notify("Failure", data.reason, "failure");
			}
			self.util.unblock();
		}
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};

CommunicationController.prototype.cancelSettings = function(){
	var self = this;
	self.getSettings();
	
}
CommunicationController.prototype.putSettings = function() {
	
	var self = this;
	
	var newMessageJson = {
		 "profilepic" : self.scope.settings.profilepic,
		 "archiveduration" : self.scope.settings.archieve,
		 "autorespone" : self.scope.settings.auto_response,
		 "autoresponsemessage" :self.scope.settings.auto_response_message,
		 "notification" :self.scope.settings.notification,
		 "stars" :"1"
	};
	
	//this.util.block("Sending Message");
	 $('#savesettings').button('Saving');
	 $('#savesettings').prop('disabled', true);
	this.http({
		method : 'POST',
		//url : 'sendMail.do',
		url : 'savemailsettings.do',
		params : newMessageJson,
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			
			 $('#savesettings').prop('disabled', false);
			self.getSettings();
			if(data.Result != null){
				self.scope.message.msgid = data.Result;
			} 
			if(data.Status == "Success"){
				self.util.notify("Info", data.reason, "success");
					self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Setting Saved Successfully</div>');
					
					 window.setTimeout(function() {self.scope.$apply(function(){
						 
						 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
			} else {
				self.util.notify("Failure", data.reason, "failure");
			}
			self.util.unblock();
		}
	}).error(function(data, status, headers, config) {
		 $('#savesettings').prop('disabled', false);
		self.util.unblock();
	});
};
CommunicationController.prototype.getSettings = function() {
	var self = this;
	if(self.scope.logged_in_user == "cust")
	{
	
	$("li").removeClass('custa_color');
	$("#settings").addClass('custa_color');
	}
	else
	{
	
	$("li").removeClass('fpa_color');
	$("#settings").addClass('fpa_color');
	}
	this.util.block("Getting Settings... Please Wait");
	this.http({
		method : 'GET',
		url : 'getSettings.do',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.Status == "Success"){
				
				var result = data.Output;
				self.scope.settings.profilepic = result.MS_PROFILE_PIC;
				self.scope.settings.archieve = result.MS_ARCHIVE_DURATION;
				self.scope.settings.auto_response = result.MS_AUTO_RES;
				self.scope.settings.auto_response_message = result.MS_AUTO_RES_MESS;
				self.scope.settings.notification = result.MS_NOTIFICATION;
				self.scope.settings.stars = "1";
				
				 
			} else {
				self.util.notify("Failure", data.reason, "failure");
				self.scope.settings.profilepic = "Enabled";
				self.scope.settings.archieve = "30";
				self.scope.settings.auto_response = "Disabled";
				self.scope.settings.auto_response_message = "";
				self.scope.settings.notification = "Enabled";
				self.scope.settings.stars = "1";
			}
			self.util.unblock();
		}
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.getuserslist = function() {
	var self = this;
	var newMessageJson = {
		 "usertype" : message.recipient
		
	};
	
	//this.util.block("Sending Message");
	this.http({
		method : 'POST',
		//url : 'sendMail.do',
		url : 'getuserslist.do',
		params : newMessageJson,
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			self.scope.message.msgid = mailObject.MI_ID;
			if(data.Status == "Success"){
				self.util.notify("Info", data.reason, "success");
				self.scope.userlist = data.Output;
			} else {
				self.util.notify("Failure", data.reason, "failure");
			}
			self.util.unblock();
		}
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.replyEmail = function() {
	var self = this;
	var from = "sentbox";
	if(self.type==='inbox')
	{
		from = "inbox"
	}
	else if(self.type==='sent')
	{
		from = "sentbox"
	}
	else if(self.type==='archive')
	{
		from = "archive"
	}
	var replyMessageJson = {
		"from" :from , 
		"messageid" : self.scope.message.MI_ID,
		"subject":self.scope.message.MI_SUBJECT,
		"content":$("#content").jqteVal(),
		"recipient":self.scope.message.MI_TO_ID
	};
	if($("#content").jqteVal().trim()=="")
	{
		self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Please Enter Mail Content</div>');
		
		 window.setTimeout(function() {self.scope.$apply(function(){
			 
			 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
		return;
	}
	this.util.block("Sending Message");
	this.http({
		method : 'POST',
		url : 'replyMail.do',
		params : replyMessageJson,
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.Status == "Success"){
				self.util.notify("Info", data.reason, "success");
				self.scope.replymail=false;
				//alert("Reply Mail sent..");
				self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Mail Sent Successfully</div>');
				
				 window.setTimeout(function() {self.scope.$apply(function(){
					 
					 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
				
					
				/* window.setTimeout(function() {self.scope.$apply(function(){
					 
					 })},2000);*/
				 window.location.reload();
				
			} else {
				self.util.notify("Failure", data.reason, "failure");
				//alert("Problem on sending...");
			}
			self.util.unblock();
		}
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};
CommunicationController.prototype.reply = function(mailObject) {
	var self = this;
	self.scope.replymail=true;
	self.scope.message=mailObject;
	if(self.scope.message.MI_TO_ID==self.scope.currentUser.UserId)
	{
		self.scope.message.MI_TO_ID=self.scope.message.MI_FROM_ID;
		self.scope.message.MI_FROM_ID=self.scope.currentUser.UserId;
	}
	window.setTimeout(function(){
		$('#content').jqte();
		// settings of status
		var jqteStatus = true;
		$(".status").click(function()
		{
			jqteStatus = jqteStatus ? false : true;
			$('#content').jqte({"status" : jqteStatus})
		});
		var container = $('#maildiv'),
	    scrollTo = $('#replydiv');
		
		container.animate({
		    scrollTop: scrollTo.offset().top - container.offset().top + container.scrollTop()
		});
	},500);
};
CommunicationController.prototype.selectedmail = function(mail)
{
	
	var self = this;
	if(this.type=='sent')
	{
		self.scope.FROM="sentbox";
	}
	else if(this.type=='draft')
	{
		self.scope.FROM="draft";
	}
	else if(this.type=='archive')
	{
		self.scope.FROM="archive";
	}
	else if(this.type=='trash')
	{
		self.scope.FROM="trash";
	}
	else
	{
		self.scope.FROM="inbox";
	}
	if(mail.selection)
	{
		self.scope.selectedList.push(mail.MI_ID);
	}
	else
	{
		self.scope.selectedList.splice(self.scope.selectedList.indexOf(mail.MI_ID),1);
		self.scope.checkAll = "false";
	}
	
}
CommunicationController.prototype.selectAll = function(input)
{
	var self = this;
	if(this.type=='sent')
	{
		self.scope.FROM="sentbox";
	}
	else if(this.type=='draft')
	{
		self.scope.FROM="draft";
	}
	else if(this.type=='archive')
	{
		self.scope.FROM="archive";
	}
	else if(this.type=='trash')
	{
		self.scope.FROM="trash";
	}
	else
	{
		self.scope.FROM="inbox";
	}
	if(input)
	{
		angular.forEach(self.scope.mailList, function (obj) {
	        obj.selection = true;
	        if(self.scope.selectedList.indexOf(obj.MI_ID)==-1)
	        	{
	        	self.scope.selectedList.push(obj.MI_ID);
	        	}
	        	
	        
	    });
	}
	else
	{
		angular.forEach(self.scope.mailList, function (obj) {
	        obj.selection = false;
	    });
		self.scope.selectedList=[];
	}
	
}
//discard
CommunicationController.prototype.discard = function() {
	var self = this;
	var ids=self.scope.selectedList.join(','); 
	self.scope.replymail=false;
	self.scope.mailread = false;
	//this.util.block("Deleting Mail");
	this.http({
		method : 'POST',
		//url : 'support/contents/mail_delete.json',
		url:'discardmail.do',
		params : {
			'mailList' : ids
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.Status == "Success"){
				//self.util.notify("Info", data.reason, "success");
				
				self.trash(1);
			} else {
				self.util.notify("Failure", data.Output, "failure");
			}
			//self.util.unblock();
		}
	}).error(function(data, status, headers, config) {
		//self.util.unblock();
	});
};


// movetoarchiveicon

CommunicationController.prototype.moveToArchiveicon = function(obj)
{
	var self = this;
	self.scope.selectedList = [];
	if(this.type=='sent')
	{
		self.scope.FROM="sentbox";
	}
	else if(this.type=='draft')
	{
		self.scope.FROM="draft";
	}
	else if(this.type=='archive')
	{
		self.scope.FROM="archive";
	}
	else if(this.type=='trash')
	{
		self.scope.FROM="trash";
	}
	else
	{
		self.scope.FROM="inbox";
	}
	self.scope.selectedList.push(obj.MI_ID);
	self.moveToArchive();
	
}

//movetotrash icon
CommunicationController.prototype.deleteMailicon = function(obj)
{
	var self = this;
	self.scope.selectedList = [];
	if(this.type=='sent')
	{
		self.scope.FROM="sentbox";
	}
	else if(this.type=='draft')
	{
		self.scope.FROM="draft";
	}
	else if(this.type=='archive')
	{
		self.scope.FROM="archive";
	}
	else if(this.type=='trash')
	{
		self.scope.FROM="trash";
	}
	else
	{
		self.scope.FROM="inbox";
	}
	self.scope.selectedList.push(obj.MI_ID);
	//self.moveToArchive();
	self.deleteMail();
	
}
//delete mail
CommunicationController.prototype.deleteMail = function() {
	
	var self = this;
	var ids=self.scope.selectedList.join(','); 
	
	var from=self.scope.FROM;
	//this.util.block("Deleting Mail");
	$("#savemvtotrash").button("Deleting Mail");
	$('#savemvtotrash').prop('disabled', true);
	this.http({
		method : 'POST',
		//url : 'support/contents/mail_delete.json',
		url:'movetotrash.do',
		params : {
			'from':from ,
			'mailList' : ids
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		
		if(data){
			self.scope.mailread = false;
			var pgno = 1;
			if(data.Status == "Success"){
				$("#savemvtotrash").button('reset');
				self.scope.alert_message=self.sce.trustAsHtml('<div class="alert alert-success fade in">Mail Trashed Successfully</div>');
				
				 window.setTimeout(function() {self.scope.$apply(function(){
					 
					 self.scope.alert_message=self.sce.trustAsHtml('<div></div>');})},1500);
				//self.util.notify("Info", data.reason, "success");
				self.scope.selectedList = [];
				if(self.scope.FROM == "inbox")
					{
					self.inbox(pgno);
					}
				else if(self.scope.FROM == "sentbox")
					{
					self.sent(pgno);
					}
				else if(self.scope.FROM == "draft")
					{
					self.draft(pgno);
					}
				else if(self.scope.FROM == "archive")
					{
					self.archive(pgno);
					}
			} else {
				$("#savemvtotrash").button('reset');
				self.util.notify("Failure", data.Output, "failure");
			}
			//self.util.unblock();
		}
	}).error(function(data, status, headers, config) {
		
		//self.util.unblock();
	});
};

//undo from trash
CommunicationController.prototype.undo = function() {
	
	var self = this;
	var ids=self.scope.selectedList.join(','); 
	
	var from=self.scope.FROM;
	this.http({
		method : 'POST',
		//url : 'support/contents/mail_delete.json',
		url:'undofromtrash.do',
		params : {
			'from':from ,
			'mailList' : ids
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		
		if(data){
			self.scope.mailread = false;
			var pgno = 1;
			if(data.Status == "Success"){
				$("#savemvtotrash").button('reset');
				//self.util.notify("Info", data.reason, "success");
				self.scope.selectedList = [];
				if(self.scope.FROM == "inbox")
					{
					self.inbox(pgno);
					}
				else if(self.scope.FROM == "sentbox")
					{
					self.sent(pgno);
					}
				else if(self.scope.FROM == "draft")
					{
					self.draft(pgno);
					}
				else if(self.scope.FROM == "archive")
					{
					self.archive(pgno);
					}
				else if(self.scope.FROM == "trash")
				{
					self.trash(pgno);
				}
			} else {
				$("#savemvtotrash").button('reset');
				self.util.notify("Failure", data.Output, "failure");
			}
			//self.util.unblock();
		}
	}).error(function(data, status, headers, config) {
		
		//self.util.unblock();
	});
};

//Refresh button concept 
CommunicationController.prototype.pagerefresh = function()
{
	//calculating the page number
	var self = this;
	self.scope.checkAll = "false";
	var pgno = 1;
	if(self.scope.windowType=="inbox")
	   {
		self.inbox(pgno);
	   }
	else if(self.scope.windowType=="sent")
		{
		self.sent(pgno);
		}
	else if(self.scope.windowType=="draft")
		{
		self.draft(pgno);
		}
	else if(self.scope.windowType=="archive")
		{
		self.archive(pgno);
		}
	else
		{
		self.trash(pgno);
		}
}
CommunicationController.prototype.compose = function() {
	var self = this
	

	if(self.scope.windowType == "compose")
		{
			if(self.scope.logged_in_user == "cust")
			{
			
			$("li").removeClass('custa_color');
			$("#compose").addClass('custa_color');
			}
		else
			{
			
			$("li").removeClass('fpa_color');
			$("#compose").addClass('fpa_color');
			}
		}
	;
	self.scope.usersList = [];
	this.util.block("Loading Users..");
	this.http({
		method : 'GET',
		url:'getChatUserList.do',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
			//
			self.scope.usersList = eval(data);
			
			
		} else {
			self.scope.usersList = [];
		}
		//self.todoController(self.scope.archiveList);
		angular.forEach(data, function(value, key) {
			self.scope.usersListbyID[value.loginId] = value
		});
		
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
	self.scope.message.recipient = "-1";
	self.scope.message.msgid = "-1";
	
};

CommunicationController.prototype.unreadmail = function()
{
	var self = this;
	self.scope.dataobj.filter = "unread";
	self.scope.dataobj.search = null;
	var pageno = 1;
	if(self.scope.windowType=="inbox")
	   {
		self.inbox(pageno);
	   }
	else if(self.scope.windowType=="sent")
		{
		self.sent(pageno);
		}
	else if(self.scope.windowType=="draft")
		{
		self.draft(pageno);
		}
	else if(self.scope.windowType=="archive")
		{
		self.archive(pageno);
		}
	else if(self.scope.windowType=="trash")
		{
		self.trash(pageno);
		}
}
//starred 
CommunicationController.prototype.starredmail = function()
{
	
	var self = this;
	self.scope.dataobj.filter = "star";
	self.scope.dataobj.search = null;
	var pageno = 1;
	if(self.scope.windowType=="inbox")
	   {
		self.inbox(pageno);
	   }
	else if(self.scope.windowType=="sent")
		{
		self.sent(pageno);
		}
	else if(self.scope.windowType=="draft")
		{
		self.draft(pageno);
		}
	else if(self.scope.windowType=="archive")
		{
		self.archive(pageno);
		}
	else if(self.scope.windowType=="trash")
		{
		self.trash(pageno);
		}
}

//all 
CommunicationController.prototype.allmail = function()
{
	var self = this;
	self.scope.dataobj.filter = "all";
	self.scope.dataobj.search = null;
	var pageno = 1;
	if(self.scope.windowType=="inbox")
	   {
		self.inbox(pageno);
	   }
	else if(self.scope.windowType=="sent")
		{
		self.sent(pageno);
		}
	else if(self.scope.windowType=="draft")
		{
		self.draft(pageno);
		}
	else if(self.scope.windowType=="archive")
		{
		self.archive(pageno);
		}
	else
		{
		self.trash(pageno);
		}
}

//get all tips
CommunicationController.prototype.gettips = function() {
	var self = this;
	this.http({
		method : 'GET',
		url : 'gettips.do',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.Status == "Success"){
			
				self.scope.tipslist = data.Result.al;
				
			} else {
				
			}
		}
	}).error(function(data, status, headers, config) {
		
	});
};

CommunicationController.prototype.setsession = function(val)
{
    if (typeof(Storage) !== "undefined")
    {
        localStorage.setItem("kbc", JSON.stringify(val));
    }
}
//get session
CommunicationController.prototype.getsession = function()
{
	var self= this;
	 $.ajax({
	        type: "GET",
	        url: "getuserdetails.do",
	        async:true,
	        success: function (data) {
	            	var names = JSON.parse(data);
	            	self.scope.first_name = names.first_name;
		        	self.scope.last_name = names.last_name;
		        	if(names.user_type == "1" || names.user_type == "2")
		        		{
		        		self.scope.logged_in_user = "cust";
		        		}
		        	else
		        		{
		        		self.scope.logged_in_user = "fp";
		        		}
		        	console.log(self.scope.logged_in_user)
		        	
		        	 var pageno =1;
			 		   var text = localStorage.getItem("kbc");
		               if (text != null)
		                {
		                	self.scope.dataobj.search = JSON.parse(text);
		                	if(self.scope.windowType=="inbox")
		         		   {
		         			self.inbox(pageno);
		         		   }
		         		else if(self.scope.windowType=="sent")
		         			{
		         			self.sent(pageno);
		         			}
		         		else if(self.scope.windowType=="draft")
		         			{
		         			self.draft(pageno);
		         			}
		         		else if(self.scope.windowType=="archive")
		         			{
		         			self.archive(pageno);
		         			}
		         		else if(self.scope.windowType=="trash")
		         			{
		         			self.trash(pageno);
		         			}
		         		else if(self.scope.windowType=="notification")
		         			{
		         			self.notification(pageno);
		         			}
		         		else if(self.scope.windowType=='compose')
		         			{
		         			self.compose();
		         			}
		         			
		                }

		    
			    else
				{
			    	
		             	if(self.scope.windowType=="inbox")
		      		   {
		      			self.inbox(pageno);
		      		   }
		      		else if(self.scope.windowType=="sent")
		      			{
		      			self.sent(pageno);
		      			}
		      		else if(self.scope.windowType=="draft")
		      			{
		      			self.draft(pageno);
		      			}
		      		else if(self.scope.windowType=="archive")
		      			{
		      			self.archive(pageno);
		      			}
		      		else if(self.scope.windowType=="trash")
		 			{
		 			self.trash(pageno);
		 			}
		      		else if(self.scope.windowType=="notification")
         			{
         			self.notification(pageno);
         			}
		      		else if(self.scope.windowType=='compose')
         			{
         			self.compose();
         			}
         			
				}
		               
		    },
	        error: function (textStatus, errorThrown) { 
	           // Success = false;//doesnt goes here
	        }
	    });
    
	 		  
             
          
          
   }

//remove session
CommunicationController.prototype.removesession = function()
{
    localStorage.removeItem("kbc");
}

//suggested search 
 CommunicationController.prototype.suggestfolder = function(foldervalue)
 {
	 var self = this;
	 self.setsession(self.scope.dataobj.search)
		if(foldervalue=="Inbox")
		   {
			window.setTimeout(function(){window.location.href="Communication.jsp?type=inbox";},100);
			}
		else if(foldervalue=="Sent")
			{
			window.setTimeout(function(){window.location.href="Communication.jsp?type=sent";},100);
			}
		else if(foldervalue=="Draft")
			{
			window.setTimeout(function(){window.location.href="Communication.jsp?type=draft";},100);
			}
		else if(foldervalue=="Archive")
			{
			window.setTimeout(function(){window.location.href="Communication.jsp?type=archive";},100);
			}
		else if(foldervalue=="Trash")
			{
			window.setTimeout(function(){window.location.href="Communication.jsp?type=trash";},100);
			}
 }
//search
CommunicationController.prototype.searchmail = function()
{
	var self = this;
	
	var pageno = 1;
	self.removesession()
	if(self.scope.dataobj.filter == undefined || self.scope.dataobj.filter == null)
		{
			self.scope.dataobj.filter = "all";
		}
	
	if(self.scope.dataobj.search == undefined || self.scope.dataobj.search == null)
	{
		alert("Please enter the search content");
	}
	else
		{
		if(self.scope.windowType=="inbox")
		   {
			self.inbox(pageno);
		   }
		else if(self.scope.windowType=="sent")
			{
			self.sent(pageno);
			}
		else if(self.scope.windowType=="draft")
			{
			self.draft(pageno);
			}
		else if(self.scope.windowType=="archive")
			{
			self.archive(pageno);
			}
		else
			{
			self.trash(pageno);
			}
		}
	
}

//cancl operation
CommunicationController.prototype.cancel = function()
{
	window.setTimeout(function(){window.location.href="Communication.jsp?type=inbox";},1000);
}


//date sortr
CommunicationController.prototype.datefunc = function()
{
	var self = this;
	var pageno = 1;
	if(self.scope.datecheck == 1)
		{
			self.scope.dataobj.filter = "desc";
			self.scope.datecheck--;
		}
	else{
			self.scope.dataobj.filter = "asc";
			self.scope.datecheck++;
		}
	
		if(self.scope.windowType=="inbox")
		   {
			self.inbox(pageno);
		   }
		else if(self.scope.windowType=="sent")
			{
			self.sent(pageno);
			}
		else if(self.scope.windowType=="draft")
			{
			self.draft(pageno);
			}
		else if(self.scope.windowType=="archive")
			{
			self.archive(pageno);
			}
		else
			{
			self.trash(pageno);
			}
		
	
}

//sort ascending
CommunicationController.prototype.ascending = function()
{
	var self = this;
	
	var pageno = 1;
	
			self.scope.dataobj.filter = "asc";
		
		if(self.scope.windowType=="inbox")
		   {
			self.inbox(pageno);
		   }
		else if(self.scope.windowType=="sent")
			{
			self.sent(pageno);
			}
		else if(self.scope.windowType=="draft")
			{
			self.draft(pageno);
			}
		else if(self.scope.windowType=="archive")
			{
			self.archive(pageno);
			}
		else
			{
			self.trash(pageno);
			}
		
	
}

// sort desecnding
CommunicationController.prototype.descending = function()
{
	var self = this;
	
	var pageno = 1;
	
					self.scope.dataobj.filter = "desc";

		if(self.scope.windowType=="inbox")
		   {
			self.inbox(pageno);
		   }
		else if(self.scope.windowType=="sent")
			{
			self.sent(pageno);
			}
		else if(self.scope.windowType=="draft")
			{
			self.draft(pageno);
			}
		else if(self.scope.windowType=="archive")
			{
			self.archive(pageno);
			}
		else
			{
			self.trash(pageno);
			}
		
	
}


//notification
CommunicationController.prototype.notification = function(pageno)
{

	var self = this;
	if(self.scope.logged_in_user == "cust")
		{
		
		$("li").removeClass('custa_color');
		$("#notification").addClass('custa_color');
		}
	else
		{
		
		$("li").removeClass('fpa_color');
		$("#notification").addClass('fpa_color');
		}
		self.scope.currentPage = pageno;
	self.scope.notificationlist = [];
	if(self.scope.dataobj.filter == "asc")
	{
	$('.arD').removeClass('descend');
	}
else
	{
	$('.arD').addClass('descend');
	}
	this.util.block("Loading Notifications..");
	this.http({
		method : 'POST',
		url:'getNotificationMessages.do',
		params : {
			"pageno" : pageno,
			"filter" : self.scope.dataobj.filter,
			"search" : self.scope.dataobj.search,
			"box"    : self.scope.windowType
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
		
			self.scope.notificationlist = eval(data.Output.al);
			self.scope.serverDate = data.Current_Time;
			self.scope.currentUser.UserId = data.UserId;
			self.scope.currentUser.UserEmail = data.UserEmail;
			self.scope.currentUser.UserName = data.UserName;
			//paginationa
			self.scope.totallength = data.Output.NumberofMails;
			
			self.scope.numPages = Math.ceil(self.scope.totallength / self.scope.numPerPage);
			
			if(self.scope.numPages == 0)
					{
						self.scope.begin = "";
						self.scope.end = "";
						self.scope.totallength = "";
						self.scope.total = "false";
					}
				else
					{
						self.scope.total = "true";
							if(self.scope.numPages == self.scope.currentPage)
							{
								//next button disable
								if(self.scope.currentPage == 1)
								{
									self.scope.begin = 1;
									self.scope.end = self.scope.totallength;
									self.scope.previousdisable = "false";
									self.scope.nextdisable = "false";
									//next disablea
								}
								else
								{
									self.scope.begin = self.scope.end + 1;
									self.scope.end = self.scope.totallength;
									self.scope.nextdisable = "false";
									self.scope.previousdisable = "true";
								}
							}
						 
						else
							{
								if(self.scope.currentPage == 1)
								{
									
									self.scope.begin = 1;
									self.scope.end = 10;
									self.scope.nextdisable = "true";
									self.scope.previousdisable = "false";
								}
								else
									{
										self.scope.lastbeforecheck  = self.scope.numPages - 1;
										if(self.scope.currentPage == self.scope.lastbeforecheck)
											{
											
											self.scope.roundtotal = self.scope.numPages * 10;
											
											self.scope.a = self.scope.roundtotal - self.scope.totallength;
											self.scope.remainder = 10 - self.scope.a; 
											
												if(self.scope.previous == 1)
												{
													
												self.scope.begin = self.scope.begin - 10;
												
												self.scope.end = self.scope.end - self.scope.remainder;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
											}
										else
											{
											
												if(self.scope.next == 1)
												{
													
													self.scope.begin = self.scope.end + 1;
													self.scope.end = self.scope.end + 10;
													self.scope.previousdisable = "true";
													self.scope.nextdisable = "true";
												}
												if(self.scope.previous == 1)
												{
												
												self.scope.begin = self.scope.begin - 10;
												self.scope.end = self.scope.end - 10;
												self.scope.previousdisable = "true";
												self.scope.nextdisable = "true";
												}
											}
										
									}
								
								
							}
					}
		} else {
			self.scope.notificationlist = [];
		}
		if(self.scope.dataobj.search != undefined && self.scope.dataobj.search != null && self.scope.dataobj.search != " ")
		{
		if(self.scope.dataobj.search.length != 0)
			{
			self.scope.inboxsearchlength = data.inbox.NumberofMails;
			self.scope.sentsearchlength = data.sent.NumberofMails;
			self.scope.trashsearchlength = data.trash.NumberofMails;
			self.scope.draftsearchlength = data.draft.NumberofMails;
			self.scope.searchvalue = "true";
			}
		else
			{
			self.scope.searchvalue = "false";
			}
		
		}
	else
		{
		self.scope.searchvalue = "false";
		}
		self.scope.mailList = self.scope.notificationlist;
		window.setTimeout(function(){ 
			$('.popper').popover({
			    placement: 'bottom',
			    container: 'body',
			    html: true,
			    content: function () {
			        return $(this).next('.popper-content').html();
			    }
			});
		},1000);
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
}


//chat controller




"use strict";

var author = null;
var authorid=null;
var customrooms;
var authorsenderid;


CommunicationController.prototype.createchatroom = function(partner_user,index) {
	var self = this;
	var pingeduser = partner_user.loginId;
	var flag = 0;
	  var disable_partner_id = '#'+partner_user.loginId;
	    $(disable_partner_id).prop('disabled', true);
	    //checking the status of partner user
	    /*if(partner_user.status == "offline" || partner_user.status == "do not disturb")
	    	{
	    		//mail ppup should open
	    		window.location.href="Communication.jsp?type=compose";
	    	}
	    else
	    	{*/
	    		//chat available
			    	$.ajax({
			            type: "POST",
			            url: "generatechatrooms.do",
			            dataType: "json",
			            data:{pingeduser:pingeduser,to_user:partner_user.firstName},
			            async:true,
			            success: function (data) {
			            	
			            	customrooms  = data.Result;
			                authorsenderid=data.Author;
			                author=data.Authorname;
			                $(disable_partner_id).button('reset');
			        	    //creating object for popup data
			                	var obj = {};
			                	var author_name = author_name;
			                	var author_id = author_id;
			                	var chat_room = chat_room;
			                	var benificiary_name = benificiary_name;
			                	obj.author_name = data.Authorname;
			                	obj.author_id = data.Author;
			                	obj.benificiary_name = partner_user.firstName;
			                	obj.chat_room = data.Result;
			            		
			            	    if(!customrooms==""){
			            			connected = true;
			            			
			            			if(self.scope.connectedRooms.length == 0)
			            				{
			            					flag = 1;
			            				}
			            			else
			            				{
			            				for(i=0;i<self.scope.connectedRooms.length;i++)
			            				{
			            					if(customrooms != self.scope.connectedRooms[i].chat_room)
			            						{
			            							flag = 1;
			            						}
			            					else
			            						{
			            							flag =0;
			            							break;
			            						}
			            				}
			            				}
			            			
			            			if(flag == 1)
			            				{
			            				self.scope.connectedRooms.push(obj)
			            				activeconnectedarray = self.scope.connectedRooms;
			            				self.scope.active_connectedrooms ="true";
			            				var active_room = active_room;
			            				partner_user.active_room = customrooms;
			            				self.scope.activeconnections.push(partner_user);
			            				activeonlineuserarray = self.scope.activeconnections;
			            				self.scope.onlineusers.splice(index, 1);
			            				////self.connect(customrooms);
			            				}
			            	}
			            	    self.minmax(customrooms);
			            },
			            error: function (textStatus, errorThrown) {
			               // Success = false;//doesnt goes here
			            }
			        });
	    		
	    	//}
	
	
};
var checkwithroom;
var checkwithname;
var authorid0;
var onlineusersarray;
var onlineauthor_name;
var activeonlineuserarray;
var activeconnectedarray;
CommunicationController.prototype.getchatroomsfunc = function() {
	
	var self = this;
	$.ajax({
        type: "POST",
        url: "getchatrooms.do",
        dataType: "json",
        async:true, 
         success: function (data) { 
        	 console.log("chatrooms and their data from database")
        	 console.log(data)
        	 authorsenderid = data.Author;
        	 self.scope.authorsendername = data.Authorname;
        	 onlineauthor_name=data.Authorname;
             //$scope.apply to updated in browsera
        	     setTimeout(function () {
			        self.scope.$apply(function () {
			           // $scope.message = "Timeout called!";
			        	console.log("$scope.apply function called so alrightitsok");
			            self.scope.onlineusers = data.Online;
			            console.log(self.scope.onlineusers)
			            onlineusersarray = self.scope.onlineusers;
			            console.log("chat rooms from db");
			        	 console.log(data.Result);
			            if(data.Result.length != 0)
			        		{
			        		for (var i=0;i<data.Result.length;i++)
			            	{
			            		customrooms = data.Result[i];
			            		console.log("customrooms");
			            		console.log(customrooms);
			            		console.log("connectedrooms[]",self.scope.connectedRooms.length);
			            		if(self.scope.connectedRooms.length == 0)
			            			{
			        				if(customrooms.ROOM_NAME!="")
			        				{
			        					var obj = {};
					                	var author_name = author_name;
					                	var author_id = author_id;
					                	var chat_room = chat_room;
					                	var benificiary_name = benificiary_name;
					                	obj.author_name = data.Authorname;
					                	obj.author_id = data.Author;
					                	obj.chat_room = customrooms.ROOM_NAME;
					                	console.log("author name");
					                	console.log(data.Authorname);
					                	
					                	if(data.Authorname == customrooms.FROM_USER)
				                		{
					                		var partner_user1 = {};
					                		console.log("if if executed");
									 		obj.benificiary_name = customrooms.TO_USER;
									 		self.scope.connectedRooms.push(obj)
									 		activeconnectedarray = self.scope.connectedRooms;
									 		self.minmax(customrooms.ROOM_NAME);
									 		//creating active online users from actual online users
									 		onlineinnerloop:
									 		for(var z=0;z<self.scope.onlineusers.length;z++)
			            					{
			            						if(self.scope.onlineusers[z].firstName == customrooms.TO_USER)
			            							{
			            									console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			            								 partner_user1 = self.scope.onlineusers[z];
			            								// self.scope.onlineusers.splice(self.scope.onlineusers[z], 1);
			            						        break onlineinnerloop; 
			            							}
			            					}
									 		//activeonline uses show
									 		self.scope.active_connectedrooms ="true";
				            				var active_room = active_room;
				            				console.log("active rooms called ");
				            				console.log(active_room);
				            				//created obj with finaliziled value
				            				partner_user1.active_room = customrooms.ROOM_NAME;
				            				//pushed into active array
				            				self.scope.activeconnections.push(partner_user1);
				            				activeonlineuserarray = self.scope.activeconnections;
				            				//self.connect(customrooms.ROOM_NAME);
				                		}
					                	else
				                		{
					                		var partner_user1 = {};
				                			console.log("else called np worries a");
				                			obj.benificiary_name = customrooms.FROM_USER;
									 		//author=data.Authorname;
									        self.scope.connectedRooms.push(obj)
									        activeconnectedarray = self.scope.connectedRooms;
									        self.minmax(customrooms.ROOM_NAME);
									      //creating active online users from actual online users
									        onlineinnerloop:
										 		for(var z=0;z<self.scope.onlineusers.length;z++)
				            					{
				            						if(self.scope.onlineusers[z].firstName == customrooms.FROM_USER)
				            							{
				            									console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				            								 partner_user1 = self.scope.onlineusers[z];
				            								// self.scope.onlineusers.splice(self.scope.onlineusers[z], 1);
				            						        break onlineinnerloop; 
				            							}
				            					}
									 		//activeonline uses show
									 		self.scope.active_connectedrooms ="true";
				            				var active_room = active_room;
				            				console.log("active rooms called ");
				            				console.log(active_room);
				            				//created obj with finaliziled value
				            				partner_user1.active_room = customrooms.ROOM_NAME;
				            				//pushed into active array
				            				self.scope.activeconnections.push(partner_user1);
				            				activeonlineuserarray = self.scope.activeconnections;
				            				//self.connect(customrooms.ROOM_NAME);
						        		}
					                	}
			        			}
			            		else{
			            				innerloop:
			            				for (var j=0;j<self.scope.connectedRooms.length;j++)
				                    	{
			            					  if(self.scope.connectedRooms[j]!=customrooms.ROOM_NAME){
			            						  if(customrooms.ROOM_NAME!=""){
			          							 	var obj = {};
			           			                	var author_name = author_name;
			           			                	var author_id = author_id;
			           			                	var chat_room = chat_room;
			           			                	var benificiary_name = benificiary_name;
			           			                	obj.author_name = data.Authorname;
			           			                	obj.author_id = data.Author;
			           			                	obj.chat_room = customrooms.ROOM_NAME;
			           			                	console.log("author name");
			           			                	console.log(data.Authorname);
			           			                	if(data.Authorname == customrooms.FROM_USER)
			           			                		{
			           			                		var partner_user1 = {};
			  	         			                		console.log("if if executed");
			  	        							 		obj.benificiary_name = customrooms.TO_USER;
			  	        							 		self.scope.connectedRooms.push(obj)
			  	        							 		activeconnectedarray = self.scope.connectedRooms;
			  	        							 		self.minmax(customrooms.ROOM_NAME);
			  	        							 		console.log(customrooms.ROOM_NAME);
			  	        							 		
			  	        							 		//creating active online users from actual online users
			  	        							 		onlineinnerloop:
			  	        							 		for(var z=0;z<self.scope.onlineusers.length;z++)
			  	        	            					{
			  	        							 			console.log(self.scope.onlineusers[z].firstName);
			  	        							 			if(self.scope.onlineusers[z].firstName == customrooms.TO_USER)
			  	        	            							{
			  	        							 					console.log("cccccccccccccccccccccccccccccccccccccccccccccccccccc");
			  	        	            								 partner_user1 = self.scope.onlineusers[z];
			  	        	            								// self.scope.onlineusers.splice(self.scope.onlineusers[z], 1);
			  	        	            								break onlineinnerloop; 
			  	        	            							}
			  	        							 			else
			  	        							 				{
			  	        							 					console.log("else called in the function");
			  	        							 					console.log(self.scope.onlineusers[z].firstName);
			  	        							 					console.log(customrooms.TO_USER);
			  	        							 					
			  	        							 				}
			  	        	            					}
			  	        							 		//activeonline uses show
			  	        							 		self.scope.active_connectedrooms ="true";
			  	        		            				var active_room = active_room;
			  	        		            				console.log("active rooms called ");
			  	        		            				console.log(active_room);
			  	        		            				//created obj with finaliziled value
			  	        		            				partner_user1.active_room = customrooms.ROOM_NAME;
			  	        		            				//pushed into active array
			  	        		            				self.scope.activeconnections.push(partner_user1);
			  	        		            				activeonlineuserarray = self.scope.activeconnections;
			  	        		            				//self.connect(customrooms.ROOM_NAME);
			  		         			        		}
			           			                	else
			           			                		{
			           			                		var partner_user1 = {};
			           			                			obj.benificiary_name = customrooms.FROM_USER;
			  	        							 		//author=data.Authorname;
			  		         						        self.scope.connectedRooms.push(obj)
			  		         						        activeconnectedarray = self.scope.connectedRooms;
			  		         						     self.minmax(customrooms.ROOM_NAME);
			  		         						//creating active online users from actual online users
			  		   						 		onlineinnerloop:
			  		   						 		for(var z=0;z<self.scope.onlineusers.length;z++)
			  		               					{
			  		   						 		if(self.scope.onlineusers[z].firstName == customrooms.FROM_USER)
			  		               							{
			  		               						console.log("ddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
			  		               								 partner_user1 = self.scope.onlineusers[z];
			  		               								 break onlineinnerloop; 
			  		               							}
			  		               					}
			  		   						 		//activeonline uses show
			  		   						 		self.scope.active_connectedrooms ="true";
			  		   	            				var active_room = active_room;
			  		   	            				console.log("active rooms called ");
			  		   	            				console.log(active_room);
			  		   	            				//created obj with finaliziled value
			  		   	            				partner_user1.active_room = customrooms.ROOM_NAME;
			  		   	            				//pushed into active array
			  		   	            				self.scope.activeconnections.push(partner_user1);
			  		   	            				activeonlineuserarray = self.scope.activeconnections;
			  		   	            				//self.connect(customrooms.ROOM_NAME);
					         			        		}
			            						  }
				            	    		  }
				            	    		  else
			            	    			  {
				            	    			  console.log("no new connection");
			            	    			  }
			            					  break innerloop; 
				                    	}
				            	}
			            	}
			        		
			        		//window.setTimeout(function(){
			        			var temparray = []
			        			var temp_cht_flag = 0;
			        		console.log(self.scope.onlineusers)
						 		for(var z=0;z<self.scope.onlineusers.length;z++)
            					{	console.log(self.scope.onlineusers.length)
						 			console.log(z);
            					console.log(self.scope.onlineusers[z])
            					console.log(self.scope.onlineusers[z].firstName)
						 			onlineinnerloopdelete:
						 			for(var x=0;x<=self.scope.activeconnections.length;x++)
					 				{
						 				if(self.scope.onlineusers[z].firstName == self.scope.activeconnections[x].firstName)
            							{
						 					console.log("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            								//self.scope.onlineusers.splice(self.scope.onlineusers[z], 1);
            								temp_cht_flag = 1;
            						        break onlineinnerloopdelete; 
            							}
						 				else
						 					{
						 					temp_cht_flag = 0;
						 					}
					 				}
					        		if(temp_cht_flag == 0)
					        		{
					        			temparray.push(self.scope.onlineusers[z])
					        			console.log(temparray)
					        		}
						 		
            					}
			        		console.log(temparray)
			        			if(temparray.length == 0)
			        				{
			        				self.scope.onlineusers = [];
			        				}
			        			else
			        				{
			        				self.scope.onlineusers = temparray;
			        				}

			        			
			        			
			        		//},1000);
			        		/*console.log("aravind rooms")
			        		console.log(self.scope.connectedRooms.length)
			        		console.log(self.scope.connectedRooms)
			        		console.log("online users");
			        		console.log(self.scope.onlineusers.length)
			        		console.log(self.scope.onlineusers)
			        		console.log("actiave connections roomsa");
			        		console.log(self.scope.activeconnections.length)
			        		console.log(self.scope.activeconnections)*/
			        		
			        		//adding message in the chat pop up boxa
			        		window.setTimeout(function(){
			        		for(b=0;b<data.Result.length;b++)
			        			{
			        			customrooms = data.Result[b];
			        			for(var a=0;a<customrooms.CHAT_MESSAGES.length;a++)
			               		{
			                   			var objects = customrooms.CHAT_MESSAGES[a];
			                   			if(objects.SENDER == authorsenderid)
			    	  			        	 {
			    	  			        	 	color = "blue";
			    	  			        	 }
			                   			else
			    	  			        	 {
			    	  			        	 	color = "black";
			    	  			        	 }
			                   			 var datetime = new Date();
			                   			  //addMessage(objects.MESSAGE,color,customrooms.ROOM_NAME,d)
			                   			var chatdisplayid = '#'+customrooms.ROOM_NAME+ ' ul';
			                   			if(color=='blue'){
			                   		    	$(chatdisplayid).append("<li class=\"leftchat\"><div class=\"timestamp timestamp_l\">"+(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
			                   		                + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())+"</div><div class=\"chatbox\">"+objects.MESSAGE+"</div><div class=\"timestamp timestamp_l\" style=\"right:3em\"></div><span class=\"chat_image chat_image_left\"><img src=\"imgs/profilepicleft.png\" /></span></li>");
			                   			
			                   			}else{
			                   				$(chatdisplayid).append("<li class=\"rightchat\"><div class=\"timestamp timestamp_r\">"+(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
			                   		                + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())+"</div><div class=\"chatbox\">"+objects.MESSAGE+"</div><span class=\"chat_image chat_image_right\"><img src=\"imgs/profilepicleft.png\" /></span></li>");
			                   		    }
			               		}
			        			}
			        		
			        		},1000);
			        	
			        		}
			        });
			    }, 100);
        	
            
         },
        error: function (textStatus, errorThrown) { 
           // Success = false;//doesnt goes here
        }
    });
	
	
	
    
};

window.setInterval(function(){/*
	
	var self = this;
	$.ajax({
        type: "POST",
        url: "getchatrooms.do",
        dataType: "json",
        async:true, 
         success: function (data) { 
        	 console.log("chatrooms and their data from database")
        	 console.log(data)
        	 authorsenderid = data.Author;
        	 self.scope.authorsendername = data.Authorname;
        	 onlineauthor_name=data.Authorname;
             //$scope.apply to updated in browsera
        	     setTimeout(function () {
			        self.scope.$apply(function () {
			           // $scope.message = "Timeout called!";
			        	console.log("$scope.apply function called so alrightitsok");
			            self.scope.onlineusers = data.Online;
			            onlineusersarray = data.Online;
			        });
			    }, 100);
        	 console.log("chat rooms from db");
        	 console.log(data.Result);
            if(data.Result.length != 0)
        		{
        		for (var i=0;i<data.Result.length;i++)
            	{
            		customrooms = data.Result[i];
            		console.log("customrooms");
            		console.log(customrooms);
            		console.log("connectedrooms[]",self.scope.connectedRooms.length);
            		if(self.scope.connectedRooms.length == 0)
            			{
        				if(customrooms.ROOM_NAME!="")
        				{
        					var obj = {};
		                	var author_name = author_name;
		                	var author_id = author_id;
		                	var chat_room = chat_room;
		                	var benificiary_name = benificiary_name;
		                	obj.author_name = data.Authorname;
		                	obj.author_id = data.Author;
		                	obj.chat_room = customrooms.ROOM_NAME;
		                	console.log("author name");
		                	console.log(data.Authorname);
		                	
		                	if(data.Authorname == customrooms.FROM_USER)
	                		{
		                		console.log("if if executed");
						 		obj.benificiary_name = customrooms.TO_USER;
						 		self.scope.connectedRooms.push(obj)
						        self.minmax(customrooms.ROOM_NAME)
						        connect(customrooms.ROOM_NAME);
							
			        
	                		}
		                	else
	                		{
	                			console.log("else called np worries a");
	                			obj.benificiary_name = customrooms.FROM_USER;
						 		//author=data.Authorname;
						        self.scope.connectedRooms.push(obj)
						        self.minmax(customrooms.ROOM_NAME)
						        connect(customrooms.ROOM_NAME);
			        		}
		                	}
        			}
            		else{
            				innerloop:
            				for (var j=0;j<self.scope.connectedRooms.length;j++)
	                    	{
            					  if(self.scope.connectedRooms[j]!=customrooms.ROOM_NAME){
            						  if(customrooms.ROOM_NAME!=""){
          							 	var obj = {};
           			                	var author_name = author_name;
           			                	var author_id = author_id;
           			                	var chat_room = chat_room;
           			                	var benificiary_name = benificiary_name;
           			                	obj.author_name = data.Authorname;
           			                	obj.author_id = data.Author;
           			                	obj.chat_room = customrooms.ROOM_NAME;
           			                	console.log("author name");
           			                	console.log(data.Authorname);
           			                	if(data.Authorname == customrooms.FROM_USER)
           			                		{
  	         			                		console.log("if if executed");
  	        							 		obj.benificiary_name = customrooms.TO_USER;
  	        							 		self.scope.connectedRooms.push(obj)
  		         						        self.minmax(customrooms.ROOM_NAME)
  		         						        connect(customrooms.ROOM_NAME);
  		         							
  		         			        
           			                		}
           			                	else
           			                		{
           			                			console.log("else called np worries a");
           			                			obj.benificiary_name = customrooms.FROM_USER;
  	        							 		//author=data.Authorname;
  		         						        self.scope.connectedRooms.push(obj)
  		         						        self.minmax(customrooms.ROOM_NAME)
  		         						        connect(customrooms.ROOM_NAME);
		         			        		}
            						  }
	            	    		  }
	            	    		  else
            	    			  {
	            	    			  console.log("no new connection");
            	    			  }
            					  break innerloop; 
	                    	}
	            	}
            	}
        		
            	}
            
         },
        error: function (textStatus, errorThrown) { 
           // Success = false;//doesnt goes here
        }
    });
	
    
*/},20000);

var buttonresettimer;
//popup
CommunicationController.prototype.abcd= function(event) {
	var self = this
    var str='#'+event;
    $(str).show();
    self.minmax(event);
    
    }



//popup box minimize maximize
CommunicationController.prototype.minmax = function(id){
		var self =this;
		var chattitle ='#'+id;
		//alert(chattitle);
		$(chattitle).show();
	    $(chattitle).toggleClass('opened');
		            if($(chattitle).hasClass('opened'))
		            {
		            	//alert("check");
		            $(chattitle).animate({
		                bottom:'21.45em'

		            },400);
		            }
		            else
		            {
		            	///alert("check else");
		                $(chattitle).animate({
		                bottom:'0'
		                });
		            }
	
	//checking for object miinimize time
	if(self.scope.eventsForMinimize.length == 0)
	{
		flag = 1;
	}
else
	{
		for(var i=0;i<self.scope.eventsForMinimize.length;i++)
			{
				if(self.scope.eventsForMinimize[i].chatroomid == id)
					{
						self.scope.eventsForMinimize[i].counter = 0;
						flag = 0;
						break;
					}
				else
					{
						flag =1;
					}
			}
	}
if(flag ==1)
	{
		var TimerForMinimize = new StopWatchForMinimize();
		var chatroomid = chatroomid;
		var counter = counter;
		TimerForMinimize.chatroomid = id;
		TimerForMinimize.counter = 0;
		TimerForMinimize.startForMinimize(TimerForMinimize);
		self.scope.eventsForMinimize.push(TimerForMinimize);
		console.log(self.scope.eventsForMinimize);
	}
	
buttonresettimer = self.scope.eventsForMinimize;
	
		
		 //minimizechat box with icon
	window.setTimeout(function(){
	      self.minimizer(id)
	},300);
		  
		};

//function minmax ppup
CommunicationController.prototype.subminmax = function(id)
		{
			var self = this;
			self.idletimecalculator();
			var chattitle ='#'+id;
		     $(chattitle).toggleClass('closed');
			            if($(chattitle).hasClass('opened'))
			            {
			            $(chattitle).animate({
			                bottom:'21.45em'

			            },400);
			            }
			            else
			            {
			                $(chattitle).animate({
			                	bottom:'21.45em'

			            });
			            }
			           
			         
		}
//popup close
CommunicationController.prototype.popupclose = function(id){
	
			var self = this;
			var partner_id;
		    var chatcloser = '#'+id;
		    $(chatcloser).fadeOut();
		   };

var print_first;
var client;
var id1;
var h;
var color;
CommunicationController.prototype.connect = function(chatroom) {
	var self = this;
    // We are now ready to cut the request
	id1 = chatroom;
	var ws = new WebSocket('ws://' + window.location.hostname + ':15674/ws');
	client = Stomp.over(ws);
	//SockJS does not support heart-beat: disable heart-beats
	client.heartbeat.outgoing = 0;
	client.heartbeat.incoming = 0;
			var on_connect = function(x) {
				  var pingeduser = 1;
				  window.setTimeout(function(){
					  id12 = client.subscribe("/topic/"+chatroom, function(d) {
						  var quote = JSON.parse(d.body);
				          if(quote.author == authorsenderid)
				        	 {
				        	 	color = "blue";
				        	 }
				         else
				        	 {
				        	 	color = "black";
				        	 }
				          //addMessage(json.author, json.message, me ? 'blue' : 'black', new Date(date));
				          var d = new Date();
				          self.subminmax(chatroom)
				          var status = "seen";
				          addMessage(quote.message,color,chatroom,d,status)
				      });
				  },100);
			      
			  };
			  var on_error =  function() {
			    console.log('error');
			  };
			  
			  client.connect('guest', 'guest', on_connect, on_error, '/');
}

CommunicationController.prototype.sendmessage = function(current_chatroom)
{
	var self = this;
	//checking for seen and unseen
	
    var data = $("input[id='" + current_chatroom + "']").val();
    $("input[id='" + current_chatroom + "']").val(' ');
    
    if(data != null)
    	{
    	  if(data.length != 0)
		  {
    		  h = data;
		  }
		  for(i=0;i<self.scope.connectedRooms.length;i++)
			  {
				  if(self.scope.connectedRooms[i].chat_room == current_chatroom)
				  {
				   data = h;
				   data = {};
				   var message = message;
				   var author = author;
				   data.message = h;
				   data.author = authorsenderid;
				   client.send('/topic/'+current_chatroom, {"content-type":"text/plain"}, JSON.stringify(data));
				  }
			  }
		  //here have to send data for server
		  $.ajax({
		        type: "POST",
		        url: "savechatmessages.do",
		        dataType: "json",
		        data:{message:h,author:authorsenderid,chat_room:current_chatroom},
		        async:true,
		        success: function (data) {
		        	console.log(data)
		        },
		        error: function (textStatus, errorThrown) { 
		           // Success = false;//doesnt goes here
		        }
		    });
		        
		        
		 
    	}
   }

function addMessage(message,color,chatroom,datetime,status) {
	var chatdisplayid = '#'+chatroom+ ' ul';
	if(color=='blue'){
		$(chatdisplayid).append("<li class=\"leftchat\"><div class=\"timestamp timestamp_l\">"+(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
                + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())+"</div><div class=\"chatbox\">"+message+"</div><div class=\"timestamp timestamp_l\" style=\"right:3em\">"+status+"</div><span class=\"chat_image chat_image_left\"><img src=\"imgs/profilepicleft.png\" /></span></li>");
	
	}else{
		$(chatdisplayid).append("<li class=\"rightchat\"><div class=\"timestamp timestamp_r\">"+(datetime.getHours() < 10 ? '0' + datetime.getHours() : datetime.getHours()) + ':'
                + (datetime.getMinutes() < 10 ? '0' + datetime.getMinutes() : datetime.getMinutes())+"</div><div class=\"chatbox\">"+message+"</div><span class=\"chat_image chat_image_right\"><img src=\"imgs/profilepicleft.png\" /></span></li>");
    }
}
var idle_timer = 300;
CommunicationController.prototype.idletimecalculator = function(chat_room)
{
	var self = this;
	for(var i=0;i<self.scope.eventsForMinimize.length;i++)
	{
		if(self.scope.eventsForMinimize[i].chatroomid == chat_room)
			{
				self.scope.eventsForMinimize[i].counter = 0;
				flag = 0;
				break;
			}
		else
			{
				flag =1;
			}
	}
}

//Avatar
CommunicationController.prototype.moodmsg_func = function(status)
{
	 $.ajax({
	        type: "POST",
	        url: "statusmsgupdate.do",
	        dataType: "json",
	        data:{Status:status},
	        async:true,
	        success: function (data) {
	        	alert(data.reason)
	        },
	        error: function (textStatus, errorThrown) { 
	           // Success = false;//doesnt goes here
	        }
	    });
	
}

var lastelement = {}; 
var lastelement_id;
var idle_timer_check_counter;
CommunicationController.prototype.minimizer = function(id){
	idle_timer_check_counter = null;
	var self = this;
	self.scope.finalminarray = [];
	var chatterWidth = $('.row.chatter').width();
     if($('div').hasClass("chat_contain"))
    {
    	 var el = $('.chat_contain').filter(function() 
        {
         return $(this).css('display') == 'block';
        });
      
        var widthOfBox = el.length;    
        var widthOfBoxTemp = $('.chat_contain').width();
        var widthOfBox = widthOfBox * widthOfBoxTemp;
        console.log("NO of elements shown" + el.length);
        console.log("Total width covered by boxes" + widthOfBox);
        console.log("Total parent width" + chatterWidth);
        var coverPercent = (widthOfBox / chatterWidth) * 100;
        lastelement_id = id;
        console.log("Total percent COvered:" + coverPercent);
        var tempflag = 0;
        if(coverPercent >= 38.40497193263833)
        {
        	for(var i=0;i<self.scope.eventsForMinimize.length;i++)
			{
				if(self.scope.lastelementarraya.length != 0)
					{
						for(var j=0;j<self.scope.lastelementarraya.length;j++)
							{
								if(self.scope.lastelementarraya[j].chatroomid != self.scope.eventsForMinimize[i].chatroomid)
									{
										if(self.scope.eventsForMinimize[i].chatroomid != id)
    		        					{
    			        					if(idle_timer_check_counter != null)
    				        					{
    				        						//checking value
    			        							if(idle_timer_check_counter.counter <= self.scope.eventsForMinimize[i].counter)
    			        								{
    			        								idle_timer_check_counter = self.scope.eventsForMinimize[i];
    			        								}
    			        						}
    			        					else
    				        					{
    				        						idle_timer_check_counter = self.scope.eventsForMinimize[i];
    				        					}
    		        					}
									}
							}
					}
				else
					{
    					if(self.scope.eventsForMinimize[i].chatroomid != id)
    					{
        					if(idle_timer_check_counter != null)
	        					{
	        						//checking value
        							if(idle_timer_check_counter.counter <= self.scope.eventsForMinimize[i].counter)
        								{
        								idle_timer_check_counter = self.scope.eventsForMinimize[i];
        								}
        						}
        					else
	        					{
	        						idle_timer_check_counter = self.scope.eventsForMinimize[i];
	        					}
    					}
					}
				
			}
        	self.scope.lastelementarraya.push(idle_timer_check_counter);
        	console.log(idle_timer_check_counter);
        	lastelement_id =  idle_timer_check_counter.chatroomid; 
        	var appendText = '#'+lastelement_id;
            lastelement_id = appendText;
            $(lastelement_id).hide();
        	
        }
}
}

function restfunction(id)
{
	var a= "#"+id+"sess";
	console.log(a);
	$(a).hide();
	$(a).remove();
	console.log(buttonresettimer)
	for(var i=0;i<buttonresettimer.length;i++)
		{
		console.log(buttonresettimer[i])
			if(buttonresettimer[i].chatroomid == id)
			{
				
				buttonresettimer[i].counter = 0;
				break;
			}
		}
	console.log(buttonresettimer)
}
var StopWatchForMinimize = function() {
	
	  // private functions
	  function startForMinimize(timer) {
		  var refreshId = setInterval(function () {
			    ++timer.counter;
			     if(timer.counter == 30)
			    	 {
			    	 			/*var chattitle ='#'+timer.chatroomid;
			    	 			var sesmesid = timer.chatroomid + 'sess';
			    	 			$(sesmesid).show();
			    	 			$(chattitle).append("<div class=\"session_msg\"  id='"+sesmesid+"' >Your Session Is Going to end.<button onClick=\"restfunction('"+timer.chatroomid+"')\">OK</button></div>");
			    	 			$(chattitle).toggleClass('closed');
			    	 			$(chattitle).show();
					            if($(chattitle).hasClass('opened'))
					            {
					            	
					            	$(chattitle).animate({
						                bottom:'21.45em'
						            },400);
						            }
					            else
					            {
					            	
					                $(chattitle).animate({
					                	bottom:'21.45em'

					            });
					            }*/
					            
					            
			    	 }
			     if(timer.counter >= 60)
		    	{/*
			    	
		    		clearInterval(refreshId);
			    	var partner_id;
				    var chatcloser = '#'+timer.chatroomid;
				    $(chatcloser).fadeOut();
				    var a=timer.chatroomid.split("-")[0];
				    var b= timer.chatroomid.split("-")[1];
				    if(authorsenderid != a)
				    	{
				    		partner_id=a;
				    	}
				    else if(authorsenderid != b)
				    	{
				    		partner_id=b;
				    	}
				    //getting partner name and partner id
				    for(var z=0;z<activeonlineuserarray.length;z++)
				    	{
				    		if(partner_id ==activeonlineuserarray[z].loginId)
				    			{
				    				var partner_name = activeonlineuserarray[z].firstName;
				    				break;
				    			}
				    	}
				    
				   //popup close// needto check with the status for partner
				      $.ajax({
					        type: "POST",
					        url: "statuschecking.do",
					        dataType: "json",
					        data:{chat_roompartnerid:partner_id,chat_room:timer.chatroomid,partner_name:partner_name,author_name:onlineauthor_name},
					        async:true,
					        success: function (data) {
						        	
					        },
					        error: function (textStatus, errorThrown) { 
					           // Success = false;//doesnt goes here
					        }
					    });
				     // activeconnectedarray = self.scope.connectedRooms;
				      for(var z=0;z<activeconnectedarray.length;z++)
	                	{
	                		if(activeconnectedarray[z].chat_room == timer.chatroomid)
	                			{
	                			activeconnectedarray.splice(activeconnectedarray[z], 1);
	                    			break;
	                			}
	                	}
				      console.log(activeconnectedarray)
				      console.log(activeconnectedarray.length)
				      
				      if(activeconnectedarray.length ==0)
				    	  {
				    	  
				    	  }
				      
				      console.log(activeonlineuserarray)
				      console.log(onlineusersarray)
				      
				      for(var z=0;z<activeonlineuserarray.length;z++)
				    	{
				    		if(partner_id ==activeonlineuserarray[z].loginId)
				    			{
				    			console.log("activelly called inside the arraya");
				    			onlineusersarray.push(activeonlineuserarray[z]);
				    			activeonlineuserarray.splice(activeonlineuserarray[z], 1);
				    			
                    			break;
				    			}
				    	}
				      //need to remove from activeconnections and add to onlineusersa
				      $("#chat_recipitents").load('');
		    	*/}
			  }, 1000);
		 
	  }
	  // public API
	 this.startForMinimize  = startForMinimize;
	 
	//};
	
	  }

CommunicationController.prototype.activeconnect = function(userobj)
{
	console.log("called")
	console.log(userobj.active_room);
	var self = this;
	var temp_flag = 0;
	console.log(self.scope.eventsForMinimize);
	var disable_partner_id = '#'+userobj.loginId;
	$(disable_partner_id).prop('disabled', true);
	//checking the chat room it is excists in minimize array aka lastelementarraya  
	if(self.scope.lastelementarraya.length == 0)
		{
		temp_flag = 0;
		}
	else
		{
		//checking the chat room it is excists in minimize array aka lastelementarraya if exsists
		//checking chat room id then open it...
			for(var z=0;z<self.scope.lastelementarraya.length;z++)
			{
				console.log(self.scope.lastelementarraya[z].chatroomid)
				if(self.scope.lastelementarraya[z].chatroomid == userobj.active_room)
					{
						console.log("called inside loop")
						self.scope.lastelementarraya.splice(self.scope.lastelementarraya[z], 1);
						self.minmax(userobj.active_room);
						temp_flag= 1;
						break;
					}
				else
					{
					temp_flag = 0;
					}
			}
			
	if(temp_flag == 0){
		self.minmax(userobj.active_room);
	}
		}
	
	
	
	
}
    