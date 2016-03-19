UserLoginController.prototype.loginFailure = function(data) {
	var self = this;
	self.loggedIn = false;
	self.username = null;
	self.loginFlag = false;
	self.loginresetlink = null;
	self.reset();
	//document.getElementById('loginFailed').show;
	
	//document.getElementById('loginFailed p').html="dhdfhdg";
	if(data.Link!=null){
		console.log(""+data.Link);
		self.loginresetlink=data.Link;
		document.getElementById('loginreset').style.display = 'block';
		var a=document.getElementById('resetcontent');
		a.href = self.loginresetlink;
		
		
	}
	document.getElementById('loginFailed').style.display = 'block';
	document.getElementById("Content1").innerHTML=data.errorMsg;
};

//page login
UserLoginController.prototype.loginpage = function() {
	//alert("called login failurwe")
	window.location.href="FPLLogin.jsp";
	/*var self = this;
	self.loggedIn = false;
	self.username = null;
	self.loginFlag = false; 
	self.reset();
	document.getElementById('loginFailed').style.display = 'block';*/
};

function UserLoginController($scope, $http, $interval){
	this.loggedIn = false;
	this.loginresetlink = null;
	this.username = null;
	this.loginFlag = true; 
	this.scope = $scope;
	this.http = $http;
	this.scope.master = {};
	this.util = new Util();
	this.loginPath = null;
}

UserLoginController.prototype.login = function(credentials) {
	
	var self = this;
	
	var loginJson = {
		'country' : 'Singapore',
		'primaryEmailId' : credentials.username,
		'password' : credentials.password
	};
	$('#loginModal').modal('hide');
	this.util.block("Logging in");
	this.http({
		method : 'POST',
		url : 'loginFpl.do',
		//url: 'support/contents/login.json',
		params : {
			'loginInput' : loginJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).	success(function(data, status, headers, config) {
      if(data.success){
			self.loggedIn = true;
			self.username = data.user;
			self.loginFlag = true;
			if(self.loginPath)
				window.location.href= self.loginPath;
			else
				window.location.href= "settings_1.jsp#personalSettings";
			//window.location.reload(true);
		} else {
			self.loginFailure(data);
			/*
			self.util.notify("Login Failure", data.errorMsg, "failure");
			self.loggedIn = false;
			self.username = null;
			self.loginFlag = false; 
			self.reset();*/
		}
      	self.util.unblock();
    }).
    error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      self.util.unblock();	
    });
};

UserLoginController.prototype.generateExpiry = function(){
	var later = new Date();
	return new Date(later.setMinutes(later.getMinutes() + 1));
};

UserLoginController.prototype.isLogged = function(){
	return (this.loggedIn);
};

UserLoginController.prototype.currentUser = function(){
	return this.username;
};

UserLoginController.prototype.logout = function($scope){
	this.loggedIn = false;
	var self = this;
	this.util.block("Logging out");
	this.http({
		method : 'POST',
		url : 'userlogout.do',
		params : {},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			self.util.notify("Logout Info", data.reason, "success");
		} else {
			self.util.notify("Logout Failure", data.reason, "failure");
		}
		self.util.unblock();
		window.location.href = "index1.jsp";
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
	
	this.username = null;
	this.reset();
};

UserLoginController.prototype.loginNotvalid = function(){
	this.loginFlag = false; 
};


UserLoginController.prototype.initfunc = function(){
	
};

UserLoginController.prototype.loginValid = function(){
	this.loginFlag = true; 
};

UserLoginController.prototype.reset = function() {
      this.scope.credentials = angular.copy(this.scope.master);
      this.scope.credentials.username="";
      this.scope.credentials.password="";
      this.scope.credentials.AlternateEmail="";
};

UserLoginController.prototype.register = function(usertype) {
      $('#RegisterModal').on('show.bs.modal', function () {
		 $('.modal-content', '#RegisterModal').css('height',$( window ).height());
	 });
	 $('#RegisterModal').on('shown.bs.modal', function () {
		 var sel = $("select[name=userType]");
		 sel.empty();
		 sel.append('<option value="">Select Type..</option>');
			if (usertype == 'Customer')
			{
				sel.append('<option value="cust_individual">Individual</option>');
				sel.append('<option value="cust_corporate">Corporate</option>');
				$("#RegisterTitle").html('Register Customer');
			}	
			else if (usertype == 'FP')
			{	
				sel.append('<option value="fp_individual">Individual</option>');
				sel.append('<option value="fp_corporate">Corporate</option>');
				$("#RegisterTitle").html('Register FP');
			}
	 });
	  $("#RegisterModal").modal("show");
};

UserLoginController.prototype.loginModal = function(loginPath) {
	$("#loginModal").modal("show");
	this.loginPath=loginPath;
};

UserLoginController.prototype.profileModal = function() {
	$('#ProfileModal').on('show.bs.modal', function () {
		 $('.modal-content', '#ProfileModal').css('height',($( window ).height()-1500));
	 });
	$("#ProfileModal").modal("show");
};

UserLoginController.prototype.changeDate = function() {
	this.scope.date = new Date();
};
