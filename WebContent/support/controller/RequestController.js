/**
 * 
 */
var ghttpVar;
var gutilVar;
function RequestController($scope, $http, $compile){
	this.scope = $scope;
	this.http = $http;
	ghttpVar = $http;
	this.compile = $compile;
	this.util = new Util();
	$scope.util = new Util();
	gutilVar=new Util();
	this.newRequestType = "0";4
	this.requestType=0;
	this.newRequestDesc = "";
	this.newRequestPostal = "";
	this.newRequestArea = "";
	this.newRequestCountry = "India";
	this.newRequestUrgency = false;
	this.newRequestFollowup = false;
	this.viewId= -1;
	this.domainName="";

	
	this.updatedDate="";
	this.currentStatus="";
	
	$scope.createdDate= Date();
	
	$scope.requestId="0";
	$scope.requestIdText="Req000";	
	this.showOnlineFP= false;
    this.domainList;	
	$scope.selectedfplId=0;
    $scope.location="";
    $scope.description="";
    $scope.postalCode="";
    $scope.latitude=0;
    $scope.longitude=0;
    $scope.edit="";
    $scope.searchStatus=-1;
    $scope.fromDate="";
    $scope.toDate="";
	$scope.selected="";
	$scope.requestType="";
	$scope.searchRequestId="";
	$scope.prevRequestType="";
	$scope.FPLList;
	$scope.RequestStatusList;
	$scope.FPLRequestSentList=new Array();
	$scope.customerDashboardList;
	$scope.names = ["Car Repair", "Car Repair Insurance", "Car Insurance", "Bike Insurance", "Bike Repair Insurance","Life Insurance","Accident Insurance","LIC"];

	this.scope.statesWithFlags = [
	 {'name':'Car Repair','flag':'4'}
	,{'name':'Car Repair Insurance','flag':'4'}
	,{'name':'Car Insurance','flag':'4'}
	,{'name':'Bike Insurance','flag':'5'}
	,{'name':'Bike Repair Insurance','flag':'5'}
	,{'name':'Life Insurance','flag':'3'}
	,{'name':'Accident Insurance','flag':'3'}
	,{'name':'LIC','flag':'3'}
	];

	
	
	
	$scope.$watch('selected', function(cntrl) {
		
		if($scope.selected.length<=0)
		{
			$scope.requestType=-1;
		}else
		{
			angular.forEach($scope.statesWithFlags, function(item){
		 if(item.name==$scope.selected || ($scope.selected.length>0 &&  item.name.trim().substring(0, $scope.selected.length).toUpperCase() ==$scope.selected.toUpperCase() ))
		 {
			 $scope.prevRequestType=$scope.requestType;
			 $scope.requestType=item.flag;
			 $scope.searchRequest();
			
		 }
	    
	    });
		}		
	});

	
$scope.saveRequest = function(){
		
		
		$scope.load();	
		var requestJson = {
				type : $scope.requestType,
				description : $scope.description,
				postalCode : $scope.postalCode,
				location : $scope.location,
				country : "",
				urgency : "0",
				followUp : "",
				keyword : $scope.selected,
				requestId : $scope.requestId,
				sendToFpl :"false"
			};
		$http({
			method : 'POST',
			url : 'saveRequest.do',
			//url : 'support/contents/request_FPList.json',
			params : {
				"requestJson" : requestJson,
				"fplId" : "0",
				"reqSaveOption" : ""
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			//window.location.href = "fetchFPLRequest.do";
			
			if (data) 
			{
				
				$scope.requestId=data.requestId;
				$scope.requestIdText=data.requestIdText;
				$scope.createdDate=data.creationDate;
				
		    }

			$scope.unload();
		}).error(function(data, status, headers, config) {
			$scope.unload();
		});
	};
	
	$scope.sendRequest = function(fplId){
		
		$scope.FPLRequestSentList.push(fplId);
		$scope.load();	
		var requestJson = {
				type : $scope.requestType,
				description : $scope.description,
				postalCode : $scope.postalCode,
				location : $scope.location,
				country : "",
				urgency : "0",
				followUp : "",
				keyword : $scope.keyword,
				requestId : $scope.requestId,
				sendToFpl :"true"
			};
		$http({
			method : 'POST',
			url : 'saveRequest.do',
			//url : 'support/contents/request_FPList.json',
			params : {
				"requestJson" : requestJson,
				"fplId" :fplId,
				"reqSaveOption" : "SendFP"
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			//window.location.href = "fetchFPLRequest.do";
			
			if (data) 
			{
				
				$scope.requestId=data.requestId;
				$scope.requestIdText=data.requestIdText;
				$scope.createdDate=data.creationDate;
				
//				 document.getElementById("SEND_"+fplId).style.display="none";
//				 document.getElementById("SENT_"+fplId).style.display="block";
				$scope.updateSearchRequest();
				 
		    }

			$scope.unload();
		}).error(function(data, status, headers, config) {
			$scope.unload();
		});
	};

    $scope.updateDashboardJson = function(reqid,fplId,status,statusdis){
    
    	if($scope.edit=='EDIT')
    	{
    		
    		 angular.forEach($scope.requestFPLList, function(fplValue){
					
				  if(fplValue.fplId==fplId)
				  {
					  fplValue.statusDis=statusdis;
					  fplValue.status=status;
					  
				  }
			    });
			
    	}else
    	{
    	
		angular.forEach($scope.customerDashboardList, function(request){
			
			
			  if(request.requestId==reqid)
			  {
				  angular.forEach(request.requestFPLList, function(fplValue){
						
					  if(fplValue.fplId==fplId)
					  {
						  fplValue.statusDis=statusdis;
						  fplValue.status=status;
						  
					  }
				    });
				  angular.forEach(request.secondRequestFPLList, function(fplValue){
						
					  if(fplValue.fplId==fplId)
					  {
						  fplValue.statusDis=statusdis;
						  fplValue.status=status;
						  
					  }
				    });
				  
			  }
		    });
    	}
    };

    
$scope.updateFPLDashboardJson = function(reqid,status,statusdis){
    
    	
    	
		angular.forEach($scope.customerDashboardList, function(request){
			
			  if(request.requestId==reqid)
			  {
				  request.requestActivityDTO.statusDis=statusdis;
				  request.requestActivityDTO.status=status;
			  }
		    });
		
    };
	$scope.searchRequest = function(){
		
		var editValue="";
		
		if($scope.edit=='EDIT')
		{
			editValue='EDIT';
		}
				
		if($scope.prevRequestType!=$scope.requestType && $scope.edit!='EDIT')
		{
			$scope.requestId=0;
			$scope.FPLRequestSentList=new Array();
		}
		$scope.prevRequestType=$scope.requestType;
		var requestJson = {
				type : $scope.requestType,
				description : "",
				postalCode : $scope.postalCode,
				latitude : $scope.latitude,
				longitude : $scope.longitude,
				location : $scope.location,
				country : "",
				urgency : "0",
				followUp : "",
				requestId : $scope.requestId,
			};
		$http({
			method : 'POST',
			url : 'fetchFPLRequest.do',
			//url : 'support/contents/request_FPList.json',
			params : {
				"requestJson" : requestJson,
				"EDIT"       : editValue
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			//window.location.href = "fetchFPLRequest.do";
			
			if (data) 
			{
				$scope.FPLList=data;
				$scope.updateSearchRequest();
		    }
		
		}).error(function(data, status, headers, config) {
		
		});
	};
	

	
$scope.searchExistingRequest = function(){
		
	$scope.setSubPage(0);
	$scope.requestId = parseInt($scope.searchRequestId.replace(/[^\d.-]/g, ''));
	$scope.fromDate=document.getElementById("startDate").value;
	$scope.toDate=document.getElementById("endDate").value;

		var requestJson = {
				requestId : $scope.requestId,
				fromDate : $scope.fromDate,
				toDate : $scope.toDate,
				type : $scope.requestType,
				status : $scope.searchStatus
				
			};
		$http({
			method : 'POST',
			url : 'SearchRequest.do',
			//url : 'support/contents/request_FPList.json',
			params : {
				"requestJson" : requestJson
				
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			//window.location.href = "fetchFPLRequest.do";
			
			if (data) 
			{
				$scope.FPLList=data.RequestList;
				$scope.domainList=data.DOMAIN;
				$scope.RequestStatusList=data.RequestStatusList;
				
		    }
		
		}).error(function(data, status, headers, config) {
		
		});
	};

	
	


	$scope.fetchAddress = function(){
		if($scope.postalCode.length>5)
		{
		  var latLng=get_postCode_To_LatLng($scope.postalCode);
	      $scope.latitude=latLng[0];
	      $scope.longitude=latLng[1];
	    
		}else 
		{
			$scope.latitude=0;
			$scope.longitude=0;
			
		}
		
		if($scope.requestType.length>0)
	    {
	    	$scope.searchRequest();
	    }
	};

	$scope.formatDate = function(date){
	    var dateOut = new Date(date);
	    return dateOut;
	};
	
	
	$scope.updateSearchRequest= function(){
	
		angular.forEach($scope.FPLList, function(fplValue){
			
			  if($scope.FPLRequestSentList.indexOf(fplValue.id)>-1)
			  {
				  fplValue.requestSent=1;
			  }
		    });
	};

	
	$scope.editRequest = function(reqid)
	{
		$scope.subitemsPerPage = 10;
		$scope.edit="EDIT";
		$http({
			method : 'POST',
			url : 'getRequestDetail.do',
			params : {
				"requestId" : reqid
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			if (data) {
				$scope.FPLList=data.respList;
				$scope.requestFPLList=data.requestFPLList;
				$scope.requestId=data.request.requestId;
				$scope.requestIdText=data.request.requestIdText;
				$scope.createdDate=data.request.creationDate;
				if(data.request.location)
				{
					$scope.location=data.request.location;
				}	
				if(data.request.keyword)
				{
					$scope.selected=data.request.keyword;
				}	
				if(data.request.postalCode)
				{
					$scope.postalCode=data.request.postalCode;
				}	
				$scope.description=data.request.description;
				$scope.domainList=data.DOMAIN;
				$scope.requestType=data.request.type;
				$scope.typeDescription=data.request.typeDescription;
				
				angular.forEach($scope.FPLList, function(fplValue){
					  if(fplValue.requestSent == 1)
					  {
						  $scope.FPLRequestSentList.push(fplValue.id);
					  }
				    });
				

			}
		}).error(function(data, status, headers, config) {
		});
		
		

	};

	$scope.fetchDashboardDetails= function()
	{
		$scope.load();
		$http({
			method : 'POST',
			url : 'getRespondedFPL.do',
			params : {
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			if (data) {		
				
				$scope.customerDashboardList=data;
			}
			$scope.unload();
		}).error(function(data, status, headers, config) {
			$scope.unload();
		}); 
	};


	$scope.rejectRequest = function(reqid,fplId)
	{
		$scope.load();
		var rejectType="";
		if(fplId>0)
		{
			rejectType="CUSTOMER";	
		}
		
		$http({
			method : 'POST',
			url : 'FPLRejectRequest.do',
			params : {
				"requestId":reqid,
				"fplId":fplId,
				"rejectType" : rejectType 
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			
			if(fplId>0)
			{
				$scope.updateDashboardJson(reqid,fplId,"Reconsider","ST_08");
			}else
			{

				$scope.updateFPLDashboardJson(reqid,"Rejected","ST_06");
			}
			
		}).error(function(data, status, headers, config) {
		}); 
		$scope.unload();
		
	};

	
	$scope.connectToFPL= function(reqid,fplId)
	{
		$scope.load();
		$http({
			method : 'POST',
			url : 'ConnectToFPL.do',
			//url:'support/contents/register.json',
			params : {
				"requestId":reqid,
				"fplId":fplId
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			if (data.status == "success") {

				$scope.updateDashboardJson(reqid,fplId,"Connected","ST_07");


			} else {
				gutilVar.notify("Response Failure",data.reason, "failure");
			}
			$scope.unload();
		}).error(function(data, status, headers, config) {
			$scope.unload();
		});
	};


	
	$scope.deleteRequest= function(reqid,src)
	{
		$scope.load();
		var retVal = confirm("Are you sure you want to delete this request?");
		if (retVal == true) {
			
	        $http({
	    		method : 'POST',
	    		url : 'deleteRequest.do',
	    		params : {
	    			"requestId":reqid
	    		},
	    		headers : {
	    			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
	    		}
	        }).success(function(data, status, headers, config) {
	    		if (data.status == "success") {
	    		  if(src==1)
	    		  {
	    			  window.location.href = "SearchRequest.jsp";
	    		  }else
	    		  {
	    			window.location.href = "RequestDashboard.jsp";
	    		  }	
	    		} else {
	    			
	    		}
	    		$scope.unload();
	    	}).error(function(data, status, headers, config) {
	    		$scope.unload();
	    	});        
	    } 
	};
	
	$scope.closeRequest= function(reqid)
	{
		
		var retVal = confirm("Are you sure you want to close this request?");
		if (retVal == true) {
			$scope.load();
	        $http({
	    		method : 'POST',
	    		url : 'closeRequest.do',
	    		params : {
	    			"requestId":reqid
	    		},
	    		headers : {
	    			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
	    		}
	        }).success(function(data, status, headers, config) {
	    		if (data.status == "success") {
	    			$scope.util.notify("Request Info", data.reason, "success");
	    			window.location.href = "RequestDashboard.jsp";
	    		} else {
	    			$scope.util.notify("Request Failure", data.reason, "failure");
	    		}
	    		$scope.unload();
	    	}).error(function(data, status, headers, config) {
	    		$scope.unload();
	    	});        
	    } 
	};
	
	$scope.acceptRequest = function(reqid)
	{
		$scope.unload();
		$http({
			method : 'POST',
			url : 'FPLAcceptRequest.do',
			params : {
				"requestId":reqid
			},
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
			}
		}).success(function(data, status, headers, config) {
			
			$scope.updateFPLDashboardJson(reqid,"Accepted","ST_05");

			if (data.status == "success") {
				
				
				
			} else {
				alert('Unable to process request');
			}
			$scope.unload();
		}).error(function(data, status, headers, config) {
			alert('Unable to process request');
			$scope.unload();
		}); 
	};

	$scope.load = function()
	{
		document.getElementById("spinner").style.display="block";
	};
	
	$scope.unload = function()
	{
		document.getElementById("spinner").style.display="none";
	};
	
	$scope.openSummary = function(requestId)
	{
		
		window.open('RequestSummary.jsp?REQ_ID='+requestId, '', 'width=800, height=300');
	};
	
	// Sorting
	
	   $scope.predicate = 'firstName';
	   $scope.reverse = true;
	   $scope.order = function(predicate) {
	    $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
	    $scope.predicate = predicate;
	  };
	// Sorting
	
	// Pagination Starts
	  $scope.itemsPerPage = 2;
	  $scope.currentPage = 0;
	  $scope.items = [];

	 
	  $scope.range = function() {
	    var rangeSize = 5;
	    var ret = [];
	    var start;

	    start = $scope.currentPage;
	    
	    if($scope.pageCount()<rangeSize)
	    {
	    	rangeSize=$scope.pageCount();	
	    }
	    if ( start > $scope.pageCount()-rangeSize ) {
	      start = $scope.pageCount()-rangeSize+1;
	    }

	    
	    for (var i=start; i<start+rangeSize; i++) {
	      ret.push(i);
	    }
	    return ret;
	  };

	  $scope.prevPage = function() {
		  $scope.subSecondCurrentPage = 0;
		  $scope.subCurrentPage = 0;
	    if ($scope.currentPage > 0) {
	      $scope.currentPage--;
	    }
	  };

	  $scope.prevPageDisabled = function() {
	    return $scope.currentPage === 0 ? "disabled" : "";
	  };

	  $scope.pageCount = function() {
		  var len=$scope.customerDashboardList.length;
		  
	    return Math.ceil(len/$scope.itemsPerPage)-1;
	  };

	  $scope.nextPage = function() {
		  $scope.subSecondCurrentPage = 0;
		  $scope.subCurrentPage = 0;
	    if ($scope.currentPage < $scope.pageCount()) {
	      $scope.currentPage++;
	    }
	  };

	  $scope.nextPageDisabled = function() {
	    return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
	  };

	  $scope.setPage = function(n) {
		  $scope.subSecondCurrentPage = 0;
		  $scope.subCurrentPage = 0;
	    $scope.currentPage = n;
	  };

	//Pagination Ends

	  
   // Sub Pagination Starts
	  $scope.subCurrentPage = 0;
	  $scope.subitemsPerPage = 2;
	  $scope.subitems = [];

	 
	  $scope.subRange = function(requestFPLList) {
	    var rangeSize = 5;
	    var ret = [];
	    var start;

	    
	    start = $scope.subCurrentPage;
	    if($scope.subPageCount(requestFPLList)<rangeSize)
	    {
	    	rangeSize=2;	
	    }
	    
	    if($scope.subPageCount(requestFPLList)>0)
	    {
	    	
	    	if ( start > $scope.subPageCount(requestFPLList)-rangeSize ) {
	    		start = $scope.subPageCount(requestFPLList)-rangeSize+1;
	    	}
	    }else
	    {
	    	start=0;
	    	rangeSize=1;
	    }
	    
	   for (var i=start; i<start+rangeSize; i++) {
		  
	      ret.push(i);
	    }
	    return ret;
	  };

	  $scope.prevSubPage = function() {
	    if ($scope.subCurrentPage > 0) {
	      $scope.subCurrentPage--;
	    }
	  };

	  $scope.prevPageDisabled = function() {
	    return $scope.subCurrentPage === 0 ? "disabled" : "";
	  };

	  $scope.subPageCount = function(requestFPLList) {
		  
	    return Math.ceil(requestFPLList.length/$scope.subitemsPerPage)-1;
	  };

	  $scope.nextSubPage = function(requestFPLList) {
	    if ($scope.subCurrentPage < $scope.subPageCount(requestFPLList)) {
	      $scope.subCurrentPage++;
	    }
	  };

	  $scope.nextPageDisabled = function() {
	    return $scope.subCurrentPage === $scope.subPageCount() ? "disabled" : "";
	  };

	  $scope.setSubPage = function(n) {
		  
	    $scope.subCurrentPage = n;
	  };

	//Pagination Ends


	  // Second Sub Pagination Starts
	  $scope.subSecondCurrentPage = 0;
	  $scope.subSeconditemsPerPage = 2;
	  $scope.subSeconitems = [];

	 
	  $scope.subSecondRange = function(requestFPLList) {
	    var rangeSize = 2;
	    var ret = [];
	    var start;

	    
	    start = $scope.subSecondCurrentPage;
	    
	    if($scope.subSecondPageCount(requestFPLList)>0)
	    {
	    	
	    	if ( start > $scope.subSecondPageCount(requestFPLList)-rangeSize ) {
	    		start = $scope.subSecondPageCount(requestFPLList)-rangeSize+1;
	    	}
	    }else
	    {
	    	start=0;
	    	rangeSize=1;
	    }
	    
	   for (var i=start; i<start+rangeSize; i++) {
		  
	      ret.push(i);
	    }
	    return ret;
	  };

	  $scope.prevSubSecondPage = function() {
	    if ($scope.subSecondCurrentPage > 0) {
	      $scope.subSecondCurrentPage--;
	    }
	  };

	  $scope.prevPageSecondDisabled = function() {
	    return $scope.subSecondCurrentPage === 0 ? "disabled" : "";
	  };

	  $scope.subSecondPageCount = function(requestFPLList) {
		  
	    return Math.ceil(requestFPLList.length/$scope.subSeconditemsPerPage)-1;
	  };

	  $scope.nextSubSecondPage = function(requestFPLList) {
	    if ($scope.subSecondCurrentPage < $scope.subSecondPageCount(requestFPLList)) {
	      $scope.subSecondCurrentPage++;
	    }
	  };

	  $scope.nextPageSecondDisabled = function() {
	    return $scope.subSecondCurrentPage === $scope.subSecondPageCount() ? "disabled" : "";
	  };

	  $scope.setSubSecondPage = function(n) {
		  
	    $scope.subSecondCurrentPage = n;
	  };

	//Pagination Ends

}




RequestController.prototype.fetchNewRequest = function(uRequest) {
	var self = this;
	
	this.http({
		method : 'POST',
		url : 'getAllDomain.do',
		params : {
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
			self.domainList=data;
		}
	}).error(function(data, status, headers, config) {
	});
}






/*RequestController.prototype.fetchRequest = function(uRequest) {
	$('#FPLList').hide();
	$("#adminRequest").hide();
	var reqId = this.util.gup("reqId");
	if(reqId != ""){
		this.fetchExistingRequest(uRequest, reqId);
	} else {
		this.fetchNewRequest(uRequest);
	}
};

RequestController.prototype.fetchExistingRequest = function(uRequest, reqId) {
	var self = this;
	$("#requestHeader").html("Edit Request");
	this.util.block("Fetching Request Details ..");
	var requesteditJson = {
			ReqId : reqId			
		};
	this.util.block("Retrieving details");	
	this.http({
		method : 'POST',
		url : 'support/contents/new_request.json',
		params : {"requesteditJson" : requesteditJson},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {		
		if(data){
			self.util.unblock();
			var reqObj = data.NewRequest;
			var reqDomains = data.Domains;
			self.populateRequestType(reqDomains, data.NewRequest.type);
			self.newRequestDesc = data.NewRequest.description;
			self.newRequestPostal = data.NewRequest.postalCode;
			self.newRequestArea = data.NewRequest.location;
			self.newRequestCountry = data.NewRequest.country;
			//self.newRequestUrgency = (data.NewRequest.urgency == 1) ? "High" : ((data.NewRequest.urgency == 2) ? "Medium" : "Low");
			self.newRequestUrgency = (data.NewRequest.urgency == 1) ? true : false; 
			self.newRequestFollowup = (data.NewRequest.urgency.followUp == 1) ? true : false;
			setTimeout(function(){self.postalChange(self);}, 3000);
		}
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};





RequestController.prototype.populateRequestType = function(data, selValue){
	var self = this;
	var sel = $("select[name=type]");
	sel.empty();
	
	sel.append('<option value="0">Select Type..</option>');
	$.each(data, function(index){
		sel.append('<option value="' + this.id + '" ' + ((index+1 == selValue) ? "selected" : "") + '>' + this.name + '</option>');
	});
	setTimeout(function(){
		self.newRequestType = selValue + "";
	}, 1000);
}

RequestController.prototype.requestChange = function(uRequest) {
	var self = this;
	this.util.block("Fetching Request description ..");
	this.http({
		method : 'POST',
		url : 'support/contents/request_desc.json',
		params : {
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data) {
			$.each(data, function(index){
				if(this.name == self.newRequestType){
					self.newRequestDesc = this.desc;
				}
			})
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
}

RequestController.prototype.postalChange = function(uRequest){
	var self = this;
	self.newRequestArea = "";
			self.http({
				method : 'POST',
				type: 'jsonp',
				url : 'http://maps.google.com/maps/api/geocode/json?address=india&components=postal_code:' + uRequest.newRequestPostal + '&sensor=false',
				params : {
				},
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
				}
			}).success(function(data, status, headers, config) {
				self.newRequestArea = data.results[0].address_components[1].short_name;
			}).error(function(data, status, headers, config) {
			});		
		self.util.unblock();	
};

RequestController.prototype.submitRequest = function(uRequest){
	var self = this;
	this.util.block("Searching Finanacial planner..");
	
	var requestJson = {
			type : uRequest.newRequestType,
			description : uRequest.newRequestDesc,
			postalCode : uRequest.newRequestPostal,
			location : uRequest.newRequestArea,
			country : uRequest.newRequestCountry,
			urgency : ((uRequest.newRequestUrgency) ? "1" : "0"),
			followUp : uRequest.newRequestFollowup
		};
	this.http({
		method : 'POST',
		url : 'fetchFPLRequest.do',
		//url : 'support/contents/request_FPList.json',
		params : {
			"requestJson" : requestJson
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		//window.location.href = "fetchFPLRequest.do";
		if (data) 
		{
			$("#adminRequest").hide();
			if(data.status == "failure")
			{
				self.util.notify("Request", data.reason, "failure");
			}
			else
			{
				$('#FPLList').show();
				var sel = $("tbody[id=FPLList]");
				sel.empty();
				if(self.showOnlineFP==false)
				{
					$.each(data, function(index){
						sel.append('<tr> <td> <input type="checkbox" class="marginLten" name="fplId" value="' + this.id + '"</td> <td>' + this.firstName + this.lastName + '</td> <td>' + this.location + '</td> <td>' + this.status + '</td> </tr>');					
					});
				}
				else if(self.showOnlineFP==true)
				{
					$.each(data, function(index){
						if(this.status == "Online")
							{
								sel.append('<tr> <td> <input type="checkbox" class="marginLten" name="fplId" value="' + this.id + '"</td> <td>' + this.firstName + this.lastName + '</td> <td>' + this.location + '</td> <td>' + this.status + '</td> </tr>');
							}	
					});
				}
			}		
	    }
		else
		{
			$("#adminRequest").show();
			self.util.notify("Request", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	});
};



RequestController.prototype.viewRequest=function (reqid)
{
	var self = this;	
	this.util.block("Retrieving details");
	this.http({
		method : 'POST',
		url :  'getRequestDetail.do',
		params : {
			"requestId":reqid
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		self.viewId = data.id;
		self.domainName = data.domainName;
		self.location = data.location;
		self.description = data.description;
		self.createdDate = data.createdDate;
		self.updatedDate = data.updatedDate;
		self.currentStatus = data.currentStatus;		
		$("#ViewRequestModal").modal("show");
		var sel = $("#viewModalBody");
		$("#viewbuttondiv").remove();
		sel.append('<div class="clear registerBtnGroup pull-down" id="viewbuttondiv"><button data-dismiss="modal" class="btn btn-primary btn-medium" type="button">OK</button></div>');
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};



RequestController.prototype.deleteRequest= function(reqid)
{
	var self = this;
	var retVal = confirm("Are you sure you want to delete this request?");
	if (retVal == true) {
		this.util.block("Deleting Request");
        this.http({
    		method : 'POST',
    		url : 'deleteRequest.do',
    		params : {
    			"requestId":reqid
    		},
    		headers : {
    			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
    		}
        }).success(function(data, status, headers, config) {
    		if (data.status == "success") {
    			self.util.notify("Request Info", data.reason, "success");
    			window.location.href = "initRequest.do";
    		} else {
    			self.util.notify("Request Failure", data.reason, "failure");
    		}
    		self.util.unblock();
    	}).error(function(data, status, headers, config) {
    		self.util.unblock();
    	});        
    } 
};

RequestController.prototype.activityHistory= function(reqid)
{
	var self = this;
	this.util.block("Retrieving Activity History");
	this.http({
		method : 'POST',
		url : 'getRequestHistory.do',
		params : {
			"requestId":reqid
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
			 if (data) {
				var sel = $("tbody[id=activityHistoryTable]");
				sel.empty();
				$.each(data, function(index){
					sel.append('<tr> <td>' + this.fpName + '</td> <td>' + this.date + '</td> <td>' + this.status + '</td> </tr>');					
				});
				$("#activitybuttondiv").remove();
				sel.append('<div class="clear registerBtnGroup pull-down" id="activitybuttondiv"><button data-dismiss="modal" class="btn btn-primary btn-medium" type="button">OK</button></div>');
			}
		 $("#ActivityHistoryModal").modal("show");
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

RequestController.prototype.respondedList= function(reqid)
{
	var self = this;
	this.util.block("Retrieving Request Details");
	this.http({
		method : 'POST',
		url : 'getRespondedFPL.do',
		params : {
			"requestId":reqid
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		var sel = $("#respondReqDiv" + reqid);
		sel.empty();
		var respondTable = "<table class='table table-hover'><thead><tr><th>FP Name</th><th>Created Date</th><th>Responded</th><th>Status</th><th>Actions</th><th><a href='#' onclick='closeRespondSection(" + reqid + ")'><img src='support/images/not_verify.GIF'/></a></th></tr></thead><tbody>";
		if (data!= '') {		
			$.each(data, function(index) {
				respondTable += "<tr><td>" + this.fpName + "</td><td>" + this.date + "</td><td>" + this.daysDiff + " days ago </td><td>"+ this.status + "</td>";
				if(this.currentStatus == "ST_03" || this.statusDis == "ST_06") {
					respondTable += "</tr>";
				} else if(this.statusDis == "ST_07"  || this.currentStatus == "ST_02") {
					respondTable += "<td><a href='#'><img src='support/images/chat.jpg'/></a></td><td><a href='#'><img src='support/images/mail.jpg'/></a></td><td><a href='#' onclick='submitPolicyCreation(document.PolicyCreationForward,\"initNewPolicy.do\"," + reqid + ")'><img src='support/images/erecord.jpg'/></a></td></tr>";
				} else {
					respondTable += "<td><a href='#' onclick='connectToFPL(" + reqid+ "," +this.fplId + ")'><img src='support/images/connect.jpg'/></a></td></tr>";
				}
			});
		}
		else 
		{
			respondTable += "<tr><td colspan='7'>No Records Found.</td></tr>";
		}
		respondTable += "</tbody></table>";
		sel.append(self.compile(respondTable)(self.scope));
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

function closeRespondSection(reqid){
	var sel = $("#respondReqDiv" + reqid);
	sel.empty();
}


RequestController.prototype.respond= function(uRequest)
{
	var self = this;
	this.util.block("Retrieving Details");
	this.http({
		method : 'POST',
		url : 'FPLRespondedRequest.do',
		params : {
			"requestId":self.requestId,
			"description":uRequest.respondDescription
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			self.util.notify("Response Info",data.reason, "success");
			self.reset();
			$("#FPLRespondModal").modal("hide");
		} else {
			self.util.notify("Response Failure",data.reason, "failure");
		}	
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
}; 

RequestController.prototype.respondModel = function(reqid) {
	var self = this;
	self.requestId = reqid;
	$("#FPLRespondModal").modal("show");
}; */


/* RequestController.prototype.searchRequest = function(uRequest)
{
	var self = this;
	this.util.block("Retrieving Request Details");	
	this.http({
		method : 'POST',
		url : 'support/contents/request_list.json',
		params : {
			"requestId":uRequest.searchReqId,
			"requestdate":uRequest.searchDate,
			"requesttype":uRequest.SearchRegtype,
			"requeststatus":uRequest.SearchStatus
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		var sel = $("tbody[id=searchRequestResult]");
		sel.empty();
		var requestTable = "";
		if (data!=[]) {
				$.each(data, function(index){
					requestTable += "<tr> <td>"+ this.id +"</td> <td>" + this.date + "</td> <td>" + this.type + "</td> <td>" + this.status + "</td>"	
					+ "<td> <a href='#' ng-click='uRequest.viewRequest("+ this.id +")'><img src='support/images/view.png' title='View' class='cursorP'/></a>";
					if(this.status == 'Request Saved as draft')
					//if(this.statusCode =='ST_04')
					{
						requestTable += "<a href='#' ng-click='uRequest.editRequest("+ this.id +")'><img src='support/images/edit.png' class='marginLten cursorP' title='Edit' /></a>"
									+ "<a href='#' ng-click='uRequest.deleteRequest("+ this.id +")'><img src='support/images/delete.jpg' class='marginLten cursorP' title='Delete'/></a>";
					}
					requestTable += "<a href='#' ng-click='uRequest.respondedList("+ this.id +")'><img src='support/images/respond.png' class='marginLten cursorP' title='Responded' /></a>"
	            	+ "<a href='#' ng-click='uRequest.activityHistory("+ this.id +")'><img src='support/images/history.png' class='marginLten cursorP' title='History' /></a>"
	            	+ "</td></tr><tr><td colspan='5'><div id='respondReqDiv"+ this.id +"'></div></td></tr>";					
				});
		}	
		else 
		{
			requestTable += "<tr> <td colspan='5'>No Records Found.</td></tr>";
		}
		requestTable += "</tbody></table>";
		sel.append(self.compile(requestTable)(self.scope));
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

RequestController.prototype.acceptRequest = function(reqid)
{
	var self = this;
	this.util.block("Sending Rsponse ");
	this.http({
		method : 'POST',
		url : 'FPLAcceptRequest.do',
		params : {
			"requestId":reqid
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			self.util.notify("Request Info", data.reason, "success");
			window.location.href = "initRequest.do";
		} else {
			self.util.notify("Request Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

RequestController.prototype.rejectRequest = function(reqid)
{
	var self = this;
	this.util.block("Sending Rsponse ");
	this.http({
		method : 'POST',
		url : 'FPLRejectRequest.do',
		params : {
			"requestId":reqid
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			self.util.notify("Request Info", data.reason, "success");
			window.location.href = "initRequest.do";
		} else {
			self.util.notify("Request Failure", data.reason, "failure");
		}
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

RequestController.prototype.searchFPLRequest = function(uRequest)
{
	var self = this;
	this.util.block("Retrieving Request Details");
	this.http({
		method : 'POST',
		url : 'support/contents/request_list.json',
		params : {
			"requestId":uRequest.searchReqId,
			"requestdate":uRequest.searchDate,
			"requesttype":uRequest.SearchRegtype,
			"requeststatus":uRequest.SearchStatus
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		var sel = $("tbody[id=searchRequestResult]");
		sel.empty();
		var requestTable = "";
		if (data!=[]) {
				$.each(data, function(index){
					requestTable += "<tr> <td>"+ this.id +"</td> <td>" + this.date + "</td> <td>" + this.type + "</td> <td>" + this.status + "</td>"	
					+ "<td> <a href='#' ng-click='uRequest.viewRequest("+ this.id +")'><img src='support/images/view.png' title='View' class='cursorP'/></a>"
					+ "<a href='#' ng-click='uRequest.respondModel("+ this.id +")'><img src='support/images/respond.png' class='marginLten cursorP' title='Respond' /></a></td></tr>";
				});
		}	
		else
		{
			requestTable += "<tr> <td colspan='5'>No Records Found</td></tr>";
		}
		requestTable += "</tbody></table>";
		sel.append(self.compile(requestTable)(self.scope));
		self.util.unblock();
	}).error(function(data, status, headers, config) {
		self.util.unblock();
	}); 
};

function connectToFPL(reqid,fplId)
{
	gutilVar.block("Retrieving Details");
	ghttpVar({
		method : 'POST',
		url : 'ConnectToFPL.do',
		//url:'support/contents/register.json',
		params : {
			"requestId":reqid,
			"fplId":fplId
		},
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		}
	}).success(function(data, status, headers, config) {
		if (data.status == "success") {
			gutilVar.notify("Response Info",data.reason, "success");
		} else {
			gutilVar.notify("Response Failure",data.reason, "failure");
		}
		gutilVar.unblock();
		closeRespondSection(reqid);
	}).error(function(data, status, headers, config) {
		gutilVar.unblock();
	});
};

RequestController.prototype.reset = function() {
    this.scope.uRequest.respondDescription = angular.copy(this.scope.master.respondDescription);
};*/