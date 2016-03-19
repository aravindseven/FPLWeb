<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="col-lg-10 col-md-10 col-sm-10 newRequest">
		<form ng-init="uRequest.fetchRequest()" ng-submit="uRequest.submitRequest(uRequest)" autocomplete="off" role="form" id="form" class="col-sm-12">
		<h3 id="requestHeader" class="blue marginNone">New Request</h3>
	  	<hr />
	  	<div class="col-lg-6 col-md-6 col-sm-6 paddingNone">
	  	<div class="form-horizontal">
	    	<div class="form-group">
	        	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Request Type</label>
	        	<div class="col-lg-8 col-md-8 col-sm-8">
		        	<select class="form-control" name="type" ng-model="uRequest.newRequestType" ng-change="uRequest.requestChange(uRequest)">
		            	<c:forEach var="domain" items="${DomainList}">
		            		<option value="${domain.id}">${domain.name}</option>
		            	</c:forEach>
		          	</select>
	        	</div>
	      	</div>
	      	<div class="form-group">
	        	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Request Description</label>
	        	<div class="col-lg-8 col-md-8 col-sm-8">
	          		<textarea type="text" rows="5" cols="20" class="form-control" id="description" name="description" value="${NewRequestPV.description}"  ng-model="uRequest.newRequestDesc"></textarea>
	        	</div>
	      	</div>
	      	</div>
	  	</div>
	  	<div class="col-lg-6 col-md-6 col-sm-6 paddingNone">
	  	<div class="form-horizontal">
	    	<div class="form-group">
	        	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Postal Code</label>
	        	<div class="col-lg-8 col-md-8 col-sm-8">
	          		<input type="text" class="form-control" id="postalCode" name="postalCode" required="required" value="${NewRequestPV.postalCode}" ng-model="uRequest.newRequestPostal" ng-blur="uRequest.postalChange(uRequest)"/>
	        	</div>
	      	</div>
	      	<div class="form-group">
	        	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Area</label>
	        	<div class="col-lg-8 col-md-8 col-sm-8">
	          		<input type="text" class="form-control" id="area" name="area" readonly="readonly" value="${NewRequestPV.country}" ng-model="uRequest.newRequestArea">
	        	</div>
	      	</div>
	      	<div class="form-group">
	        	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Country</label>
	        	<div class="col-lg-8 col-md-8 col-sm-8">
	          		<input type="text" class="form-control" id="country" name="country" readonly="readonly" value="${NewRequestPV.country}" ng-model="uRequest.newRequestCountry">
	        	</div>
	      	</div>
	      	</div>
	  	</div>
	   	<div class="col-lg-9 col-md-9 col-sm-9 paddingNone">
	   	<div class="form-horizontal">
	      	<div class="form-group">
	        	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Priority</label>
	        	<div class="col-lg-8 col-md-8 col-sm-8">
	        		<input type="checkbox" name="urgency" ng-model="uRequest.newRequestUrgency"/>
	          		<!-- High <input type="radio" name="urgency" value="High"  ng-model="uRequest.newRequestUrgency"> 
	          		Medium <input type="radio" name="urgency" value="Medium" ng-model="uRequest.newRequestUrgency" value=true> 
	          		Low <input type="radio" name="urgency" value="Low" ng-model="uRequest.newRequestUrgency">  -->
	        	</div>
	      	</div>
	      	<!--<div class="form-group">
	        	<label class="control-label pull-left col-lg-3 col-md-3 col-sm-3 textLeft">Follow Up Notification</label>
	        	<div class="col-lg-8 col-md-8 col-sm-8">
					<input type="checkbox" name="followUp" ng-model="uRequest.newRequestFollowup"/> 
	        	</div>
	      	</div>-->
	      	</div>
	  	</div>
	  	<div class="col-lg-9 col-md-9 col-sm-9 paddingNone">
	  	<div class="form-horizontal">
	      	<div class="form-group">
	        	<label class="control-label pull-left col-lg-2 col-md-2 col-sm-2 textLeft"></label>
	        	<div class="col-lg-8 col-md-8 col-sm-8">
	        		<button type="submit" id="normalRequest" class="btn btn-success">Search FinancialPlanner</button>
	        		<button type="button" id="adminRequest" class="btn btn-primary" disabled>Send Request to Admin</button>
	        	</div>
	      	</div>
	  	</div>
	  	</div>
	  	</form>
	  	<div id="FPLList">
	  	<jsp:include page="/include/request/customer/FPLSelection.jsp" flush="true" />
	  	</div>
	</div>
	
	