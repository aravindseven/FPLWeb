function UserRegisterController($scope, $http) {
	this.scope = $scope;
	this.confirmPwdError = false;
	this.confirmCaptcha = false;
	this.userPwdError = false;
	this.emailAltEmailError = false;
	this.mobilePhoneError = false;
	this.http = $http;
	this.scope.master = {};
	this.util = new Util();
	this.CountryCode = 'IN';
	this.type="";
}
  

UserRegisterController.prototype.register1 = function() {
	alert(this.scope.type)
}
UserRegisterController.prototype.register = function(user) {
	document.getElementById("Content0").style.display="none";
	document.getElementById("Content1").style.display="none";
	document.getElementById("Content2").style.display="none";
	document.getElementById("Content4").style.display="none";
	console.log(user);
	var self = this;
	console.log(self.scope.type)
	console.log(""+self.scope.type);
	user.password=generatePasword();
	self.emailAltEmailError = self.matchConfirm(user.firstname, user.email);
	if(!self.emailAltEmailError){
	if (true) {
		if (true) { 
			if (!false) {
				self.confirmCaptcha = false;
				 
				var registerJson = {
					"primaryEmailId" : user.email,
					"firstname" : user.firstname,
					"lastname" : user.lastname,
					"userType" : self.scope.type,
					"secretAnswer" : user.regSecretQuestionAnswer
				};
				this.util.block("Registering user");
				this.http(
								{
									method : 'POST',
									url : 'userRegister.do',
									params : {
										'registerInput' : registerJson
									},
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
									}
								}).success(
								function(data, status, headers, config) {
									if (data.status == "success") {
										console.log("custoemr or indivisualsdffsdhkf");
										console.log(self.scope.type);
										
										if(self.scope.type=="fp_corporate"||self.scope.type=="fp_individual")
											{
											self.util.notify("Register Info",
													data.reason, "success");
											document.getElementById("successMSG2").style.display="block";
											document.getElementById("Content1").style.display="inline-block";
											document.getElementById("Content1").innerHTML = document.getElementById("Content1").innerHTML + data.reason;

											}
										else
											{
											self.util.notify("Register Info",
													data.reason, "success");
											document.getElementById("successMSG1").style.display="block";
											document.getElementById("Content0").style.display="inline-block";
											document.getElementById("Content0").innerHTML = document.getElementById("Content0").innerHTML + data.reason;

											}
									
										
									} else {
										console.log(self.scope.type);
										console.log(self.scope.type)
										if(self.scope.type=="fp_corporate"||self.scope.type=="fp_individual")
										{
										self.util.notify("Register Info",
												data.reason, "success");
										document.getElementById("successMSG2").style.display="block";
										document.getElementById("Content4").style.display="inline-block";
										document.getElementById("Content4").innerHTML = document.getElementById("Content4").innerHTML + data.reason;

										}
									else
										{
										self.util.notify("Register Info",
												data.reason, "success");
										document.getElementById("successMSG1").style.display="block";
										document.getElementById("Content2").style.display="inline-block";
										document.getElementById("Content2").innerHTML = document.getElementById("Content2").innerHTML + data.reason;

										}
									}									
									//$("#RegisterModal").modal("hide");
									self.util.unblock();
									}).error(
								function(data, status, headers, config) {
									if(self.scope.type=="fp_corporate"||self.scope.type=="fp_individual")
									{
									self.util.notify("Register Info",
											data.reason, "success");
									document.getElementById("successMSG2").style.display="block";
									document.getElementById("Content4").style.display="inline-block";
									document.getElementById("Content4").innerHTML = document.getElementById("Content4").innerHTML + data.reason;

									self.util.unblock();
									}
								else
									{
									self.util.notify("Register Info",
											data.reason, "success");
									document.getElementById("successMSG1").style.display="block";
									document.getElementById("Content2").style.display="inline-block";
									document.getElementById("Content2").innerHTML = document.getElementById("Content2").innerHTML + data.reason;
                            		self.util.unblock();
									}
									
								});
			} else {
				this.confirmCaptcha = true;
			}
		  }	
		}
	}
	
	self.reset();
	
};

UserRegisterController.prototype.generateExpiry = function() {
	var later = new Date();
	return new Date(later.setMinutes(later.getMinutes() + 1));
};

UserRegisterController.prototype.matchConfirm = function(pwd, confirmPwd) {
	return (pwd === confirmPwd);
};

UserRegisterController.prototype.regenerateCaptcha = function() {
	this.captcha.generate();
};

