function TipsController($scope, $http, $sce) {
	this.scope = $scope;
	this.http = $http;
	this.sce = $sce; 
	
	
	$scope.tipslist = [];
	$scope.previous = 0;
	$scope.next = 0;
	$scope.tip_name;
	$scope.tip_description;
	
	
	

 };

//get all tips by pagination
TipsController.prototype.getalltips = function() {
	console.log("pagination called for new tips page");
	var self = this;
	this.http({
		method : 'GET',
		url : 'gettips.do',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if(data){
			if(data.Status == "Success")
			{
				console.log("succes data get");
				console.log(data);
				self.scope.tipslist = data.Result.al;
				console.log(self.scope.tipslist);
			}
			else {
			}
		}
	}).error(function(data, status, headers, config) {
		
	});
};

//save tips checking

TipsController.prototype.savetip = function()
{
	var self = this;
	console.log(self.scope.tip_name)
	console.log(self.scope.tip_description)
	
	if(self.scope.tip_name == undefined || self.scope.tip_name == null)
		{
			alert("Please Enter Tip Name");
		}
	else if(self.scope.tip_description == undefined || self.scope.tip_description == null)
		{
			alert("Please Enter Tip Description");
		}
	else
		{
			$("#save").button("Saving..");
			$("#save").button("Saving..");
			this.http({
				method : 'POST',
				//url : 'support/contents/mail_delete.json',
				url:'savetips.do',
				params : {
					'tip_name':self.scope.tip_name,
					'tip_description' : self.scope.tip_description
					
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
				}
			}).success(function(data, status, headers, config) {
				$("#myModal").modal('hide');
				window.setTimeout(function(){window.location.href="Tips.jsp";},1000);
				
				
			}).error(function(data, status, headers, config) {
				console.log("errora tips");
				$("#save").button("reset");
				
			});
		}
}

//add modal

TipsController.prototype.addmodal = function()
{
	var self = this;
	self.scope.tip_name = " ";
	self.scope.tip_description = " ";
	
	$("#myModal").modal('show');
}

//edit modal
TipsController.prototype.editmodal = function(tip_name)
{
	var self = this;
	this.http({
		method : 'POST',
		//url : 'support/contents/mail_delete.json',
		url:'gettipsbyid.do',
		params : {
			'tip_name':tip_name
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		self.scope.dataobj = data.Result;
		$("#myModal1").modal('show');
		//window.setTimeout(function(){window.location.href="Tips.jsp";},1000);
		
		
	}).error(function(data, status, headers, config) {
		console.log("errora tips");
	});
}

//update tip

TipsController.prototype.updatetip = function()
{
	var self = this;
	 if(self.scope.dataobj.Tip_Description == undefined || self.scope.dataobj.Tip_Description == null)
		{
			alert("Please Enter Tip Description");
		}
	else
		{
			$("#save").button("Saving..");
			$("#save").button("Saving..");
			this.http({
				method : 'POST',
				//url : 'support/contents/mail_delete.json',
				url:'updatetips.do',
				params : {
					'tip_name':self.scope.dataobj.Tip_Name,
					'tip_description' : self.scope.dataobj.Tip_Description
					
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
				}
			}).success(function(data, status, headers, config) {
				$("#myModal1").modal('hide');
				window.setTimeout(function(){window.location.href="Tips.jsp";},1000);
				
				
			}).error(function(data, status, headers, config) {
				console.log("errora tips");
				$("#save").button("reset");
				
			});
		}
}


//delete tip

TipsController.prototype.deletetip = function()
{
	var self = this;
	this.http({
		method : 'POST',
		//url : 'support/contents/mail_delete.json',
		url:'deletetipsbyid.do',
		params : {
			'tip_name':self.scope.dataobj.Tip_Name
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		console.log("editpage called");
		console.log(data);
		$("#myModal1").modal('hide');
		window.setTimeout(function(){window.location.href="Tips.jsp";},1000);
		
		
	}).error(function(data, status, headers, config) {
		console.log("errora tips");
	});
}
