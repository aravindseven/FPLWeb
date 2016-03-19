function ListFPLSubscriptionController($scope, $http) {
	this.scope = $scope;
	this.scope.master = {};
	this.http = $http;
	this.util = new Util();
	this.subId=0;
	this.fpName="";
	this.subIdText="";
	this.fPSubscriptionListPVs;
	this.searchJson="";

}

ListFPLSubscriptionController.prototype.fetchFPLDetailsbyId= function(fplSub) {
	var self = this;
	this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'listFPSubscription.do?action=FETCHFPLBYID',
		params : { },
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		
		if(data){
			if(data.FPSubscriptionListPVs){
				
				self.fPSubscriptionListPVs=data.FPSubscriptionListPVs;
				if(self.fPSubscriptionListPVs.length==0)
				{
					document.getElementById("NO_RECORD").style.display="block";
				}
			}
			
		}
		self.util.unblock();
	
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});

};

ListFPLSubscriptionController.prototype.fetchPrevCriteria= function(fName,fsubId) {
	
	if((fName && fName.length>0 && fName!="null") || subId>0 )
	{
		this.fpName=fName;
		this.subId=fsubId;
		this.fetchSubscribeDetails(this);
	}
	
};

ListFPLSubscriptionController.prototype.fetchSubscribeDetails = function(fplSub) {
	var self = this;
	document.getElementById("NO_RECORD").style.display="none";
	document.getElementById("TABLEDATA").style.display="none";
	document.getElementById("ERROR").style.display="none";
	fplSub.subId = parseInt(fplSub.subIdText.replace(/[^\d.-]/g, ''));
	
	if(fplSub.fpName || fplSub.subId>0 )
	{
	
		
	var searchProfileJson = {
			 "name" : fplSub.fpName,
			 "subScriptionId" : fplSub.subId,
	};
	
	this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url : 'listFPSubscription.do',
		params : {
			'searchProfileJson' : searchProfileJson,
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		document.getElementById("TABLEDATA").style.display="block";
		if(data){
			if(data.FPSubscriptionListPVs){
				
				self.fPSubscriptionListPVs=data.FPSubscriptionListPVs;
				if(self.fPSubscriptionListPVs.length==0)
				{
					document.getElementById("NO_RECORD").style.display="block";
				}
			}
			
		}
		self.util.unblock();
	
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
	
   }else
	 {
	   document.getElementById("ERROR").style.display="block";
	 }
};



ListFPLSubscriptionController.prototype.formatDate = function(date){
    var dateOut = new Date(date);
    return dateOut;
};