UserRegisterController.prototype.speakCaptcha = function() {
	var str = this.captcha._settings.text;
	var chars = str.split('');
	for (var i = 0; i < chars.length; i++) {
		var singleChar = chars[i];
		if (singleChar == singleChar.toUpperCase()
				&& isNaN(parseInt(singleChar, 10))) {
			chars[i] = "caps " + singleChar;
		}
	}
	var finalStr = chars.join(', ');
	meSpeak.speak(finalStr, {
		amplitude : 100,
		wordgap : 10,
		pitch : 50,
		speed : 175,
		variant : ""
	});
};

UserRegisterController.prototype.reset = function() {
	var self=this;
	self.scope.user = angular.copy(this.scope.master);
	
	if(self.scope.type=="fp_corporate"||self.scope.type=="fp_individual")
	{
		self.scope.userForm1.$setPristine();
	}
else
	{
	self.scope.userForm.$setPristine();
	}
	 self.scope.userForm.$setPristine();
	 self.scope.user.username = "";
	 self.scope.user.firstname = "";
	 self.scope.user.lastname = "";
	 self.scope.user.email = "";
	 self.scope.user.mobilephone = "";
	 self.scope.user.type = "";
	 self.scope.user.password="";
	 self.scope.user.confirmPassword="";
	 self.scope.user.regSecretQuestionAnswer="";
	 self.scope.user.captcha="";
	 self.scope.user.terms="";
	 self.confirmPwdError = false;
	 self.confirmCaptcha = false;
	 self.userPwdError = false;
	 self.emailAltEmailError = false;
	$("#password-strength-text").hide();
};


UserRegisterController.prototype.passwordStrength = function(user) {
$("#password-strength-text").show();
var passinput = user.password;
//alert(user.password);

var characters = 0;
var capitalletters = 0; 
var loweletters = 0;
var number = 0;
var special = 0;

var upperCase= new RegExp('[A-Z]');
var lowerCase= new RegExp('[a-z]');
var numbers = new RegExp('[0-9]');
var specialchars = new RegExp('([!,%,&,@,#,$,^,*,?,_,~])');
if(passinput)
	{
		if (passinput.length >= 8) { characters = 1; } else { characters = 0; };
		if (passinput.match(upperCase)) { capitalletters = 1;} else { capitalletters = 0; };
		if (passinput.match(lowerCase)) { loweletters = 1;}  else { loweletters = 0; };
		if (passinput.match(numbers)) { number = 1;}  else { number = 0; };
		if (passinput.match(specialchars)) { special = 1;}  else { special = 0; };
	}
else
	{
		$("#password-strength-text").hide();
		$("#password-strength-text").removeClass().html('');
		$("#regConfirmPassword").attr('disabled','disabled');
	}
var total = characters + capitalletters + loweletters + number + special;
if(total == 0){
	 $("#password-strength-text").removeClass().html('');
}else if (total <= 2) {
	$("#password-strength-text").removeClass();
	$("#password-strength-text").addClass('veryweak').html('<p>Strength: very weak</p>');
} else if (total == 3){
	$("#password-strength-text").removeClass();
	$("#password-strength-text").addClass('weak').html('<p>Strength: weak</p>');
} else if(total == 4){
	$("#password-strength-text").removeClass();
	$("#password-strength-text").addClass('medium').html('<p>Strength: medium</p>');
} else {
	$("#password-strength-text").removeClass();
	$("#password-strength-text").addClass('strong').html('<p>Strength: strong</p>');
	$("#regConfirmPassword").removeAttr('disabled');
} 
};

UserRegisterController.prototype.checkCountry = function(user) {
	
	if(this.CountryCode =='IN')
		$("#mobilephone").attr('pattern','[0-9]{3}[0-9]{3}[0-9]{2}'); 
	else
		$("#mobilephone").attr('type','number');
};


/*Logic to AutoGenerate password*/
var specials = '!@#$';
var lowercase = 'abcdefghijklmnopqrstuvwxyz';
var uppercase = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
var numbers = '0123456789';

var all = lowercase + uppercase + numbers;

String.prototype.pick = function(min, max) {
    var n, chars = '';

    if (typeof max === 'undefined') {
        n = min;
    } else {
        n = min + Math.floor(Math.random() * (max - min));
    }

    for (var i = 0; i < n; i++) {
        chars += this.charAt(Math.floor(Math.random() * this.length));
    }

    return chars;
};


// Credit to @Christoph: http://stackoverflow.com/a/962890/464744
String.prototype.shuffle = function() {
    var array = this.split('');
    var tmp, current, top = array.length;

    if (top) while (--top) {
        current = Math.floor(Math.random() * (top + 1));
        tmp = array[current];
        array[current] = array[top];
        array[top] = tmp;
    }

    return array.join('');
};

generatePasword = function() {
       return ((specials.pick(1) + lowercase.pick(1) + uppercase.pick(1) + all.pick(5)).shuffle()).shuffle();
};